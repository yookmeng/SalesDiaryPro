package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.SpringMVC.model.ClosingPeriod;
import org.springframework.jdbc.core.RowMapper;
 
public class ClosingPeriodMapper implements RowMapper<ClosingPeriod> {
 
    @Override
    public ClosingPeriod mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int id = rs.getInt("id");
        int companyid = rs.getInt("companyid");        
        int controlyear = rs.getInt("controlyear");
        int controlmonth = rs.getInt("controlmonth");
        Date opendate = rs.getDate("opendate");
        Date closedate = rs.getDate("closedate");
        
        return new ClosingPeriod(id, companyid, controlyear, controlmonth, opendate, closedate);
    }
}