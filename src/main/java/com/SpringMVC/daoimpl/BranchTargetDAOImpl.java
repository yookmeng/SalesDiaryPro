package com.SpringMVC.daoimpl;

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
            String sql = "UPDATE tblBranchTarget SET prospect=?, sales=?, totalsales=? "
            		+ "WHERE targetid=?";
            this.getJdbcTemplate().update(sql, 
            		branchTarget.getprospect(), branchTarget.getsales(), branchTarget.gettotalsales(), 
            		branchTarget.gettargetid());
        } else {
            // insert
            String sql = "INSERT INTO tblBranchTarget "
            		+ "(branchid, period, prospect, sales, totalsales) "
            		+ "VALUES (?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		branchTarget.getbranchid(), branchTarget.getperiod(), branchTarget.getprospect(),
            		branchTarget.getsales(), branchTarget.gettotalsales());
            }
    }
    
    public void delete(int targetid) {
        String sql = "DELETE FROM tblBranchTarget WHERE targetid=?";
        this.getJdbcTemplate().update(sql, targetid);
    }
    
    public BranchTarget get(int targetid) {
	    String sql = "SELECT * FROM tblBranchTarget WHERE targetid="+targetid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<BranchTarget>() {
	 
	        @Override
	        public BranchTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                BranchTarget branchTarget = new BranchTarget();
	                branchTarget.settargetid(rs.getInt("targetid"));
	                branchTarget.setbranchid(rs.getInt("branchid"));
	                branchTarget.setperiod(rs.getDate("period"));
	                branchTarget.setprospect(rs.getInt("prospect"));
	                branchTarget.setsales(rs.getInt("sales"));
	                branchTarget.settotalsales(rs.getFloat("totalsales"));
	                return branchTarget;
	            }	 
	            return null;
	        }
        });
    }

    public List<BranchTarget> list(int branchid) {
        String sql = "SELECT * FROM tblBranchTarget WHERE branchid = " + branchid;
        BranchTargetMapper mapper = new BranchTargetMapper();
        List<BranchTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
}