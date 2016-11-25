package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.SpringMVC.model.Activity;
import org.springframework.jdbc.core.RowMapper;
 
public class ActivityMapper implements RowMapper<Activity> {
 
    @Override
    public Activity mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int activityid = rs.getInt("activityid");
        int prospectid = rs.getInt("prospectid");        
        String prospectname = rs.getString("prospectname");        
        Date activitydate = rs.getDate("activitydate");
        int brandid = rs.getInt("brandid");
        String brandname = rs.getString("brandname");
        int modelid = rs.getInt("modelid");
        String modelname = rs.getString("modelname");
        boolean demo = rs.getBoolean("demo");
        boolean testdrive = rs.getBoolean("testdrive");
        boolean quotation = rs.getBoolean("quotation");
        boolean followup = rs.getBoolean("followup");
        boolean closed = rs.getBoolean("closed");
        boolean lost = rs.getBoolean("lost");
        boolean demostatus = rs.getBoolean("demostatus");
        boolean testdrivestatus = rs.getBoolean("testdrivestatus");
        String followupremark = rs.getString("followupremark");
        boolean followupstatus = rs.getBoolean("followupstatus");
        int quotationid = rs.getInt("quotationid");
        String quotationpdflink = rs.getString("quotationpdflink");
        int closedid = rs.getInt("closedid");
        String lostremark = rs.getString("lostremark");
        
        return new Activity(activityid, prospectid, prospectname, activitydate, 
        		brandid, brandname, modelid, modelname,
        		demo, testdrive, quotation, followup, closed, lost, 
        		demostatus, testdrivestatus, followupremark, followupstatus,        		
        		quotationid, quotationpdflink, closedid, lostremark);
    }
}