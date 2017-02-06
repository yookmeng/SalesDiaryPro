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

import com.SpringMVC.dao.BranchDAO;
import com.SpringMVC.dao.BrandDAO;
import com.SpringMVC.dao.CodeMasterDAO;
import com.SpringMVC.dao.ModelDAO;
import com.SpringMVC.dao.ProspectDAO;
import com.SpringMVC.dao.QuestionaireDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.APIProspect;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.Brand;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.Model;
import com.SpringMVC.model.Prospect;
import com.SpringMVC.model.Questionaire;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.ProspectRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class ProspectController {

	@Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private BrandDAO brandDAO;

    @Autowired
    private ModelDAO modelDAO;

    @Autowired
    private QuestionaireDAO questionaireDAO;

    @Autowired
    private ProspectDAO prospectDAO;

    @Autowired
    private CodeMasterDAO codeMasterDAO;

    private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }

    @RequestMapping(value = ProspectRestURIConstant.Get, method = RequestMethod.GET)
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

    @RequestMapping(value = ProspectRestURIConstant.GetAll, method = RequestMethod.POST)
	public String getAllProspect(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(prospectDAO.list(userLogin.getuserid()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ProspectRestURIConstant.Add, method = RequestMethod.POST)
    public ResponseEntity<Questionaire> addProspect(@RequestBody APIProspect aPIProspect) throws IOException {
    	Questionaire questionaire = new Questionaire();
    	UserLogin userLogin = userLoginDAO.findUserEmail(aPIProspect.getuseremail());
    	questionaire.setuserid(userLogin.getuserid());
    	questionaire.setprospectname(aPIProspect.getprospectname());
    	questionaire.setmobile(aPIProspect.getmobile());
    	questionaire.setbrandname(aPIProspect.getbrandname());
    	Brand brand = brandDAO.getByName(aPIProspect.getbrandname());
    	questionaire.setbrandid(brand.getbrandid());
    	questionaire.setmodelname(aPIProspect.getmodelname());
    	Model model = modelDAO.getByName(aPIProspect.getmodelname());
    	questionaire.setmodelid(model.getmodelid());
    	questionaire.setsource(aPIProspect.getsource());
    	questionaireDAO.save(questionaire);
        return new ResponseEntity<Questionaire>(questionaire, HttpStatus.CREATED);
    }

    @RequestMapping(value = ProspectRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Prospect> createProspect(@RequestBody Prospect prospect) throws IOException {
        prospectDAO.save(prospect);
        return new ResponseEntity<Prospect>(prospect, HttpStatus.CREATED);
    }

    @RequestMapping(value = ProspectRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Prospect> updateProspect(@PathVariable("prospectid") int prospectid, @RequestBody Prospect prospect) {
    	Prospect currentProspect = prospectDAO.get(prospectid);
         
        if (currentProspect==null) {
            return new ResponseEntity<Prospect>(HttpStatus.NOT_FOUND);
        }
        
        currentProspect.setfirstname(prospect.getfirstname());
        currentProspect.setlastname(prospect.getlastname());
        currentProspect.setsource(prospect.getsource());
        currentProspect.sethomeaddress(prospect.gethomeaddress());
        currentProspect.setmobile(prospect.getmobile());
        currentProspect.sethtelno(prospect.gethtelno());
        currentProspect.setworkaddress(prospect.getworkaddress());
        currentProspect.setwtelno(prospect.getwtelno());
        currentProspect.setemail(prospect.getemail());
        currentProspect.setoccupation(prospect.getoccupation());
        currentProspect.setage(prospect.getage());
        currentProspect.setgender(prospect.getgender());
        currentProspect.setincome(prospect.getincome());
        currentProspect.setemail(prospect.getemail());
        currentProspect.setstatus(prospect.getstatus());

        prospectDAO.update(currentProspect);
        return new ResponseEntity<Prospect>(prospect, HttpStatus.OK);
    }

    @RequestMapping(value = ProspectRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Prospect> deleteProspect(@PathVariable("prospectid") int prospectid) {
    	Prospect prospect = prospectDAO.get(prospectid);
        if (prospect == null) {
            return new ResponseEntity<Prospect>(HttpStatus.NOT_FOUND);
        }
 
        prospectDAO.delete(prospectid);
        return new ResponseEntity<Prospect>(HttpStatus.OK);
    }

    @RequestMapping(value="/listProspects", method = RequestMethod.GET)
    public ModelAndView listProspects(Principal principal) {
        UserLogin userLogin = userLoginDAO.get(principal.getName());
 	    ModelAndView mav = new ModelAndView("prospectList");
		mav.addObject("role", userLogin.getrole());
		Roles role = Roles.valueOf(userLogin.getrole()); 
		switch (role){
		case USER:
			mav.addObject("listProspect", prospectDAO.list(userLogin.getuserid()));
			break;    	   
		case TL:
			Team team = teamDAO.getByUser(userLogin.getuserid());
			mav.addObject("listProspect", prospectDAO.listByTeam(team.getteamid()));			
			break;    	   
		case MA:
			Branch branch = branchDAO.getByMA(userLogin.getuserid());
			mav.addObject("listProspect", prospectDAO.listByBranch(branch.getbranchid()));			
			break;    	   
		case MD:
			mav.addObject("listProspect", prospectDAO.listByCompany(userLogin.getcompanyid()));			
			break;
		default:
			break;	
		}		
		return mav;
 	}

    @RequestMapping(value = "/addProspect", method = RequestMethod.GET)
    public ModelAndView addProspect(HttpServletRequest request) {
        int userid = Integer.parseInt(request.getParameter("userid"));
        Prospect newProspect = new Prospect();
        newProspect.setuserid(userid);
        newProspect.setstatus("Hot");
        ModelAndView mav = new ModelAndView("prospectForm");
        List<String> sources = codeMasterDAO.getCode("SOURCE");	
        mav.addObject("sourcelist", sources);
        List<String> status = codeMasterDAO.getCode("STATUS");	
        mav.addObject("statuslist", status);
        List<String> gender = codeMasterDAO.getCode("GENDER");	
        mav.addObject("genderlist", gender);
        mav.addObject("prospect", newProspect);
        return mav;
    }

    @RequestMapping(value = "/editProspect", method = RequestMethod.GET)
    public ModelAndView editProspect(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid")); 
        Prospect editProspect = prospectDAO.get(prospectid);
        ModelAndView mav = new ModelAndView("prospectForm");
        List<String> sources = codeMasterDAO.getCode("SOURCE");
        mav.addObject("sourcelist", sources);
        List<String> status = codeMasterDAO.getCode("STATUS");	
        mav.addObject("statuslist", status);        
        List<String> gender = codeMasterDAO.getCode("GENDER");	
        mav.addObject("genderlist", gender);
        mav.addObject("prospect", editProspect);
        return mav;
    } 
}
