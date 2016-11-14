package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface CommonDAO {	
    public List<String> periodList();
}