package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Prospect;
@Repository 
public interface ProspectDAO {
     
    public void saveOrUpdate(Prospect prospect);
     
    public void delete(int prospectid);
     
    public Prospect get(int prospectid);
     
    public List<Prospect> list(int userid);
    
    public List<String> prospectList();
}