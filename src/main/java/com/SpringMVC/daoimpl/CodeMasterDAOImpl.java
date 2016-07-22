package com.SpringMVC.daoimpl;

import java.util.List;
 
import javax.sql.DataSource; 
import com.SpringMVC.dao.CodeMasterDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class CodeMasterDAOImpl extends JdbcDaoSupport implements CodeMasterDAO {
 
    @Autowired
    public CodeMasterDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    @Override
    public List<String> getCode(String codetype) {
        String sql = "SELECT codeid FROM tblCodeMaster WHERE codetype = ? ";
         
        Object[] params = new Object[] { codetype };         
        List<String> codes = this.getJdbcTemplate().queryForList(sql,params, String.class);         
        return codes;
    }
}