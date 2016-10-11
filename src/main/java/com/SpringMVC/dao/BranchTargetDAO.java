package com.SpringMVC.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.BranchTarget;
@Repository 
public interface BranchTargetDAO {
     
    public void saveOrUpdate(BranchTarget branchTarget);
     
    public void delete(int targetid);
     
    public BranchTarget get(int targetid);

    public List<BranchTarget> list(Date period, int companyid);

    public List<BranchTarget> listByBranch(int branchid);
}