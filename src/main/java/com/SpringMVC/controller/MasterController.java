package com.SpringMVC.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.SpringMVC.dao.CodeMasterDAO;
import com.SpringMVC.dao.CompanyDAO;
import com.SpringMVC.dao.BrandDAO;
import com.SpringMVC.dao.ModelDAO;
import com.SpringMVC.dao.BranchDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.UserProfileDAO;
import com.SpringMVC.dao.ProspectDAO;
import com.SpringMVC.dao.SurveyDAO;
import com.SpringMVC.dao.RequestDAO;
import com.SpringMVC.dao.ScheduleDAO;

import com.SpringMVC.model.Company;
import com.SpringMVC.model.Brand;
import com.SpringMVC.model.Model;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.UserProfile;
import com.SpringMVC.model.Prospect;
import com.SpringMVC.model.Survey;
import com.SpringMVC.model.Request;
import com.SpringMVC.model.Schedule;

@Controller
public class MasterController {
    @Autowired
    private CodeMasterDAO codeMasterDAO;

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private BrandDAO brandDAO;

    @Autowired
    private ModelDAO modelDAO;

    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private UserProfileDAO userProfileDAO;

    @Autowired
    private ProspectDAO prospectDAO;

    @Autowired
    private SurveyDAO surveyDAO;

    @Autowired
    private RequestDAO requestDAO;

    @Autowired
    private ScheduleDAO scheduleDAO;

    @RequestMapping(value="/listCompany")
    public ModelAndView listCompany(ModelAndView mav) throws IOException{
 	    List<Company> listCompany = companyDAO.list();
 	    mav.addObject("listCompany", listCompany);
 	    mav.setViewName("companyList");	 	    
 	    return mav;
 	}
 	   
    @RequestMapping(value = "/addCompany", method = RequestMethod.GET)
    public ModelAndView addCompany(ModelAndView mav) {
        Company newCompany = new Company();
        mav.addObject("company", newCompany);
        mav.setViewName("companyForm");
        return mav;
    }
    
    @RequestMapping(value = "/saveCompany", method = RequestMethod.POST)
    public ModelAndView saveCompany(@ModelAttribute Company company) {
        companyDAO.saveOrUpdate(company);
        return new ModelAndView("redirect:/listCompany");
    }
    
    @RequestMapping(value = "/deleteCompany", method = RequestMethod.GET)
    public ModelAndView deleteCompany(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        companyDAO.delete(companyid);
        return new ModelAndView("redirect:/listCompany");
    }
    
    @RequestMapping(value = "/editCompany", method = RequestMethod.GET)
    public ModelAndView editCompany(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        Company editCompany= companyDAO.get(companyid);
        ModelAndView mav = new ModelAndView("companyForm");
        mav.addObject("company", editCompany);
        return mav;
    }
    
    @RequestMapping(value="/listBrand", method = RequestMethod.GET)
    public ModelAndView listBrand(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
 	    List<Brand> listBrand = brandDAO.list(companyid);
        ModelAndView mav = new ModelAndView("brandList");
        mav.addObject("companyid", companyid);
        mav.addObject("listBrand", listBrand);
 	    return mav;
 	}

    @RequestMapping(value = "/addBrand", method = RequestMethod.GET)
    public ModelAndView addBrand(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        Brand newBrand = new Brand();
        newBrand.setcompanyid(companyid);
        ModelAndView mav = new ModelAndView("brandForm");
        mav.addObject("brand", newBrand);
        return mav;
    }

    @RequestMapping(value = "/saveBrand", method = RequestMethod.POST)
    public ModelAndView saveBrand(@ModelAttribute Brand brand) {
        brandDAO.saveOrUpdate(brand);
        return new ModelAndView("redirect:/listBrand?companyid="+brand.getcompanyid());
    }

    @RequestMapping(value = "/deleteBrand", method = RequestMethod.GET)
    public ModelAndView deleteBrand(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        int brandid = Integer.parseInt(request.getParameter("brandid"));        
        brandDAO.delete(brandid);
        return new ModelAndView("redirect:/listBrand?companyid="+companyid);
    }

    @RequestMapping(value = "/editBrand", method = RequestMethod.GET)
    public ModelAndView editBrand(HttpServletRequest request) {
        int brandid = Integer.parseInt(request.getParameter("brandid"));        
        Brand editBrand = brandDAO.get(brandid);
        ModelAndView mav = new ModelAndView("brandForm");
        mav.addObject("brand", editBrand);
        return mav;
    }   
    
    @RequestMapping(value="/listModel", method = RequestMethod.GET)
    public ModelAndView listModel(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        int brandid = Integer.parseInt(request.getParameter("brandid"));
 	    List<Model> listModel = modelDAO.list(companyid, brandid);
        ModelAndView mav = new ModelAndView("modelList");
        mav.addObject("companyid", companyid);
        mav.addObject("brandid", brandid);
 	    mav.addObject("listModel", listModel);
 	    return mav;
 	}

    @RequestMapping(value = "/addModel", method = RequestMethod.GET)
    public ModelAndView addModel(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        int brandid = Integer.parseInt(request.getParameter("brandid"));
        Model newModel = new Model();
        newModel.setcompanyid(companyid);
        newModel.setbrandid(brandid);
        ModelAndView mav = new ModelAndView("modelForm");
        mav.addObject("model", newModel);
        return mav;
    }

    @RequestMapping(value = "/saveModel", method = RequestMethod.POST)
    public ModelAndView saveModel(@ModelAttribute Model model) {
        modelDAO.saveOrUpdate(model);
        return new ModelAndView("redirect:/listModel?companyid="+model.getcompanyid()+"&brandid="+model.getbrandid());
    }

    @RequestMapping(value = "/deleteModel", method = RequestMethod.GET)
    public ModelAndView deleteModel(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        int brandid = Integer.parseInt(request.getParameter("brandid"));
        int modelid = Integer.parseInt(request.getParameter("modelid"));        
        modelDAO.delete(modelid);
        return new ModelAndView("redirect:/listModel?companyid="+companyid+"&brandid="+brandid);
    }

    @RequestMapping(value = "/editModel", method = RequestMethod.GET)
    public ModelAndView editModel(HttpServletRequest request) {
        int modelid = Integer.parseInt(request.getParameter("modelid"));        
        Model editModel = modelDAO.get(modelid);
        ModelAndView mav = new ModelAndView("modelForm");
        mav.addObject("model", editModel);
        return mav;
    }   

    @RequestMapping(value="/listBranch", method = RequestMethod.GET)
    public ModelAndView listBranch(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
 	    List<Branch> listBranch = branchDAO.list(companyid);
        ModelAndView mav = new ModelAndView("branchList");
        mav.addObject("companyid", companyid);
        mav.addObject("listBranch", listBranch);
 	    return mav;
 	}
 	   
    @RequestMapping(value = "/addBranch", method = RequestMethod.GET)
    public ModelAndView addBranch(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        Branch newBranch = new Branch();
        newBranch.setcompanyid(companyid);
        ModelAndView mav = new ModelAndView("branchForm");
        mav.addObject("branch", newBranch);
        return mav;
    }

    @RequestMapping(value = "/saveBranch", method = RequestMethod.POST)
    public ModelAndView saveBranch(@ModelAttribute Branch branch) {
        branchDAO.saveOrUpdate(branch);
        return new ModelAndView("redirect:/listBranch?companyid="+branch.getcompanyid());
    }

    @RequestMapping(value = "/deleteBranch", method = RequestMethod.GET)
    public ModelAndView deleteBranch(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        int branchid = Integer.parseInt(request.getParameter("branchid"));        
        branchDAO.delete(branchid);
        return new ModelAndView("redirect:/listBranch?companyid="+companyid);
    }

    @RequestMapping(value = "/editBranch", method = RequestMethod.GET)
    public ModelAndView editBranch(HttpServletRequest request) {
        int branchid = Integer.parseInt(request.getParameter("branchid"));        
        Branch editBranch = branchDAO.get(branchid);
        ModelAndView mav = new ModelAndView("branchForm");
        mav.addObject("branch", editBranch);
        return mav;
    }    

    @RequestMapping(value="/listTeam", method = RequestMethod.GET)
    public ModelAndView listTeam(HttpServletRequest request) {
        int branchid = Integer.parseInt(request.getParameter("branchid"));
 	    List<Team> listTeam = teamDAO.list(branchid);
        ModelAndView mav = new ModelAndView("teamList");
        mav.addObject("branchid", branchid);
        mav.addObject("listTeam", listTeam);
 	    return mav;
 	}

    @RequestMapping(value = "/addTeam", method = RequestMethod.GET)
    public ModelAndView addTeam(HttpServletRequest request) {
        int branchid = Integer.parseInt(request.getParameter("branchid"));
        Team newTeam = new Team();
        newTeam.setbranchid(branchid);
        ModelAndView mav = new ModelAndView("teamForm");
        mav.addObject("team", newTeam);
        return mav;
    }

    @RequestMapping(value = "/saveTeam", method = RequestMethod.POST)
    public ModelAndView saveTeam(@ModelAttribute Team team) {
        teamDAO.saveOrUpdate(team);
        return new ModelAndView("redirect:/listTeam?branchid="+team.getbranchid());
    }

    @RequestMapping(value = "/deleteTeam", method = RequestMethod.GET)
    public ModelAndView deleteTeam(HttpServletRequest request) {
        int branchid = Integer.parseInt(request.getParameter("branchid"));
        int teamid = Integer.parseInt(request.getParameter("teamid"));        
        teamDAO.delete(teamid);
        return new ModelAndView("redirect:/listTeam?branchid="+branchid);
    }

    @RequestMapping(value = "/editTeam", method = RequestMethod.GET)
    public ModelAndView editTeam(HttpServletRequest request) {
        int teamid = Integer.parseInt(request.getParameter("teamid"));        
        Team editTeam = teamDAO.get(teamid);
        ModelAndView mav = new ModelAndView("teamForm");
        mav.addObject("team", editTeam);
        return mav;
    }    

    @RequestMapping(value="/listMember", method = RequestMethod.GET)
    public ModelAndView listMember(HttpServletRequest request) {
        int teamid = Integer.parseInt(request.getParameter("teamid"));
 	    List<UserProfile> userProfile = userProfileDAO.list(teamid);
        ModelAndView mav = new ModelAndView("userProfileList");
        mav.addObject("teamid", teamid);
        mav.addObject("userProfile", userProfile);
 	    return mav;
 	}

    @RequestMapping(value = "/addMember", method = RequestMethod.GET)
    public ModelAndView addMember(HttpServletRequest request) {
        int teamid = Integer.parseInt(request.getParameter("teamid"));
        UserProfile newUserProfile = new UserProfile();
        newUserProfile.setteamid(teamid);
        newUserProfile.setrole("USER");        
        ModelAndView mav = new ModelAndView("userProfileForm");
        mav.addObject("userProfile", newUserProfile);
        return mav;
    }

    @RequestMapping(value = "/saveMember", method = RequestMethod.POST)
    public ModelAndView saveMember(@ModelAttribute UserProfile userProfile) {
        userProfileDAO.saveOrUpdate(userProfile);
        return new ModelAndView("redirect:/listMember?teamid="+userProfile.getteamid());
    }

    @RequestMapping(value = "/deleteMember", method = RequestMethod.GET)
    public ModelAndView deleteMember(HttpServletRequest request) {
        int teamid = Integer.parseInt(request.getParameter("teamid"));
        String username = request.getParameter("username");
        userProfileDAO.delete(username);
        return new ModelAndView("redirect:/listMember?teamid="+teamid);
    }

    @RequestMapping(value = "/editMember", method = RequestMethod.GET)
    public ModelAndView editMember(HttpServletRequest request) {
        String username = request.getParameter("username"); 
        UserProfile userProfile = userProfileDAO.get(username);
        ModelAndView mav = new ModelAndView("userProfileForm");
        mav.addObject("userProfile", userProfile);
        return mav;
    }    

    @RequestMapping(value="/listProspect", method = RequestMethod.GET)
    public ModelAndView listProspect(HttpServletRequest request) {
        int userid = Integer.parseInt(request.getParameter("userid"));
 	    List<Prospect> listProspect = prospectDAO.list(userid);
        ModelAndView mav = new ModelAndView("prospectList");
        mav.addObject("userid", userid);
        mav.addObject("listProspect", listProspect);
 	    return mav;
 	}

    @RequestMapping(value = "/addProspect", method = RequestMethod.GET)
    public ModelAndView addProspect(HttpServletRequest request) {
    	String codetype = "SOURCE";
        int userid = Integer.parseInt(request.getParameter("userid"));
        Prospect newProspect = new Prospect();
        newProspect.setuserid(userid);
        ModelAndView mav = new ModelAndView("prospectForm");
        List<String> codes= codeMasterDAO.getCode(codetype);	
        mav.addObject("sourcelist", codes);
        mav.addObject("prospect", newProspect);
        return mav;
    }

    @RequestMapping(value = "/saveProspect", method = RequestMethod.POST)
    public ModelAndView saveProspect(@ModelAttribute Prospect prospect) {
        prospectDAO.saveOrUpdate(prospect);
        return new ModelAndView("redirect:/listProspect?userid="+prospect.getuserid());
    }

    @RequestMapping(value = "/deleteProspect", method = RequestMethod.GET)
    public ModelAndView deleteProspect(HttpServletRequest request) {
        int userid = Integer.parseInt(request.getParameter("userid"));
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));        
        prospectDAO.delete(prospectid);
        return new ModelAndView("redirect:/listProspect?userid="+userid);
    }

    @RequestMapping(value = "/editProspect", method = RequestMethod.GET)
    public ModelAndView editProspect(HttpServletRequest request) {
    	String codetype = "SOURCE";
        int prospectid = Integer.parseInt(request.getParameter("prospectid")); 
        Prospect editProspect = prospectDAO.get(prospectid);
        ModelAndView mav = new ModelAndView("prospectForm");
        List<String> codes= codeMasterDAO.getCode(codetype);
        mav.addObject("sourcelist", codes);
        mav.addObject("prospect", editProspect);
        return mav;
    } 

    @RequestMapping(value="/listSurvey", method = RequestMethod.GET)
    public ModelAndView listSurvey(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
 	    List<Survey> listSurvey = surveyDAO.list(prospectid);
        ModelAndView mav = new ModelAndView("surveyList");
        mav.addObject("prospectid", prospectid);
        mav.addObject("listSurvey", listSurvey);
 	    return mav;
 	}

    @RequestMapping(value = "/addSurvey", method = RequestMethod.GET)
    public ModelAndView addSurvey(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        Survey newSurvey = new Survey();
        newSurvey.setprospectid(prospectid);
        ModelAndView mav = new ModelAndView("surveyForm");
        mav.addObject("survey", newSurvey);
        return mav;
    }

    @RequestMapping(value = "/saveSurvey", method = RequestMethod.POST)
    public ModelAndView saveSurvey(@ModelAttribute Survey survey) {
        surveyDAO.saveOrUpdate(survey);
        return new ModelAndView("redirect:/listSurvey?prospectid="+survey.getprospectid());
    }

    @RequestMapping(value = "/deleteSurvey", method = RequestMethod.GET)
    public ModelAndView deleteSurvey(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        int surveyid = Integer.parseInt(request.getParameter("surveyid"));        
        surveyDAO.delete(surveyid);
        return new ModelAndView("redirect:/listSurvey?prospectid="+prospectid);
    }

    @RequestMapping(value = "/editSurvey", method = RequestMethod.GET)
    public ModelAndView editSurvey(HttpServletRequest request) {
        int surveyid = Integer.parseInt(request.getParameter("surveyid")); 
        Survey editSurvey = surveyDAO.get(surveyid);
        ModelAndView mav = new ModelAndView("surveyForm");
        mav.addObject("survey", editSurvey);
        return mav;
    } 

    @RequestMapping(value="/listRequest", method = RequestMethod.GET)
    public ModelAndView listRequest(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
 	    List<Request> listRequest = requestDAO.list(prospectid);
        ModelAndView mav = new ModelAndView("requestList");
        mav.addObject("prospectid", prospectid);
        mav.addObject("listRequest", listRequest);
 	    return mav;
 	}

    @RequestMapping(value = "/addRequest", method = RequestMethod.GET)
    public ModelAndView addRequest(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        String status = "Hot";
        Request newRequest = new Request();
        newRequest.setprospectid(prospectid);
        newRequest.setstatus(status);
        ModelAndView mav = new ModelAndView("requestForm");
        List<String> brands = brandDAO.getBrands(1);	
        mav.addObject("brandlist", brands);
        List<String> models = modelDAO.getModels(1, 1);	
        mav.addObject("modellist", models);
        mav.addObject("request", newRequest);
        return mav;
    }

    @RequestMapping(value = "/saveRequest", method = RequestMethod.POST)
    public ModelAndView saveRequest(@ModelAttribute Request request) {
    	requestDAO.saveOrUpdate(request);
        return new ModelAndView("redirect:/listRequest?prospectid="+request.getprospectid());
    }

    @RequestMapping(value = "/deleteRequest", method = RequestMethod.GET)
    public ModelAndView deleteRequest(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        int requestid = Integer.parseInt(request.getParameter("requestid"));        
        requestDAO.delete(requestid);
        return new ModelAndView("redirect:/listRequest?prospectid="+prospectid);
    }

    @RequestMapping(value = "/editRequest", method = RequestMethod.GET)
    public ModelAndView editRequest(HttpServletRequest request) {
        int requestid = Integer.parseInt(request.getParameter("requestid")); 
        Request editRequest = requestDAO.get(requestid);
        ModelAndView mav = new ModelAndView("requestForm");
        mav.addObject("request", editRequest);
        return mav;
    } 

    @RequestMapping(value="/listSchedule", method = RequestMethod.GET)
    public ModelAndView listSchedule(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
 	    List<Schedule> listSchedule = scheduleDAO.list(prospectid);
        ModelAndView mav = new ModelAndView("scheduleList");
        mav.addObject("prospectid", prospectid);
        mav.addObject("listSchedule", listSchedule);
 	    return mav;
 	}

    @RequestMapping(value = "/addSchedule", method = RequestMethod.GET)
    public ModelAndView addSchedule(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        Schedule newSchedule = new Schedule();
        newSchedule.setprospectid(prospectid);
        ModelAndView mav = new ModelAndView("scheduleForm");
        mav.addObject("schedule", newSchedule);
        return mav;
    }

    @RequestMapping(value = "/saveSchedule", method = RequestMethod.POST)
    public ModelAndView saveSchedule(@ModelAttribute Schedule schedule) {
        scheduleDAO.saveOrUpdate(schedule);
        return new ModelAndView("redirect:/listSchedule?prospectid="+schedule.getprospectid());
    }

    @RequestMapping(value = "/deleteSchedule", method = RequestMethod.GET)
    public ModelAndView deleteSchedule(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        int scheduleid = Integer.parseInt(request.getParameter("scheduleid"));        
        scheduleDAO.delete(scheduleid);
        return new ModelAndView("redirect:/listSchedule?prospectid="+prospectid);
    }

    @RequestMapping(value = "/editSchedule", method = RequestMethod.GET)
    public ModelAndView editSchedule(HttpServletRequest request) {
        int scheduleid = Integer.parseInt(request.getParameter("scheduleid")); 
        Schedule editSchedule = scheduleDAO.get(scheduleid);
        ModelAndView mav = new ModelAndView("scheduleForm");
        mav.addObject("schedule", editSchedule);
        return mav;
    } 
}
