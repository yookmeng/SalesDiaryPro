package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.Model;
import org.springframework.jdbc.core.RowMapper;
 
public class ModelMapper implements RowMapper<Model> {
 
    @Override
    public Model mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int modelid = rs.getInt("modelid");
        String modelname = rs.getString("modelname");
        int brandid = rs.getInt("brandid");
        float price = rs.getFloat("price");
        float commission = rs.getFloat("commission");
        
        return new Model(modelid, modelname, brandid, price, commission);
    }
}