package com.SpringMVC.dao;

import java.util.List;

import com.SpringMVC.model.UserLogin;
 
public interface UserLoginDAO {
     
    public UserLogin findUserLogin(String username);
     
    public List<String> getUserRoles(String username);

    public List<String> getAllRoles();

    public UserLogin get(String username);

    public List<UserLogin> list();

    public List<String> salist();

    public List<String> mdlist();

    public List<String> malist();

    public List<String> leaderlist();

    public void saveOrUpdate(UserLogin userLogin);
    
    public void delete(String username);
}