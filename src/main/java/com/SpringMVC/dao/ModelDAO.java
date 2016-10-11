package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Model;
@Repository 
public interface ModelDAO {
     
    public void saveOrUpdate(Model model);
     
    public void delete(int modelid);

    public List<String> getModels(int brandid);
    
    public Model get(int modelid);

    public Model getByName(String modelname);

    public List<Model> list(int brandid);
}