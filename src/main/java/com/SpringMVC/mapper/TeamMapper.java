package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.Team;
import org.springframework.jdbc.core.RowMapper;
 
public class TeamMapper implements RowMapper<Team> {
 
    @Override
    public Team mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int teamid = rs.getInt("teamid");
        String teamname = rs.getString("teamname");
        int branchid = rs.getInt("branchid");
        String pic = rs.getString("pic");
        
        return new Team(teamid, teamname, branchid, pic);
    }
}