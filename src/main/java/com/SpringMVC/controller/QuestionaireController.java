package com.SpringMVC.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.SpringMVC.dao.BrandDAO;
import com.SpringMVC.dao.ModelDAO;
import com.SpringMVC.dao.QuestionaireDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.dao.UserProfileDAO;
import com.SpringMVC.model.Brand;
import com.SpringMVC.model.Model;
import com.SpringMVC.model.Questionaire;
import com.SpringMVC.model.UserProfile;
import com.SpringMVC.uriconstant.QuestionaireRestURIConstant;

@EnableWebMvc
@RestController
public class QuestionaireController {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private UserProfileDAO userProfileDAO;

    @Autowired
    private BrandDAO brandDAO;

    @Autowired
    private ModelDAO modelDAO;

    @Autowired
    private QuestionaireDAO questionaireDAO;

    @RequestMapping(value = "/addQuestionaire", method = RequestMethod.GET)
    public ModelAndView addQuestionaire(HttpServletRequest request) {
    	UserProfile userProfile = userProfileDAO.get(request.getUserPrincipal().getName());
    	int companyid = userLoginDAO.getCompanyID(request.getUserPrincipal().getName());
    	Questionaire newQuestionaire = new Questionaire();
    	newQuestionaire.setuserid(userProfile.getuserid());
        ModelAndView mav = new ModelAndView("questionaireForm");
        List<String> brands = brandDAO.getBrands(companyid);
        mav.addObject("brandlist", brands);
        Brand brand = brandDAO.getByName(brands.get(0));
        List<String> models = modelDAO.getModels(brand.getbrandid());	
        mav.addObject("brandlist", brands);
        mav.addObject("modellist", models);
        mav.addObject("questionaire", newQuestionaire);
        return mav;
    }

    @RequestMapping(value = QuestionaireRestURIConstant.CREATE, method = RequestMethod.POST)
    public ResponseEntity<Questionaire> createQuestionaire(@RequestBody Questionaire questionaire) throws IOException {
    	Brand brand = brandDAO.getByName(questionaire.getbrandname());
    	questionaire.setbrandid(brand.getbrandid());
    	Model model = modelDAO.getByName(questionaire.getmodelname());
    	questionaire.setmodelid(model.getmodelid());
    	questionaireDAO.save(questionaire);
        return new ResponseEntity<Questionaire>(questionaire, HttpStatus.CREATED);
    }    
}
