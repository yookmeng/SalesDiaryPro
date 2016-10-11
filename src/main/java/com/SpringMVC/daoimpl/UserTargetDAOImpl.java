package com.SpringMVC.daoimpl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
    public void saveOrUpdate(UserTarget userTarget) {
        if (userTarget.gettargetid() > 0)  {
            // update
            String sql = "UPDATE tblUserTarget SET prospect=?, testdrive=?, closed=? "
            		+ "WHERE targetid=?";
            this.getJdbcTemplate().update(sql, 
            		userTarget.getprospect(), userTarget.gettestdrive(), userTarget.getclosed(), 
            		userTarget.gettargetid());
        } else {
            // insert
            String sql = "INSERT INTO tblUserTarget "
            		+ "(userid, period, teamtargetid, prospect, testdrive, closed) "
            		+ "VALUES (?, ?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		userTarget.getuserid(), userTarget.getperiod(), userTarget.getteamtargetid(),
            		userTarget.getprospect(), userTarget.gettestdrive(), userTarget.getclosed());
            }
    }
    
    public void delete(int targetid) {
        String sql = "DELETE FROM tblUserTarget WHERE targetid=?";
        this.getJdbcTemplate().update(sql, targetid);
    }
    
    public UserTarget get(int targetid) {
	    String sql = "SELECT ut.targetid targetid, ut.userid userid, u.username username, "
	    		+ "ut.period period, CONVERT(varchar(7), ut.period, 111) displayperiod, "
	    		+ "ut.teamtargetid teamtargetid, ut.prospect prospect, "
	    		+ "ut.testdrive testdrive, ut.closed closed "
	    		+ "FROM tblUserTarget ut "
	    		+ "LEFT JOIN tblUser u ON u.userid = ut.userid "	    		
	    		+ "WHERE ut.targetid="+targetid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<UserTarget>() {
	 
	        @Override
	        public UserTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                UserTarget userTarget = new UserTarget();
	                userTarget.settargetid(rs.getInt("targetid"));
	                userTarget.setuserid(rs.getInt("userid"));
	                userTarget.setusername(rs.getString("username"));	                
	                userTarget.setperiod(rs.getDate("period"));
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
	    String sql = "SELECT ut.targetid targetid, ut.userid userid, u.username username, "
	    		+ "ut.period period, CONVERT(varchar(7), ut.period, 111) displayperiod, "
	    		+ "ut.teamtargetid teamtargetid, ut.prospect prospect, "
	    		+ "ut.testdrive testdrive, ut.closed closed "
	    		+ "FROM tblUserTarget ut "
	    		+ "LEFT JOIN tblUser u ON u.userid = ut.userid "	    		
	    		+ "WHERE ut.period='"+period+"' "
	    		+ "AND ut.userid="+userid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<UserTarget>() {
	 
	        @Override
	        public UserTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                UserTarget userTarget = new UserTarget();
	                userTarget.settargetid(rs.getInt("targetid"));
	                userTarget.setuserid(rs.getInt("userid"));
	                userTarget.setusername(rs.getString("username"));	                
	                userTarget.setperiod(rs.getDate("period"));
	                userTarget.setdisplayperiod(rs.getString("displayperiod"));
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
	    String sql = "SELECT ut.targetid targetid, ut.userid userid, up.username username, "
	    		+ "ut.period period, CONVERT(varchar(7), ut.period, 111) displayperiod, "
	    		+ "ut.teamtargetid teamtargetid, ut.prospect prospect, "
	    		+ "ut.testdrive testdrive, ut.closed closed "
	    		+ "FROM tblUserTarget ut "
	    		+ "LEFT JOIN tblUserProfile up ON up.userid = ut.userid "	    		
        		+ "WHERE ut.period = '"+period+"' "
        		+ "AND up.teamid ="+teamid;
        UserTargetMapper mapper = new UserTargetMapper();
        List<UserTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<UserTarget> listByUser(int userid) {
	    String sql = "SELECT ut.targetid targetid, ut.userid userid, up.username username, "
	    		+ "ut.period period, CONVERT(varchar(7), ut.period, 111) displayperiod, "
	    		+ "ut.teamtargetid teamtargetid, ut.prospect prospect, "
	    		+ "ut.testdrive testdrive, ut.closed closed "
	    		+ "FROM tblUserTarget ut "
	    		+ "LEFT JOIN tblUserProfile up ON up.userid = ut.userid "	    		
        		+ "WHERE ut.userid ="+userid+" "
        		+ "AND ut.period > dateadd(m,-2,getdate()) ";
        UserTargetMapper mapper = new UserTargetMapper();
        List<UserTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
}