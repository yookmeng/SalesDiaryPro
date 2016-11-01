package com.SpringMVC.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.SpringMVC.model.UserTarget;
import org.springframework.jdbc.core.RowMapper;
 
public class UserTargetMapper implements RowMapper<UserTarget> {
 
    @Override
    public UserTarget mapRow(ResultSet rs, int rowNum) throws SQLException { 
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
        int targetid = rs.getInt("targetid");
        int userid = rs.getInt("userid");
        String username = rs.getString("username");
        Date period = rs.getDate("period");
        String displayperiod = dateFormat.format(rs.getDate("period"));
        int teamtargetid = rs.getInt("teamtargetid");
        int prospect = rs.getInt("prospect");
        int testdrive = rs.getInt("testdrive");
        int closed = rs.getInt("closed");
        
        return new UserTarget(targetid, userid, username, period, displayperiod, 
        		teamtargetid, prospect, testdrive, closed);
    }
}