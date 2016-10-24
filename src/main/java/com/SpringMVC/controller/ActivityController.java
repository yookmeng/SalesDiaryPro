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

import com.SpringMVC.dao.ActivityDAO;
import com.SpringMVC.dao.BrandDAO;
import com.SpringMVC.dao.ModelDAO;
import com.SpringMVC.dao.ProspectDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.dao.UserProfileDAO;
import com.SpringMVC.model.Activity;
import com.SpringMVC.model.Brand;
import com.SpringMVC.model.Model;
import com.SpringMVC.model.Prospect;
import com.SpringMVC.model.UserProfile;
import com.SpringMVC.uriconstant.ActivityRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class ActivityController {

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
    private ActivityDAO activityDAO;

    @RequestMapping(value = ActivityRestURIConstant.Get, method = RequestMethod.GET)
	public String getActivity(@PathVariable int activityid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(activityDAO.get(activityid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ActivityRestURIConstant.GetByProspect, method = RequestMethod.GET)
	public String getAllActivity(@PathVariable int prospectid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(activityDAO.list(prospectid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ActivityRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) throws IOException {
    	Brand brand = brandDAO.getByName(activity.getbrandname());
    	activity.setbrandid(brand.getbrandid());
    	Model model = modelDAO.getByName(activity.getmodelname());
    	activity.setmodelid(model.getmodelid());

    	activityDAO.save(activity);
        return new ResponseEntity<Activity>(activity, HttpStatus.CREATED);
    }

    @RequestMapping(value = ActivityRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Activity> updateActivity(@PathVariable("activityid") int activityid, @RequestBody Activity activity) {
    	Activity currentActivity = activityDAO.get(activityid);         
        if (currentActivity==null) {
            return new ResponseEntity<Activity>(HttpStatus.NOT_FOUND);
        }

    	Brand brand = brandDAO.getByName(activity.getbrandname());
    	activity.setbrandid(brand.getbrandid());
    	Model model = modelDAO.getByName(activity.getmodelname());
    	activity.setmodelid(model.getmodelid());
        
        currentActivity.setactivitydate(activity.getactivitydate());
        currentActivity.setbrandid(activity.getbrandid());
        currentActivity.setbrandname(activity.getbrandname());
        currentActivity.setmodelid(activity.getmodelid());
        currentActivity.setmodelname(activity.getmodelname());
        currentActivity.setdemo(activity.getdemo());
        currentActivity.settestdrive(activity.gettestdrive());
        currentActivity.setquotation(activity.getquotation());
        currentActivity.setlinkevent(activity.getlinkevent());
        currentActivity.setremark1(activity.getremark1());
        currentActivity.setremark2(activity.getremark2());
        currentActivity.setremark3(activity.getremark3());

        activityDAO.update(currentActivity);
        return new ResponseEntity<Activity>(activity, HttpStatus.OK);
    }

    @RequestMapping(value = ActivityRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Activity> deleteActivity(@PathVariable("activityid") int activityid) {
    	Activity activity = activityDAO.get(activityid);
        if (activity == null) {
            return new ResponseEntity<Activity>(HttpStatus.NOT_FOUND);
        }
 
        activityDAO.delete(activityid);
        return new ResponseEntity<Activity>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/listActivity", method = RequestMethod.GET)
    public ModelAndView listActivity(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        Prospect prospect = prospectDAO.get(prospectid);
        UserProfile userProfile = userProfileDAO.findUser(prospect.getuserid());
 	    List<Activity> listActivity= activityDAO.list(prospectid);
        ModelAndView mav = new ModelAndView("activityList");
        mav.addObject("userProfile", userProfile);
        mav.addObject("prospect", prospect);
        mav.addObject("listActivity", listActivity);
 	    return mav;
 	}

    @RequestMapping(value = "/addActivity", method = RequestMethod.GET)
    public ModelAndView addActivity(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        Activity newActivity = new Activity();
        newActivity.setprospectid(prospectid);
        Prospect prospect = prospectDAO.get(prospectid);
        UserProfile userProfile = userProfileDAO.findUser(prospect.getuserid());
        ModelAndView mav = new ModelAndView("activityForm");
        List<String> brands = brandDAO.getBrands(userLoginDAO.getCompanyID(request.getUserPrincipal().getName()));	
        mav.addObject("brandlist", brands);
        Brand brand = brandDAO.getByName(brands.get(0));
        List<String> models = modelDAO.getModels(brand.getbrandid());	
        mav.addObject("userProfile", userProfile);
        mav.addObject("prospect", prospect);
        mav.addObject("modellist", models);
        mav.addObject("activity", newActivity);
        return mav;
    }

    @RequestMapping(value = "/editActivity", method = RequestMethod.GET)
    public ModelAndView editActivity(HttpServletRequest request) {
        int activityid = Integer.parseInt(request.getParameter("activityid")); 
        Activity editActivity = activityDAO.get(activityid);
        Prospect prospect = prospectDAO.get(editActivity.getprospectid());
        UserProfile userProfile = userProfileDAO.findUser(prospect.getuserid());
        ModelAndView mav = new ModelAndView("activityForm");
        List<String> brands = brandDAO.getBrands(userLoginDAO.getCompanyID(request.getUserPrincipal().getName()));	
        mav.addObject("brandlist", brands);
        Brand brand = brandDAO.getByName(brands.get(0));
        List<String> models = modelDAO.getModels(brand.getbrandid());	
        mav.addObject("userProfile", userProfile);
        mav.addObject("prospect", prospect);
        mav.addObject("modellist", models);
        mav.addObject("activity", editActivity);
        return mav;
    } 
}
