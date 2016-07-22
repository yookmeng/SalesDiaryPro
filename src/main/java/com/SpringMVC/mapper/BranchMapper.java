package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.Branch;
import org.springframework.jdbc.core.RowMapper;
 
public class BranchMapper implements RowMapper<Branch> {
 
    @Override
    public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        int branchid = rs.getInt("branchid");
        String branchname = rs.getString("branchname");
        int companyid = rs.getInt("companyid");
        String regno = rs.getString("regno");
        String pic = rs.getString("pic");
        String address = rs.getString("address");
        String zipcode = rs.getString("zipcode");
        String city = rs.getString("city");
        String state = rs.getString("state");
        String country = rs.getString("country");
        String telephone = rs.getString("telephone");
        String fax = rs.getString("fax");
        String email = rs.getString("email");
        String website = rs.getString("website");
        
        return new Branch(branchid, branchname, companyid, regno, pic, address, zipcode, city, state, country, telephone, fax, email, website);
    }
 
}