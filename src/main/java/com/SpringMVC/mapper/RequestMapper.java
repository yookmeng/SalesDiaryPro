package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.SpringMVC.model.Request;

import org.springframework.jdbc.core.RowMapper;
 
public class RequestMapper implements RowMapper<Request> {
 
    @Override
    public Request mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int requestid = rs.getInt("requestid");
        int prospectid = rs.getInt("prospectid");        
        Date requestdate = rs.getDate("requestdate");
        int brandid = rs.getInt("brandid");
        String brandname = rs.getString("brandname");
        int modelid = rs.getInt("modelid");
        String modelname = rs.getString("modelname");
        String remark = rs.getString("remark");
        String status = rs.getString("status");
        
        return new Request(requestid, prospectid, requestdate, brandid, brandname, modelid, modelname, remark, status);
    }
}