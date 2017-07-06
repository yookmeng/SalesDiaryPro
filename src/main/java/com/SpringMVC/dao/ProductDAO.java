package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Product;
@Repository 
public interface ProductDAO {
     
    public void save(Product product);

    public void update(Product product);
     
    public void delete(int productid);

    public Product get(int productid);

    public Product getByName(String productname);

    public List<Product> list(int companyid);

    public boolean isExist(Product product);
}