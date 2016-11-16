package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Calendar;
import com.SpringMVC.model.Event;
@Repository 
public interface EventDAO {
     
    public void save(Event event);
     
    public void update(Event event);

    public void delete(int eventid);
     
    public Event get(int eventid);
     
    public List<Calendar> list(int userid, String period);    
}