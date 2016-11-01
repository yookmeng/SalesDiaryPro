package com.SpringMVC.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.SpringMVC.dao.ClosingPeriodDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.ClosingPeriod;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.ClosingPeriodRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class ClosingPeriodController {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private ClosingPeriodDAO closingPeriodDAO;

    @RequestMapping(value = ClosingPeriodRestURIConstant.Get, method = RequestMethod.GET)
	public String getClosingPeriod(@PathVariable int id) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(closingPeriodDAO.get(id));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ClosingPeriodRestURIConstant.GetAll, method = RequestMethod.GET)
	public String getAllClosingPeriod(Principal principal) {
    	int companyid = userLoginDAO.getCompanyID(principal.getName());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(closingPeriodDAO.list(companyid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ClosingPeriodRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<ClosingPeriod> createClosingPeriod(@RequestBody ClosingPeriod closingPeriod) throws IOException {
    	closingPeriodDAO.save(closingPeriod);
        return new ResponseEntity<ClosingPeriod>(closingPeriod, HttpStatus.CREATED);
    }

    @RequestMapping(value = ClosingPeriodRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<ClosingPeriod> updateClosingPeriod(@PathVariable("id") int id, @RequestBody ClosingPeriod closingPeriod) {
    	ClosingPeriod currentClosingPeriod = closingPeriodDAO.get(id);
         
        if (currentClosingPeriod==null) {
            return new ResponseEntity<ClosingPeriod>(HttpStatus.NOT_FOUND);
        }
        
    	currentClosingPeriod.setopendate(closingPeriod.getopendate());
    	currentClosingPeriod.setclosedate(closingPeriod.getclosedate());

        closingPeriodDAO.update(currentClosingPeriod);
        return new ResponseEntity<ClosingPeriod>(closingPeriod, HttpStatus.OK);
    }

    @RequestMapping(value = ClosingPeriodRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<ClosingPeriod> deleteClosingPeriod(@PathVariable("id") int id) {
    	ClosingPeriod closingPeriod = closingPeriodDAO.get(id);
        if (closingPeriod == null) {
            return new ResponseEntity<ClosingPeriod>(HttpStatus.NOT_FOUND);
        }
 
        closingPeriodDAO.delete(id);
        return new ResponseEntity<ClosingPeriod>(HttpStatus.OK);
    }

    @RequestMapping(value="/listClosingPeriod")
    public ModelAndView listClosingPeriod(ModelAndView mav, Principal principal) throws IOException{
        UserLogin userLogin = userLoginDAO.get(principal.getName());
        int companyid = userLoginDAO.getCompanyID(principal.getName());
 	    List<ClosingPeriod> listClosingPeriod = closingPeriodDAO.list(companyid);
 	    mav.addObject("listClosingPeriod", listClosingPeriod);
        mav.addObject("role", userLogin.getrole());        
 	    mav.setViewName("closingPeriodList");	 	    
 	    return mav;
 	}
 	   
    @RequestMapping(value = "/addClosingPeriod", method = RequestMethod.GET)
    public ModelAndView addClosingPeriod(ModelAndView mav, Principal principal) {
        UserLogin userLogin = userLoginDAO.get(principal.getName());
        int companyid = userLoginDAO.getCompanyID(principal.getName());
        int controlyear = Calendar.getInstance().get(Calendar.YEAR);
        ClosingPeriod newClosingPeriod = new ClosingPeriod();
        newClosingPeriod.setcompanyid(companyid);
        newClosingPeriod.setcontrolyear(controlyear);
        List<Integer> years = new ArrayList<Integer>();
        for(int y=controlyear-10;y<=controlyear+10;y++){
        	years.add(y);
        }
        List<Integer> months = new ArrayList<Integer>();
        for(int m=1;m<=12;m++){
        	months.add(m);
        }
        mav.addObject("closingPeriod", newClosingPeriod);
        mav.addObject("yearlist", years);                
        mav.addObject("monthlist", months);
        mav.addObject("role", userLogin.getrole());        
        mav.setViewName("closingPeriodForm");
        return mav;
    }
            
    @RequestMapping(value = "/editClosingPeriod", method = RequestMethod.GET)
    public ModelAndView editClosingPeriod(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));       	
        ClosingPeriod editClosingPeriod = closingPeriodDAO.get(id);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        int controlyear = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> years = new ArrayList<Integer>();
        for(int y=controlyear-10;y<=controlyear+10;y++){
        	years.add(y);
        }
        List<Integer> months = new ArrayList<Integer>();
        for(int m=1;m<=12;m++){
        	months.add(m);
        }
        ModelAndView mav = new ModelAndView("closingPeriodForm");
        mav.addObject("yearlist", years);                
        mav.addObject("monthlist", months);                  
 	   	mav.addObject("role", userLogin.getrole());
        mav.addObject("closingPeriod", editClosingPeriod);
        return mav;
    }
}