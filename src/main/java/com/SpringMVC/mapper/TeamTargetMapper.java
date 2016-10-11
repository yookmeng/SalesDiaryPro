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
        String teamname = rs.getString("teamname");
        Date period = rs.getDate("period");
        String displayperiod = rs.getString("displayperiod");
        int branchtargetid = rs.getInt("branchtargetid");
        int prospect = rs.getInt("prospect");
        int testdrive = rs.getInt("testdrive");
        int closed = rs.getInt("closed");
        
        return new TeamTarget(targetid, teamid, teamname, period, 
        		displayperiod, branchtargetid, prospect, testdrive, closed);
    }
}