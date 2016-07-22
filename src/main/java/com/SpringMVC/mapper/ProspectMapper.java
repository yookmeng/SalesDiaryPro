package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.Prospect;
import org.springframework.jdbc.core.RowMapper;
 
public class ProspectMapper implements RowMapper<Prospect> {
 
    @Override
    public Prospect mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int prospectid = rs.getInt("prospectid");
        String prospectname = rs.getString("prospectname");
        int userid = rs.getInt("userid");
        String source = rs.getString("source");
        String haddress = rs.getString("haddress");
        String hzipcode = rs.getString("hzipcode");
        String hcity = rs.getString("hcity");
        String hstate = rs.getString("hstate");
        String hcountry = rs.getString("hcountry");
        String mobile = rs.getString("mobile");
        String htelno = rs.getString("htelno");
        String waddress = rs.getString("waddress");
        String wzipcode = rs.getString("wzipcode");
        String wcity = rs.getString("wcity");
        String wstate = rs.getString("wstate");
        String wcountry = rs.getString("wcountry");
        String wtelno = rs.getString("wtelno");
        String occupation = rs.getString("occupation");
        int age = rs.getInt("age");
        String income = rs.getString("income");
        String email = rs.getString("email");
        
        return new Prospect(prospectid, prospectname, userid, source, 
        		haddress, hzipcode, hcity, hstate, hcountry, mobile, htelno, 
        		waddress, wzipcode, wcity, wstate, wcountry, wtelno, 
        		occupation, age, income, email);
    } 
}