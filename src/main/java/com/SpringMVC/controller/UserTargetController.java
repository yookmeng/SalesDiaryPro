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

import com.SpringMVC.dao.TeamTargetDAO;
import com.SpringMVC.dao.UserProfileDAO;
import com.SpringMVC.dao.UserTargetDAO;
import com.SpringMVC.model.TeamTarget;
import com.SpringMVC.model.UserProfile;
import com.SpringMVC.model.UserTarget;
import com.SpringMVC.uriconstant.UserTargetRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class UserTargetController {

    @Autowired
    private UserProfileDAO userProfileDAO;

    @Autowired
    private TeamTargetDAO teamTargetDAO;

    @Autowired
    private UserTargetDAO userTargetDAO;

    @RequestMapping(value = UserTargetRestURIConstant.Get, method = RequestMethod.GET)
	public String getUserTarget(@PathVariable int targetid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(userTargetDAO.get(targetid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = UserTargetRestURIConstant.GetByPeriodMember, method = RequestMethod.GET)
	public String getAllUserTargetByPeriodMember(@PathVariable String period, int userid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(userTargetDAO.getByPeriod(period, userid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = UserTargetRestURIConstant.GetByPeriodTeam, method = RequestMethod.GET)
	public String getAllUserTargetByPeriodTeam(@PathVariable Date period, int teamid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(userTargetDAO.list(period, teamid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = UserTargetRestURIConstant.GetByMember, method = RequestMethod.GET)
	public String getAllUserTargetByMember(@PathVariable int userid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(userTargetDAO.listByUser(userid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = UserTargetRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<UserTarget> createUserTarget(@RequestBody UserTarget userTarget) throws IOException {
    	UserProfile userProfile = userProfileDAO.get(userTarget.getusername());
    	userTarget.setuserid(userProfile.getuserid());
    	userTargetDAO.save(userTarget);
        return new ResponseEntity<UserTarget>(userTarget, HttpStatus.CREATED);
    }

    @RequestMapping(value = UserTargetRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<UserTarget> updateUserTarget(@PathVariable("targetid") int targetid, @RequestBody UserTarget userTarget) {
    	UserTarget currentUserTarget = userTargetDAO.get(targetid);         
        if (currentUserTarget==null) {
            return new ResponseEntity<UserTarget>(HttpStatus.NOT_FOUND);
        }

        currentUserTarget.setprospect(userTarget.getprospect());
        currentUserTarget.settestdrive(userTarget.gettestdrive());
        currentUserTarget.setclosed(userTarget.getclosed());

        userTargetDAO.update(currentUserTarget);
        return new ResponseEntity<UserTarget>(userTarget, HttpStatus.OK);
    }

    @RequestMapping(value = UserTargetRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<UserTarget> deleteUserTarget(@PathVariable("targetid") int targetid) {
    	UserTarget userTarget = userTargetDAO.get(targetid);
        if (userTarget == null) {
            return new ResponseEntity<UserTarget>(HttpStatus.NOT_FOUND);
        }
 
        userTargetDAO.delete(targetid);
        return new ResponseEntity<UserTarget>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/listUserTarget", method = RequestMethod.GET)
    public ModelAndView listUserTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        TeamTarget teamTarget = teamTargetDAO.get(targetid);
 	    List<UserTarget> listUserTarget = userTargetDAO.list(teamTarget.getperiod(), teamTarget.getteamid());
        ModelAndView mav = new ModelAndView("userTargetList");
        mav.addObject("teamTarget", teamTarget);
 	    mav.addObject("listTarget", listUserTarget);
 	    return mav;
 	}

    @RequestMapping(value = "/addUserTarget", method = RequestMethod.GET)
    public ModelAndView addUserTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        TeamTarget teamTarget = teamTargetDAO.get(targetid);
        UserTarget newUserTarget = new UserTarget();
        newUserTarget.setperiod(teamTarget.getperiod());
        newUserTarget.setteamtargetid(teamTarget.gettargetid());        
        ModelAndView mav = new ModelAndView("userTargetForm");
        List<String> members = userProfileDAO.userList(teamTarget.getteamid());	
        mav.addObject("userlist", members);
        mav.addObject("teamTarget", teamTarget);
        mav.addObject("userTarget", newUserTarget);
        return mav;
    }

    @RequestMapping(value = "/editUserTarget", method = RequestMethod.GET)
    public ModelAndView editUserTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        UserTarget editUserTarget = userTargetDAO.get(targetid);
        TeamTarget teamTarget = teamTargetDAO.get(editUserTarget.getteamtargetid());
        ModelAndView mav = new ModelAndView("userTargetForm");
        List<String> members = userProfileDAO.userList(teamTarget.getteamid());	
        mav.addObject("userlist", members);
        mav.addObject("teamTarget", teamTarget);
        mav.addObject("userTarget", editUserTarget);
        return mav;
    } 
    
}
