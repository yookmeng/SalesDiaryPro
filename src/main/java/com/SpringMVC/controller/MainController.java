package com.SpringMVC.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.SpringMVC.model.UserLogin;
import com.SpringMVC.dao.UserLoginDAO;
 
@Controller
public class MainController {
    @Autowired
    private UserLoginDAO userLoginDAO;

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
   public String home(Model model, Principal principal) {
	   model.addAttribute("username", principal.getName());
	   List<String> roles = userLoginDAO.getUserRoles(principal.getName());
       if(roles!= null)  {
           for(String role: roles)  {
        	   model.addAttribute("role", role);
           }
       }        
       return "home";
   }
 
   @RequestMapping(value="/listUser")
   public ModelAndView listUser(ModelAndView model) throws IOException{
	    List<UserLogin> listUser = userLoginDAO.list();
	    model.addObject("listUser", listUser);
	    model.setViewName("userList");	 	    
	    return model;
	}
	   
   @RequestMapping(value = "/newUser", method = RequestMethod.GET)
   public ModelAndView addUser(ModelAndView model) {
       UserLogin newUser = new UserLogin();
       model.addObject("user", newUser);
       List<String> roles= userLoginDAO.getAllRoles();	
       model.addObject("rolelist", roles);
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
       List<String> roles= userLoginDAO.getAllRoles();	
       ModelAndView model = new ModelAndView("userForm");
       model.addObject("rolelist", roles);
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