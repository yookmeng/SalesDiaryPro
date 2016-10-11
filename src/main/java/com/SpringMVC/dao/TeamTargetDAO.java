package com.SpringMVC.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.TeamTarget;
@Repository 
public interface TeamTargetDAO {
     
    public void saveOrUpdate(TeamTarget teamTarget);
     
    public void delete(int targetid);
     
    public TeamTarget get(int targetid);

    public TeamTarget getByPeriod(String period, int teamid);

    public TeamTarget getByTeam(int teamid);

    public List<TeamTarget> list(Date period, int branchid);

    public List<TeamTarget> listByTeam(int teamid);
}