package com.SpringMVC.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

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
import com.SpringMVC.dao.ReviewDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.dao.UserMonthlySummaryDAO;
import com.SpringMVC.dao.UserProfileDAO;
import com.SpringMVC.dao.UserTargetDAO;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.Company;
import com.SpringMVC.model.Review;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.model.UserMonthlySummary;
import com.SpringMVC.model.UserProfile;
import com.SpringMVC.model.UserTarget;
import com.SpringMVC.uriconstant.ReviewRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class ReviewController {

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
    private UserTargetDAO userTargetDAO;

    @Autowired
    private UserMonthlySummaryDAO userMonthlySummaryDAO;

    @Autowired
    private ReviewDAO reviewDAO;

    private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }
    
    @RequestMapping(value = ReviewRestURIConstant.Get, method = RequestMethod.GET)
	public String getReview(@PathVariable int reviewid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(reviewDAO.get(reviewid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ReviewRestURIConstant.GetByMember, method = RequestMethod.GET)
	public String getReviewByMember(@PathVariable int userid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(reviewDAO.list(userid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ReviewRestURIConstant.GetByBranch, method = RequestMethod.GET)
	public String getReviewByBranch(@PathVariable int branchid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(reviewDAO.listByBranch(branchid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ReviewRestURIConstant.GetByCompany, method = RequestMethod.GET)
	public String getReviewByCompany(@PathVariable int companyid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(reviewDAO.listByCompany(companyid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ReviewRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Review> createReview(@RequestBody Review review) throws IOException {
    	reviewDAO.save(review);
        return new ResponseEntity<Review>(review, HttpStatus.CREATED);
    }

    @RequestMapping(value = ReviewRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Review> updateReview(@PathVariable("reviewid") int reviewid, @RequestBody Review review) {
    	Review currentReview = reviewDAO.get(reviewid);
         
        if (currentReview==null) {
            return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
        }
        currentReview.setreviewdate(review.getreviewdate());
        currentReview.setprospect(review.getprospect());
        currentReview.settestdrive(review.gettestdrive());
        currentReview.setclosed(review.getclosed());
        currentReview.setminute(review.getminute());
        currentReview.setreviewby(review.getreviewby());

        reviewDAO.update(currentReview);
        return new ResponseEntity<Review>(review, HttpStatus.OK);
    }

    @RequestMapping(value = ReviewRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Review> deleteReview(@PathVariable("reviewid") int reviewid) {
    	Review review = reviewDAO.get(reviewid);
        if (review == null) {
            return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
        }
        reviewDAO.delete(reviewid);
        return new ResponseEntity<Review>(HttpStatus.OK);
    }

    @RequestMapping(value="/listReview", method = RequestMethod.GET)
    public ModelAndView listReview(HttpServletRequest request) {
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("reviewList");
        mav.addObject("role", userLogin.getrole());
        Roles role = Roles.valueOf(userLogin.getrole()); 
        switch (role){
        case MD:
            int companyid = userLoginDAO.getCompanyID(request.getUserPrincipal().getName());
            mav.addObject("listReview", reviewDAO.listByCompany(companyid));
            break;
        case MA:
            Branch branch = branchDAO.getByMA(userLogin.getuserid());
            mav.addObject("listReview", reviewDAO.listByBranch(branch.getbranchid()));
            break;
        case TL:
            Team team = teamDAO.getByUser(userLogin.getuserid());
            mav.addObject("listReview", reviewDAO.listByTeam(team.getteamid()));
            break;
        default:
            mav.addObject("listReview", reviewDAO.list(userLogin.getuserid()));
        }
 	    return mav;
 	}

    @RequestMapping(value = "/addReview", method = RequestMethod.GET)
    public ModelAndView addReview(HttpServletRequest request) {
        int userid = Integer.parseInt(request.getParameter("userid"));
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        UserProfile userProfile = userProfileDAO.findUser(userid);
        UserTarget userTarget = userTargetDAO.get(targetid);
        Team team = teamDAO.get(userProfile.getteamid());
        Branch branch = branchDAO.get(team.getbranchid());
        Company company = companyDAO.get(branch.getcompanyid());
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        UserMonthlySummary userMonthlySummary = userMonthlySummaryDAO.get(userTarget.getperiod(), userid, userProfile.getrole()); 
        Review newReview = new Review();
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        newReview.setreviewdate(date);
        newReview.setperiod(userTarget.getperiod());
        newReview.setuserid(userid);
        newReview.setusername(userProfile.getusername());
        newReview.settargetid(userTarget.gettargetid());
        newReview.setteamtargetid(userTarget.getteamtargetid());
        newReview.setteamid(team.getteamid());
        newReview.setteamname(team.getteamname());
        newReview.setbranchid(branch.getbranchid());
        newReview.setbranchname(branch.getbranchname());
        newReview.setcompanyid(company.getcompanyid());
        newReview.setcompanyname(company.getcompanyname());
        newReview.setprospect(userMonthlySummary.getactualprospect());
        newReview.settestdrive(userMonthlySummary.getactualtestdrive());
        newReview.setclosed(userMonthlySummary.getactualclosed());
        newReview.setreviewby(userLogin.getuserid());  
        ModelAndView mav = new ModelAndView("reviewForm");
        mav.addObject("role", userLogin.getrole());
        mav.addObject("userTarget", userTarget);
        mav.addObject("review", newReview);
        return mav;
    }

    @RequestMapping(value = "/editReview", method = RequestMethod.GET)
    public ModelAndView editReview(HttpServletRequest request) {
        int reviewid = Integer.parseInt(request.getParameter("reviewid")); 
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Review editReview = reviewDAO.get(reviewid);
        UserTarget userTarget = userTargetDAO.get(editReview.gettargetid());
        ModelAndView mav = new ModelAndView("reviewForm");
        mav.addObject("role", userLogin.getrole());        
        mav.addObject("userTarget", userTarget);
        mav.addObject("review", editReview);
        return mav;
    }    
}
