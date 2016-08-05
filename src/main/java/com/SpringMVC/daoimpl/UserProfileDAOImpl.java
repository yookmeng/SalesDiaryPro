package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;

import com.SpringMVC.model.UserProfile;
import com.SpringMVC.mapper.UserProfileMapper;
import com.SpringMVC.dao.UserProfileDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class UserProfileDAOImpl extends JdbcDaoSupport implements UserProfileDAO {
    @Autowired
    public UserProfileDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    @Override
    public UserProfile findUser(int userid) {
        String sql = "SELECT u.userid userid, u.username username, u.password password, r.role role, up.teamid teamid, up.mobile mobile, up.email email "
        		+ "FROM	tblUser u "
				+ "LEFT JOIN tblRole r ON u.username = r.username "
				+ "LEFT JOIN tblUserProfile up ON u.userid = up.userid "
                + "where u.userid = ? ";
 
        Object[] params = new Object[] { userid };
        UserProfileMapper mapper = new UserProfileMapper();
        try {
            UserProfile userProfile = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userProfile;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    public void saveOrUpdate(UserProfile userProfile) {
        if (userProfile.getuserid() > 0)  {
        	String sqlUser = "UPDATE tblUser SET password=? WHERE userid=?";
            this.getJdbcTemplate().update(sqlUser, 
            		userProfile.getpassword(), userProfile.getuserid());
        	
    		String sqlRole = "UPDATE tblRole SET role=? WHERE username=?";
            this.getJdbcTemplate().update(sqlRole, 
            		userProfile.getrole(), userProfile.getusername());
            
            // update if role = USER then update tblUserProfile else no need        	
            String sql = "UPDATE tblUserProfile SET "
            		+ "username=?, teamid=?, mobile=?, email=? "
            		+ "WHERE userid=? ";
            this.getJdbcTemplate().update(sql, 
            		userProfile.getusername(), userProfile.getteamid(),
            		userProfile.getmobile(), userProfile.getemail(), userProfile.getuserid());            	
        } else {
            // insert
        	String sql = "EXEC spCreateMember ?, ?, ?, ?, ?, ? ";
            this.getJdbcTemplate().update(sql, 
            		userProfile.getusername(), userProfile.getpassword(), userProfile.getrole(), 
            		userProfile.getteamid(), userProfile.getmobile(), userProfile.getemail());
        }
    }
    
    public void delete(int userid) {
        String sql = "DELETE FROM tblUserProfile WHERE userid=?";
        this.getJdbcTemplate().update(sql, userid);
    }
    
    @Override
    public List<UserProfile> list(int teamid) {
        String sql = "SELECT u.userid AS userid, u.username AS username, u.password AS password, r.role AS role, up.teamid AS teamid, up.mobile AS mobile, up.email AS email "
        		+ "FROM	tblUser u "
				+ "LEFT JOIN tblRole r ON u.username = r.username "
				+ "LEFT JOIN tblUserProfile up ON u.userid = up.userid "
				+ "WHERE up.teamid = " + teamid;
        UserProfileMapper mapper = new UserProfileMapper();
        List<UserProfile> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
    @Override
    public List<String> userList() {
        String sql = "SELECT username FROM tblUserProfile";
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
        return list;
    }

    @Override
    public UserProfile get(String username) {
	    String sql = "SELECT u.userid userid, u.username username, u.password password, r.role role, up.teamid teamid, up.mobile mobile, up.email email "
        		+ "FROM	tblUser u "
				+ "LEFT JOIN tblRole r ON u.username = r.username "
				+ "LEFT JOIN tblUserProfile up ON u.userid = up.userid "
				+ "WHERE u.username='" + username + "'";
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<UserProfile>() {
	 
	        @Override
	        public UserProfile extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                UserProfile userProfile = new UserProfile();
	                userProfile.setuserid(rs.getInt("userid"));
	                userProfile.setusername(rs.getString("username"));
	                userProfile.setpassword(rs.getString("password"));	                
	                userProfile.setrole(rs.getString("role"));
	                userProfile.setteamid(rs.getInt("teamid"));
	                userProfile.setmobile(rs.getString("mobile"));
	                userProfile.setemail(rs.getString("email"));
	                return userProfile;
	            }	 
	            return null;
	        }
        });
    }
}