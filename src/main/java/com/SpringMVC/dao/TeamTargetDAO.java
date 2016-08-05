package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.TeamTarget;
@Repository 
public interface TeamTargetDAO {
     
    public void saveOrUpdate(TeamTarget teamTarget);
     
    public void delete(int targetid);
     
    public TeamTarget get(int targetid);

    public List<TeamTarget> list(int teamid);
}