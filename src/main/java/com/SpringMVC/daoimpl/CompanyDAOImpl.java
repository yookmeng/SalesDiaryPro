package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.SpringMVC.model.Company;
import com.SpringMVC.dao.CompanyDAO;
import com.SpringMVC.mapper.CompanyMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class CompanyDAOImpl extends JdbcDaoSupport implements CompanyDAO {
 
    @Autowired
    public CompanyDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void saveOrUpdate(Company company) {
        if (company.getcompanyid() > 0)  {
            // update
            String sql = "UPDATE tblCompany SET "
            		+ "companyName=?, regno=?, pic=?, "
            		+ "address=?, zipcode=?, city=?, "
            		+ "state=?, country=?, telephone=?, "
            		+ "fax=?, email=?, website=? "
            		+ "WHERE companyid=?";
            this.getJdbcTemplate().update(sql, 
            		company.getcompanyname(), company.getregno(), company.getpic(),
            		company.getaddress(), company.getzipcode(), company.getcity(),
            		company.getstate(), company.getcountry(), company.gettelephone(),
            		company.getfax(), company.getemail(), company.getwebsite(), company.getcompanyid());
        } else {
            // insert
            String sql = "INSERT INTO tblCompany "
            		+ "(companyname, regno, pic, address, zipcode, city, state, country, telephone, fax, email, website) "
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		company.getcompanyname(), company.getregno(), company.getpic(),
            		company.getaddress(), company.getzipcode(), company.getcity(),
            		company.getstate(), company.getcountry(), company.gettelephone(),
            		company.getfax(), company.getemail(), company.getwebsite());
            }
    }
    
    public void delete(int companyid) {
        String sql = "DELETE FROM tblCompany WHERE companyid=?";
        this.getJdbcTemplate().update(sql, companyid);
    }
    
    public List<Company> list() {
        String sql = "SELECT * FROM tblCompany ";
        CompanyMapper mapper = new CompanyMapper();
        List<Company> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }                          

    public List<String> companyList() {
        String sql = "SELECT companyname FROM tblCompany";
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
        return list;
    }

    public Company get(int companyid) {
	    String sql = "SELECT * FROM tblCompany WHERE companyid=" + companyid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Company>() {
	 
	        @Override
	        public Company extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Company company = new Company();
	                company.setcompanyid(rs.getInt("companyid"));
	                company.setcompanyname(rs.getString("companyname"));
	                company.setregno(rs.getString("regno"));
	                company.setpic(rs.getString("pic"));
	                company.setaddress(rs.getString("address"));
	                company.setzipcode(rs.getString("zipcode"));
	                company.setcity(rs.getString("city"));
	                company.setstate(rs.getString("state"));
	                company.setcountry(rs.getString("country"));
	                company.settelephone(rs.getString("telephone"));
	                company.setfax(rs.getString("fax"));
	                company.setemail(rs.getString("email"));
	                company.setwebsite(rs.getString("website"));
	                return company;
	            }	 
	            return null;
	        }
        });
    }
}