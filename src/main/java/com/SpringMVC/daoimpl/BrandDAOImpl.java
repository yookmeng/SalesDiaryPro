	package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Brand;
import com.SpringMVC.dao.BrandDAO;
import com.SpringMVC.mapper.BrandMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class BrandDAOImpl extends JdbcDaoSupport implements BrandDAO { 
    @Autowired
    public BrandDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<String> getBrands(int companyid) {
        String sql = "Select brandname FROM tblBrand WHERE companyid = " + companyid;
         
        List<String> brands = this.getJdbcTemplate().queryForList(sql, String.class);         
        return brands;
    }
    
    @Override
    public void saveOrUpdate(Brand brand) {
        if (brand.getbrandid() > 0)  {
            // update
            String sql = "UPDATE tblBrand SET brandname=? WHERE brandid=?";
            this.getJdbcTemplate().update(sql, brand.getbrandname(), brand.getbrandid());
        } else {
            // insert
            String sql = "INSERT INTO tblBrand (companyid, brandname) VALUES (?, ?)";
            this.getJdbcTemplate().update(sql, brand.getcompanyid(), brand.getbrandname());
            }
    }
    
    @Override
    public void delete(int brandid) {
        String sql = "DELETE FROM tblBrand WHERE brandid=?";
        this.getJdbcTemplate().update(sql, brandid);
    }
    
    @Override
    public List<Brand> list(int companyid) {
        String sql = "SELECT * FROM tblBrand WHERE companyid = " + companyid;
        BrandMapper mapper = new BrandMapper();
        List<Brand> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
    @Override
    public Brand get(int brandid) {
	    String sql = "SELECT * FROM tblBrand WHERE brandid=" + brandid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Brand>() {
	 
	        @Override
	        public Brand extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Brand brand = new Brand();
	                brand.setbrandid(rs.getInt("brandid"));
	                brand.setbrandname(rs.getString("brandname"));
	                brand.setcompanyid(rs.getInt("companyid"));
	                return brand;
	            }	 
	            return null;
	        }
        });
    }
}