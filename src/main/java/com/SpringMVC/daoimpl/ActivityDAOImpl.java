package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	
    public void saveOrUpdate(Activity activity) {
        if (activity.getactivityid() > 0)  {
            // update
            String sql = "UPDATE tblActivity SET activitydate=?, brandid=?, modelid=?, "
            		+ "demo=?, testdrive=?, quotation=?, remark1=?, remark2=?, remark3=? "
            		+ "WHERE activityid=?";
            this.getJdbcTemplate().update(sql, activity.getactivitydate(), 
            		activity.getbrandid(), activity.getmodelid(), 
            		activity.getdemo(), activity.gettestdrive(), activity.getquotation(), 
            		activity.getremark1(), activity.getremark2(), activity.getremark3(), 
            		activity.getactivityid());
        } else {
            // insert
            String sql = "INSERT INTO tblActivity "
            		+ "(prospectid, activitydate, brandid, modelid, "
            		+ "demo, testdrive, quotation, remark1, remark2, remark3) "
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		activity.getprospectid(), activity.getactivitydate(), 
            		activity.getbrandid(), activity.getmodelid(), 
            		activity.getdemo(), activity.gettestdrive(), activity.getquotation(), 
            		activity.getremark1(), activity.getremark2(), activity.getremark3());
            }
    }
    
    public void delete(int activityid) {
        String sql = "DELETE FROM tblActivity WHERE activityid=?";
        this.getJdbcTemplate().update(sql, activityid);
    }
    
    public List<Activity> list(int prospectid) {
        String sql = "SELECT a.activityid activityid, a.prospectid prospectid, a.activitydate activitydate, "
        		+ "a.brandid brandid, a.modelid modelid, a.demo demo, a.testdrive testdrive, a.quotation quotation, "
        		+ "b.brandname brandname, m.modelname modelname, "
        		+ "a.remark1 remark1, a.remark2 remark2, a.remark3 remark3 "
        		+ "FROM tblActivity a "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
        		+ "WHERE a.prospectid = " + prospectid;
        ActivityMapper mapper = new ActivityMapper();
        List<Activity> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Activity get(int activityid) {
	    String sql = "SELECT a.activityid activityid, a.prospectid prospectid, a.activitydate activitydate, "
        		+ "a.brandid brandid, a.modelid modelid, a.demo demo, a.testdrive testdrive, a.quotation quotation, "
        		+ "b.brandname brandname, m.modelname modelname, "
        		+ "a.remark1 remark1, a.remark2 remark2, a.remark3 remark3 "
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