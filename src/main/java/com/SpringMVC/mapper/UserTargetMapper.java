package com.SpringMVC.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.UserTarget;
import org.springframework.jdbc.core.RowMapper;
 
public class UserTargetMapper implements RowMapper<UserTarget> {
 
    @Override
    public UserTarget mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int targetid = rs.getInt("targetid");
        int userid = rs.getInt("userid");
        Date period = rs.getDate("period");
        int prospect = rs.getInt("prospect");
        int sales = rs.getInt("sales");
        float totalsales = rs.getFloat("totalsales");
        
        return new UserTarget(targetid, userid, period, prospect, sales, totalsales);
    }
}