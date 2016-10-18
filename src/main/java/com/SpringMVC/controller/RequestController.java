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

import com.SpringMVC.dao.BrandDAO;
import com.SpringMVC.dao.ModelDAO;
import com.SpringMVC.dao.ProspectDAO;
import com.SpringMVC.dao.RequestDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.dao.UserProfileDAO;
import com.SpringMVC.model.Brand;
import com.SpringMVC.model.Model;
import com.SpringMVC.model.Prospect;
import com.SpringMVC.model.Request;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.model.UserProfile;
import com.SpringMVC.uriconstant.RequestRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class RequestController {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private UserProfileDAO userProfileDAO;

    @Autowired
    private ProspectDAO prospectDAO;

    @Autowired
    private BrandDAO brandDAO;

    @Autowired
    private ModelDAO modelDAO;

    @Autowired
    private RequestDAO requestDAO;

    @RequestMapping(value = RequestRestURIConstant.GET, method = RequestMethod.GET)
	public String getRequest(@PathVariable int requestid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(requestDAO.get(requestid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = RequestRestURIConstant.GET_ALL, method = RequestMethod.GET)
	public String getAllRequest(Principal principal) {
    	UserLogin userLogin = userLoginDAO.findUserLogin(principal.getName());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(requestDAO.list(userLogin.getuserid()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = RequestRestURIConstant.CREATE, method = RequestMethod.POST)
    public ResponseEntity<Request> createRequest(@RequestBody Request request) throws IOException {
    	Brand brand = brandDAO.getByName(request.getbrandname());
    	request.setbrandid(brand.getbrandid());
    	Model model = modelDAO.getByName(request.getmodelname());
    	request.setmodelid(model.getmodelid());

    	requestDAO.save(request);
        return new ResponseEntity<Request>(request, HttpStatus.CREATED);
    }

    @RequestMapping(value = RequestRestURIConstant.UPDATE, method = RequestMethod.POST)
    public ResponseEntity<Request> updateRequest(@PathVariable("requestid") int requestid, @RequestBody Request request) {
    	Request currentRequest = requestDAO.get(requestid);         
        if (currentRequest==null) {
            return new ResponseEntity<Request>(HttpStatus.NOT_FOUND);
        }

    	Brand brand = brandDAO.getByName(request.getbrandname());
    	request.setbrandid(brand.getbrandid());
    	Model model = modelDAO.getByName(request.getmodelname());
    	request.setmodelid(model.getmodelid());
    	
        currentRequest.setrequestdate(request.getrequestdate());
        currentRequest.setbrandid(request.getbrandid());
        currentRequest.setbrandname(request.getbrandname());
        currentRequest.setmodelid(request.getmodelid());
        currentRequest.setmodelname(request.getmodelname());
        currentRequest.setremark(request.getremark());
        currentRequest.setstatus(request.getstatus());

        requestDAO.update(currentRequest);
        return new ResponseEntity<Request>(request, HttpStatus.OK);
    }

    @RequestMapping(value = RequestRestURIConstant.DELETE, method = RequestMethod.DELETE)
    public ResponseEntity<Request> deleteRequest(@PathVariable("requestid") int requestid) {
    	Request request = requestDAO.get(requestid);
        if (request == null) {
            return new ResponseEntity<Request>(HttpStatus.NOT_FOUND);
        }
 
        requestDAO.delete(requestid);
        return new ResponseEntity<Request>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/listRequest", method = RequestMethod.GET)
    public ModelAndView listRequest(HttpServletRequest request) {
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        Prospect prospect = prospectDAO.get(prospectid);
 	    List<Request> listRequest = requestDAO.list(prospectid);
        ModelAndView mav = new ModelAndView("requestList");
        mav.addObject("userLogin", userLogin);
        mav.addObject("prospect", prospect);
        mav.addObject("listRequest", listRequest);
 	    return mav;
 	}

    @RequestMapping(value = "/addRequest", method = RequestMethod.GET)
    public ModelAndView addRequest(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        Request newRequest = new Request();
        newRequest.setprospectid(prospectid);
        newRequest.setstatus("Hot");
        Prospect prospect = prospectDAO.get(prospectid);
        UserProfile userProfile = userProfileDAO.findUser(prospect.getuserid());
        ModelAndView mav = new ModelAndView("requestForm");
        List<String> brands = brandDAO.getBrands(userProfileDAO.getCompanyID(request.getUserPrincipal().getName()));	
        mav.addObject("brandlist", brands);
        Brand brand = brandDAO.getByName(brands.get(0));
        List<String> models = modelDAO.getModels(brand.getbrandid());	
        mav.addObject("userProfile", userProfile);
        mav.addObject("prospect", prospect);
        mav.addObject("modellist", models);
        mav.addObject("request", newRequest);
        return mav;
    }

    @RequestMapping(value = "/editRequest", method = RequestMethod.GET)
    public ModelAndView editRequest(HttpServletRequest request) {
        int requestid = Integer.parseInt(request.getParameter("requestid")); 
        Request editRequest = requestDAO.get(requestid);
        Prospect prospect = prospectDAO.get(editRequest.getprospectid());
        UserProfile userProfile = userProfileDAO.findUser(prospect.getuserid());
        ModelAndView mav = new ModelAndView("requestForm");
        List<String> brands = brandDAO.getBrands(userProfileDAO.getCompanyID(request.getUserPrincipal().getName()));	
        mav.addObject("brandlist", brands);
        List<String> models = modelDAO.getModels(editRequest.getbrandid());	
        mav.addObject("userProfile", userProfile);
        mav.addObject("prospect", prospect);
        mav.addObject("modellist", models);
        mav.addObject("request", editRequest);
        return mav;
    } 
}
