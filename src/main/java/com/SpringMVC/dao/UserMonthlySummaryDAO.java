package com.SpringMVC.dao;

import java.sql.Date;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.UserMonthlySummary;

@Repository 
public interface UserMonthlySummaryDAO {
	
    public UserMonthlySummary get(Date period, int userid);

}