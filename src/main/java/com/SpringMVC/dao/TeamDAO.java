package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Team;
@Repository 
public interface TeamDAO {
     
    public void saveOrUpdate(Team team);
     
    public void delete(int teamid);
     
    public Team get(int teamid);
     
    public List<Team> list(int branchid);
    
    public List<String> teamList();
}