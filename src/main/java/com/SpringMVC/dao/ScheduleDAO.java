package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Schedule;
@Repository 
public interface ScheduleDAO {
     
    public void saveOrUpdate(Schedule schedule);
     
    public void delete(int scheduleid);
     
    public Schedule get(int scheduleid);
     
    public List<Schedule> list(int prospectid);    
}