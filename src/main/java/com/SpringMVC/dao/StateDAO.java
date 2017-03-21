package com.SpringMVC.dao;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.State;

@Repository 
public interface StateDAO {	
    
   public void save(State state);
    
   public void update(State state);

   public void delete(int stateid);
    
   public State get(int stateid);

   public State getAll();
}