package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import com.SpringMVC.model.Order;

import com.SpringMVC.dao.OrderDAO;
import com.SpringMVC.mapper.OrderMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class OrderDAOImpl extends JdbcDaoSupport implements OrderDAO {
 
    @Autowired
    public OrderDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Order order) {
        String sql = "INSERT INTO tblOrder "
        		+ "(projectid, productid, quantity, price, discount, amount) "
        		+ "VALUES (?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		order.getprojectid(), order.getproductid(), order.getquantity(), 
        		order.getprice(), order.getdiscount(), order.getamount());
        }
    
    public void update(Order order) {
        String sql = "UPDATE tblOrder "
        		+ "SET productid=?, quantity=?, price=?, discount=?, amount=? "
        		+ "WHERE orderid=?";
        this.getJdbcTemplate().update(sql, 
        		order.getproductid(), order.getquantity(), order.getprice(),
        		order.getdiscount(), order.getamount(), order.getorderid());
    }

    public void delete(int orderid) {
        String sql = "DELETE FROM tblOrder WHERE orderid=?";
        this.getJdbcTemplate().update(sql, orderid);
    }
    
    public Order get(int orderid) {
        String sql = "SELECT o.orderid, o.projectid, pj.projectname, "
        		+ "o.productid, pd.productname, o.quantity, o.price, o.discount, o.amount "
        		+ "FROM tblOrder o "
        		+ "LEFT JOIN tblProject pj ON pj.projectid = o.projectid "
        		+ "LEFT JOIN tblProduct pd ON pd.productid = o.productid "
	    		+ "WHERE o.orderid = " + orderid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Order>() {
	 
	        @Override
	        public Order extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Order order = new Order();
	            	order.setorderid(rs.getInt("orderid"));
	            	order.setprojectid(rs.getInt("projectid"));
	            	order.setprojectname(rs.getString("projectname"));
	            	order.setproductid(rs.getInt("productid"));
	            	order.setproductname(rs.getString("productname"));
	            	order.setquantity(rs.getInt("quantity"));
	            	order.setprice(rs.getFloat("price"));
	            	order.setdiscount(rs.getInt("discount"));
	            	order.setamount(rs.getFloat("amount"));
	                return order;
	            }	 
	            return null;
	        }
        });
    }

    public List<Order> list(int projectid) {
        String sql = "SELECT o.orderid, o.projectid, pj.projectname, "
        		+ "o.productid, pd.productname, o.quantity, o.price, o.discount, o.amount "
        		+ "FROM tblOrder o "
        		+ "LEFT JOIN tblProject pj ON pj.projectid = o.projectid "
        		+ "LEFT JOIN tblProduct pd ON pd.productid = o.productid "
	    		+ "WHERE o.projectid = " + projectid;
        
        OrderMapper mapper = new OrderMapper();
        List<Order> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
}