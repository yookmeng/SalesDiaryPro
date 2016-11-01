package com.SpringMVC.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Activity;
import com.SpringMVC.dao.ActivityDAO;
import com.SpringMVC.mapper.ActivityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class ActivityDAOImpl extends JdbcDaoSupport implements ActivityDAO {
 
    @Autowired
    public ActivityDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Activity activity) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spActivityInsUpdDel(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setString(2, "0");
	    	proc.setInt(3, activity.getactivityid());
	    	proc.setInt(4, activity.getprospectid());
	    	proc.setDate(5, activity.getactivitydate());
	    	proc.setInt(6, activity.getbrandid());
	    	proc.setInt(7, activity.getmodelid());
	    	proc.setBoolean(8, activity.getdemo());
	    	proc.setBoolean(9, activity.gettestdrive());
	    	proc.setBoolean(10, activity.getquotation());
	    	proc.setBoolean(11, activity.getlinkevent());
	    	proc.setString(12, activity.getremark1());
	    	proc.setString(13, activity.getremark2());
	    	proc.setString(14, activity.getremark3());
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public void update(Activity activity) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spActivityInsUpdDel(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setString(2, "1");
	    	proc.setInt(3, activity.getactivityid());
	    	proc.setInt(4, activity.getprospectid());
	    	proc.setDate(5, activity.getactivitydate());
	    	proc.setInt(6, activity.getbrandid());
	    	proc.setInt(7, activity.getmodelid());
	    	proc.setBoolean(8, activity.getdemo());
	    	proc.setBoolean(9, activity.gettestdrive());
	    	proc.setBoolean(10, activity.getquotation());
	    	proc.setBoolean(11, activity.getlinkevent());
	    	proc.setString(12, activity.getremark1());
	    	proc.setString(13, activity.getremark2());
	    	proc.setString(14, activity.getremark3());
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }

    public void delete(int activityid) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spActivityInsUpdDel(?, ?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setString(2, "2");
	    	proc.setInt(3, activityid);
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public List<Activity> list(int prospectid) {
        String sql = "SELECT a.activityid AS activityid, "
        		+ "a.prospectid AS prospectid, "
        		+ "a.activitydate AS activitydate, "
        		+ "a.brandid AS brandid, "
        		+ "a.modelid AS modelid, "
        		+ "a.demo AS demo, "
        		+ "a.testdrive AS testdrive, "
        		+ "a.quotation AS quotation, "
        		+ "a.linkevent AS linkevent, "
        		+ "b.brandname AS brandname, "
        		+ "m.modelname AS modelname, "
        		+ "a.remark1 AS remark1, "
        		+ "a.remark2 AS remark2, "
        		+ "a.remark3 AS remark3 "
        		+ "FROM tblActivity a "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
        		+ "WHERE a.prospectid = " + prospectid + " "
        		+ "ORDER BY a.activitydate ";
        ActivityMapper mapper = new ActivityMapper();
        List<Activity> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Activity get(int activityid) {
	    String sql = "SELECT a.activityid AS activityid, "
	    		+ "a.prospectid AS prospectid, "
	    		+ "a.activitydate AS activitydate, "
        		+ "a.brandid AS brandid, "
        		+ "a.modelid AS modelid, "
        		+ "a.demo AS demo, "
        		+ "a.testdrive AS testdrive, "
        		+ "a.quotation AS quotation, "
        		+ "a.linkevent AS linkevent, "
        		+ "b.brandname AS brandname, "
        		+ "m.modelname AS modelname, "
        		+ "a.remark1 AS remark1, "
        		+ "a.remark2 AS remark2, "
        		+ "a.remark3 AS remark3 "
        		+ "FROM tblActivity a "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
	    		+ "WHERE activityid=" + activityid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Activity>() {
	 
	        @Override
	        public Activity extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Activity activity = new Activity();
	                activity.setactivityid(rs.getInt("activityid"));
	                activity.setprospectid(rs.getInt("prospectid"));
	                activity.setactivitydate(rs.getDate("activitydate"));
	                activity.setbrandid(rs.getInt("brandid"));
	                activity.setbrandname(rs.getString("brandname"));
	                activity.setmodelid(rs.getInt("modelid"));
	                activity.setmodelname(rs.getString("modelname"));
	                activity.setdemo(rs.getBoolean("demo"));
	                activity.settestdrive(rs.getBoolean("testdrive"));
	                activity.setquotation(rs.getBoolean("quotation"));
	                activity.setlinkevent(rs.getBoolean("linkevent"));
	                activity.setremark1(rs.getString("remark1"));
	                activity.setremark2(rs.getString("remark2"));
	                activity.setremark3(rs.getString("remark3"));
	                return activity;
	            }	 
	            return null;
	        }
        });
    }
}