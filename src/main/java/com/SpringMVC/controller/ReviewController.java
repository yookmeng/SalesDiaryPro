package com.SpringMVC.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.SpringMVC.dao.ReviewDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.TeamTargetDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.dao.UserMonthlySummaryDAO;
import com.SpringMVC.dao.UserProfileDAO;
import com.SpringMVC.dao.UserTargetDAO;
import com.SpringMVC.model.Review;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.TeamTarget;
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
    private TeamTargetDAO teamTargetDAO;

    @Autowired
    private UserTargetDAO userTargetDAO;

    @Autowired
    private UserMonthlySummaryDAO userMonthlySummaryDAO;

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private ReviewDAO reviewDAO;

    @RequestMapping(value = ReviewRestURIConstant.Get, method = RequestMethod.GET)
	public String getTeam(@PathVariable int reviewid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(reviewDAO.get(reviewid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ReviewRestURIConstant.GetAll, method = RequestMethod.GET)
	public String getAllTeam(@PathVariable int userid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(reviewDAO.list(userid));
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
        int userid = Integer.parseInt(request.getParameter("userid"));
        UserProfile userProfile = userProfileDAO.findUser(userid);
 	    List<Review> listReview = reviewDAO.list(userid);
 	   	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
 	   	Calendar c = Calendar.getInstance(); 
 	    String period = dateFormat.format(c.getTime());
		UserMonthlySummary userMonthlySummary = userMonthlySummaryDAO.get(period, userLogin.getuserid());
        ModelAndView mav = new ModelAndView("reviewList");
		mav.addObject("userMonthlySummary", userMonthlySummary);
        mav.addObject("role", userLogin.getrole());
        mav.addObject("userProfile", userProfile);
        mav.addObject("listReview", listReview);
 	    return mav;
 	}

    @RequestMapping(value = "/addReview", method = RequestMethod.GET)
    public ModelAndView addReview(HttpServletRequest request) {
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        int userid = Integer.parseInt(request.getParameter("userid"));
        Team team = teamDAO.getByUser(userid);
 	   	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
 	   	Calendar c = Calendar.getInstance(); 
 	    c.set(Calendar.DAY_OF_MONTH, 1);
 	    String period = dateFormat.format(c.getTime());
		TeamTarget teamTarget = teamTargetDAO.getByPeriod(period, team.getteamid());
 	    UserTarget userTarget = userTargetDAO.getByPeriod(period, userid);
        Review newReview = new Review();
        newReview.setuserid(userid);        
        newReview.settargetid(userTarget.gettargetid());
        newReview.setteamtargetid(teamTarget.gettargetid());
        newReview.setreviewby(userLogin.getuserid());                
        ModelAndView mav = new ModelAndView("reviewForm");
        mav.addObject("teamTarget", teamTarget);
        mav.addObject("userTarget", userTarget);
        mav.addObject("review", newReview);
        return mav;
    }

    @RequestMapping(value = "/editReview", method = RequestMethod.GET)
    public ModelAndView editReview(HttpServletRequest request) {
        int reviewid = Integer.parseInt(request.getParameter("reviewid")); 
        Review editReview = reviewDAO.get(reviewid);
        ModelAndView mav = new ModelAndView("reviewForm");
 	    List<UserTarget> userTarget = userTargetDAO.listByUser(editReview.getuserid());
        mav.addObject("userTarget", userTarget);        
        mav.addObject("userid", editReview.getuserid());
        mav.addObject("review", editReview);
        return mav;
    }    
}
