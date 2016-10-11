package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.SpringMVC.model.Event;
import org.springframework.jdbc.core.RowMapper;
 
public class EventMapper implements RowMapper<Event> {
 
    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException { 
    	DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        int eventid = rs.getInt("eventid");
        int userid = rs.getInt("userid");        
        String title = rs.getString("title");
        String remark = rs.getString("remark");
        LocalDateTime startdate = LocalDateTime.parse((rs.getDate("startdate")+" "+rs.getTime("startdate")), sdf);
        LocalDateTime enddate = LocalDateTime.parse((rs.getDate("enddate")+" "+rs.getTime("enddate")), sdf);
        String url = rs.getString("url");
        boolean allday = rs.getBoolean("allday");
        
        return new Event(eventid, userid, title, remark, 
        		startdate, enddate, url, allday);
    }
}