package com.SpringMVC.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.BranchTarget;
import org.springframework.jdbc.core.RowMapper;
 
public class BranchTargetMapper implements RowMapper<BranchTarget> {
 
    @Override
    public BranchTarget mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int targetid = rs.getInt("targetid");
        int branchid = rs.getInt("branchid");
        Date period = rs.getDate("period");
        int prospect = rs.getInt("prospect");
        int sales = rs.getInt("sales");
        float totalsales = rs.getFloat("totalsales");
        
        return new BranchTarget(targetid, branchid, period, prospect, sales, totalsales);
    }
}