package com.SpringMVC.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Event;
@Repository 
public interface EventDAO {
     
    public void saveOrUpdate(Event event);
     
    public void delete(int eventid);
     
    public Event get(int eventid);
     
    public List<Event> list(int userid, Date period);    
}