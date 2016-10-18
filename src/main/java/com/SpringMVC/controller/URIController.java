package com.SpringMVC.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.SpringMVC.dao.ModelDAO;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.ModelRestURIConstant;

import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.dao.UserMonthlySummaryDAO;
import com.SpringMVC.uriconstant.UserMonthlySummaryRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class URIController {
    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private ModelDAO modelDAO;

    @Autowired
    private UserMonthlySummaryDAO userMonthlySummaryDAO;

    @RequestMapping(value = ModelRestURIConstant.GET_MODEL, method = RequestMethod.GET)
	public String getModel(@PathVariable int modelid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(modelDAO.get(modelid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    // UserMonthlySummary
    @RequestMapping(value = UserMonthlySummaryRestURIConstant.GET, method = RequestMethod.GET)
	public String get(@PathVariable String period, HttpServletRequest request) {
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(userMonthlySummaryDAO.get(period, userLogin.getuserid()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}
}
