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
        Date activitydate = rs.getDate("activitydate");
        int brandid = rs.getInt("brandid");
        String brandname = rs.getString("brandname");
        int modelid = rs.getInt("modelid");
        String modelname = rs.getString("modelname");
        boolean demo = rs.getBoolean("demo");
        boolean testdrive = rs.getBoolean("testdrive");
        boolean quotation = rs.getBoolean("quotation");
        boolean linkevent = rs.getBoolean("linkevent");
        String remark1 = rs.getString("remark1");
        String remark2 = rs.getString("remark2");
        String remark3 = rs.getString("remark3");
        
        return new Activity(activityid, prospectid, activitydate, 
        		brandid, brandname, modelid, modelname,
        		demo, testdrive, quotation, linkevent, remark1, remark2, remark3);
    }
}