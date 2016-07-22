package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.UserProfile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
@Repository 
public class UserProfileMapper implements RowMapper<UserProfile> { 
    @Override
    public UserProfile mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int userid = rs.getInt("userid");
        String username = rs.getString("username");  
        String password = rs.getString("password");          
        String role = rs.getString("role");
        int teamid = rs.getInt("teamid");
        String mobile = rs.getString("mobile");
        String email = rs.getString("email");
        
        return new UserProfile(userid, username, password, role, teamid, mobile, email);
    }
}