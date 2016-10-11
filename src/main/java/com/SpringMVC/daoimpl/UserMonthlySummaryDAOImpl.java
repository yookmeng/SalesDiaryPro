package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.SpringMVC.model.UserMonthlySummary;
import com.SpringMVC.dao.UserMonthlySummaryDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class UserMonthlySummaryDAOImpl extends JdbcDaoSupport implements UserMonthlySummaryDAO {
 
    @Autowired
    public UserMonthlySummaryDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    public UserMonthlySummary get(String period, int userid) {
	    String sql = "EXEC spGetUserMonthlySummary '"+userid+"', '"+period+"' ";
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<UserMonthlySummary>() {
	 
	        @Override
	        public UserMonthlySummary extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	UserMonthlySummary userMonthlySummary = new UserMonthlySummary();
	            	userMonthlySummary.setperiod(rs.getDate("period"));
	            	userMonthlySummary.setuserid(rs.getInt("userid"));
	            	userMonthlySummary.setusername(rs.getString("username"));
	            	userMonthlySummary.settargetprospect(rs.getInt("targetprospect"));
	            	userMonthlySummary.settargettestdrive(rs.getInt("targettestdrive"));
	            	userMonthlySummary.settargetclosed(rs.getInt("targetclosed"));
	            	userMonthlySummary.setactualprospect(rs.getInt("actualprospect"));
	            	userMonthlySummary.setactualtestdrive(rs.getInt("actualtestdrive"));
	            	userMonthlySummary.setactualclosed(rs.getInt("actualclosed"));
	            	userMonthlySummary.setpercentprospect(rs.getFloat("percentprospect"));
	            	userMonthlySummary.setpercenttestdrive(rs.getFloat("percenttestdrive"));
	            	userMonthlySummary.setpercentclosed(rs.getFloat("percentclosed"));
	                return userMonthlySummary;
	            }	 
	            return null;
	        }
        });
    }
}