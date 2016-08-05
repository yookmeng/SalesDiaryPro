package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;

import com.SpringMVC.model.CompanyTarget;
import com.SpringMVC.dao.CompanyTargetDAO;
import com.SpringMVC.mapper.CompanyTargetMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class CompanyTargetDAOImpl extends JdbcDaoSupport implements CompanyTargetDAO {
 
    @Autowired
    public CompanyTargetDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void saveOrUpdate(CompanyTarget companyTarget) {
        if (companyTarget.gettargetid() > 0)  {
            // update
            String sql = "UPDATE tblCompanyTarget SET prospect=?, sales=?, totalsales=? "
            		+ "WHERE targetid=?";
            this.getJdbcTemplate().update(sql, 
            		companyTarget.getprospect(), companyTarget.getsales(), companyTarget.gettotalsales(), 
            		companyTarget.gettargetid());
        } else {
            // insert
            String sql = "INSERT INTO tblCompanyTarget "
            		+ "(companyid, period, prospect, sales, totalsales) "
            		+ "VALUES (?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		companyTarget.getcompanyid(), companyTarget.getperiod(), companyTarget.getprospect(),
            		companyTarget.getsales(), companyTarget.gettotalsales());
            }
    }
    
    public void delete(int targetid) {
        String sql = "DELETE FROM tblCompanyTarget WHERE targetid=?";
        this.getJdbcTemplate().update(sql, targetid);
    }
    
    public CompanyTarget get(int targetid) {
	    String sql = "SELECT * FROM tblCompanyTarget WHERE targetid="+targetid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<CompanyTarget>() {
	 
	        @Override
	        public CompanyTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                CompanyTarget companyTarget = new CompanyTarget();
	                companyTarget.settargetid(rs.getInt("targetid"));
	                companyTarget.setcompanyid(rs.getInt("companyid"));
	                companyTarget.setperiod(rs.getDate("period"));
	                companyTarget.setprospect(rs.getInt("prospect"));
	                companyTarget.setsales(rs.getInt("sales"));
	                companyTarget.settotalsales(rs.getFloat("totalsales"));
	                return companyTarget;
	            }	 
	            return null;
	        }
        });
    }

    public List<CompanyTarget> list(int companyid) {
        String sql = "SELECT * FROM tblCompanyTarget WHERE companyid = " + companyid;
        CompanyTargetMapper mapper = new CompanyTargetMapper();
        List<CompanyTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
}