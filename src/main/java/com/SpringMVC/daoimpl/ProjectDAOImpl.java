package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;

import com.SpringMVC.model.Project;
import com.SpringMVC.dao.ProjectDAO;
import com.SpringMVC.mapper.ProjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class ProjectDAOImpl extends JdbcDaoSupport implements ProjectDAO {
 
    @Autowired
    public ProjectDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public void save(Project project) {
    	String sql = "";
    	
        sql = "INSERT INTO tblProject "
    		+ "(projectname, userid, name, mobile, email, "
    		+ "titleid, propertyid, units, orderid, quotationid,"
    		+ "smsflag, datecreated, forecastperiod, status) "
    		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
    		project.getprojectname(), project.getuserid(), project.getname(), 
    		project.getmobile(), project.getemail(), project.gettitleid(), 
    		project.getpropertyid(), project.getunits(), project.getorderid(),
    		project.getquotationid(), project.getsmsflag(), project.getdatecreated(), 
    		project.getforecastperiod(), project.getstatus());
    }
    
    public void update(Project project) {
    	String sql = "";

    	sql = "UPDATE tblProject SET projectname=?, name=?, mobile=?, email=?, "
			+ "titleid=?, propertyid=?, units=?, orderid=?, quotationid=?, "
			+ "smsflag=?, forecastperiod=?, status=? "
    		+ "WHERE projectid=?";
        this.getJdbcTemplate().update(sql, 
    		project.getprojectname(), project.getname(), project.getmobile(), 
    		project.getemail(), project.gettitleid(), project.getpropertyid(),
    		project.getunits(), project.getorderid(), project.getquotationid(),
    		project.getsmsflag(), project.getforecastperiod(), project.getstatus(),
    		project.getprojectid());
	}

    public void delete(int projectid) {
        String sql = "DELETE FROM tblProject WHERE projectid=?";
        this.getJdbcTemplate().update(sql, projectid);
    }
    
    public List<Project> list(int userid) {
        String sql = "SELECT projectid, projectname, userid, name, mobile, email, "
        		+ "titleid, tt.codename AS titlename, "
        		+ "propertyid, ppt.codename AS propertyname, "
        		+ "units, orderid, quotationid, smsflag, forecastperiod, "
        		+ "datecreated, status, sts.codename AS statusname "
        		+ "FROM tblProject "
        		+ "LEFT JOIN tblCodeMaster tt ON tt.codetype = 'TITLE' AND tt.codeid = titleid "
        		+ "LEFT JOIN tblCodeMaster ppt ON ppt.codetype = 'PROPERTY' AND ppt.codeid = propertyid "
        		+ "LEFT JOIN tblCodeMaster sts ON sts.codetype = 'STATUS' AND sts.codeid = status "
        		+ "WHERE userid = " + userid + " "
				+ "ORDER BY projectname";
        ProjectMapper mapper = new ProjectMapper();
        List<Project> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
    public List<Project> listByTeam(int teamid) {
        String sql = "SELECT projectid, projectname, p.userid, name, mobile, email, "
        		+ "titleid, tt.codename AS titlename, "
        		+ "propertyid, ppt.codename AS propertyname, "
        		+ "units, orderid, quotationid, smsflag, forecastperiod, "
        		+ "datecreated, status, sts.codename AS statusname "
        		+ "FROM tblProject p "
        		+ "LEFT JOIN tblCodeMaster tt ON tt.codetype = 'TITLE' AND tt.codeid = titleid "
        		+ "LEFT JOIN tblCodeMaster ppt ON ppt.codetype = 'PROPERTY' AND ppt.codeid = propertyid "
        		+ "LEFT JOIN tblCodeMaster sts ON sts.codetype = 'STATUS' AND sts.codeid = status "
        		+ "LEFT JOIN tblUser u ON u.userid = p.userid "
        		+ "WHERE u.teamid = " + teamid + " "
				+ "ORDER BY projectname";
        ProjectMapper mapper = new ProjectMapper();
        List<Project> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Project> listByBranch(int branchid) {
        String sql = "SELECT projectid, projectname, p.userid, name, mobile, email, "
        		+ "titleid, tt.codename AS titlename, "
        		+ "propertyid, ppt.codename AS propertyname, "
        		+ "units, orderid, quotationid, smsflag, forecastperiod, "
        		+ "datecreated, status, sts.codename AS statusname "
        		+ "FROM tblProject p "
        		+ "LEFT JOIN tblCodeMaster tt ON tt.codetype = 'TITLE' AND tt.codeid = titleid "
        		+ "LEFT JOIN tblCodeMaster ppt ON ppt.codetype = 'PROPERTY' AND ppt.codeid = propertyid "
        		+ "LEFT JOIN tblCodeMaster sts ON sts.codetype = 'STATUS' AND sts.codeid = status "
        		+ "LEFT JOIN tblUser u ON u.userid = p.userid "
        		+ "WHERE u.branchid = " + branchid + " "
        		+ "ORDER BY projecttname";
        ProjectMapper mapper = new ProjectMapper();
        List<Project> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Project> listByCompany(int companyid) {
        String sql = "SELECT projectid, projectname, p.userid, name, mobile, email, "
        		+ "titleid, tt.codename AS titlename, "
        		+ "propertyid, ppt.codename AS propertyname, "
        		+ "units, orderid, quotationid, smsflag, forecastperiod, "
        		+ "datecreated, status, sts.codename AS statusname "
        		+ "FROM tblProject p "
        		+ "LEFT JOIN tblCodeMaster tt ON tt.codetype = 'TITLE' AND tt.codeid = titleid "
        		+ "LEFT JOIN tblCodeMaster ppt ON ppt.codetype = 'PROPERTY' AND ppt.codeid = propertyid "
        		+ "LEFT JOIN tblCodeMaster sts ON sts.codetype = 'STATUS' AND sts.codeid = status "
        		+ "LEFT JOIN tblUser u ON u.userid = p.userid "
        		+ "WHERE u.companyid = " + companyid + " "
        		+ "ORDER BY projectname";
        ProjectMapper mapper = new ProjectMapper();
        List<Project> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<String> projectList() {
        String sql = "SELECT projectname FROM tblProject";
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
        return list;
    }

    public Project get(int projectid) {
        String sql = "SELECT projectid, projectname, p.userid, name, mobile, email, "
        		+ "titleid, tt.codename AS titlename, "
        		+ "propertyid, ppt.codename AS propertyname, "
        		+ "units, orderid, quotationid, smsflag, forecastperiod, "
        		+ "datecreated, status, sts.codename AS statusname "
        		+ "FROM tblProject p "
        		+ "LEFT JOIN tblCodeMaster tt ON tt.codetype = 'TITLE' AND tt.codeid = titleid "
        		+ "LEFT JOIN tblCodeMaster ppt ON ppt.codetype = 'PROPERTY' AND ppt.codeid = propertyid "
        		+ "LEFT JOIN tblCodeMaster sts ON sts.codetype = 'STATUS' AND sts.codeid = status "
        		+ "LEFT JOIN tblUser u ON u.userid = p.userid "
        		+ "WHERE projectid = " + projectid + " ";
        
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Project>() {
	 
	        @Override
	        public Project extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Project project = new Project();
	            	project.setprojectid(rs.getInt("projectid"));
	            	project.setprojectname(rs.getString("projectname"));
	            	project.setuserid(rs.getInt("userid"));
	            	project.setname(rs.getString("name"));
	            	project.setmobile(rs.getString("mobile"));
	            	project.setemail(rs.getString("email"));
	            	project.settitleid(rs.getInt("titleid"));
	            	project.settitlename(rs.getString("titlename"));
	            	project.setpropertyid(rs.getInt("propertyid"));
	            	project.setpropertyname(rs.getString("propertyname"));
	            	project.setunits(rs.getInt("units"));
	            	project.setorderid(rs.getInt("orderid"));
	            	project.setquotationid(rs.getInt("quotationid"));
	            	project.setsmsflag(rs.getBoolean("smsflag"));
	            	project.setdatecreated(rs.getDate("datecreated"));
	            	project.setforecastperiod(rs.getString("forecastperiod"));
	            	project.setstatus(rs.getString("status"));
	            	project.setstatusname(rs.getString("statusname"));
	                return project;
	            }	 
	            return null;
	        }
        });
    }

    public int getlastprojectid(int userid) {
    	String sql = "SELECT MAX(projectid) FROM tblProject WHERE userid = ?";
        int projectid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {userid}, int.class);
        return projectid;
    }
}