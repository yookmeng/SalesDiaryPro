	package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Product;
import com.SpringMVC.dao.ProductDAO;
import com.SpringMVC.mapper.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class ProductDAOImpl extends JdbcDaoSupport implements ProductDAO { 
    @Autowired
    public ProductDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Product product) {
        // insert
        String sql = "INSERT INTO tblProduct(productname, price) VALUES (?, ?)";
        this.getJdbcTemplate().update(sql, product.getproductname(), product.getprice());
    }
    
    public void update(Product product) {
        // update
        String sql = "UPDATE tblProduct SET productname=?, price=? WHERE productid=?";
        this.getJdbcTemplate().update(sql, 
        		product.getproductname(), product.getprice(), product.getproductid());
    }

    public void delete(int productid) {
        String sql = "DELETE FROM tblProduct WHERE productid=?";
        this.getJdbcTemplate().update(sql, productid);
    }
    
    public List<Product> list(int companyid) {
        String sql = "SELECT * FROM tblProduct WHERE companyid = " + companyid;
        ProductMapper mapper = new ProductMapper();
        List<Product> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Product get(int productid) {
	    String sql = "SELECT * FROM tblProduct WHERE productid=" + productid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Product>() {
	 
	        @Override
	        public Product extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Product product = new Product();
	                product.setproductid(rs.getInt("productid"));
	                product.setproductname(rs.getString("productname"));
	                product.setcompanyid(rs.getInt("companyid"));
	                product.setprice(rs.getFloat("price"));
	                return product;
	            }	 
	            return null;
	        }
        });
    }

    public Product getByName(String productname) {
	    String sql = "SELECT * FROM tblProduct WHERE productname='" + productname + "'";
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Product>() {
	 
	        @Override
	        public Product extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Product product = new Product();
	                product.setproductid(rs.getInt("productid"));
	                product.setproductname(rs.getString("productname"));
	                product.setcompanyid(rs.getInt("companyid"));
	                product.setprice(rs.getFloat("price"));
	                return product;
	            }	 
	            return null;
	        }
        });
    }

    public boolean isExist(Product product) {
        return getByName(product.getproductname())!=null;
    }
}