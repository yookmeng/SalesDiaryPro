package com.SpringMVC.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.CompanyTarget;
import org.springframework.jdbc.core.RowMapper;
 
public class CompanyTargetMapper implements RowMapper<CompanyTarget> {
 
    @Override
    public CompanyTarget mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int targetid = rs.getInt("targetid");
        int companyid = rs.getInt("companyid");
        Date period = rs.getDate("period");
        int prospect = rs.getInt("prospect");
        int sales = rs.getInt("sales");
        float totalsales = rs.getFloat("totalsales");
        
        return new CompanyTarget(targetid, companyid, period, prospect, sales, totalsales);
    }
}