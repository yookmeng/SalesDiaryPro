package com.SpringMVC.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
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
import com.SpringMVC.dao.CompanyDAO;
import com.SpringMVC.dao.NotesDAO;
import com.SpringMVC.dao.ProspectDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.dao.UserProfileDAO;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.Company;
import com.SpringMVC.model.Notes;
import com.SpringMVC.model.Prospect;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.model.UserProfile;
import com.SpringMVC.uriconstant.NotesRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class NotesController {

    @Autowired
    private UserProfileDAO userProfileDAO;

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private ProspectDAO prospectDAO;

    @Autowired
    private NotesDAO notesDAO;

    private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }
    
    @RequestMapping(value = NotesRestURIConstant.Get, method = RequestMethod.GET)
	public String getNotes(@PathVariable int noteid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(notesDAO.get(noteid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = NotesRestURIConstant.GetByMember, method = RequestMethod.GET)
	public String getNotesByMember(@PathVariable int userid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(notesDAO.list(userid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = NotesRestURIConstant.GetByBranch, method = RequestMethod.GET)
	public String getNotesByBranch(@PathVariable int branchid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(notesDAO.listByBranch(branchid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = NotesRestURIConstant.GetByCompany, method = RequestMethod.GET)
	public String getNotesByCompany(@PathVariable int companyid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(notesDAO.listByCompany(companyid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = NotesRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Notes> createNotes(@RequestBody Notes notes) throws IOException {
    	notesDAO.save(notes);
        return new ResponseEntity<Notes>(notes, HttpStatus.CREATED);
    }

    @RequestMapping(value = NotesRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Notes> updateNotes(@PathVariable("noteid") int noteid, 
    		@RequestBody Notes notes) {
    	Notes currentNotes = notesDAO.get(noteid);
         
        if (currentNotes==null) {
            return new ResponseEntity<Notes>(HttpStatus.NOT_FOUND);
        }
        currentNotes.setnotedate(notes.getnotedate());
        currentNotes.setnote(notes.getnote());
        currentNotes.setstatus(notes.getstatus());
        currentNotes.setremark(notes.getremark());
        currentNotes.setreviewby(notes.getreviewby());

        notesDAO.update(currentNotes);
        return new ResponseEntity<Notes>(notes, HttpStatus.OK);
    }

    @RequestMapping(value = NotesRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Notes> deleteNotes(@PathVariable("noteid") int noteid) {
    	Notes notes = notesDAO.get(noteid);
        if (notes == null) {
            return new ResponseEntity<Notes>(HttpStatus.NOT_FOUND);
        }
        notesDAO.delete(noteid);
        return new ResponseEntity<Notes>(HttpStatus.OK);
    }

    @RequestMapping(value="/listNote", method = RequestMethod.GET)
    public ModelAndView listNote(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        Prospect prospect = prospectDAO.get(prospectid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
 	    List<Notes> listNotes= notesDAO.list(prospectid);
        ModelAndView mav = new ModelAndView("noteList");
		mav.addObject("role", userLogin.getrole());
        mav.addObject("prospect", prospect);
        mav.addObject("listNotes", listNotes);
 	    return mav;
 	}
    
    @RequestMapping(value="/listNotes", method = RequestMethod.GET)
    public ModelAndView listNotes(HttpServletRequest request) {
        int companyid = userLoginDAO.getCompanyID(request.getUserPrincipal().getName());
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("notesList");
        mav.addObject("role", userLogin.getrole());
        Roles role = Roles.valueOf(userLogin.getrole()); 
        switch (role){
        case MD:
            mav.addObject("listNotes", notesDAO.listByCompany(companyid));
            break;
        case MA:
            Branch branch = branchDAO.getByMA(userLogin.getuserid());
            mav.addObject("listNotes", notesDAO.listByBranch(branch.getbranchid()));
            break;
        case TL:
            Team team = teamDAO.getByUser(userLogin.getuserid());
            mav.addObject("listNotes", notesDAO.listByTeam(team.getteamid()));
            break;
        default:
            mav.addObject("listNotes", notesDAO.list(userLogin.getuserid()));
        }
 	    return mav;
 	}

    @RequestMapping(value = "/addNotes", method = RequestMethod.GET)
    public ModelAndView addReview(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        UserLogin userLogin = userLoginDAO.findUserLogin(request.getUserPrincipal().getName());
        UserProfile userProfile = userProfileDAO.get(request.getUserPrincipal().getName());
        Team team = teamDAO.get(userProfile.getteamid());
        Branch branch = branchDAO.get(team.getbranchid());
        Company company = companyDAO.get(branch.getcompanyid());
        Notes newNotes = new Notes();
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        newNotes.setnotedate(date);
        newNotes.setuserid(userLogin.getuserid());
        newNotes.setusername(userLogin.getusername());
        newNotes.setteamid(team.getteamid());
        newNotes.setteamname(team.getteamname());
        newNotes.setbranchid(branch.getbranchid());
        newNotes.setbranchname(branch.getbranchname());
        newNotes.setcompanyid(company.getcompanyid());
        newNotes.setcompanyname(company.getcompanyname());
        newNotes.setprospectid(prospectid);
        newNotes.setstatus(0);
        newNotes.setreviewby(userLogin.getuserid());  
        ModelAndView mav = new ModelAndView("notesForm");
        mav.addObject("role", userLogin.getrole());
        mav.addObject("notes", newNotes);
        return mav;
    }

    @RequestMapping(value = "/editNotes", method = RequestMethod.GET)
    public ModelAndView editNotes(HttpServletRequest request) {
        int noteid = Integer.parseInt(request.getParameter("noteid")); 
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Notes editNotes = notesDAO.get(noteid);
        ModelAndView mav = new ModelAndView("notesForm");
        mav.addObject("role", userLogin.getrole());
        mav.addObject("notes", editNotes);
        return mav;
    }    
}
