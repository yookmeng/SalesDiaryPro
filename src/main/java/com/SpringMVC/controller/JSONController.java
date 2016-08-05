package com.SpringMVC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.SpringMVC.dao.ProspectDAO;
import com.SpringMVC.model.Prospect;

@Controller
public class JSONController {
    @Autowired
    private ProspectDAO prospectDAO;

    @RequestMapping(value = "/listProspectJSON/{prospectid}", method = RequestMethod.GET)    
    public String listProspectJSON(@PathVariable int prospectid, Model model) {      
        Prospect prospect = prospectDAO.get(prospectid);
    	model.addAttribute("prospect", prospect);
        return "prospect";
    }

    @RequestMapping(value = "/listAllProspectJSON/{userid}", method = RequestMethod.GET)    
    public String listAllProspectJSON(@PathVariable int userid, Model model) {      
        List<Prospect> prospect = prospectDAO.list(userid);
    	model.addAttribute("prospect", prospect);
        return "prospect";
    }
}
