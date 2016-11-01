package com.SpringMVC.daoimpl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
 
import javax.sql.DataSource;

import com.SpringMVC.model.UserTarget;
import com.SpringMVC.dao.UserTargetDAO;
import com.SpringMVC.mapper.UserTargetMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class UserTargetDAOImpl extends JdbcDaoSupport implements UserTargetDAO {
 
    @Autowired
    public UserTargetDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(UserTarget userTarget) {
        String sql = "INSERT INTO tblUserTarget "
        		+ "(userid, period, teamtargetid, prospect, testdrive, closed) "
        		+ "VALUES (?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		userTarget.getuserid(), userTarget.getperiod(), userTarget.getteamtargetid(),
        		userTarget.getprospect(), userTarget.gettestdrive(), userTarget.getclosed());
    }
    
    public void update(UserTarget userTarget) {
        String sql = "UPDATE tblUserTarget SET prospect=?, testdrive=?, closed=? "
        		+ "WHERE targetid=?";
        this.getJdbcTemplate().update(sql, 
        		userTarget.getprospect(), userTarget.gettestdrive(), userTarget.getclosed(), 
        		userTarget.gettargetid());
    }

    public void delete(int targetid) {
        String sql = "DELETE FROM tblUserTarget WHERE targetid=?";
        this.getJdbcTemplate().update(sql, targetid);
    }
    
    public UserTarget get(int targetid) {
	    String sql = "SELECT ut.targetid AS targetid, "
	    		+ "ut.userid AS userid, "
	    		+ "u.username AS username, "
	    		+ "ut.period AS period, "
	    		+ "ut.teamtargetid AS teamtargetid, "
	    		+ "ut.prospect AS prospect, "
	    		+ "ut.testdrive AS testdrive, "
	    		+ "ut.closed AS closed "
	    		+ "FROM tblUserTarget ut "
	    		+ "LEFT JOIN tblUser u ON u.userid = ut.userid "	    		
	    		+ "WHERE ut.targetid="+targetid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<UserTarget>() {
	 
	        @Override
	        public UserTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
	                UserTarget userTarget = new UserTarget();
	                userTarget.settargetid(rs.getInt("targetid"));
	                userTarget.setuserid(rs.getInt("userid"));
	                userTarget.setusername(rs.getString("username"));	                
	                userTarget.setperiod(rs.getDate("period"));
	                userTarget.setdisplayperiod(dateFormat.format(rs.getDate("period")));
	                userTarget.setteamtargetid(rs.getInt("teamtargetid"));
	                userTarget.setprospect(rs.getInt("prospect"));
	                userTarget.settestdrive(rs.getInt("testdrive"));
	                userTarget.setclosed(rs.getInt("closed"));
	                return userTarget;
	            }	 
	            return null;
	        }
        });
    }

    public UserTarget getByPeriod(String period, int userid) {
	    String sql = "SELECT ut.targetid AS targetid, "
	    		+ "ut.userid AS userid, "
	    		+ "u.username AS username, "
	    		+ "ut.period AS period, "
	    		+ "ut.teamtargetid AS teamtargetid, "
	    		+ "ut.prospect AS prospect, "
	    		+ "ut.testdrive AS testdrive, "
	    		+ "ut.closed AS closed "
	    		+ "FROM tblUserTarget ut "
	    		+ "LEFT JOIN tblUser u ON u.userid = ut.userid "	    		
	    		+ "WHERE ut.period='"+period+"' "
	    		+ "AND ut.userid="+userid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<UserTarget>() {
	 
	        @Override
	        public UserTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
	                UserTarget userTarget = new UserTarget();
	                userTarget.settargetid(rs.getInt("targetid"));
	                userTarget.setuserid(rs.getInt("userid"));
	                userTarget.setusername(rs.getString("username"));	                
	                userTarget.setperiod(rs.getDate("period"));
	                userTarget.setdisplayperiod(dateFormat.format(rs.getDate("period")));
	                userTarget.setteamtargetid(rs.getInt("teamtargetid"));
	                userTarget.setprospect(rs.getInt("prospect"));
	                userTarget.settestdrive(rs.getInt("testdrive"));
	                userTarget.setclosed(rs.getInt("closed"));
	                return userTarget;
	            }	 
	            return null;
	        }
        });
    }

    public List<UserTarget> list(Date period, int teamid) {
	    String sql = "SELECT ut.targetid AS targetid, "
	    		+ "ut.userid AS userid, "
	    		+ "up.username AS username, "
	    		+ "ut.period AS period, "
	    		+ "ut.teamtargetid AS teamtargetid, "
	    		+ "ut.prospect AS prospect, "
	    		+ "ut.testdrive AS testdrive, "
	    		+ "ut.closed AS closed "
	    		+ "FROM tblUserTarget ut "
	    		+ "LEFT JOIN tblUserProfile up ON up.userid = ut.userid "	    		
        		+ "WHERE ut.period = '"+period+"' "
        		+ "AND up.teamid ="+teamid;
        UserTargetMapper mapper = new UserTargetMapper();
        List<UserTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<UserTarget> listByUser(int userid) {
	    String sql = "SELECT ut.targetid AS targetid, "
	    		+ "ut.userid AS userid, "
	    		+ "up.username AS username, "
	    		+ "ut.period AS period, "
	    		+ "ut.teamtargetid AS teamtargetid, "
	    		+ "ut.prospect AS prospect, "
	    		+ "ut.testdrive AS testdrive, "
	    		+ "ut.closed AS closed "
	    		+ "FROM tblUserTarget ut "
	    		+ "LEFT JOIN tblUserProfile up ON up.userid = ut.userid "	    		
        		+ "WHERE ut.userid ="+userid;
        UserTargetMapper mapper = new UserTargetMapper();
        List<UserTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
}