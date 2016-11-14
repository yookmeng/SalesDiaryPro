package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Prospect;
@Repository 
public interface ProspectDAO {
     
    public void save(Prospect prospect);
     
    public void update(Prospect prospect);

    public void delete(int prospectid);
     
    public Prospect get(int prospectid);
     
    public List<Prospect> list(int userid);
    
    public List<Prospect> listByTeam(int teamid);

    public List<Prospect> listByBranch(int branchid);

    public List<Prospect> listByCompany(int companyid);

    public List<Prospect> listfilter(int userid, String status);

    public List<Prospect> listByTeamfilter(int teamid, String status);

    public List<Prospect> listByBranchfilter(int branchid, String status);

    public List<Prospect> listByCompanyfilter(int companyid, String status);

    public List<String> prospectList();
}