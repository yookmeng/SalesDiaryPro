package com.SpringMVC.dao;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.UserMonthlySummary;

@Repository 
public interface UserMonthlySummaryDAO {
	
    public UserMonthlySummary get(String period, int userid);

}