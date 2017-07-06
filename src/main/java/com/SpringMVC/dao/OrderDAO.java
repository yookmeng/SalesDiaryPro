package com.SpringMVC.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.SpringMVC.model.Order;

@Repository 
public interface OrderDAO {
     
    public void save(Order order);
     
    public void update(Order order);

    public void delete(int orderid);
     
    public Order get(int orderid);
     
    public List<Order> list(int projectid);    
}