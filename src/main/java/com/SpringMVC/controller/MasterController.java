package com.SpringMVC.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.SpringMVC.dao.CompanyDAO;
import com.SpringMVC.dao.CompanyTargetDAO;
import com.SpringMVC.dao.BrandDAO;
import com.SpringMVC.dao.ModelDAO;
import com.SpringMVC.dao.BranchDAO;
import com.SpringMVC.dao.BranchTargetDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.TeamTargetDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.dao.UserMonthlySummaryDAO;
import com.SpringMVC.dao.UserProfileDAO;
import com.SpringMVC.dao.UserTargetDAO;
import com.SpringMVC.exceptions.ServiceException;
import com.SpringMVC.dao.ProspectDAO;
import com.SpringMVC.dao.ReviewDAO;

import com.SpringMVC.model.Company;
import com.SpringMVC.model.CompanyTarget;
import com.SpringMVC.model.Brand;
import com.SpringMVC.model.Model;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.BranchTarget;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.TeamTarget;
import com.SpringMVC.model.UserProfile;
import com.SpringMVC.model.UserTarget;
import com.SpringMVC.model.Prospect;
import com.SpringMVC.model.Review;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.model.UserMonthlySummary;

@Controller
public class MasterController {
    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private CompanyTargetDAO companyTargetDAO;

    @Autowired
    private BrandDAO brandDAO;

    @Autowired
    private ModelDAO modelDAO;

    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private BranchTargetDAO branchTargetDAO;

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private TeamTargetDAO teamTargetDAO;

    @Autowired
    private UserProfileDAO userProfileDAO;

    @Autowired
    private UserTargetDAO userTargetDAO;

    @Autowired
    private ProspectDAO prospectDAO;


    @Autowired
    private UserMonthlySummaryDAO userMonthlySummaryDAO;

    @Autowired
    private ReviewDAO reviewDAO;


    @RequestMapping(value="/listCompany")
    public ModelAndView listCompany(ModelAndView mav) throws IOException{
 	    List<Company> listCompany = companyDAO.list();
 	    mav.addObject("listCompany", listCompany);
 	    mav.setViewName("companyList");	 	    
 	    return mav;
 	}
 	   
    @RequestMapping(value = "/addCompany", method = RequestMethod.GET)
    public ModelAndView addCompany(ModelAndView mav, Principal principal) {
        Company newCompany = new Company();        
        mav.addObject("company", newCompany);
        List<String> mdlist = userLoginDAO.mdlist();	
        mav.addObject("mdlist", mdlist);        
        List<String> salist = userLoginDAO.salist();	
        mav.addObject("salist", salist);        
        mav.addObject("role", userLoginDAO.getUserRoles(principal.getName()));        
        mav.setViewName("companyForm");
        return mav;
    }
    
    @RequestMapping(value = "/saveCompany", method = RequestMethod.POST)
    public ModelAndView saveCompany(@ModelAttribute Company company, Principal principal) {
    	UserLogin mdid = userLoginDAO.get(company.getmdname());
    	company.setmdid(mdid.getuserid());
    	UserLogin said = userLoginDAO.get(company.getsaname());
    	company.setsaid(said.getuserid());
        companyDAO.saveOrUpdate(company);
    	UserLogin userLogin = userLoginDAO.get(principal.getName());
        if (userLogin.getrole().equals("DEV")){
            return new ModelAndView("redirect:/listCompany");
        }
        else {
     	   return new ModelAndView("redirect:/home");
        }        
    }
    
    @RequestMapping(value = "/deleteCompany", method = RequestMethod.GET)
    public ModelAndView deleteCompany(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        List<Branch> ListBranch = branchDAO.list(companyid);
        List<Brand> ListBrand = brandDAO.list(companyid);
        if (ListBranch.isEmpty() && ListBrand.isEmpty())
        {
            companyDAO.delete(companyid);        	
        }
        else
        {
        	throw new ServiceException(HttpStatus.NOT_FOUND, "Unable to delete company that being used.");
        }
        return new ModelAndView("redirect:/listCompany");
    }
    
    @RequestMapping(value = "/editCompany", method = RequestMethod.GET)
    public ModelAndView editCompany(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        if (companyid == 0){
        	companyid = userProfileDAO.getCompanyID(request.getUserPrincipal().getName());
        }
        Company editCompany= companyDAO.get(companyid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("companyForm");
        if (userLogin.getrole().equals("DEV")){
            List<String> mdlist = userLoginDAO.mdlist();	
            mav.addObject("mdlist", mdlist); 
            List<String> salist = userLoginDAO.salist();	        	
            mav.addObject("salist", salist);        
        }
        else {
        	List<String> mdlist = new ArrayList<String>();
        	mdlist.add(editCompany.getmdname());
            mav.addObject("mdlist", mdlist); 
        	List<String> salist = new ArrayList<String>();
        	salist.add(editCompany.getsaname());
            mav.addObject("salist", salist);        
        }
 	   	mav.addObject("role", userLogin.getrole());
        mav.addObject("company", editCompany);
        return mav;
    }
    
    @RequestMapping(value="/listCompanyTarget", method = RequestMethod.GET)
    public ModelAndView listCompanyTarget(HttpServletRequest request) {
        int companyid = userProfileDAO.getCompanyID(request.getUserPrincipal().getName());
        Company company = companyDAO.get(companyid);
 	    List<CompanyTarget> listCompanyTarget = companyTargetDAO.list(companyid);
        ModelAndView mav = new ModelAndView("companyTargetList");
        mav.addObject("company", company);
 	    mav.addObject("listTarget", listCompanyTarget);
 	    return mav;
 	}
 	   
    @RequestMapping(value = "/addCompanyTarget", method = RequestMethod.GET)
    public ModelAndView addCompanyTarget(HttpServletRequest request) {
        int companyid = userProfileDAO.getCompanyID(request.getUserPrincipal().getName());
        Company company = companyDAO.get(companyid);
        CompanyTarget newCompanyTarget = new CompanyTarget();
        newCompanyTarget.setcompanyid(companyid);
        ModelAndView mav = new ModelAndView("companyTargetForm");
        mav.addObject("company", company);
        mav.addObject("companyTarget", newCompanyTarget);
        return mav;
    }

    @RequestMapping(value = "/saveCompanyTarget", method = RequestMethod.POST)
    public ModelAndView saveCompanyTarget(@ModelAttribute CompanyTarget companyTarget, Principal principal) {
    	companyTargetDAO.saveOrUpdate(companyTarget);
        return new ModelAndView("redirect:/listCompanyTarget");
    }

    @RequestMapping(value = "/deleteCompanyTarget", method = RequestMethod.GET)
    public ModelAndView deleteCompanyTarget(HttpServletRequest request, Principal principal) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));        
        companyTargetDAO.delete(targetid);       	
        return new ModelAndView("redirect:/listCompanyTarget");
    }

    @RequestMapping(value = "/editCompanyTarget", method = RequestMethod.GET)
    public ModelAndView editCompanyTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));        
        CompanyTarget editCompanyTarget = companyTargetDAO.get(targetid);
        Company company = companyDAO.get(editCompanyTarget.getcompanyid());
        ModelAndView mav = new ModelAndView("companyTargetForm");
        mav.addObject("company", company);
        mav.addObject("companyTarget", editCompanyTarget);
        return mav;
    }   

    @RequestMapping(value="/listBrand", method = RequestMethod.GET)
    public ModelAndView listBrand(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        if (companyid == 0){
        	companyid = userProfileDAO.getCompanyID(request.getUserPrincipal().getName());
        }
        if (companyid != userProfileDAO.getCompanyID(request.getUserPrincipal().getName())) {
        	throw new ServiceException(HttpStatus.NOT_FOUND, "Data not found!");
        }
        Company company = companyDAO.get(companyid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
 	    List<Brand> listBrand = brandDAO.list(companyid);
        ModelAndView mav = new ModelAndView("brandList");
        mav.addObject("company", company);
 	   	mav.addObject("role", userLogin.getrole());
        mav.addObject("listBrand", listBrand);
 	    return mav;
 	}

    @RequestMapping(value = "/addBrand", method = RequestMethod.GET)
    public ModelAndView addBrand(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        Company company = companyDAO.get(companyid);
        Brand newBrand = new Brand();
        newBrand.setcompanyid(companyid);
        ModelAndView mav = new ModelAndView("brandForm");
        mav.addObject("company", company);
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
        List<Model> ListModel = modelDAO.list(brandid);        
        if (ListModel.isEmpty())
        {
        	brandDAO.delete(brandid);       	
        }
        else
        {
        	throw new ServiceException(HttpStatus.NOT_FOUND, "Unable to delete brand that being used.");
        }
        return new ModelAndView("redirect:/listBrand?companyid="+companyid);
    }

    @RequestMapping(value = "/editBrand", method = RequestMethod.GET)
    public ModelAndView editBrand(HttpServletRequest request) {
        int brandid = Integer.parseInt(request.getParameter("brandid"));        
        Brand editBrand = brandDAO.get(brandid);
        Company company = companyDAO.get(editBrand.getcompanyid());
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("brandForm");
 	   	mav.addObject("role", userLogin.getrole());
        mav.addObject("company", company);
        mav.addObject("brand", editBrand);
        return mav;
    }   
    
    @RequestMapping(value="/listModel", method = RequestMethod.GET)
    public ModelAndView listModel(HttpServletRequest request) {
        int brandid = Integer.parseInt(request.getParameter("brandid"));
        Brand brand = brandDAO.get(brandid);
        Company company = companyDAO.get(brand.getcompanyid());
 	    List<Model> listModel = modelDAO.list(brandid);
        ModelAndView mav = new ModelAndView("modelList");
        mav.addObject("company", company);
        mav.addObject("brand", brand);
 	    mav.addObject("listModel", listModel);
 	    return mav;
 	}

    @RequestMapping(value = "/addModel", method = RequestMethod.GET)
    public ModelAndView addModel(HttpServletRequest request) {
        int brandid = Integer.parseInt(request.getParameter("brandid"));
        Brand brand = brandDAO.get(brandid);
        Company company = companyDAO.get(brand.getcompanyid());
        Model newModel = new Model();
        newModel.setbrandid(brandid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("modelForm");
 	   	mav.addObject("role", userLogin.getrole());
        mav.addObject("company", company);
        mav.addObject("brand", brand);
        mav.addObject("model", newModel);
        return mav;
    }

    @RequestMapping(value = "/saveModel", method = RequestMethod.POST)
    public ModelAndView saveModel(@ModelAttribute Model model) {
        modelDAO.saveModel(model);
        return new ModelAndView("redirect:/listModel?brandid="+model.getbrandid());
    }

    @RequestMapping(value = "/deleteModel", method = RequestMethod.GET)
    public ModelAndView deleteModel(HttpServletRequest request) {
        int brandid = Integer.parseInt(request.getParameter("brandid"));
        int modelid = Integer.parseInt(request.getParameter("modelid"));        
        modelDAO.delete(modelid);
        return new ModelAndView("redirect:/listModel?brandid="+brandid);
    }

    @RequestMapping(value = "/editModel", method = RequestMethod.GET)
    public ModelAndView editModel(HttpServletRequest request) {
        int modelid = Integer.parseInt(request.getParameter("modelid"));   
        Model editModel = modelDAO.get(modelid);
        Brand brand = brandDAO.get(editModel.getbrandid());
        Company company = companyDAO.get(brand.getcompanyid());
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("modelForm");
 	   	mav.addObject("role", userLogin.getrole());
        mav.addObject("company", company);
        mav.addObject("brand", brand);
        mav.addObject("model", editModel);
        return mav;
    }   

    @RequestMapping(value="/listBranch", method = RequestMethod.GET)
    public ModelAndView listBranch(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        if (companyid == 0){
        	companyid = userProfileDAO.getCompanyID(request.getUserPrincipal().getName());
        }
        if (companyid != userProfileDAO.getCompanyID(request.getUserPrincipal().getName())) {
        	throw new ServiceException(HttpStatus.NOT_FOUND, "Data not found!");
        }
        Company company = companyDAO.get(companyid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
 	    List<Branch> listBranch = branchDAO.list(companyid);
        ModelAndView mav = new ModelAndView("branchList");
        mav.addObject("company", company);
        mav.addObject("role", userLogin.getrole());
        mav.addObject("listBranch", listBranch);
 	    return mav;
 	}
    
    @RequestMapping(value="/listBranch2", method = RequestMethod.GET)
    public ModelAndView listBranch2(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        if (companyid == 0){
        	companyid = userProfileDAO.getCompanyID(request.getUserPrincipal().getName());
        }
        if (companyid != userProfileDAO.getCompanyID(request.getUserPrincipal().getName())) {
        	throw new ServiceException(HttpStatus.NOT_FOUND, "Data not found!");
        }
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
 	    List<Branch> listBranch = branchDAO.list(companyid);
        ModelAndView mav = new ModelAndView("branchList2");
        mav.addObject("companyid", companyid);
        mav.addObject("role", userLogin.getrole());
        mav.addObject("listBranch", listBranch);
 	    return mav;
 	}
 	   
    @RequestMapping(value = "/addBranch", method = RequestMethod.GET)
    public ModelAndView addBranch(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        Branch newBranch = new Branch();
        Company company = companyDAO.get(companyid);
        newBranch.setcompanyid(companyid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("branchForm");
        List<String> malist = userLoginDAO.malist();	
        mav.addObject("company", company);
        mav.addObject("role", userLogin.getrole());
        mav.addObject("malist", malist);        
        mav.addObject("branch", newBranch);
        return mav;
    }

    @RequestMapping(value = "/saveBranch", method = RequestMethod.POST)
    public ModelAndView saveBranch(@ModelAttribute Branch branch) {
    	UserLogin maid = userLoginDAO.get(branch.getmaname());
    	branch.setmaid(maid.getuserid());
        branchDAO.saveOrUpdate(branch);
        return new ModelAndView("redirect:/listBranch?companyid="+branch.getcompanyid());
    }

    @RequestMapping(value = "/deleteBranch", method = RequestMethod.GET)
    public ModelAndView deleteBranch(HttpServletRequest request) {
        int companyid = Integer.parseInt(request.getParameter("companyid"));
        int branchid = Integer.parseInt(request.getParameter("branchid"));                
        List<Team> ListTeam = teamDAO.list(branchid);        
        if (ListTeam.isEmpty())
        {
        	branchDAO.delete(branchid);       	
        }
        else
        {
        	throw new ServiceException(HttpStatus.NOT_FOUND, "Unable to delete branch that being used.");
        }
        return new ModelAndView("redirect:/listBranch?companyid="+companyid);
    }

    @RequestMapping(value = "/editBranch", method = RequestMethod.GET)
    public ModelAndView editBranch(HttpServletRequest request) {
        int branchid = Integer.parseInt(request.getParameter("branchid"));        
        Branch editBranch = branchDAO.get(branchid);
        Company company = companyDAO.get(editBranch.getcompanyid());
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("branchForm");
        if (userLogin.getrole().equals("DEV")){
            List<String> malist = userLoginDAO.malist();	
            mav.addObject("malist", malist); 
        }
        else {
        	List<String> malist = new ArrayList<String>();
        	malist.add(editBranch.getmaname());
            mav.addObject("malist", malist); 
        }
        mav.addObject("role", userLogin.getrole());
        mav.addObject("company", company);
        mav.addObject("branch", editBranch);
        return mav;
    }    

    @RequestMapping(value="/listBranchTarget", method = RequestMethod.GET)
    public ModelAndView listBranchTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        CompanyTarget companyTarget = companyTargetDAO.get(targetid);
 	    List<BranchTarget> listBranchTarget = branchTargetDAO.list(companyTarget.getperiod(), companyTarget.getcompanyid());
        ModelAndView mav = new ModelAndView("branchTargetList");
        mav.addObject("companyTarget", companyTarget);
 	    mav.addObject("listTarget", listBranchTarget);
 	    return mav;
 	}
 	   
    @RequestMapping(value="/listBranchTargetMA", method = RequestMethod.GET)
    public ModelAndView listBranchTargetMA(HttpServletRequest request) {
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Branch branch = branchDAO.getByMA(userLogin.getuserid());
 	    List<BranchTarget> listBranchTarget = branchTargetDAO.listByBranch(branch.getbranchid());
        ModelAndView mav = new ModelAndView("branchTargetMAList");
 	    mav.addObject("branch", branch);        
 	    mav.addObject("listTarget", listBranchTarget);
 	    return mav;
 	}

    @RequestMapping(value = "/addBranchTarget", method = RequestMethod.GET)
    public ModelAndView addBranchTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        CompanyTarget companyTarget = companyTargetDAO.get(targetid);
        BranchTarget newBranchTarget = new BranchTarget();
        newBranchTarget.setperiod(companyTarget.getperiod());
        newBranchTarget.setcompanytargetid(companyTarget.gettargetid());        
        ModelAndView mav = new ModelAndView("branchTargetForm");
        List<String> branches = branchDAO.branchList(companyTarget.getcompanyid());	
        mav.addObject("branchlist", branches);
        mav.addObject("companyTarget", companyTarget);
        mav.addObject("branchTarget", newBranchTarget);
        return mav;
    }

    @RequestMapping(value = "/saveBranchTarget", method = RequestMethod.POST)
    public ModelAndView saveBranchTarget(@ModelAttribute BranchTarget branchTarget) {
    	Branch branch = branchDAO.getByName(branchTarget.getbranchname());
    	branchTarget.setbranchid(branch.getbranchid());
    	branchTargetDAO.saveOrUpdate(branchTarget);
        return new ModelAndView("redirect:/listBranchTarget?targetid="+branchTarget.getcompanytargetid());
    }

    @RequestMapping(value = "/deleteBranchTarget", method = RequestMethod.GET)
    public ModelAndView deleteBranchTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        BranchTarget branchTarget = branchTargetDAO.get(targetid);
        int companytargetid = branchTarget.getcompanytargetid();
        branchTargetDAO.delete(targetid);       	
        return new ModelAndView("redirect:/listBranchTarget?targetid="+companytargetid);
    }

    @RequestMapping(value = "/editBranchTarget", method = RequestMethod.GET)
    public ModelAndView editBranchTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        BranchTarget editBranchTarget = branchTargetDAO.get(targetid);
        CompanyTarget companyTarget = companyTargetDAO.get(editBranchTarget.getcompanytargetid());
        ModelAndView mav = new ModelAndView("branchTargetForm");
        List<String> branches = branchDAO.branchList(companyTarget.getcompanyid());	
        mav.addObject("branchlist", branches);
        mav.addObject("companyTarget", companyTarget);
        mav.addObject("branchTarget", editBranchTarget);
        return mav;
    }   
    
    @RequestMapping(value="/listTeam", method = RequestMethod.GET)
    public ModelAndView listTeam(HttpServletRequest request) {
        int branchid = Integer.parseInt(request.getParameter("branchid"));
        Branch branch = branchDAO.get(branchid);
        Company company = companyDAO.get(branch.getcompanyid());
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
 	    List<Team> listTeam = teamDAO.list(branchid);
        ModelAndView mav = new ModelAndView("teamList");        
        mav.addObject("role", userLogin.getrole());
        mav.addObject("company", company);
        mav.addObject("branch", branch);
        mav.addObject("listTeam", listTeam);
 	    return mav;
 	}

    @RequestMapping(value="/listTeam2", method = RequestMethod.GET)
    public ModelAndView listTeam2(HttpServletRequest request) {
        int branchid = Integer.parseInt(request.getParameter("branchid"));
        Branch branch = branchDAO.get(branchid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
 	    List<Team> listTeam = teamDAO.list(branchid);
        ModelAndView mav = new ModelAndView("teamList2");        
        mav.addObject("role", userLogin.getrole());
        mav.addObject("companyid", branch.getcompanyid());
        mav.addObject("branch", branch);
        mav.addObject("listTeam", listTeam);
 	    return mav;
 	}

    @RequestMapping(value = "/addTeam", method = RequestMethod.GET)
    public ModelAndView addTeam(HttpServletRequest request) {
        int branchid = Integer.parseInt(request.getParameter("branchid"));
        Branch branch = branchDAO.get(branchid);
        Company company = companyDAO.get(branch.getcompanyid());
        Team newTeam = new Team();
        newTeam.setbranchid(branchid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("teamForm");
        List<String> leaderlist = userLoginDAO.leaderlist();	
        mav.addObject("company", company);        
        mav.addObject("branch", branch);        
        mav.addObject("role", userLogin.getrole());
        mav.addObject("leaderlist", leaderlist);        
        mav.addObject("team", newTeam);
        return mav;
    }

    @RequestMapping(value = "/saveTeam", method = RequestMethod.POST)
    public ModelAndView saveTeam(@ModelAttribute Team team) {
    	UserLogin leaderid = userLoginDAO.get(team.getleadername());
    	team.setleaderid(leaderid.getuserid());
        teamDAO.saveOrUpdate(team);
        return new ModelAndView("redirect:/listTeam?branchid="+team.getbranchid());
    }

    @RequestMapping(value = "/deleteTeam", method = RequestMethod.GET)
    public ModelAndView deleteTeam(HttpServletRequest request) {
        int branchid = Integer.parseInt(request.getParameter("branchid"));
        int teamid = Integer.parseInt(request.getParameter("teamid"));                
        List<UserProfile> userProfile = userProfileDAO.list(teamid);        
        if (userProfile.isEmpty())
        {
        	teamDAO.delete(teamid);
        }
        else
        {
        	throw new ServiceException(HttpStatus.NOT_FOUND, "Unable to delete team that being used.");
        }
        return new ModelAndView("redirect:/listTeam?branchid="+branchid);
    }

    @RequestMapping(value = "/editTeam", method = RequestMethod.GET)
    public ModelAndView editTeam(HttpServletRequest request) {
        int teamid = Integer.parseInt(request.getParameter("teamid"));        
        Team editTeam = teamDAO.get(teamid);
        Branch branch = branchDAO.get(editTeam.getbranchid());
        Company company = companyDAO.get(branch.getcompanyid());
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("teamForm");
        if (userLogin.getrole().equals("DEV")){
            List<String> leaderlist = userLoginDAO.leaderlist();	
            mav.addObject("leaderlist", leaderlist); 
        }
        else {
        	List<String> leaderlist = new ArrayList<String>();
        	leaderlist.add(editTeam.getleadername());
            mav.addObject("leaderlist", leaderlist); 
        }
        mav.addObject("role", userLogin.getrole());        
        mav.addObject("company", company);
        mav.addObject("branch", branch);
        mav.addObject("team", editTeam);
        return mav;
    }    

    @RequestMapping(value="/listTeamTarget", method = RequestMethod.GET)
    public ModelAndView listTeamTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        BranchTarget branchTarget = branchTargetDAO.get(targetid);
 	    List<TeamTarget> listTeamTarget = teamTargetDAO.list(branchTarget.getperiod(), branchTarget.getbranchid());
        ModelAndView mav = new ModelAndView("teamTargetList");
        mav.addObject("branchTarget", branchTarget);
 	    mav.addObject("listTarget", listTeamTarget);
 	    return mav;
 	}
 	   
    @RequestMapping(value="/listTeamTargetUser", method = RequestMethod.GET)
    public ModelAndView listTeamTargetUser(HttpServletRequest request) {
        UserProfile userProfile = userProfileDAO.get(request.getUserPrincipal().getName());
        Team team = teamDAO.get(userProfile.getteamid());
        if (userProfile.getuserid() == team.getleaderid()){
	 	    List<TeamTarget> listTeamTarget = teamTargetDAO.listByTeam(team.getteamid());
	 	    ModelAndView mav = new ModelAndView("teamTargetUserList");
	 	    mav.addObject("listTarget", listTeamTarget);
	 	    return mav;
        }    	
	    else {
        	throw new ServiceException(HttpStatus.NOT_FOUND, "You have no access right!");
	    }
    }
    
    @RequestMapping(value = "/addTeamTarget", method = RequestMethod.GET)
    public ModelAndView addTeamTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        BranchTarget branchTarget = branchTargetDAO.get(targetid);
        TeamTarget newTeamTarget = new TeamTarget();
        newTeamTarget.setperiod(branchTarget.getperiod());
        newTeamTarget.setbranchtargetid(branchTarget.gettargetid());        
        ModelAndView mav = new ModelAndView("teamTargetForm");
        List<String> teams = teamDAO.teamList(branchTarget.getbranchid());	
        mav.addObject("teamlist", teams);
        mav.addObject("branchTarget", branchTarget);
        mav.addObject("teamTarget", newTeamTarget);
        return mav;
    }

    @RequestMapping(value = "/saveTeamTarget", method = RequestMethod.POST)
    public ModelAndView saveTeamTarget(@ModelAttribute TeamTarget teamTarget) {
    	Team team = teamDAO.getByName(teamTarget.getteamname());
    	teamTarget.setteamid(team.getteamid());
    	teamTargetDAO.saveOrUpdate(teamTarget);
        return new ModelAndView("redirect:/listTeamTarget?targetid="+teamTarget.getbranchtargetid());
    }

    @RequestMapping(value = "/deleteTeamTarget", method = RequestMethod.GET)
    public ModelAndView deleteTeamTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));        
        TeamTarget teamTarget = teamTargetDAO.get(targetid);
        teamTargetDAO.delete(targetid);       	
        return new ModelAndView("redirect:/listTeamTarget?targetid="+teamTarget.getbranchtargetid());
    }

    @RequestMapping(value = "/editTeamTarget", method = RequestMethod.GET)
    public ModelAndView editTeamTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        TeamTarget editTeamTarget = teamTargetDAO.get(targetid);
        BranchTarget branchTarget = branchTargetDAO.get(editTeamTarget.getbranchtargetid());
        ModelAndView mav = new ModelAndView("teamTargetForm");
        List<String> teams = teamDAO.teamList(branchTarget.getbranchid());	
        mav.addObject("teamlist", teams);
        mav.addObject("branchTarget", branchTarget);
        mav.addObject("teamTarget", editTeamTarget);
        return mav;
    }   
       
    @RequestMapping(value="/listMember", method = RequestMethod.GET)
    public ModelAndView listMember(HttpServletRequest request) {
        int teamid = Integer.parseInt(request.getParameter("teamid"));
        Team team = teamDAO.get(teamid);
        Branch branch = branchDAO.get(team.getbranchid());
        Company company = companyDAO.get(branch.getcompanyid());
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
 	    List<UserProfile> userProfile = userProfileDAO.list(teamid);
        ModelAndView mav = new ModelAndView("userProfileList");
        mav.addObject("role", userLogin.getrole());
        mav.addObject("company", company);
        mav.addObject("branch", branch);
        mav.addObject("team", team);
        mav.addObject("userProfile", userProfile);
 	    return mav;
 	}

    @RequestMapping(value="/listMember2", method = RequestMethod.GET)
    public ModelAndView listMember2(HttpServletRequest request) {
        int teamid = Integer.parseInt(request.getParameter("teamid"));
        Team team = teamDAO.get(teamid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
 	    List<UserProfile> userProfile = userProfileDAO.list(teamid);
        ModelAndView mav = new ModelAndView("userProfileList2");
        mav.addObject("role", userLogin.getrole());
        mav.addObject("companyid", userLogin.getcompanyid());
        mav.addObject("branchid", team.getbranchid());
        mav.addObject("team", team);
        mav.addObject("userProfile", userProfile);
 	    return mav;
 	}

    @RequestMapping(value = "/addMember", method = RequestMethod.GET)
    public ModelAndView addMember(HttpServletRequest request) {
        int teamid = Integer.parseInt(request.getParameter("teamid"));
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Team team = teamDAO.get(teamid);
        Branch branch = branchDAO.get(team.getbranchid());
        Company company = companyDAO.get(branch.getcompanyid());
        UserProfile newUserProfile = new UserProfile();
        newUserProfile.setteamid(teamid);
        newUserProfile.setrole("USER");        
        ModelAndView mav = new ModelAndView("userProfileForm");
        mav.addObject("company", company);
        mav.addObject("branch", branch);
        mav.addObject("team", team);
        mav.addObject("role", userLogin.getrole());
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
        int userid = Integer.parseInt(request.getParameter("userid"));
        int teamid = Integer.parseInt(request.getParameter("teamid"));
        List<Prospect> ListProspect = prospectDAO.list(userid); 
        if (ListProspect.isEmpty())
        {
            userProfileDAO.delete(userid);
        }
        else
        {
        	throw new ServiceException(HttpStatus.NOT_FOUND, "Unable to delete team member that being used.");
        }
        return new ModelAndView("redirect:/listMember?teamid="+teamid);
    }

    @RequestMapping(value = "/editMember", method = RequestMethod.GET)
    public ModelAndView editMember(HttpServletRequest request) {
        int userid = Integer.parseInt(request.getParameter("userid"));
        Team team = teamDAO.getByUser(userid);
        Branch branch = branchDAO.get(team.getbranchid());
        Company company = companyDAO.get(branch.getcompanyid());
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        UserProfile userProfile = userProfileDAO.findUser(userid);
        ModelAndView mav = new ModelAndView("userProfileForm");
        mav.addObject("company", company);
        mav.addObject("branch", branch);
        mav.addObject("team", team);
        mav.addObject("role", userLogin.getrole());
        mav.addObject("userProfile", userProfile);
        return mav;
    }    

    @RequestMapping(value="/listUserTarget", method = RequestMethod.GET)
    public ModelAndView listUserTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        TeamTarget teamTarget = teamTargetDAO.get(targetid);
 	    List<UserTarget> listUserTarget = userTargetDAO.list(teamTarget.getperiod(), teamTarget.getteamid());
        ModelAndView mav = new ModelAndView("userTargetList");
        mav.addObject("teamTarget", teamTarget);
 	    mav.addObject("listTarget", listUserTarget);
 	    return mav;
 	}

    @RequestMapping(value = "/addUserTarget", method = RequestMethod.GET)
    public ModelAndView addUserTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        TeamTarget teamTarget = teamTargetDAO.get(targetid);
        UserTarget newUserTarget = new UserTarget();
        newUserTarget.setperiod(teamTarget.getperiod());
        newUserTarget.setteamtargetid(teamTarget.gettargetid());        
        ModelAndView mav = new ModelAndView("userTargetForm");
        List<String> members = userProfileDAO.userList(teamTarget.getteamid());	
        mav.addObject("userlist", members);
        mav.addObject("teamTarget", teamTarget);
        mav.addObject("userTarget", newUserTarget);
        return mav;
    }

    @RequestMapping(value = "/saveUserTarget", method = RequestMethod.POST)
    public ModelAndView saveUserTarget(@ModelAttribute UserTarget userTarget) {
    	UserProfile userProfile = userProfileDAO.get(userTarget.getusername());
    	userTarget.setuserid(userProfile.getuserid());
    	userTargetDAO.saveOrUpdate(userTarget);
        return new ModelAndView("redirect:/listUserTarget?targetid="+userTarget.getteamtargetid());
    }

    @RequestMapping(value = "/deleteUserTarget", method = RequestMethod.GET)
    public ModelAndView deleteUserTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));        
        UserTarget userTarget = userTargetDAO.get(targetid);
        userTargetDAO.delete(targetid);       	
        return new ModelAndView("redirect:/listUserTarget?targetid="+userTarget.getteamtargetid());
    }

    @RequestMapping(value = "/editUserTarget", method = RequestMethod.GET)
    public ModelAndView editUserTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        UserTarget editUserTarget = userTargetDAO.get(targetid);
        TeamTarget teamTarget = teamTargetDAO.get(editUserTarget.getteamtargetid());
        ModelAndView mav = new ModelAndView("userTargetForm");
        List<String> members = userProfileDAO.userList(teamTarget.getteamid());	
        mav.addObject("userlist", members);
        mav.addObject("teamTarget", teamTarget);
        mav.addObject("userTarget", editUserTarget);
        return mav;
    } 
    
 
    @RequestMapping(value="/listReview", method = RequestMethod.GET)
    public ModelAndView listReview(HttpServletRequest request) {
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        int userid = Integer.parseInt(request.getParameter("userid"));
 	    List<Review> listReview = reviewDAO.list(userid);
 	   	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
 	   	Calendar c = Calendar.getInstance(); 
 	    String period = dateFormat.format(c.getTime());
		UserMonthlySummary userMonthlySummary = userMonthlySummaryDAO.get(period, userLogin.getuserid());
        ModelAndView mav = new ModelAndView("reviewList");
		mav.addObject("userMonthlySummary", userMonthlySummary);
        mav.addObject("userid", userid);
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

    @RequestMapping(value = "/saveReview", method = RequestMethod.POST)
    public ModelAndView saveReview(@ModelAttribute Review review) {
    	reviewDAO.saveOrUpdate(review);
        return new ModelAndView("redirect:/listReview?userid="+review.getuserid());
    }

    @RequestMapping(value = "/deleteReview", method = RequestMethod.GET)
    public ModelAndView deleteReview(HttpServletRequest request) {
        int reviewid = Integer.parseInt(request.getParameter("reviewid"));
        Review review = reviewDAO.get(reviewid);
        reviewDAO.delete(reviewid);
        return new ModelAndView("redirect:/listReview?userid="+review.getuserid());
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
