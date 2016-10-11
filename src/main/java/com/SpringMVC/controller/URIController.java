package com.SpringMVC.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.SpringMVC.dao.ContactDAO;
import com.SpringMVC.dao.ModelDAO;
import com.SpringMVC.model.Contact;
import com.SpringMVC.model.Model;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.ContactRestURIConstant;
import com.SpringMVC.uriconstant.ModelRestURIConstant;

import com.SpringMVC.dao.ProspectDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.dao.UserMonthlySummaryDAO;
import com.SpringMVC.uriconstant.ProspectRestURIConstant;
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
    private ProspectDAO prospectDAO;

    @Autowired
    private ContactDAO contactDAO;

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

    @RequestMapping(value = ModelRestURIConstant.CREATE_MODEL
    		, produces = "application/json"
			,headers="Accept=*/*"
    		, method = RequestMethod.POST)  
    public  HttpStatus update(String a) {  
    	Model model = new Model();
    	model.setmodelid(100);
    	model.setbrandid(1);
    	model.setmodelname("aaa");
    	model.setprice(1000);
    	modelDAO.saveOrUpdate(model);
    	return HttpStatus.OK;
    }
    
	// Prospect   
    @RequestMapping(value = ProspectRestURIConstant.GET, method = RequestMethod.GET)
	public String getProspect(@PathVariable int prospectid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(prospectDAO.get(prospectid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ProspectRestURIConstant.GET_ALL, method = RequestMethod.GET)
	public String getAllProspect(Principal principal) {
    	UserLogin userLogin = userLoginDAO.findUserLogin(principal.getName());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(prospectDAO.list(userLogin.getuserid()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    // Contact
    @RequestMapping(value = ContactRestURIConstant.GET, method = RequestMethod.GET)
	public String getContact(@PathVariable int contactid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(contactDAO.get(contactid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ContactRestURIConstant.GET_ALL, method = RequestMethod.GET)
	public String getAllContact(Principal principal) {
    	UserLogin userLogin = userLoginDAO.findUserLogin(principal.getName());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(contactDAO.list(userLogin.getuserid()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ContactRestURIConstant.CREATE, method = RequestMethod.POST)
    public ResponseEntity<Void> createContact(@RequestBody Contact contact, UriComponentsBuilder ucBuilder) {
        if (contactDAO.isExist(contact)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        contactDAO.save(contact);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path(ContactRestURIConstant.GET).buildAndExpand(contact.getcontactid()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = ContactRestURIConstant.UPDATE, method = RequestMethod.PUT)
    public ResponseEntity<Contact> updateContact(@PathVariable("contactid") int contactid, @RequestBody Contact contact) {
        Contact currentContact = contactDAO.get(contactid);
         
        if (currentContact==null) {
            return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
        }
 
        currentContact.setfirstname(contact.getfirstname());
        currentContact.setlastname(contact.getlastname());
        currentContact.setmobile(contact.getmobile());
        currentContact.sethome(contact.gethome());
        currentContact.setwork(contact.getwork());
        currentContact.setemail(contact.getemail());
        currentContact.setbirthday(contact.getbirthday());
        currentContact.setcountry(contact.getcountry());
        currentContact.setzipcode(contact.getzipcode());
        currentContact.setstate(contact.getstate());
        currentContact.setcity(contact.getcity());
        currentContact.setstreet(contact.getstreet());
        currentContact.setcompany(contact.getcompany());
        currentContact.settitle(contact.gettitle());
        currentContact.setnote(contact.getnote());
        currentContact.setwebsite(contact.getwebsite());

        contactDAO.update(currentContact);
        return new ResponseEntity<Contact>(currentContact, HttpStatus.OK);
    }

    @RequestMapping(value = ContactRestURIConstant.DELETE, method = RequestMethod.DELETE)
    public ResponseEntity<Contact> deleteContact(@PathVariable("contactid") int contactid) {
        Contact contact = contactDAO.get(contactid);
        if (contact == null) {
            return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
        }
 
        contactDAO.delete(contactid);
        return new ResponseEntity<Contact>(HttpStatus.OK);
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
