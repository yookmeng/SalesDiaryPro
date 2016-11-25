package com.SpringMVC.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.SpringMVC.model.Address;
import com.SpringMVC.model.Prospect;
import org.springframework.jdbc.core.RowMapper;
 
public class ProspectMapper implements RowMapper<Prospect> {
 
    @Override
    public Prospect mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int prospectid = rs.getInt("prospectid");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        int userid = rs.getInt("userid");
        String source = rs.getString("source");
        Date datecreated = rs.getDate("datecreated");
        int modelid = rs.getInt("modelid");
        String modelname = rs.getString("modelname");
        Address homeaddress = new Address();
        homeaddress.setcountry(rs.getString("hcountry"));
        homeaddress.setzipcode(rs.getString("hzipcode"));
        homeaddress.setstate(rs.getString("hstate"));
        homeaddress.setcity(rs.getString("hcity"));
        homeaddress.setstreet(rs.getString("hstreet"));
        String mobile = rs.getString("mobile");
        String htelno = rs.getString("htelno");
        int contactid= rs.getInt("contactid");
        Address workaddress = new Address();
        workaddress.setcountry(rs.getString("wcountry"));
        workaddress.setzipcode(rs.getString("wzipcode"));
        workaddress.setstate(rs.getString("wstate"));
        workaddress.setcity(rs.getString("wcity"));
        workaddress.setstreet(rs.getString("wstreet"));
        String wtelno = rs.getString("wtelno");
        String occupation = rs.getString("occupation");
        int age = rs.getInt("age");
        String gender = rs.getString("gender");
        String income = rs.getString("income");
        String email = rs.getString("email");
        String status = rs.getString("status");
        
        return new Prospect(prospectid, firstname, lastname, userid, source, 
        		datecreated, modelid, modelname, 
        		homeaddress, mobile, htelno, contactid,
        		workaddress, wtelno, 
        		occupation, age, gender, income, email, status);
    } 
}