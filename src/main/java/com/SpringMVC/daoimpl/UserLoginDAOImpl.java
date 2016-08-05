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
        String sql = "SELECT u.userid AS userid, u.username AS username, u.password AS password, "
        		+ "r.role AS role, ISNULL(c.companyid, 0) AS companyid "
        		+ "FROM	tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
				+ "LEFT JOIN tblCompany c ON c.said = u.userid "
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
    public List<String> getUserRoles(String username) {
        String sql = "Select role "
                + " from tblRole where username = ? ";
         
        Object[] params = new Object[] { username };         
        List<String> roles = this.getJdbcTemplate().queryForList(sql,params, String.class);         
        return roles;
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
            // insert
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
    public List<UserLogin> list() {
        String sql = "SELECT u.userid AS userid, u.username AS username, u.password AS password, "
        		+ "r.role AS role, ISNULL(c.companyid, 0) AS companyid "
        		+ "FROM	tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
				+ "LEFT JOIN tblCompany c ON c.said = u.userid "
        		+ "WHERE r.role <> 'USER'";
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
    public UserLogin get(String username) {
	    String sql = "SELECT u.userid userid, u.username username, u.password password, "
	    		+ "r.role role, ISNULL(c.companyid, 0) AS companyid "
        		+ "FROM	tblUser u "
				+ "LEFT JOIN tblRole r ON u.username = r.username "
				+ "LEFT JOIN tblCompany c ON c.said = u.userid "
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
}