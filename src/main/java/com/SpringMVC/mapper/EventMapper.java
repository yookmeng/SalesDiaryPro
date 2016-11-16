package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.SpringMVC.model.Event;
import org.springframework.jdbc.core.RowMapper;
 
public class EventMapper implements RowMapper<Event> {
 
    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int id = rs.getInt("id");
        int userid = rs.getInt("userid");
        String period = rs.getString("period");
        String title = rs.getString("title");
        String remark = rs.getString("remark");
        String start = rs.getString("start");
        String end = rs.getString("end");
        String url = rs.getString("url");
        boolean allDay = rs.getBoolean("allDay");
        int activityid = rs.getInt("activityid");
        
        return new Event(id, userid, period, title, remark, start, end, url, allDay, activityid);
    }
}