package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.CompanyTarget;
@Repository 
public interface CompanyTargetDAO {
     
    public void saveOrUpdate(CompanyTarget companyTarget);
     
    public void delete(int targetid);
     
    public CompanyTarget get(int targetid);

    public List<CompanyTarget> list(int companyid);
}