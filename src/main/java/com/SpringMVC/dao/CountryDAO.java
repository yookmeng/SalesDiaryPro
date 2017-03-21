package com.SpringMVC.dao;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Country;

@Repository 
public interface CountryDAO {	
    
   public void save(Country country);
    
   public void update(Country country);

   public void delete(int countryid);
    
   public Country get(int countryid);

   public Country getAll();
}