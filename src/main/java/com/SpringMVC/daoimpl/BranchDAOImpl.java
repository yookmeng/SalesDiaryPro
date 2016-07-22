	package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Branch;
import com.SpringMVC.dao.BranchDAO;
import com.SpringMVC.mapper.BranchMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class BranchDAOImpl extends JdbcDaoSupport implements BranchDAO { 
    @Autowired
    public BranchDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void saveOrUpdate(Branch branch) {
        if (branch.getbranchid() > 0)  {
            // update
            String sql = "UPDATE tblBranch SET "
            		+ "branchname=?, regno=?, pic=?, "
            		+ "address=?, zipcode=?, city=?, "
            		+ "state=?, country=?, telephone=?, "
            		+ "fax=?, email=?, website=? "
            		+ "WHERE branchid=?";
            this.getJdbcTemplate().update(sql, 
            		branch.getbranchname(), branch.getregno(), branch.getpic(),
            		branch.getaddress(), branch.getzipcode(), branch.getcity(),
            		branch.getstate(), branch.getcountry(), branch.gettelephone(),
            		branch.getfax(), branch.getemail(), branch.getwebsite(), branch.getbranchid());
        } else {
            // insert
            String sql = "INSERT INTO tblBranch "
            		+ "(companyid, branchname, regno, pic, address, zipcode, city, state, country, telephone, fax, email, website) "
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		branch.getcompanyid(), branch.getbranchname(), branch.getregno(), branch.getpic(),
            		branch.getaddress(), branch.getzipcode(), branch.getcity(),
            		branch.getstate(), branch.getcountry(), branch.gettelephone(),
            		branch.getfax(), branch.getemail(), branch.getwebsite());
            }
    }
    
    public void delete(int branchid) {
        String sql = "DELETE FROM tblBranch WHERE branchid=?";
        this.getJdbcTemplate().update(sql, branchid);
    }
    
    public List<Branch> list(int companyid) {
        String sql = "SELECT * FROM tblBranch WHERE companyid = " + companyid;
        BranchMapper mapper = new BranchMapper();
        List<Branch> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
    public List<String> branchList() {
        String sql = "SELECT name FROM tblBranch";
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
        return list;
    }

    public Branch get(int branchid) {
	    String sql = "SELECT * FROM tblBranch WHERE branchid=" + branchid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Branch>() {
	 
	        @Override
	        public Branch extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Branch branch = new Branch();
	                branch.setbranchid(rs.getInt("branchid"));
	                branch.setbranchname(rs.getString("branchname"));
	                branch.setcompanyid(rs.getInt("companyid"));
	                branch.setregno(rs.getString("regno"));
	                branch.setpic(rs.getString("pic"));
	                branch.setaddress(rs.getString("address"));
	                branch.setzipcode(rs.getString("zipcode"));
	                branch.setcity(rs.getString("city"));
	                branch.setstate(rs.getString("state"));
	                branch.setcountry(rs.getString("country"));
	                branch.settelephone(rs.getString("telephone"));
	                branch.setfax(rs.getString("fax"));
	                branch.setemail(rs.getString("email"));
	                branch.setwebsite(rs.getString("website"));
	                return branch;
	            }	 
	            return null;
	        }
        });
    }
}