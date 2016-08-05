package com.SpringMVC.daoimpl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;

import com.SpringMVC.model.TeamTarget;
import com.SpringMVC.dao.TeamTargetDAO;
import com.SpringMVC.mapper.TeamTargetMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class TeamTargetDAOImpl extends JdbcDaoSupport implements TeamTargetDAO {
 
    @Autowired
    public TeamTargetDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void saveOrUpdate(TeamTarget teamTarget) {
        if (teamTarget.getteamid() > 0)  {
            // update
            String sql = "UPDATE tblTeamTarget SET prospect=?, sales=?, totalsales=? "
            		+ "WHERE teamid=? AND period=?";
            this.getJdbcTemplate().update(sql, 
            		teamTarget.getprospect(), teamTarget.getsales(), teamTarget.gettotalsales(), 
            		teamTarget.getteamid(), teamTarget.getperiod());
        } else {
            // insert
            String sql = "INSERT INTO tblTeamTarget "
            		+ "(teamid, period, prospect, sales, totalsales) "
            		+ "VALUES (?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		teamTarget.getteamid(), teamTarget.getperiod(), teamTarget.getprospect(),
            		teamTarget.getsales(), teamTarget.gettotalsales());
            }
    }
    
    public void delete(int teamid, Date period) {
        String sql = "DELETE FROM tblTeamTarget WHERE teamid=? AND period=?";
        this.getJdbcTemplate().update(sql, teamid, period);
    }
    
    public TeamTarget get(int teamid, Date period) {
	    String sql = "SELECT * FROM tblTeamTarget WHERE teamid="+teamid+" AND period='"+period+"'";
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<TeamTarget>() {
	 
	        @Override
	        public TeamTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                TeamTarget teamTarget = new TeamTarget();
	                teamTarget.setteamid(rs.getInt("teamid"));
	                teamTarget.setperiod(rs.getDate("period"));
	                teamTarget.setprospect(rs.getInt("prospect"));
	                teamTarget.setsales(rs.getInt("sales"));
	                teamTarget.settotalsales(rs.getFloat("totalsales"));
	                return teamTarget;
	            }	 
	            return null;
	        }
        });
    }

    public List<TeamTarget> list(int teamid) {
        String sql = "SELECT * FROM tblTeamTarget WHERE teamid = " + teamid;
        TeamTargetMapper mapper = new TeamTargetMapper();
        List<TeamTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
}