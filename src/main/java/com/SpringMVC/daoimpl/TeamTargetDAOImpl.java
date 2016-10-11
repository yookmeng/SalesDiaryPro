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
        if (teamTarget.gettargetid() > 0)  {
            // update
            String sql = "UPDATE tblTeamTarget SET prospect=?, testdrive=?, closed=? "
            		+ "WHERE teamid=? AND period=?";
            this.getJdbcTemplate().update(sql, 
            		teamTarget.getprospect(), teamTarget.gettestdrive(), teamTarget.getclosed(), 
            		teamTarget.getteamid(), teamTarget.getperiod());
        } else {
            // insert
            String sql = "INSERT INTO tblTeamTarget "
            		+ "(teamid, period, branchtargetid, prospect, testdrive, closed) "
            		+ "VALUES (?, ?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		teamTarget.getteamid(), teamTarget.getperiod(), teamTarget.getbranchtargetid(), 
            		teamTarget.getprospect(), teamTarget.gettestdrive(), teamTarget.getclosed());
            }
    }
    
    public void delete(int targetid) {
        String sql = "DELETE FROM tblTeamTarget WHERE targetid=?";
        this.getJdbcTemplate().update(sql, targetid);
    }
    
    public TeamTarget get(int targetid) {
	    String sql = "SELECT tt.targetid targetid, tt.teamid teamid, t.teamname teamname, "
	    		+ "tt.period period, CONVERT(varchar(7), tt.period, 111) displayperiod, "
	    		+ "tt.branchtargetid branchtargetid, "
	    		+ "tt.prospect prospect, tt.testdrive testdrive, tt.closed closed "
	    		+ "FROM tblTeamTarget tt "
        		+ "LEFT JOIN tblTeam t ON t.teamid = tt.teamid "	    		
	    		+ "WHERE tt.targetid="+targetid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<TeamTarget>() {
	 
	        @Override
	        public TeamTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                TeamTarget teamTarget = new TeamTarget();
	                teamTarget.settargetid(rs.getInt("targetid"));
	                teamTarget.setteamid(rs.getInt("teamid"));
	                teamTarget.setteamname(rs.getString("teamname"));
	                teamTarget.setperiod(rs.getDate("period"));
	                teamTarget.setdisplayperiod(rs.getString("displayperiod"));
	                teamTarget.setbranchtargetid(rs.getInt("branchtargetid"));
	                teamTarget.setprospect(rs.getInt("prospect"));
	                teamTarget.settestdrive(rs.getInt("testdrive"));
	                teamTarget.setclosed(rs.getInt("closed"));
	                return teamTarget;
	            }	 
	            return null;
	        }
        });
    }

    public TeamTarget getByPeriod(String period, int teamid) {
	    String sql = "SELECT tt.targetid targetid, tt.teamid teamid, t.teamname teamname, "
	    		+ "tt.period period, CONVERT(varchar(7), tt.period, 111) displayperiod, "
	    		+ "tt.branchtargetid branchtargetid, "
	    		+ "tt.prospect prospect, tt.testdrive testdrive, tt.closed closed "
	    		+ "FROM tblTeamTarget tt "
        		+ "LEFT JOIN tblTeam t ON t.teamid = tt.teamid "	    		
	    		+ "WHERE tt.period='"+period + "' "
	    		+ "AND tt.teamid="+teamid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<TeamTarget>() {
	 
	        @Override
	        public TeamTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                TeamTarget teamTarget = new TeamTarget();
	                teamTarget.settargetid(rs.getInt("targetid"));
	                teamTarget.setteamid(rs.getInt("teamid"));
	                teamTarget.setteamname(rs.getString("teamname"));
	                teamTarget.setperiod(rs.getDate("period"));
	                teamTarget.setdisplayperiod(rs.getString("displayperiod"));
	                teamTarget.setbranchtargetid(rs.getInt("branchtargetid"));
	                teamTarget.setprospect(rs.getInt("prospect"));
	                teamTarget.settestdrive(rs.getInt("testdrive"));
	                teamTarget.setclosed(rs.getInt("closed"));
	                return teamTarget;
	            }	 
	            return null;
	        }
        });
    }

    public TeamTarget getByTeam(int teamid) {
	    String sql = "SELECT tt.targetid targetid, tt.teamid teamid, t.teamname teamname, "
	    		+ "tt.period period, CONVERT(varchar(7), tt.period, 111) displayperiod, "
	    		+ "tt.branchtargetid branchtargetid, "
	    		+ "tt.prospect prospect, tt.testdrive testdrive, tt.closed closed "
	    		+ "FROM tblTeamTarget tt "
        		+ "LEFT JOIN tblTeam t ON t.teamid = tt.teamid "	    		
	    		+ "WHERE tt.teamid="+teamid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<TeamTarget>() {
	 
	        @Override
	        public TeamTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                TeamTarget teamTarget = new TeamTarget();
	                teamTarget.settargetid(rs.getInt("targetid"));
	                teamTarget.setteamid(rs.getInt("teamid"));
	                teamTarget.setteamname(rs.getString("teamname"));
	                teamTarget.setperiod(rs.getDate("period"));
	                teamTarget.setdisplayperiod(rs.getString("displayperiod"));
	                teamTarget.setbranchtargetid(rs.getInt("branchtargetid"));
	                teamTarget.setprospect(rs.getInt("prospect"));
	                teamTarget.settestdrive(rs.getInt("testdrive"));
	                teamTarget.setclosed(rs.getInt("closed"));
	                return teamTarget;
	            }	 
	            return null;
	        }
        });
    }

    public List<TeamTarget> list(Date period, int branchid) {
	    String sql = "SELECT tt.targetid targetid, tt.teamid teamid, t.teamname teamname, "
	    		+ "tt.period period, CONVERT(varchar(7), tt.period, 111) displayperiod, "
	    		+ "tt.branchtargetid branchtargetid, "
	    		+ "tt.prospect prospect, tt.testdrive testdrive, tt.closed closed "
	    		+ "FROM tblTeamTarget tt "
        		+ "LEFT JOIN tblTeam t ON t.teamid = tt.teamid "	    		
        		+ "WHERE tt.period = '" + period + "' "
        		+ "AND t.branchid = " + branchid;
        TeamTargetMapper mapper = new TeamTargetMapper();
        List<TeamTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<TeamTarget> listByTeam(int teamid) {
	    String sql = "SELECT tt.targetid targetid, tt.teamid teamid, t.teamname teamname, "
	    		+ "tt.period period, CONVERT(varchar(7), tt.period, 111) displayperiod, "
	    		+ "tt.branchtargetid branchtargetid, "
	    		+ "tt.prospect prospect, tt.testdrive testdrive, tt.closed closed "
	    		+ "FROM tblTeamTarget tt "
        		+ "LEFT JOIN tblTeam t ON t.teamid = tt.teamid "	    		
        		+ "WHERE t.teamid = " + teamid + " "
        		+ "ORDER BY period";

        TeamTargetMapper mapper = new TeamTargetMapper();
        List<TeamTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
}