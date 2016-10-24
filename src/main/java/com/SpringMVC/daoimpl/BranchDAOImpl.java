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
	
    public void save(Branch branch) {
        String sql = "INSERT INTO tblBranch "
        		+ "(companyid, branchname, regno, maid, address, zipcode, city, state, country, telephone, fax, email, website) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		branch.getcompanyid(), branch.getbranchname(), branch.getregno(), branch.getmaid(),
        		branch.getaddress(), branch.getzipcode(), branch.getcity(),
        		branch.getstate(), branch.getcountry(), branch.gettelephone(),
        		branch.getfax(), branch.getemail(), branch.getwebsite());
	}
    
    public void update(Branch branch) {
        String sql = "UPDATE tblBranch SET "
        		+ "branchname=?, regno=?, maid=?, "
        		+ "address=?, zipcode=?, city=?, "
        		+ "state=?, country=?, telephone=?, "
        		+ "fax=?, email=?, website=? "
        		+ "WHERE branchid=?";
        this.getJdbcTemplate().update(sql, 
        		branch.getbranchname(), branch.getregno(), branch.getmaid(),
        		branch.getaddress(), branch.getzipcode(), branch.getcity(),
        		branch.getstate(), branch.getcountry(), branch.gettelephone(),
        		branch.getfax(), branch.getemail(), branch.getwebsite(), branch.getbranchid());
    }
    
    public void delete(int branchid) {
        String sql = "DELETE FROM tblBranch WHERE branchid=?";
        this.getJdbcTemplate().update(sql, branchid);
    }
    
    public List<Branch> list(int companyid) {
	    String sql = "SELECT b.branchid branchid, b.branchname branchname, b.companyid companyid, "
	    		+ "b.regno regno, b.maid maid, ma.username maname, b.address address, "
	    		+ "b.zipcode zipcode, b.city city, b.state state, b.country country, "
	    		+ "b.telephone telephone, b.fax fax, b.email email, b.website website "
	    		+ "FROM tblBranch b "
	    		+ "LEFT JOIN tblUser ma on b.maid = ma.userid "
        		+ "WHERE b.companyid=" + companyid;
        BranchMapper mapper = new BranchMapper();
        List<Branch> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
    public List<String> branchList(int companyid) {
        String sql = "SELECT branchname FROM tblBranch WHERE companyid=" + companyid;
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
        return list;
    }

    public Branch get(int branchid) {
	    String sql = "SELECT b.branchid branchid, b.branchname branchname, b.companyid companyid, "
	    		+ "b.regno regno, b.maid maid, ma.username maname, b.address address, "
	    		+ "b.zipcode zipcode, b.city city, b.state state, b.country country, "
	    		+ "b.telephone telephone, b.fax fax, b.email email, b.website website "
	    		+ "FROM tblBranch b "
	    		+ "LEFT JOIN tblUser ma on b.maid = ma.userid "
	    		+ "WHERE b.branchid=" + branchid;
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
	                branch.setmaid(rs.getInt("maid"));
	                branch.setmaname(rs.getString("maname"));
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
    
    public Branch getByName(String branchname) {
	    String sql = "SELECT b.branchid branchid, b.branchname branchname, b.companyid companyid, "
	    		+ "b.regno regno, b.maid maid, ma.username maname, b.address address, "
	    		+ "b.zipcode zipcode, b.city city, b.state state, b.country country, "
	    		+ "b.telephone telephone, b.fax fax, b.email email, b.website website "
	    		+ "FROM tblBranch b "
	    		+ "LEFT JOIN tblUser ma on b.maid = ma.userid "	    		
	    		+ "WHERE b.branchname='" + branchname + "'";
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
	                branch.setmaid(rs.getInt("maid"));
	                branch.setmaname(rs.getString("maname"));
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

    public Branch getByMA(int maid) {
	    String sql = "SELECT b.branchid branchid, b.branchname branchname, b.companyid companyid, "
	    		+ "b.regno regno, b.maid maid, ma.username maname, b.address address, "
	    		+ "b.zipcode zipcode, b.city city, b.state state, b.country country, "
	    		+ "b.telephone telephone, b.fax fax, b.email email, b.website website "
	    		+ "FROM tblBranch b "
	    		+ "LEFT JOIN tblUser ma on b.maid = ma.userid "
	    		+ "WHERE b.maid=" + maid;
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
	                branch.setmaid(rs.getInt("maid"));
	                branch.setmaname(rs.getString("maname"));
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