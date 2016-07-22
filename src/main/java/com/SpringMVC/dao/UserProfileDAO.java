package com.SpringMVC.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.SpringMVC.model.UserProfile;

@Repository 
public interface UserProfileDAO {

	public UserProfile findUser(String username);

    public void saveOrUpdate(UserProfile userProfile);
     
    public void delete(String username);
     
    public UserProfile get(String username);
     
    public List<UserProfile> list(int teamid);
    
    public List<String> userList();
}