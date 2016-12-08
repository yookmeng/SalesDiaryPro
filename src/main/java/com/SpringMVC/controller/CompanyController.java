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

import com.SpringMVC.dao.CompanyDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.Company;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.CompanyRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class CompanyController {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private CompanyDAO companyDAO;

    @RequestMapping(value = CompanyRestURIConstant.Get, method = RequestMethod.GET)
	public String getCompany(@PathVariable int companyid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(companyDAO.get(companyid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = CompanyRestURIConstant.GetAll, method = RequestMethod.GET)
	public String getCompanys(Principal principal) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(companyDAO.list());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = CompanyRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Company> createCompany(@RequestBody Company company) throws IOException {
    	UserLogin userLoginMD = userLoginDAO.get(company.getmdname());
    	company.setmdid(userLoginMD.getuserid());
    	UserLogin userLoginSA = userLoginDAO.get(company.getsaname());
    	company.setsaid(userLoginSA.getuserid());

    	companyDAO.save(company);
        return new ResponseEntity<Company>(company, HttpStatus.CREATED);
    }

    @RequestMapping(value = CompanyRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Company> updateCompany(@PathVariable("companyid") int companyid, @RequestBody Company company) {
    	Company currentCompany = companyDAO.get(companyid);
        if (currentCompany==null) {
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
        }
        UserLogin userLoginMD = userLoginDAO.get(company.getmdname());
    	company.setmdid(userLoginMD.getuserid());
    	UserLogin userLoginSA = userLoginDAO.get(company.getsaname());
    	company.setsaid(userLoginSA.getuserid());

    	currentCompany.setcompanyname(company.getcompanyname());
    	currentCompany.setregno(company.getregno());
    	currentCompany.setmdid(company.getmdid());
    	currentCompany.setaddress(company.getaddress());    	
    	currentCompany.settelephone(company.gettelephone());
    	currentCompany.setfax(company.getfax());
    	currentCompany.setemail(company.getemail());
    	currentCompany.setwebsite(company.getwebsite());
    	currentCompany.setsaid(company.getsaid());

        companyDAO.update(currentCompany);
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }

    @RequestMapping(value = CompanyRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Company> deleteCompany(@PathVariable("companyid") int companyid) {
    	Company company = companyDAO.get(companyid);
        if (company == null) {
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
        }
 
        companyDAO.delete(companyid);
        return new ResponseEntity<Company>(HttpStatus.OK);
    }

    @RequestMapping(value="/listCompany", method = RequestMethod.GET)
    public ModelAndView listCompany(ModelAndView mav) throws IOException{
 	    List<Company> listCompany = companyDAO.list();
 	    mav.addObject("listCompany", listCompany);
 	    mav.setViewName("companyList");	 	    
 	    return mav;
 	}
 	   
    @RequestMapping(value = "/addCompany", method = RequestMethod.GET)
    public ModelAndView addCompany(ModelAndView mav, Principal principal) {
        UserLogin userLogin = userLoginDAO.get(principal.getName());
        List<String> mdlist = userLoginDAO.mdlist();	
        List<String> salist = userLoginDAO.salist();	
        Company newCompany = new Company();        
        mav.addObject("company", newCompany);
        mav.addObject("mdlist", mdlist);        
        mav.addObject("salist", salist);
        mav.addObject("role", userLogin.getrole());        
        mav.setViewName("companyForm");
        return mav;
    }
            
    @RequestMapping(value = "/editCompany", method = RequestMethod.GET)
    public ModelAndView editCompany(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        if (companyid==0){
        	companyid = userLoginDAO.getCompanyID(request.getUserPrincipal().getName());
        }
        Company editCompany= companyDAO.get(companyid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("companyForm");
        if (userLogin.getrole().equals("DEV")){
            List<String> mdlist = userLoginDAO.mdlist();	
            mav.addObject("mdlist", mdlist); 
            List<String> salist = userLoginDAO.salist();	        	
            mav.addObject("salist", salist);        
        }
        else {
        	List<String> mdlist = new ArrayList<String>();
        	mdlist.add(editCompany.getmdname());
            mav.addObject("mdlist", mdlist); 
        	List<String> salist = new ArrayList<String>();
        	salist.add(editCompany.getsaname());
            mav.addObject("salist", salist);        
        }
 	   	mav.addObject("role", userLogin.getrole());
        mav.addObject("company", editCompany);
        return mav;
    }
}
