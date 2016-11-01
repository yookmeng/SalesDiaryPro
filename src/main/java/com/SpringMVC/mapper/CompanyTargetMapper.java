package com.SpringMVC.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.SpringMVC.model.CompanyTarget;
import org.springframework.jdbc.core.RowMapper;
 
public class CompanyTargetMapper implements RowMapper<CompanyTarget> {
 
    @Override
    public CompanyTarget mapRow(ResultSet rs, int rowNum) throws SQLException { 
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
        int targetid = rs.getInt("targetid");
        int companyid = rs.getInt("companyid");
        String companyname = rs.getString("companyname");
        Date period = rs.getDate("period");
        String displayperiod = dateFormat.format(rs.getDate("period"));
        int prospect = rs.getInt("prospect");
        int testdrive = rs.getInt("testdrive");
        int closed = rs.getInt("closed");
        
        return new CompanyTarget(targetid, companyid, companyname, period, 
        		displayperiod, prospect, testdrive, closed);
    }
}