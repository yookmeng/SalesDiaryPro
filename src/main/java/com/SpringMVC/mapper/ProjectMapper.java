package com.SpringMVC.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.SpringMVC.model.Project;
import org.springframework.jdbc.core.RowMapper;
 
public class ProjectMapper implements RowMapper<Project> {
 
    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int projectid = rs.getInt("projectid");
        String projectname = rs.getString("projectname");
        int userid = rs.getInt("userid");
        String name = rs.getString("name");
        String mobile = rs.getString("mobile");
        String email = rs.getString("email");
        int titleid = rs.getInt("titleid");
        String titlename = rs.getString("titlename");
        int propertyid = rs.getInt("propertyid");
        String propertyname = rs.getString("propertyname");
        int units = rs.getInt("units");
        int orderid = rs.getInt("orderid");
        int quotationid = rs.getInt("quotationid");
        boolean smsflag = rs.getBoolean("smsflag");
        Date datecreated = rs.getDate("datecreated");
        String forecastperiod = rs.getString("forecastperiod");
        String status = rs.getString("status");
        String statusname = rs.getString("statusname");
        
        return new Project(projectid, projectname, userid, name, mobile, email, 
        		titleid, titlename, propertyid, propertyname, units, orderid, quotationid, 
        		smsflag, datecreated, forecastperiod, status, statusname);
    } 
}