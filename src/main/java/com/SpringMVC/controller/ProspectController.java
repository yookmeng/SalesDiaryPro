package com.SpringMVC.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.Time;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.SpringMVC.dao.QuotationDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.APIProspect;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.Brand;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.Model;
import com.SpringMVC.model.Prospect;
import com.SpringMVC.model.Questionaire;
import com.SpringMVC.model.Quotation;
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
    private QuotationDAO quotationDAO;

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
			Roles role = Roles.valueOf(userLogin.getrole()); 
			switch (role){
				case USER:
					jsonInString = mapper.writeValueAsString(prospectDAO.list(userLogin.getuserid()));
					break;    	   
				case TL:
					Team team = teamDAO.getByUser(userLogin.getuserid());
					jsonInString = mapper.writeValueAsString(prospectDAO.listByTeam(team.getteamid()));
					break;    	   
				case MA:
					Branch branch = branchDAO.getByMA(userLogin.getuserid());
					jsonInString = mapper.writeValueAsString(prospectDAO.listByBranch(branch.getbranchid()));
					break;    	   
				case MD:
					jsonInString = mapper.writeValueAsString(prospectDAO.listByCompany(userLogin.getcompanyid()));
					break;
				default:
					break;	
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ProspectRestURIConstant.Add, method = RequestMethod.POST)
    public ResponseEntity<Questionaire> addProspect(@RequestBody APIProspect aPIProspect, 
    		HttpServletRequest request) throws IOException, ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
    	Date convertDate = new Date();
    	Date convertTime = new Date();
    	String stringDate = "";
    	
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
    	questionaire.setstatus(aPIProspect.getstatus());

    	questionaire.setdemo(aPIProspect.getdemo()); 
    	if (aPIProspect.getdemo()){
        	convertDate = sdf.parse(aPIProspect.getdemodate());
        	stringDate = sdf.format(convertDate);
        	convertTime = stf.parse(aPIProspect.getdemodate());
        	Time demoTime = new Time(convertTime.getTime());
        	questionaire.setdemodate(java.sql.Date.valueOf(stringDate));
        	questionaire.setdemotime(demoTime);    		
    	};

    	questionaire.settestdrive(aPIProspect.gettestdrive());
    	if (aPIProspect.gettestdrive()){
        	convertDate = sdf.parse(aPIProspect.gettestdrivedate());
        	stringDate = sdf.format(convertDate);
        	convertTime = stf.parse(aPIProspect.gettestdrivedate());    	
        	Time testDriveTime = new Time(convertTime.getTime());
        	questionaire.settestdrivedate(java.sql.Date.valueOf(stringDate));
        	questionaire.settestdrivetime(testDriveTime);    		
    	};

    	questionaire.setquotation(aPIProspect.getquotation());
    	if (aPIProspect.getquotation()) {
        	convertDate = sdf.parse(aPIProspect.getquotationdate());
        	stringDate = sdf.format(convertDate);
        	convertTime = stf.parse(aPIProspect.getquotationdate());    	
        	Time quotationTime = new Time(convertTime.getTime());
        	questionaire.setquotationdate(java.sql.Date.valueOf(stringDate));
        	questionaire.setquotationtime(quotationTime);    		
    	};

    	if (aPIProspect.getstatus()=="4"){
        	convertDate = sdf.parse(aPIProspect.getstatusdate());
        	stringDate = sdf.format(convertDate);
        	convertTime = stf.parse(aPIProspect.getstatusdate());    	
        	Time statusTime = new Time(convertTime.getTime());
        	questionaire.setstatusdate(java.sql.Date.valueOf(stringDate));
        	questionaire.setstatustime(statusTime);    		
    	};
    	
    	questionaireDAO.save(questionaire);
    	
    	int prospectid = prospectDAO.getlastprospectid(userLogin.getuserid());
        int quotationid = quotationDAO.getlastquotationid(prospectid);
        Quotation newQuotation = quotationDAO.get(quotationid);
    	quotationDAO.createpdf(newQuotation, request);
   	
        return new ResponseEntity<Questionaire>(questionaire, HttpStatus.CREATED);
    }

    @RequestMapping(value = ProspectRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Prospect> createProspect(@RequestBody Prospect prospect) throws IOException {
        prospectDAO.save(prospect);
        return new ResponseEntity<Prospect>(prospect, HttpStatus.CREATED);
    }

    @RequestMapping(value = ProspectRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Prospect> updateProspect(@RequestBody Prospect prospect) {
    	Prospect currentProspect = prospectDAO.get(prospect.getprospectid());
         
        if (currentProspect==null) {
            return new ResponseEntity<Prospect>(prospect, HttpStatus.NOT_FOUND);
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
        currentProspect.setgender(prospect.getgender());
        currentProspect.setemail(prospect.getemail());
        currentProspect.setstatus(prospect.getstatus());

        prospectDAO.update(currentProspect);
        return new ResponseEntity<Prospect>(prospect, HttpStatus.OK);
    }

    @RequestMapping(value = ProspectRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Prospect> deleteProspect(@RequestBody Prospect prospect) {
        if (prospect == null) {
            return new ResponseEntity<Prospect>(prospect, HttpStatus.NOT_FOUND);
        }
 
        prospectDAO.delete(prospect.getprospectid());
        return new ResponseEntity<Prospect>(prospect, HttpStatus.OK);
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
        List<String> sources = codeMasterDAO.getType("SOURCE");	
        mav.addObject("sourcelist", sources);
        List<String> status = codeMasterDAO.getType("STATUS");	
        mav.addObject("statuslist", status);
        List<String> gender = codeMasterDAO.getType("GENDER");	
        mav.addObject("genderlist", gender);
        mav.addObject("prospect", newProspect);
        return mav;
    }

    @RequestMapping(value = "/editProspect", method = RequestMethod.GET)
    public ModelAndView editProspect(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid")); 
        Prospect editProspect = prospectDAO.get(prospectid);
        ModelAndView mav = new ModelAndView("prospectForm");
        List<String> sources = codeMasterDAO.getType("SOURCE");
        mav.addObject("sourcelist", sources);
        List<String> status = codeMasterDAO.getType("STATUS");	
        mav.addObject("statuslist", status);        
        List<String> gender = codeMasterDAO.getType("GENDER");	
        mav.addObject("genderlist", gender);
        mav.addObject("prospect", editProspect);
        return mav;
    } 
}
