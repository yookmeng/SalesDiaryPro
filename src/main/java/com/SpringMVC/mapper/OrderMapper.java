package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.Order;
import org.springframework.jdbc.core.RowMapper;
 
public class OrderMapper implements RowMapper<Order> {
 
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int orderid = rs.getInt("orderid");
        int projectid = rs.getInt("projectid");
        String projectname = rs.getString("projectname");
        int productid = rs.getInt("productid");
        String productname = rs.getString("productname");
        int quantity = rs.getInt("quantity");
        float price = rs.getFloat("price");
        int discount = rs.getInt("discount");
        float amount = rs.getFloat("amount");

        return new Order(orderid, projectid, projectname, productid, productname,
        		quantity, price, discount, amount);        
    }
}