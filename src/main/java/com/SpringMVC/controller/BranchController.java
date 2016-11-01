package com.SpringMVC.controller;

import java.io.IOException;
import java.security.Principal;
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
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.BranchRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class BranchController {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private BranchDAO branchDAO;


    @RequestMapping(value = BranchRestURIConstant.Get, method = RequestMethod.GET)
	public String getBranch(@PathVariable int branchid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(branchDAO.get(branchid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = BranchRestURIConstant.GetAll, method = RequestMethod.GET)
	public String getBranchs(Principal principal) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(branchDAO.list(userLoginDAO.getCompanyID(principal.getName())));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = BranchRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Branch> createBranch(@RequestBody Branch branch) throws IOException {
    	UserLogin userLogin = userLoginDAO.get(branch.getmaname());
    	branch.setmaid(userLogin.getuserid());
    	branchDAO.save(branch);
        return new ResponseEntity<Branch>(branch, HttpStatus.CREATED);
    }

    @RequestMapping(value = BranchRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Branch> updateBranch(@PathVariable("branchid") int branchid, @RequestBody Branch branch) {
    	Branch currentBranch = branchDAO.get(branchid);
         
        if (currentBranch==null) {
            return new ResponseEntity<Branch>(HttpStatus.NOT_FOUND);
        }
        
    	UserLogin userLogin = userLoginDAO.get(branch.getmaname());
    	branch.setmaid(userLogin.getuserid());

    	currentBranch.setbranchname(branch.getbranchname());
    	currentBranch.setregno(branch.getregno());
    	currentBranch.setmaid(branch.getmaid());
    	currentBranch.setmaname(branch.getmaname());
    	currentBranch.setcountry(branch.getcountry());
    	currentBranch.setzipcode(branch.getzipcode());
    	currentBranch.setstate(branch.getstate());
    	currentBranch.setcity(branch.getcity());
    	currentBranch.setaddress(branch.getaddress());
    	currentBranch.settelephone(branch.gettelephone());
    	currentBranch.setfax(branch.getfax());
    	currentBranch.setemail(branch.getemail());
    	currentBranch.setwebsite(branch.getwebsite());

        branchDAO.update(currentBranch);
        return new ResponseEntity<Branch>(branch, HttpStatus.OK);
    }

    @RequestMapping(value = BranchRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Branch> deleteBranch(@PathVariable("branchid") int branchid) {
    	Branch branch = branchDAO.get(branchid);
        if (branch == null) {
            return new ResponseEntity<Branch>(HttpStatus.NOT_FOUND);
        }
 
        branchDAO.delete(branchid);
        return new ResponseEntity<Branch>(HttpStatus.OK);
    }

    @RequestMapping(value="/listBranch", method = RequestMethod.GET)
    public ModelAndView listBranch(HttpServletRequest request) {
    	UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
    	List<Branch> listBranch = branchDAO.list(userLoginDAO.getCompanyID(request.getUserPrincipal().getName()));
        ModelAndView mav = new ModelAndView("branchList");        
        mav.addObject("role", userLogin.getrole());
        mav.addObject("listBranch", listBranch);
 	    return mav;
 	}
     	   
    @RequestMapping(value = "/addBranch", method = RequestMethod.GET)
    public ModelAndView addBranch(HttpServletRequest request) {
    	UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Branch newBranch = new Branch();
        newBranch.setcompanyid(userLoginDAO.getCompanyID(request.getUserPrincipal().getName()));
        ModelAndView mav = new ModelAndView("branchForm");
        List<String> malist = userLoginDAO.malist();	
        mav.addObject("role", userLogin.getrole());
        mav.addObject("malist", malist);        
        mav.addObject("branch", newBranch);
        return mav;
    }

    @RequestMapping(value = "/editBranch", method = RequestMethod.GET)
    public ModelAndView editBranch(HttpServletRequest request) {
        int branchid = Integer.parseInt(request.getParameter("branchid"));        
    	UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Branch editBranch = branchDAO.get(branchid);
        ModelAndView mav = new ModelAndView("branchForm");
    	List<String> malist = new ArrayList<String>();
    	malist.add(editBranch.getmaname());
        mav.addObject("role", userLogin.getrole());
        mav.addObject("malist", malist); 
        mav.addObject("branch", editBranch);
        return mav;
    }    
}
