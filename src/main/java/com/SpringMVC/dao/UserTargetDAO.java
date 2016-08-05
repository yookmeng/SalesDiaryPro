package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.UserTarget;
@Repository 
public interface UserTargetDAO {
     
    public void saveOrUpdate(UserTarget userTarget);
     
    public void delete(int targetid);
     
    public UserTarget get(int targetid);

    public List<UserTarget> list(int userid);
}