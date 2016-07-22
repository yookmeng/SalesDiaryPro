package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Team;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.mapper.TeamMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class TeamDAOImpl extends JdbcDaoSupport implements TeamDAO {
 
    @Autowired
    public TeamDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void saveOrUpdate(Team team) {
        if (team.getteamid() > 0)  {
            // update
            String sql = "UPDATE tblTeam SET "
            		+ "teamname=?, pic=? "
            		+ "WHERE teamid=?";
            this.getJdbcTemplate().update(sql, 
            		team.getteamname(), team.getpic(), team.getteamid());
        } else {
            // insert
            String sql = "INSERT INTO tblTeam "
            		+ "(teamname, branchid, pic) "
            		+ "VALUES (?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		team.getteamname(), team.getbranchid(), team.getpic());
            }
    }
    
    public void delete(int teamid) {
        String sql = "DELETE FROM tblTeam WHERE teamid=?";
        this.getJdbcTemplate().update(sql, teamid);
    }
    
    public List<Team> list(int branchid) {
        String sql = "SELECT * FROM tblTeam WHERE branchid = " + branchid;
        TeamMapper mapper = new TeamMapper();
        List<Team> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
    public List<String> teamList() {
        String sql = "SELECT name FROM tblTeam";
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
        return list;
    }

    public Team get(int teamid) {
	    String sql = "SELECT * FROM tblTeam WHERE teamid=" + teamid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Team>() {
	 
	        @Override
	        public Team extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Team team = new Team();
	                team.setteamid(rs.getInt("teamid"));
	                team.setteamname(rs.getString("teamname"));
	                team.setbranchid(rs.getInt("branchid"));
	                team.setpic(rs.getString("pic"));
	                return team;
	            }	 
	            return null;
	        }
        });
    }
}