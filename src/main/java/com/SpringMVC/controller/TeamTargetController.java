package com.SpringMVC.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.SpringMVC.model.UserProfile;
import com.SpringMVC.dao.BranchTargetDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.TeamTargetDAO;
import com.SpringMVC.dao.UserProfileDAO;
import com.SpringMVC.exceptions.ServiceException;
import com.SpringMVC.model.BranchTarget;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.TeamTarget;
import com.SpringMVC.uriconstant.TeamTargetRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class TeamTargetController {

    @Autowired
    private UserProfileDAO userProfileDAO;

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private BranchTargetDAO branchTargetDAO;

    @Autowired
    private TeamTargetDAO teamTargetDAO;

    @RequestMapping(value = TeamTargetRestURIConstant.Get, method = RequestMethod.GET)
	public String getTeamTarget(@PathVariable int targetid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(teamTargetDAO.get(targetid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = TeamTargetRestURIConstant.GetByPeriodTeam, method = RequestMethod.GET)
	public String getAllTeamTargetByPeriodTeam(@PathVariable String period, int teamid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(teamTargetDAO.getByPeriod(period, teamid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = TeamTargetRestURIConstant.GetByPeriodBranch, method = RequestMethod.GET)
	public String getAllTeamTargetByPeriodBranch(@PathVariable Date period, int branchid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(teamTargetDAO.list(period, branchid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = TeamTargetRestURIConstant.GetByTeam, method = RequestMethod.GET)
	public String getAllTeamTargetByTeam(@PathVariable int teamid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(teamTargetDAO.getByTeam(teamid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = TeamTargetRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<TeamTarget> createTeamTarget(@RequestBody TeamTarget teamTarget) throws IOException {
    	Team team = teamDAO.getByName(teamTarget.getteamname());
    	teamTarget.setteamid(team.getteamid());
    	teamTargetDAO.save(teamTarget);
        return new ResponseEntity<TeamTarget>(teamTarget, HttpStatus.CREATED);
    }

    @RequestMapping(value = TeamTargetRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<TeamTarget> updateTeamTarget(@PathVariable("targetid") int targetid, @RequestBody TeamTarget teamTarget) {
    	TeamTarget currentTeamTarget = teamTargetDAO.get(targetid);         
        if (currentTeamTarget==null) {
            return new ResponseEntity<TeamTarget>(HttpStatus.NOT_FOUND);
        }

        currentTeamTarget.setprospect(teamTarget.getprospect());
        currentTeamTarget.settestdrive(teamTarget.gettestdrive());
        currentTeamTarget.setclosed(teamTarget.getclosed());

        teamTargetDAO.update(currentTeamTarget);
        return new ResponseEntity<TeamTarget>(teamTarget, HttpStatus.OK);
    }

    @RequestMapping(value = TeamTargetRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<TeamTarget> deleteTeamTarget(@PathVariable("targetid") int targetid) {
    	TeamTarget teamTarget = teamTargetDAO.get(targetid);
        if (teamTarget == null) {
            return new ResponseEntity<TeamTarget>(HttpStatus.NOT_FOUND);
        }
 
        teamTargetDAO.delete(targetid);
        return new ResponseEntity<TeamTarget>(HttpStatus.OK);
    }

    @RequestMapping(value="/listTeamTarget", method = RequestMethod.GET)
    public ModelAndView listTeamTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        BranchTarget branchTarget = branchTargetDAO.get(targetid);
 	    List<TeamTarget> listTeamTarget = teamTargetDAO.list(branchTarget.getperiod(), branchTarget.getbranchid());
        ModelAndView mav = new ModelAndView("teamTargetList");
        mav.addObject("branchTarget", branchTarget);
 	    mav.addObject("listTarget", listTeamTarget);
 	    return mav;
 	}
 	   
    @RequestMapping(value="/listTeamTargetUser", method = RequestMethod.GET)
    public ModelAndView listTeamTargetUser(HttpServletRequest request) {
        UserProfile userProfile = userProfileDAO.get(request.getUserPrincipal().getName());
        Team team = teamDAO.get(userProfile.getteamid());
        if (userProfile.getuserid() == team.getleaderid()){
	 	    List<TeamTarget> listTeamTarget = teamTargetDAO.listByTeam(team.getteamid());
	 	    ModelAndView mav = new ModelAndView("teamTargetUserList");
	 	    mav.addObject("listTarget", listTeamTarget);
	 	    return mav;
        }    	
	    else {
        	throw new ServiceException(HttpStatus.NOT_FOUND, "You have no access right!");
	    }
    }
    
    @RequestMapping(value = "/addTeamTarget", method = RequestMethod.GET)
    public ModelAndView addTeamTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        BranchTarget branchTarget = branchTargetDAO.get(targetid);
        TeamTarget newTeamTarget = new TeamTarget();
        newTeamTarget.setperiod(branchTarget.getperiod());
        newTeamTarget.setbranchtargetid(branchTarget.gettargetid());        
        ModelAndView mav = new ModelAndView("teamTargetForm");
        List<String> teams = teamDAO.teamList(branchTarget.getbranchid());	
        mav.addObject("teamlist", teams);
        mav.addObject("branchTarget", branchTarget);
        mav.addObject("teamTarget", newTeamTarget);
        return mav;
    }

    @RequestMapping(value = "/editTeamTarget", method = RequestMethod.GET)
    public ModelAndView editTeamTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        TeamTarget editTeamTarget = teamTargetDAO.get(targetid);
        BranchTarget branchTarget = branchTargetDAO.get(editTeamTarget.getbranchtargetid());
        ModelAndView mav = new ModelAndView("teamTargetForm");
        List<String> teams = teamDAO.teamList(branchTarget.getbranchid());	
        mav.addObject("teamlist", teams);
        mav.addObject("branchTarget", branchTarget);
        mav.addObject("teamTarget", editTeamTarget);
        return mav;
    }   
}
