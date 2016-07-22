package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.Schedule;
import org.springframework.jdbc.core.RowMapper;
 
public class ScheduleMapper implements RowMapper<Schedule> {
 
    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int scheduleid = rs.getInt("scheduleid");
        int prospectid = rs.getInt("prospectid");        
        String scheduledate = rs.getString("scheduledate");
        String type = rs.getString("type");
        String remark1 = rs.getString("remark1");
        String remark2 = rs.getString("remark2");
        String remark3 = rs.getString("remark3");
        String remark4 = rs.getString("remark4");
        String remark5 = rs.getString("remark5");
        
        return new Schedule(scheduleid, prospectid, scheduledate, type,
        		remark1, remark2, remark3, remark4, remark5);
    }
}