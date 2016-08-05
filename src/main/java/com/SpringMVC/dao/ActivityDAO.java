package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Activity;
@Repository 
public interface ActivityDAO {
     
    public void saveOrUpdate(Activity activity);
     
    public void delete(int activityid);
     
    public Activity get(int activityid);
     
    public List<Activity> list(int prospectid);    
}