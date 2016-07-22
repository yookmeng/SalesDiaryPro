package com.SpringMVC.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository 
public interface CodeMasterDAO {	
    public List<String> getCode(String codetype);
}