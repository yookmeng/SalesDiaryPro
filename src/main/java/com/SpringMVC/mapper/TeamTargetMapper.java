package com.SpringMVC.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.TeamTarget;
import org.springframework.jdbc.core.RowMapper;
 
public class TeamTargetMapper implements RowMapper<TeamTarget> {
 
    @Override
    public TeamTarget mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int targetid = rs.getInt("targetid");
        int teamid = rs.getInt("teamid");
        Date period = rs.getDate("period");
        int prospect = rs.getInt("prospect");
        int sales = rs.getInt("sales");
        float totalsales = rs.getFloat("totalsales");
        
        return new TeamTarget(targetid, teamid, period, prospect, sales, totalsales);
    }
}