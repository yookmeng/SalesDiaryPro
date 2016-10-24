	package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Model;
import com.SpringMVC.dao.ModelDAO;
import com.SpringMVC.mapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class ModelDAOImpl extends JdbcDaoSupport implements ModelDAO { 
    @Autowired
    public ModelDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    @Override
    public List<String> getModels(int brandid) {
        String sql = "Select modelname FROM tblModel WHERE brandid = " + brandid;         
        List<String> brands = this.getJdbcTemplate().queryForList(sql, String.class);         
        return brands;
    }

    public void save(Model model) {
        // insert
        String sql = "INSERT INTO tblModel (brandid, modelname, price) VALUES (?, ?, ?)";
        this.getJdbcTemplate().update(sql, model.getbrandid(), model.getmodelname(), model.getprice());
    }
    
    public void update(Model model) {
        // update
        String sql = "UPDATE tblModel SET modelname=?, price=? WHERE modelid=?";
        this.getJdbcTemplate().update(sql, model.getmodelname(), model.getprice(), model.getmodelid());
    }

    public void delete(int modelid) {
        String sql = "DELETE FROM tblModel WHERE modelid=?";
        this.getJdbcTemplate().update(sql, modelid);
    }
    
    public List<Model> list(int brandid) {
        String sql = "SELECT * FROM tblModel WHERE brandid = " + brandid;
        ModelMapper mapper = new ModelMapper();
        List<Model> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Model get(int modelid) {
	    String sql = "SELECT * FROM tblModel WHERE modelid=" + modelid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Model>() {
	 
	        @Override
	        public Model extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Model model = new Model();
	                model.setmodelid(rs.getInt("modelid"));
	                model.setmodelname(rs.getString("modelname"));
	                model.setbrandid(rs.getInt("brandid"));
	                model.setprice(rs.getFloat("price"));
	                return model;
	            }	 
	            return null;
	        }
        });
    }

    public Model getByName(String modelname) {
	    String sql = "SELECT * FROM tblModel WHERE modelname='" + modelname + "'";
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Model>() {
	 
	        @Override
	        public Model extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Model model = new Model();
	                model.setmodelid(rs.getInt("modelid"));
	                model.setmodelname(rs.getString("modelname"));
	                model.setbrandid(rs.getInt("brandid"));
	                model.setprice(rs.getFloat("price"));
	                return model;
	            }	 
	            return null;
	        }
        });
    }

    public boolean isExist(Model model) {
        return getByName(model.getmodelname())!=null;
    }}