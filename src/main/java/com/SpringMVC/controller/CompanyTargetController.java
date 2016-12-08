package com.SpringMVC.controller;

import java.io.IOException;
import java.security.Principal;
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

import com.SpringMVC.dao.CommonDAO;
import com.SpringMVC.dao.CompanyDAO;
import com.SpringMVC.dao.CompanyTargetDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.Company;
import com.SpringMVC.model.CompanyTarget;
import com.SpringMVC.uriconstant.CompanyTargetRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class CompanyTargetController {

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private UserLoginDAO userLoginDAO;

	@Autowired
    private CompanyTargetDAO companyTargetDAO;

    @Autowired
    private CommonDAO commonDAO;

    @RequestMapping(value = CompanyTargetRestURIConstant.Get, method = RequestMethod.GET)
	public String getCompanyTarget(@PathVariable int targetid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(companyTargetDAO.get(targetid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = CompanyTargetRestURIConstant.GetAll, method = RequestMethod.GET)
	public String getAllCompanyTarget(Principal principal) {
    	int companyid = userLoginDAO.getCompanyID(principal.getName());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(companyTargetDAO.list(companyid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = CompanyTargetRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<CompanyTarget> createCompanyTarget(@RequestBody CompanyTarget companyTarget) throws IOException {
    	companyTargetDAO.save(companyTarget);
        return new ResponseEntity<CompanyTarget>(companyTarget, HttpStatus.CREATED);
    }

    @RequestMapping(value = CompanyTargetRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<CompanyTarget> updateCompanyTarget(@PathVariable("targetid") int targetid, @RequestBody CompanyTarget companyTarget) {
    	CompanyTarget currentCompanyTarget = companyTargetDAO.get(targetid);         
        if (currentCompanyTarget==null) {
            return new ResponseEntity<CompanyTarget>(HttpStatus.NOT_FOUND);
        }

        currentCompanyTarget.setprospect(companyTarget.getprospect());
        currentCompanyTarget.settestdrive(companyTarget.gettestdrive());
        currentCompanyTarget.setclosed(companyTarget.getclosed());

        companyTargetDAO.update(currentCompanyTarget);
        return new ResponseEntity<CompanyTarget>(companyTarget, HttpStatus.OK);
    }

    @RequestMapping(value = CompanyTargetRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<CompanyTarget> deleteCompanyTarget(@PathVariable("targetid") int targetid) {
    	CompanyTarget companyTarget = companyTargetDAO.get(targetid);
        if (companyTarget == null) {
            return new ResponseEntity<CompanyTarget>(HttpStatus.NOT_FOUND);
        }
 
        companyTargetDAO.delete(targetid);
        return new ResponseEntity<CompanyTarget>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/listCompanyTarget", method = RequestMethod.GET)
    public ModelAndView listCompanyTarget(Principal principal) {
        int companyid = userLoginDAO.getCompanyID(principal.getName());
        Company company = companyDAO.get(companyid);
 	    List<CompanyTarget> listCompanyTarget = companyTargetDAO.list(companyid);
        ModelAndView mav = new ModelAndView("companyTargetList");
        mav.addObject("company", company);
 	    mav.addObject("listTarget", listCompanyTarget);
 	    return mav;
 	}
 	   
    @RequestMapping(value = "/addCompanyTarget", method = RequestMethod.GET)
    public ModelAndView addCompanyTarget(HttpServletRequest request) {
        int companyid = userLoginDAO.getCompanyID(request.getUserPrincipal().getName());
        Company company = companyDAO.get(companyid);
        List<String> periods = commonDAO.periodList();
        CompanyTarget newCompanyTarget = new CompanyTarget();
        newCompanyTarget.setcompanyid(companyid);
        ModelAndView mav = new ModelAndView("companyTargetForm");
        mav.addObject("periodlist", periods);
        mav.addObject("company", company);
        mav.addObject("companyTarget", newCompanyTarget);
        return mav;
    }

    @RequestMapping(value = "/editCompanyTarget", method = RequestMethod.GET)
    public ModelAndView editCompanyTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));        
        List<String> periods = commonDAO.periodList();
        CompanyTarget editCompanyTarget = companyTargetDAO.get(targetid);
        Company company = companyDAO.get(editCompanyTarget.getcompanyid());
        ModelAndView mav = new ModelAndView("companyTargetForm");
        mav.addObject("periodlist", periods);
        mav.addObject("company", company);
        mav.addObject("companyTarget", editCompanyTarget);
        return mav;
    }   
}
