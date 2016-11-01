package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.mapper.UserLoginMapper;
import com.SpringMVC.model.UserLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service
@Transactional
public class UserLoginDAOImpl extends JdbcDaoSupport implements UserLoginDAO {
 
    @Autowired
    public UserLoginDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
  
    @Override
    public UserLogin findUserLogin(String username) {
        String sql = "SELECT u.userid AS userid, "
        		+ "u.username AS username, "
        		+ "u.password AS password, "
        		+ "r.role AS role, "
        		+ "CASE WHEN sa.companyid IS NULL THEN "
        		+ "CASE WHEN md.companyid IS NULL THEN 0 ELSE md.companyid END "
        		+ "ELSE sa.companyid END AS companyid "
        		+ "FROM	tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
				+ "LEFT JOIN tblCompany sa ON sa.said = u.userid "
				+ "LEFT JOIN tblCompany md ON md.mdid = u.userid "
                + "WHERE u.username = ? ";
 
        Object[] params = new Object[] { username };
        UserLoginMapper mapper = new UserLoginMapper();
        try {
            UserLogin userLogin = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userLogin;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
 
    @Override
    public String getUserRoles(String username) {
        String sql = "Select role from tblRole where username = ? ";

        String role = (String)getJdbcTemplate().queryForObject(sql, new Object[] {username}, String.class);         
        return role;
    }
     
    @Override
    public List<String> getAllRoles() {
        String sql = "Select codeid AS role from tblCodeMaster where codetype = 'ROLE' and codeid <> 'DEV'";
         
        List<String> roles = this.getJdbcTemplate().queryForList(sql, String.class);         
        return roles;
    }
    
    public void saveOrUpdate(UserLogin userLogin) {
        if (userLogin.getuserid() > 0)  {
        	String sqlUser = "UPDATE tblUser SET password=? WHERE userid=?";
            this.getJdbcTemplate().update(sqlUser, 
            		userLogin.getpassword(), userLogin.getuserid());
        	
    		String sqlRole = "UPDATE tblRole SET role=? WHERE username=?";
            this.getJdbcTemplate().update(sqlRole, 
            		userLogin.getrole(), userLogin.getusername());            
        } else {
        	String sqlUser = "INSERT INTO tblUser (username, password, enabled) "
        			+ "VALUES (?, ?, '1')";
            this.getJdbcTemplate().update(sqlUser, 
            		userLogin.getusername(), userLogin.getpassword());

    		String sqlRole = "INSERT INTO tblRole (username, role) "
    				+ "VALUES (?, ?)";
            this.getJdbcTemplate().update(sqlRole, 
            		userLogin.getusername(), userLogin.getrole());            	
        }
    }
    
    public void delete(String username) {
        String sql = "DELETE FROM tblUser WHERE username=?";
        this.getJdbcTemplate().update(sql, username);

        String sqlRole = "DELETE FROM tblRole WHERE username=?";
        this.getJdbcTemplate().update(sqlRole, username);
    }
    
    @Override
    public List<UserLogin> list(String role, int companyid) {
    	String sql = "";
    	if (role.equals("DEV") ){    	
	        sql = "SELECT u.userid AS userid, "
	        		+ "u.username AS username, "
	        		+ "u.password AS password, "
	        		+ "r.role AS role, "
	        		+ "CASE WHEN sa.companyid IS NULL THEN "
	        		+ "CASE WHEN md.companyid IS NULL THEN 0 ELSE md.companyid END "
	        		+ "ELSE sa.companyid END AS companyid "
	        		+ "FROM	tblUser u "
					+ "LEFT JOIN tblRole r ON u.username = r.username "
					+ "LEFT JOIN tblCompany sa ON sa.said = u.userid "
					+ "LEFT JOIN tblCompany md ON md.mdid = u.userid "
    				+ "WHERE r.role IN ('SA', 'MD')";    		    		
    	}
    	else {
	        sql = "SELECT u.userid AS userid, "
	        		+ "u.username AS username, "
	        		+ "u.password AS password, "
	        		+ "r.role AS role, "
	        		+ "CASE WHEN b1.companyid IS NULL THEN "
	        		+ "CASE WHEN b2.companyid IS NULL THEN 0 ELSE b2.companyid END "
	        		+ "ELSE b1.companyid END AS companyid "
	        		+ "FROM	tblUser u "        		
	        		+ "LEFT JOIN tblRole r ON u.username = r.username "
	        		+ "LEFT JOIN tblBranch b1 ON b1.maid = u.userid "
	        		+ "LEFT JOIN tblUserProfile up ON u.userid = up.userid "
	        		+ "LEFT JOIN tblTeam t ON t.teamid = up.teamid "
	        		+ "LEFT JOIN tblBranch b2 ON b2.branchid = t.branchid "	        		
	        		+ "WHERE r.role IN ('MA', 'TL') "
	        		+ "AND (b1.companyid IS NULL OR b1.companyid=" + companyid + " "
    				+ "OR b2.companyid IS NULL OR b2.companyid=" + companyid + ")";
    	}
        UserLoginMapper mapper = new UserLoginMapper();
        List<UserLogin> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    @Override
    public List<String> salist() {
        String sql = "SELECT u.username AS username FROM tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
        		+ "WHERE r.role = 'SA'";
        List<String> salist = this.getJdbcTemplate().queryForList(sql, String.class);         
        return salist;
    }

    @Override
    public List<String> mdlist() {
        String sql = "SELECT u.username AS username FROM tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
        		+ "WHERE r.role = 'MD'";
        List<String> mdlist = this.getJdbcTemplate().queryForList(sql, String.class);         
        return mdlist;
    }

    @Override
    public List<String> malist() {
        String sql = "SELECT u.username AS username FROM tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
        		+ "WHERE r.role = 'MA'";
        List<String> malist = this.getJdbcTemplate().queryForList(sql, String.class);         
        return malist;
    }

    @Override
    public List<String> leaderlist() {
        String sql = "SELECT u.username AS username FROM tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
        		+ "WHERE r.role = 'TL'";
        List<String> leaderlist = this.getJdbcTemplate().queryForList(sql, String.class);         
        return leaderlist;
    }

    @Override
    public UserLogin get(String username) {
	    String sql = "SELECT u.userid AS userid, "
	    		+ "u.username AS username, "
	    		+ "u.password AS password, "
	    		+ "r.role AS role, "
        		+ "CASE WHEN sa.companyid IS NULL THEN "
        		+ "CASE WHEN md.companyid IS NULL THEN 0 ELSE md.companyid END "
        		+ "ELSE sa.companyid END AS companyid "
        		+ "FROM	tblUser u "
				+ "LEFT JOIN tblRole r ON u.username = r.username "
				+ "LEFT JOIN tblCompany sa ON sa.said = u.userid "
				+ "LEFT JOIN tblCompany md ON md.mdid = u.userid "
				+ "WHERE u.username='" + username + "'";
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<UserLogin>() {
	 
	        @Override
	        public UserLogin extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                UserLogin userLogin = new UserLogin();
	                userLogin.setuserid(rs.getInt("userid"));
	                userLogin.setusername(rs.getString("username"));
	                userLogin.setpassword(rs.getString("password"));	                
	                userLogin.setrole(rs.getString("role"));
	                userLogin.setcompanyid(rs.getInt("companyid"));
	                return userLogin;
	            }	 
	            return null;
	        }
        });
    }

    @Override
    public int getCompanyID(String username) {
    	String sql = "SELECT spGetCompanyID(?) ";
//    	String sql = "EXEC spGetCompanyID ? ";
        int companyid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {username}, int.class);
        return companyid;
    }
}