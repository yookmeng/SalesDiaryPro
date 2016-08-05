package com.SpringMVC.daoimpl;

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
            String sql = "UPDATE tblUserTarget SET prospect=?, sales=?, totalsales=? "
            		+ "WHERE targetid=?";
            this.getJdbcTemplate().update(sql, 
            		userTarget.getprospect(), userTarget.getsales(), userTarget.gettotalsales(), 
            		userTarget.gettargetid());
        } else {
            // insert
            String sql = "INSERT INTO tblUserTarget "
            		+ "(userid, period, prospect, sales, totalsales) "
            		+ "VALUES (?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		userTarget.getuserid(), userTarget.getperiod(), userTarget.getprospect(),
            		userTarget.getsales(), userTarget.gettotalsales());
            }
    }
    
    public void delete(int targetid) {
        String sql = "DELETE FROM tblUserTarget WHERE targetid=?";
        this.getJdbcTemplate().update(sql, targetid);
    }
    
    public UserTarget get(int targetid) {
	    String sql = "SELECT * FROM tblUserTarget WHERE targetid="+targetid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<UserTarget>() {
	 
	        @Override
	        public UserTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                UserTarget userTarget = new UserTarget();
	                userTarget.settargetid(rs.getInt("targetid"));
	                userTarget.setuserid(rs.getInt("userid"));
	                userTarget.setperiod(rs.getDate("period"));
	                userTarget.setprospect(rs.getInt("prospect"));
	                userTarget.setsales(rs.getInt("sales"));
	                userTarget.settotalsales(rs.getFloat("totalsales"));
	                return userTarget;
	            }	 
	            return null;
	        }
        });
    }

    public List<UserTarget> list(int teamid) {
        String sql = "SELECT * FROM tblUserTarget WHERE teamid = " + teamid;
        UserTargetMapper mapper = new UserTargetMapper();
        List<UserTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
}