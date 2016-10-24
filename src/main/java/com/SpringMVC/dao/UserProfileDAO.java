package com.SpringMVC.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.SpringMVC.model.UserProfile;

@Repository 
public interface UserProfileDAO {

	public UserProfile findUser(int userid);

    public void save(UserProfile userProfile);
     
    public void update(UserProfile userProfile);

    public void delete(int userid);
     
    public UserProfile get(String username);
     
    public List<UserProfile> list(int teamid);
    
    public List<String> userList(int teamid);
}