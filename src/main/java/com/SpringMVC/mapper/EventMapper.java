package com.SpringMVC.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import com.SpringMVC.model.Event;
import org.springframework.jdbc.core.RowMapper;
 
public class EventMapper implements RowMapper<Event> {
 
    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int id = rs.getInt("id");
        int userid = rs.getInt("userid");
        int prospectid = rs.getInt("prospectid");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        String mobile = rs.getString("mobile");
        int activityid = rs.getInt("activityid");
        String brandname = rs.getString("brandname");
        String modelname = rs.getString("modelname");
        String quotationpdflink = rs.getString("quotationpdflink");
        String title = rs.getString("title");
        String remark = rs.getString("remark");
        Date startdate = rs.getDate("startdate");
        Time starttime = rs.getTime("startdate");
        Date enddate = rs.getDate("enddate");
        Time endtime = rs.getTime("enddate");
        String url = rs.getString("url");
        boolean allDay = rs.getBoolean("allDay");
        
        return new Event(id, userid, prospectid, firstname, lastname, mobile, 
        		activityid, brandname, modelname, quotationpdflink, 
        		title, remark, 
        		startdate, starttime, enddate, endtime, url, allDay);
    }
}