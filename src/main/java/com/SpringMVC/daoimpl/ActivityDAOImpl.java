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
	    	CallableStatement proc = conn.prepareCall("{ ? = call spActivityInsUpd("
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?) }");
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
	    	proc.setBoolean(11, activity.getfollowup());
	    	proc.setBoolean(12, activity.getclosed());
	    	proc.setBoolean(13, activity.getlost());
	    	proc.setBoolean(14, activity.getdemostatus());
	    	proc.setBoolean(15, activity.gettestdrivestatus());
	    	proc.setString(16, activity.getfollowupremark());
	    	proc.setBoolean(17, activity.getfollowupstatus());
	    	proc.setInt(18, activity.getquotationid());
	    	proc.setInt(19, activity.getclosedid());
	    	proc.setString(20, activity.getlostremark());
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
	    	CallableStatement proc = conn.prepareCall("{ ? = call spActivityInsUpd("
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?) }");
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
	    	proc.setBoolean(11, activity.getfollowup());
	    	proc.setBoolean(12, activity.getclosed());
	    	proc.setBoolean(13, activity.getlost());
	    	proc.setBoolean(14, activity.getdemostatus());
	    	proc.setBoolean(15, activity.gettestdrivestatus());
	    	proc.setString(16, activity.getfollowupremark());
	    	proc.setBoolean(17, activity.getfollowupstatus());
	    	proc.setInt(18, activity.getquotationid());
	    	proc.setInt(19, activity.getclosedid());
	    	proc.setString(20, activity.getlostremark());
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
	    	CallableStatement proc = conn.prepareCall("{ ? = call spActivityDel(?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setInt(2, activityid);
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public List<Activity> list(int prospectid) {
        String sql = "SELECT activityid, a.prospectid, p.firstname AS prospectname, activitydate, "
        		+ "a.brandid AS brandid, a.modelid AS modelid, "
        		+ "b.brandname AS brandname, m.modelname AS modelname, "
        		+ "demo, testdrive, quotation, followup, closed, lost, "
        		+ "demostatus, testdrivestatus, followupremark, followupstatus, "
        		+ "quotationid, quotationpdflink, closedid, lostremark "
        		+ "FROM tblActivity a "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = a.prospectid "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
        		+ "WHERE a.prospectid = " + prospectid + " "
        		+ "ORDER BY activitydate ";
        ActivityMapper mapper = new ActivityMapper();
        List<Activity> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Activity> listByUser(int userid) {
        String sql = "SELECT activityid, a.prospectid, p.firstname AS prospectname, activitydate, "
        		+ "a.brandid AS brandid, a.modelid AS modelid, "
        		+ "b.brandname AS brandname, m.modelname AS modelname, "
        		+ "demo, testdrive, quotation, followup, closed, lost, "
        		+ "demostatus, testdrivestatus, followupremark, followupstatus, "
        		+ "quotationid, quotationpdflink, closedid, lostremark "
        		+ "FROM tblActivity a "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = a.prospectid "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
        		+ "WHERE p.userid = " + userid + " "
				+ "AND p.status IN ('Hot', 'Warm') "
        		+ "AND ((demo = 'TRUE' AND demostatus = 'FALSE') OR "
        		+ "(testdrive = 'TRUE' AND testdrivestatus = 'FALSE') OR "
        		+ "(followup = 'TRUE' AND followupstatus = 'FALSE'))"        		
        		+ "ORDER BY activitydate ";
        ActivityMapper mapper = new ActivityMapper();
        List<Activity> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Activity> listByTeam(int teamid) {
        String sql = "SELECT activityid, a.prospectid, p.firstname AS prospectname, activitydate, "
        		+ "a.brandid AS brandid, a.modelid AS modelid, "
        		+ "b.brandname AS brandname, m.modelname AS modelname, "
        		+ "demo, testdrive, quotation, followup, closed, lost, "
        		+ "demostatus, testdrivestatus, followupremark, followupstatus, "
        		+ "quotationid, quotationpdflink, closedid, lostremark "
        		+ "FROM tblActivity a "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = a.prospectid "
        		+ "LEFT JOIN tblUser u ON u.userid = p.userid "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
        		+ "WHERE u.teamid = " + teamid + " "
				+ "AND p.status IN ('Hot', 'Warm') "
        		+ "AND ((demo = 'TRUE' AND demostatus = 'FALSE') OR "
        		+ "(testdrive = 'TRUE' AND testdrivestatus = 'FALSE') OR "
        		+ "(followup = 'TRUE' AND followupstatus = 'FALSE'))"        		
        		+ "ORDER BY activitydate ";
        ActivityMapper mapper = new ActivityMapper();
        List<Activity> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Activity> listByBranch(int branchid) {
        String sql = "SELECT activityid, a.prospectid, p.firstname AS prospectname, activitydate, "
        		+ "a.brandid AS brandid, a.modelid AS modelid, "
        		+ "b.brandname AS brandname, m.modelname AS modelname, "
        		+ "demo, testdrive, quotation, followup, closed, lost, "
        		+ "demostatus, testdrivestatus, followupremark, followupstatus, "
        		+ "quotationid, quotationpdflink, closedid, lostremark "
        		+ "FROM tblActivity a "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = a.prospectid "
        		+ "LEFT JOIN tblUser u ON u.userid = p.userid "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
        		+ "WHERE u.branchid = " + branchid + " "
				+ "AND p.status IN ('Hot', 'Warm') "
        		+ "AND ((demo = 'TRUE' AND demostatus = 'FALSE') OR "
        		+ "(testdrive = 'TRUE' AND testdrivestatus = 'FALSE') OR "
        		+ "(followup = 'TRUE' AND followupstatus = 'FALSE'))"        		
        		+ "ORDER BY activitydate ";
        ActivityMapper mapper = new ActivityMapper();
        List<Activity> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Activity> listByCompany(int companyid) {
        String sql = "SELECT activityid, a.prospectid, p.firstname AS prospectname, activitydate, "
        		+ "a.brandid AS brandid, a.modelid AS modelid, "
        		+ "b.brandname AS brandname, m.modelname AS modelname, "
        		+ "demo, testdrive, quotation, followup, closed, lost, "
        		+ "demostatus, testdrivestatus, followupremark, followupstatus, "
        		+ "quotationid, quotationpdflink, closedid, lostremark "
        		+ "FROM tblActivity a "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = a.prospectid "
        		+ "LEFT JOIN tblUser u ON u.userid = p.userid "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
        		+ "WHERE u.companyid = " + companyid + " "
				+ "AND p.status IN ('Hot', 'Warm') "
        		+ "AND ((demo = 'TRUE' AND demostatus = 'FALSE') OR "
        		+ "(testdrive = 'TRUE' AND testdrivestatus = 'FALSE') OR "
        		+ "(followup = 'TRUE' AND followupstatus = 'FALSE'))"        		
        		+ "ORDER BY activitydate ";
        ActivityMapper mapper = new ActivityMapper();
        List<Activity> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Activity get(int activityid) {
        String sql = "SELECT activityid, a.prospectid, p.firstname AS prospectname, activitydate, "
        		+ "a.brandid AS brandid, a.modelid AS modelid, "
        		+ "b.brandname AS brandname, m.modelname AS modelname, "
        		+ "demo, testdrive, quotation, followup, closed, lost, "
        		+ "demostatus, testdrivestatus, followupremark, followupstatus, "
        		+ "quotationid, quotationpdflink, closedid, lostremark "
        		+ "FROM tblActivity a "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = a.prospectid "
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
	                activity.setprospectname(rs.getString("prospectname"));
	                activity.setactivitydate(rs.getDate("activitydate"));
	                activity.setbrandid(rs.getInt("brandid"));
	                activity.setbrandname(rs.getString("brandname"));
	                activity.setmodelid(rs.getInt("modelid"));
	                activity.setmodelname(rs.getString("modelname"));
	                activity.setdemo(rs.getBoolean("demo"));
	                activity.settestdrive(rs.getBoolean("testdrive"));
	                activity.setquotation(rs.getBoolean("quotation"));
	                activity.setfollowup(rs.getBoolean("followup"));
	                activity.setclosed(rs.getBoolean("closed"));
	                activity.setlost(rs.getBoolean("lost"));
	                activity.setdemostatus(rs.getBoolean("demostatus"));
	                activity.settestdrivestatus(rs.getBoolean("testdrivestatus"));
	                activity.setfollowupremark(rs.getString("followupremark"));
	                activity.setfollowupstatus(rs.getBoolean("followupstatus"));
	                activity.setquotationid(rs.getInt("quotationid"));
	                activity.setquotationpdflink(rs.getString("quotationpdflink"));
	                activity.setclosedid(rs.getInt("closedid"));
	                activity.setlostremark(rs.getString("lostremark"));
	                return activity;
	            }	 
	            return null;
	        }
        });
    }

    public int getlastactivityid(int prospectid) {
    	String sql = "SELECT MAX(activityid) FROM tblActivity WHERE prospectid = ?";
        int activityid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {prospectid}, int.class);
        return activityid;
    }
}