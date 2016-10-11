package com.SpringMVC.daoimpl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;

import com.SpringMVC.model.BranchTarget;
import com.SpringMVC.dao.BranchTargetDAO;
import com.SpringMVC.mapper.BranchTargetMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class BranchTargetDAOImpl extends JdbcDaoSupport implements BranchTargetDAO {
 
    @Autowired
    public BranchTargetDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void saveOrUpdate(BranchTarget branchTarget) {
        if (branchTarget.gettargetid() > 0)  {
            // update
            String sql = "UPDATE tblBranchTarget SET prospect=?, testdrive=?, closed=? "
            		+ "WHERE targetid=?";
            this.getJdbcTemplate().update(sql, 
            		branchTarget.getprospect(), branchTarget.gettestdrive(), branchTarget.getclosed(), 
            		branchTarget.gettargetid());
        } else {
            // insert
            String sql = "INSERT INTO tblBranchTarget "
            		+ "(branchid, period, companytargetid, prospect, testdrive, closed) "
            		+ "VALUES (?, ?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		branchTarget.getbranchid(), branchTarget.getperiod(), branchTarget.getcompanytargetid(), 
            		branchTarget.getprospect(), branchTarget.gettestdrive(), branchTarget.getclosed());
            }
    }
    
    public void delete(int targetid) {
        String sql = "DELETE FROM tblBranchTarget WHERE targetid=?";
        this.getJdbcTemplate().update(sql, targetid);
    }
    
    public BranchTarget get(int targetid) {
	    String sql = "SELECT bt.targetid targetid, bt.branchid branchid, b.branchname branchname, "
        		+ "bt.period period, CONVERT(varchar(7), bt.period, 111) displayperiod, "
        		+ "bt.companytargetid companytargetid, bt.prospect prospect, "
        		+ "bt.testdrive testdrive, bt.closed closed "
	    		+ "FROM tblBranchTarget bt "
        		+ "LEFT JOIN tblBranch b ON b.branchid = bt.branchid "
	    		+ "WHERE targetid="+targetid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<BranchTarget>() {
	 
	        @Override
	        public BranchTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                BranchTarget branchTarget = new BranchTarget();
	                branchTarget.settargetid(rs.getInt("targetid"));
	                branchTarget.setbranchid(rs.getInt("branchid"));
	                branchTarget.setbranchname(rs.getString("branchname"));
	                branchTarget.setcompanytargetid(rs.getInt("companytargetid"));
	                branchTarget.setperiod(rs.getDate("period"));
	                branchTarget.setdisplayperiod(rs.getString("displayperiod"));
	                branchTarget.setprospect(rs.getInt("prospect"));
	                branchTarget.settestdrive(rs.getInt("testdrive"));
	                branchTarget.setclosed(rs.getInt("closed"));
	                return branchTarget;
	            }	 
	            return null;
	        }
        });
    }

    public List<BranchTarget> list(Date period, int companyid) {
        String sql = "SELECT bt.targetid targetid, bt.branchid branchid, b.branchname branchname, "
        		+ "bt.period period, CONVERT(varchar(7), bt.period, 111) displayperiod, "
        		+ "bt.companytargetid companytargetid, bt.prospect prospect, "
        		+ "bt.testdrive testdrive, bt.closed closed "
        		+ "FROM tblBranchTarget bt "
        		+ "LEFT JOIN tblBranch b ON b.branchid = bt.branchid "
        		+ "WHERE bt.period = '" + period + "' "
				+ "AND b.companyid = " + companyid + " "
				+ "ORDER BY branchid";
        BranchTargetMapper mapper = new BranchTargetMapper();
        List<BranchTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<BranchTarget> listByBranch(int branchid) {
        String sql = "SELECT bt.targetid targetid, bt.branchid branchid, b.branchname branchname, "
        		+ "bt.period period, CONVERT(varchar(7), bt.period, 111) displayperiod, "
        		+ "bt.companytargetid companytargetid, bt.prospect prospect, "
        		+ "bt.testdrive testdrive, bt.closed closed "
        		+ "FROM tblBranchTarget bt "
        		+ "LEFT JOIN tblBranch b ON b.branchid = bt.branchid "
        		+ "WHERE bt.branchid = " + branchid + " "
        		+ "ORDER BY period";
        BranchTargetMapper mapper = new BranchTargetMapper();
        List<BranchTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
}