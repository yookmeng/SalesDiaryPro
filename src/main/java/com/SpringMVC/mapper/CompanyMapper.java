package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.Company;
import org.springframework.jdbc.core.RowMapper;
 
public class CompanyMapper implements RowMapper<Company> {
 
    @Override
    public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
        int companyid = rs.getInt("companyid");
        String companyname = rs.getString("companyname");
        String regno = rs.getString("regno");
        String pic = rs.getString("pic");
        String address = rs.getString("address");
        String zipcode = rs.getString("zipCode");
        String city = rs.getString("city");
        String state = rs.getString("state");
        String country = rs.getString("country");
        String telephone = rs.getString("telephone");
        String fax = rs.getString("fax");
        String email = rs.getString("email");
        String website = rs.getString("website");
        
        return new Company(companyid, companyname, regno, pic, address, zipcode, city, state, country, telephone, fax, email, website);
    }
}