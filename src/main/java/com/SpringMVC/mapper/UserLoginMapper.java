package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.SpringMVC.model.UserLogin;
 
public class UserLoginMapper implements RowMapper<UserLogin> {
	
	@Override
	public UserLogin mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int userid = rs.getInt("userid");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String role = rs.getString("role");
        
        return new UserLogin(userid, username, password, role);
    }
 
}