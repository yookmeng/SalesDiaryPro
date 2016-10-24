package com.SpringMVC.controller;

import java.io.IOException;
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
import com.SpringMVC.dao.CompanyDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.dao.UserProfileDAO;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.Company;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.model.UserProfile;
import com.SpringMVC.uriconstant.UserProfileRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class UserProfileController {

	@Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private UserProfileDAO userProfileDAO;
    
    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private TeamDAO teamDAO;

    @RequestMapping(value = UserProfileRestURIConstant.Get, method = RequestMethod.GET)
	public String getUserProfile(@PathVariable String username) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(userProfileDAO.get(username));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = UserProfileRestURIConstant.GetAll, method = RequestMethod.GET)
	public String getAllUserProfile(@PathVariable int teamid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(userProfileDAO.list(teamid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = UserProfileRestURIConstant.Find, method = RequestMethod.GET)
	public String getUserProfileByID(@PathVariable int userid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(userProfileDAO.findUser(userid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = UserProfileRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<UserProfile> createUserProfile(@RequestBody UserProfile userProfile) throws IOException {
    	userProfileDAO.save(userProfile);
        return new ResponseEntity<UserProfile>(userProfile, HttpStatus.CREATED);
    }

    @RequestMapping(value = UserProfileRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<UserProfile> updateUserProfile(@PathVariable("userid") int userid, @RequestBody UserProfile userProfile) {
    	UserProfile currentUserProfile = userProfileDAO.findUser(userid);         
        if (currentUserProfile==null) {
            return new ResponseEntity<UserProfile>(HttpStatus.NOT_FOUND);
        }

        currentUserProfile.setpassword(userProfile.getpassword());
        currentUserProfile.setmobile(userProfile.getmobile());
        currentUserProfile.setemail(userProfile.getemail());

        userProfileDAO.update(currentUserProfile);
        return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);
    }

    @RequestMapping(value = UserProfileRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<UserProfile> deleteUserProfile(@PathVariable("userid") int userid) {
    	UserProfile userProfile = userProfileDAO.findUser(userid);
        if (userProfile == null) {
            return new ResponseEntity<UserProfile>(HttpStatus.NOT_FOUND);
        }
 
        userProfileDAO.delete(userid);
        return new ResponseEntity<UserProfile>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/listMember", method = RequestMethod.GET)
    public ModelAndView listMember(HttpServletRequest request) {
        int teamid = Integer.parseInt(request.getParameter("teamid"));
        Team team = teamDAO.get(teamid);
        Branch branch = branchDAO.get(team.getbranchid());
        Company company = companyDAO.get(branch.getcompanyid());
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
 	    List<UserProfile> userProfile = userProfileDAO.list(teamid);
        ModelAndView mav = new ModelAndView("userProfileList");
        mav.addObject("role", userLogin.getrole());
        mav.addObject("company", company);
        mav.addObject("branch", branch);
        mav.addObject("team", team);
        mav.addObject("userProfile", userProfile);
 	    return mav;
 	}

    @RequestMapping(value = "/addMember", method = RequestMethod.GET)
    public ModelAndView addMember(HttpServletRequest request) {
        int teamid = Integer.parseInt(request.getParameter("teamid"));
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Team team = teamDAO.get(teamid);
        Branch branch = branchDAO.get(team.getbranchid());
        Company company = companyDAO.get(branch.getcompanyid());
        UserProfile newUserProfile = new UserProfile();
        newUserProfile.setteamid(teamid);
        newUserProfile.setrole("USER");        
        ModelAndView mav = new ModelAndView("userProfileForm");
        mav.addObject("company", company);
        mav.addObject("branch", branch);
        mav.addObject("team", team);
        mav.addObject("role", userLogin.getrole());
        mav.addObject("userProfile", newUserProfile);
        return mav;
    }

    @RequestMapping(value = "/editMember", method = RequestMethod.GET)
    public ModelAndView editMember(HttpServletRequest request) {
        int userid = Integer.parseInt(request.getParameter("userid"));
        UserProfile userProfile = userProfileDAO.findUser(userid);
        Team team = teamDAO.get(userProfile.getteamid());
        Branch branch = branchDAO.get(team.getbranchid());
        Company company = companyDAO.get(branch.getcompanyid());
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("userProfileForm");
        mav.addObject("company", company);
        mav.addObject("branch", branch);
        mav.addObject("team", team);
        mav.addObject("role", userLogin.getrole());
        mav.addObject("userProfile", userProfile);
        return mav;
    }        
}
