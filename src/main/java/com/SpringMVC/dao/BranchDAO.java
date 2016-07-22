package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Branch;
@Repository 
public interface BranchDAO {
     
    public void saveOrUpdate(Branch branch);
     
    public void delete(int branchid);
     
    public Branch get(int branchid);
     
    public List<Branch> list(int companyid);
    
    public List<String> branchList();
}