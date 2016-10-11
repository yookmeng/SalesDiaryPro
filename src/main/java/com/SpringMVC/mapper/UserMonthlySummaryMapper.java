package com.SpringMVC.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.UserMonthlySummary;
import org.springframework.jdbc.core.RowMapper;
 
public class UserMonthlySummaryMapper implements RowMapper<UserMonthlySummary> {
 
    @Override
    public UserMonthlySummary mapRow(ResultSet rs, int rowNum) throws SQLException { 
        Date period = rs.getDate("period");
        int userid = rs.getInt("userid");
        String username = rs.getString("username");
        int targetprospect = rs.getInt("targetprospect");
        int targettestdrive = rs.getInt("targettestdrive");
        int targetclosed = rs.getInt("targetclosed");
        int actualprospect = rs.getInt("actualprospect");
        int actualtestdrive = rs.getInt("actualtestdrive");
        int actualclosed = rs.getInt("actualclosed");
        float percentprospect = rs.getFloat("percentprospect");
        float percenttestdrive = rs.getFloat("percenttestdrive");
        float percentclosed = rs.getFloat("percentclosed");
        
        return new UserMonthlySummary(period, userid, username, 
        		targetprospect, targettestdrive, targetclosed,
        		actualprospect, actualtestdrive, actualclosed,
        		percentprospect, percenttestdrive, percentclosed);
    }
}