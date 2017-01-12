package com.SpringMVC.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import com.SpringMVC.dao.BranchDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.TeamRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class TeamController {

	@Autowired
    private BranchDAO branchDAO;

	@Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private TeamDAO teamDAO;

    @RequestMapping(value = TeamRestURIConstant.Get, method = RequestMethod.GET)
	public String getTeam(@PathVariable int teamid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(teamDAO.get(teamid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = TeamRestURIConstant.GetByBranch, method = RequestMethod.GET)
	public String getAllTeam(@PathVariable int branchid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(teamDAO.list(branchid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = TeamRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Team> createTeam(@RequestBody Team team) throws IOException {
    	UserLogin userLogin = userLoginDAO.get(team.getleadername());
    	Branch branch = branchDAO.get(team.getbranchid());
    	team.setleaderid(userLogin.getuserid());
    	teamDAO.save(team);
    	
    	userLogin.setcompanyid(branch.getcompanyid());
    	userLogin.setbranchid(team.getbranchid());
    	userLogin.setteamid(team.getteamid());
    	userLoginDAO.update(userLogin);
        return new ResponseEntity<Team>(team, HttpStatus.CREATED);
    }

    @RequestMapping(value = TeamRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Team> updateTeam(@PathVariable("teamid") int teamid, @RequestBody Team team) {
    	Team currentTeam = teamDAO.get(teamid);
         
        if (currentTeam==null) {
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        }
        
    	UserLogin userLoginClear = userLoginDAO.get(currentTeam.getleadername());
    	userLoginClear.setcompanyid(0);
    	userLoginClear.setbranchid(0);
    	userLoginClear.setteamid(0);
    	userLoginDAO.update(userLoginClear);

    	UserLogin userLogin = userLoginDAO.get(team.getleadername());
    	team.setleaderid(userLogin.getuserid());

    	Branch branch = branchDAO.get(team.getbranchid());
    	userLogin.setcompanyid(branch.getcompanyid());
    	userLogin.setbranchid(team.getbranchid());
    	userLogin.setteamid(team.getteamid());
    	userLoginDAO.update(userLogin);

    	currentTeam.setteamname(team.getteamname());
        currentTeam.setleaderid(team.getleaderid());
        
        teamDAO.update(currentTeam);
        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }

    @RequestMapping(value = TeamRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Team> deleteTeam(@PathVariable("teamid") int teamid) {
    	Team team = teamDAO.get(teamid);
        if (team == null) {
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        }
    	UserLogin userLogin = userLoginDAO.get(team.getleadername());
    	userLogin.setcompanyid(0);
    	userLogin.setbranchid(0);
    	userLogin.setteamid(0);
    	userLoginDAO.update(userLogin);
        teamDAO.delete(teamid);
        return new ResponseEntity<Team>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/listTeam", method = RequestMethod.GET)
    public ModelAndView listTeam(HttpServletRequest request) {
        int branchid = Integer.parseInt(request.getParameter("branchid"));
        Branch branch = branchDAO.get(branchid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
 	    List<Team> listTeam = teamDAO.list(branchid);
        ModelAndView mav = new ModelAndView("teamList");        
        mav.addObject("role", userLogin.getrole());
        mav.addObject("branch", branch);
        mav.addObject("listTeam", listTeam);
 	    return mav;
 	}

    @RequestMapping(value = "/addTeam", method = RequestMethod.GET)
    public ModelAndView addTeam(HttpServletRequest request) {
        int branchid = Integer.parseInt(request.getParameter("branchid"));
        Branch branch = branchDAO.get(branchid);
        Team newTeam = new Team();
        newTeam.setbranchid(branchid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("teamForm");
        List<String> leaderlist = userLoginDAO.leaderlist();	
        mav.addObject("branch", branch);        
        mav.addObject("role", userLogin.getrole());
        mav.addObject("leaderlist", leaderlist);        
        mav.addObject("team", newTeam);
        return mav;
    }

    @RequestMapping(value = "/editTeam", method = RequestMethod.GET)
    public ModelAndView editTeam(HttpServletRequest request) {
        int teamid = Integer.parseInt(request.getParameter("teamid"));        
        Team editTeam = teamDAO.get(teamid);
        Branch branch = branchDAO.get(editTeam.getbranchid());
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("teamForm");
        if (userLogin.getrole().equals("SA")){
            List<String> leaderlist = userLoginDAO.leaderlist();	
            mav.addObject("leaderlist", leaderlist); 
        }
        else {
        	List<String> leaderlist = new ArrayList<String>();
        	leaderlist.add(editTeam.getleadername());
            mav.addObject("leaderlist", leaderlist); 
        }
        mav.addObject("role", userLogin.getrole());        
        mav.addObject("branch", branch);
        mav.addObject("team", editTeam);
        return mav;
    }
}
