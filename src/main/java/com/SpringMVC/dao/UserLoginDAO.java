package com.SpringMVC.dao;

import java.util.List;

import com.SpringMVC.model.UserLogin;
 
public interface UserLoginDAO {
     
    public UserLogin findUserLogin(String username);
     
    public List<String> getUserRoles(String username);

    public List<String> getAllRoles();

    public UserLogin get(String username);

    public List<UserLogin> list();

    public void saveOrUpdate(UserLogin userLogin);
    
    public void delete(String username);
}