package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;

import com.SpringMVC.model.ClosingPeriod;
import com.SpringMVC.dao.ClosingPeriodDAO;
import com.SpringMVC.mapper.ClosingPeriodMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class ClosingPeriodDAOImpl extends JdbcDaoSupport implements ClosingPeriodDAO {
 
    @Autowired
    public ClosingPeriodDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(ClosingPeriod closingPeriod) {
        String sql = "INSERT INTO tblClosingPeriod "
        		+ "(companyid, controlyear, controlmonth, opendate, closedate) "
        		+ "VALUES (?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		closingPeriod.getcompanyid(), closingPeriod.getcontrolyear(), closingPeriod.getcontrolmonth(), 
        		closingPeriod.getopendate(), closingPeriod.getclosedate());
    }
    
    public void update(ClosingPeriod closingPeriod) {
        String sql = "UPDATE tblClosingPeriod SET opendate=?, closedate=? "
        		+ "WHERE id=?";
        this.getJdbcTemplate().update(sql, 
        		closingPeriod.getopendate(), closingPeriod.getclosedate(), closingPeriod.getid());
    }

    public void delete(int id) {
        String sql = "DELETE FROM tblClosingPeriod WHERE id=?";
        this.getJdbcTemplate().update(sql, id);
    }
    
    public ClosingPeriod get(int id) {
	    String sql = "SELECT id, companyid, "
	    		+ "controlyear, controlmonth, "
	    		+ "opendate, closedate "
	    		+ "FROM tblClosingPeriod "
	    		+ "WHERE id="+id;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<ClosingPeriod>() {
	 
			@Override
	        public ClosingPeriod extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	ClosingPeriod closingPeriod = new ClosingPeriod();
	            	closingPeriod.setid(rs.getInt("id"));
	            	closingPeriod.setcompanyid(rs.getInt("companyid"));
	            	closingPeriod.setcontrolyear(rs.getInt("controlyear"));
	            	closingPeriod.setcontrolmonth(rs.getInt("controlmonth"));
	            	closingPeriod.setopendate(rs.getDate("opendate"));
	            	closingPeriod.setclosedate(rs.getDate("closedate"));
	                return closingPeriod;
	            }	 
	            return null;
	        }
        });
    }

    public List<ClosingPeriod> list(int companyid) {
	    String sql = "SELECT id, companyid, "
	    		+ "controlyear, controlmonth, "
	    		+ "opendate, closedate "
	    		+ "FROM tblClosingPeriod "	    
	    		+ "WHERE companyid=" + companyid + " "
				+ "ORDER BY controlyear, controlmonth";
        ClosingPeriodMapper mapper = new ClosingPeriodMapper();
        List<ClosingPeriod> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
}