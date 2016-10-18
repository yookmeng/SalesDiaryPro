package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Request;
@Repository 
public interface RequestDAO {
     
    public void save(Request request);
     
    public void update(Request request);

    public void delete(int requestid);
     
    public Request get(int requestid);
     
    public List<Request> list(int prospectid);    
}