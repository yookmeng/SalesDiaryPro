package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Brand;
@Repository 
public interface BrandDAO {
     
    public void saveOrUpdate(Brand brand);
     
    public void delete(int brandid);
     
    public List<String> getBrands(int companyid);

    public Brand get(int brandid);
     
    public List<Brand> list(int companyid);    
}