package com.SpringMVC.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
        String sql = "SELECT u.userid AS userid, "
        		+ "u.username AS username, "
        		+ "u.password AS password, "
        		+ "r.role AS role, "
        		+ "up.teamid AS teamid, "
        		+ "up.mobile AS mobile, "
        		+ "up.email AS email "
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
    
    public void save(UserProfile userProfile) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spCreateMember(?, ?, ?, ?, ?, ?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setString(2, userProfile.getusername());
	    	proc.setString(3, userProfile.getpassword());
	    	proc.setString(4, userProfile.getrole());
	    	proc.setInt(5, userProfile.getteamid());
	    	proc.setString(6, userProfile.getmobile());
	    	proc.setString(7, userProfile.getemail());	    	
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public void update(UserProfile userProfile) {
    	String sqlUser = "UPDATE tblUser SET password=? WHERE userid=?";
        this.getJdbcTemplate().update(sqlUser, 
        		userProfile.getpassword(), userProfile.getuserid());
    	
		String sqlRole = "UPDATE tblRole SET role=? WHERE username=?";
        this.getJdbcTemplate().update(sqlRole, 
        		userProfile.getrole(), userProfile.getusername());
        
        String sql = "UPDATE tblUserProfile SET mobile=?, email=? "
        		+ "WHERE userid=? ";
        this.getJdbcTemplate().update(sql, 
        		userProfile.getmobile(), userProfile.getemail(), userProfile.getuserid());            	
    }

    public void delete(String username) {
        String sqlUserProfile = "DELETE FROM tblUserProfile WHERE username=?";
        this.getJdbcTemplate().update(sqlUserProfile, username);

        String sqlUser = "DELETE FROM tblUser WHERE username=?";
        this.getJdbcTemplate().update(sqlUser, username);

        String sqlRole = "DELETE FROM tblRole WHERE username=?";
        this.getJdbcTemplate().update(sqlRole, username);
    }
    
    @Override
    public List<UserProfile> list(int teamid) {
        String sql = "SELECT u.userid AS userid, "
        		+ "u.username AS username, "
        		+ "u.password AS password, "
        		+ "r.role AS role, "
        		+ "up.teamid AS teamid, "
        		+ "up.mobile AS mobile, "
        		+ "up.email AS email "
        		+ "FROM	tblUser u "
				+ "LEFT JOIN tblRole r ON u.username = r.username "
				+ "LEFT JOIN tblUserProfile up ON u.userid = up.userid "
				+ "WHERE up.teamid = " + teamid;
        UserProfileMapper mapper = new UserProfileMapper();
        List<UserProfile> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
 
    @Override
    public List<String> userList(int teamid) {
        String sql = "SELECT username FROM tblUserProfile WHERE teamid="+teamid;
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
        return list;
    }

    @Override
    public List<String> userListByBranch(int branchid) {
        String sql = "SELECT up.username AS username "
        		+ "FROM tblUserProfile up "
        		+ "LEFT JOIN tblTeam t ON t.teamid = up.teamid "
        		+ "WHERE t.branchid="+branchid;
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
        return list;
    }

    @Override
    public List<String> userListByCompany(int companyid) {
        String sql = "SELECT up.username AS username "
        		+ "FROM tblUserProfile up "
        		+ "LEFT JOIN tblTeam t ON t.teamid = up.teamid "
        		+ "LEFT JOIN tblBranch b ON b.branchid = t.branchid "
        		+ "WHERE b.companyid="+companyid;
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
        return list;
    }

    @Override
    public UserProfile get(String username) {
	    String sql = "SELECT u.userid AS userid, "
	    		+ "u.username AS username, "
	    		+ "u.password AS password, "
	    		+ "r.role AS role, "
	    		+ "up.teamid AS teamid, "
	    		+ "up.mobile AS mobile, "
	    		+ "up.email AS email "
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

    public boolean isExist(UserProfile userProfile) {
        return findUser(userProfile.getuserid())!=null;
    }
}