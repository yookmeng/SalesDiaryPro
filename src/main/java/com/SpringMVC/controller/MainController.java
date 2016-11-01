package com.SpringMVC.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.SpringMVC.model.Branch;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.dao.BranchDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.UserMonthlySummary;
import com.SpringMVC.dao.UserMonthlySummaryDAO;
 
@Controller
public class MainController {
    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private UserMonthlySummaryDAO userMonthlySummaryDAO;

    private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }
    
    @RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String loginPage(Model model) {
       return "login";
	}
   
	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
       model.addAttribute("title", "Logout");
       return "logoutSuccessful";
	}
 
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, Principal principal) throws ParseException {
	   model.addAttribute("username", principal.getName());
	   UserLogin userLogin = userLoginDAO.findUserLogin(principal.getName());
	   model.addAttribute("role", userLogin.getrole());
	   Roles role = Roles.valueOf(userLogin.getrole()); 
       switch (role){
       case USER:
    	   Calendar c = Calendar.getInstance(); 
    	   c.set(Calendar.DAY_OF_MONTH, 1);
    	   Date period = new java.sql.Date(c.getTimeInMillis());
    	   UserMonthlySummary userMonthlySummary = userMonthlySummaryDAO.get(period, userLogin.getuserid());
    	   model.addAttribute("userMonthlySummary", userMonthlySummary);    	   
    	   return "userDashBoard";    	   
       case TL:
    	   Team team = teamDAO.getByUser(userLogin.getuserid());
    	   model.addAttribute("team", team);    	   
           return "tlDashBoard";    	   
       case MA:
    	   Branch branch = branchDAO.getByMA(userLogin.getuserid());
    	   model.addAttribute("branch", branch);    	   
           return "maDashBoard";    	   
       case MD:
           return "mdDashBoard";    	   
       case SA:
           return "saDashBoard";    	   
       default:
           return "home";
       }
   }

   @RequestMapping(value = "/calendar", method = RequestMethod.GET)
   public String calendar(Model model, Principal principal) {
       return "calendar";
   }
 
	@RequestMapping(value="/listUser")
	public ModelAndView listUser(ModelAndView model, Principal principal) throws IOException{
		List<UserLogin> listUser = userLoginDAO.list(
				userLoginDAO.getUserRoles(principal.getName()),
				userLoginDAO.getCompanyID(principal.getName()));
		model.addObject("listUser", listUser);
		model.addObject("role", userLoginDAO.getUserRoles(principal.getName()));
		model.setViewName("userList");	 	    
		return model;
	}
	   
   @RequestMapping(value = "/newUser", method = RequestMethod.GET)
   public ModelAndView addUser(ModelAndView model, Principal principal) {
       UserLogin userLogin = userLoginDAO.get(principal.getName());
       UserLogin newUser = new UserLogin();
       model.addObject("user", newUser);
       if (userLogin.getrole().equals("DEV")){       
	       List<String> roles= userLoginDAO.getAllRoles();	
	       model.addObject("role", userLoginDAO.getUserRoles(principal.getName()));
	       model.addObject("rolelist", roles);
       }
	   else {		// SA
    	   List<String> roles= new ArrayList<String>();	
    	   roles.add("MA");
    	   roles.add("TL");
    	   model.addObject("role", userLogin.getrole());
           model.addObject("rolelist", roles);
       }
       model.setViewName("userForm");
       return model;
   }
   
   @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
   public ModelAndView saveUser(@ModelAttribute UserLogin userLogin) {
       userLoginDAO.saveOrUpdate(userLogin);
       return new ModelAndView("redirect:/listUser");    	   
   }
   
   @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
   public ModelAndView deleteUser(HttpServletRequest request) {
       String username = request.getParameter("username");
       userLoginDAO.delete(username);
       return new ModelAndView("redirect:/listUser");
   }
   
   @RequestMapping(value = "/editUser", method = RequestMethod.GET)
   public ModelAndView editUser(HttpServletRequest request) {
       String username = request.getParameter("username");
       UserLogin userLogin = userLoginDAO.get(username);
       ModelAndView model = new ModelAndView("userForm");
       if (userLogin.getrole().equals("DEV")){
        	   List<String> roles= userLoginDAO.getAllRoles();	
               model.addObject("role", userLogin.getrole());
               model.addObject("rolelist", roles);
           }
	   else {		// SA
        	   List<String> roles= new ArrayList<String>();	
        	   roles.add("MA");
        	   model.addObject("role", userLogin.getrole());
               model.addObject("rolelist", roles);
    	   }
       model.addObject("user", userLogin);       
       return model;
   }
   
   @RequestMapping(value = "/403", method = RequestMethod.GET)
   public String accessDenied(Model model, Principal principal) {
        
       if (principal != null) {
           model.addAttribute("message", "Hi " + principal.getName()
                   + "<br> You do not have permission to access this page!");
       } else {
           model.addAttribute("msg",
                   "You do not have permission to access this page!");
       }
       return "403";
   }
}