package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.SpringMVC.model.Review;
import org.springframework.jdbc.core.RowMapper;
 
public class ReviewMapper implements RowMapper<Review> {
 
    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int reviewid = rs.getInt("reviewid");
        int userid = rs.getInt("userid");        
        int targetid = rs.getInt("targetid");        
        int teamtargetid = rs.getInt("teamtargetid");        
        Date reviewdate = rs.getDate("reviewdate");
        int prospect = rs.getInt("prospect");
        int testdrive = rs.getInt("testdrive");
        int closed = rs.getInt("closed");
        String minute = rs.getString("minute");
        int reviewby = rs.getInt("reviewby");
        
        return new Review(reviewid, userid, targetid, teamtargetid, reviewdate, 
        		prospect, testdrive, closed, minute, reviewby);
    }
}