package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Schedule;
import com.SpringMVC.dao.ScheduleDAO;
import com.SpringMVC.mapper.ScheduleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class ScheduleDAOImpl extends JdbcDaoSupport implements ScheduleDAO {
 
    @Autowired
    public ScheduleDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void saveOrUpdate(Schedule schedule) {
        if (schedule.getscheduleid() > 0)  {
            // update
            String sql = "UPDATE tblSchedule SET scheduledate=?, type = ?, "
            		+ "remark1=?, remark2=?, remark3=?, remark4=?, remark5=? "
            		+ "WHERE scheduleid=?";
            this.getJdbcTemplate().update(sql, schedule.getscheduledate(), 
            		schedule.gettype(), schedule.getremark1(), schedule.getremark2(),             		
            		schedule.getremark3(), schedule.getremark4(), schedule.getremark5(), 
            		schedule.getscheduleid());
        } else {
            // insert
            String sql = "INSERT INTO tblSchedule "
            		+ "(prospectid, scheduledate, type, "
            		+ "remark1, remark2, remark3, remark4, remark5) "
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		schedule.getprospectid(), schedule.getscheduledate(), 
            		schedule.gettype(), schedule.getremark1(), schedule.getremark2(), 
            		schedule.getremark3(), schedule.getremark4(), schedule.getremark5());
            }
    }
    
    public void delete(int scheduleid) {
        String sql = "DELETE FROM tblSchedule WHERE scheduleid=?";
        this.getJdbcTemplate().update(sql, scheduleid);
    }
    
    public List<Schedule> list(int prospectid) {
        String sql = "SELECT * FROM tblSchedule WHERE prospectid = " + prospectid;
        ScheduleMapper mapper = new ScheduleMapper();
        List<Schedule> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Schedule get(int scheduleid) {
	    String sql = "SELECT * FROM tblSchedule WHERE scheduleid=" + scheduleid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Schedule>() {
	 
	        @Override
	        public Schedule extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Schedule schedule = new Schedule();
	                schedule.setscheduleid(rs.getInt("scheduleid"));
	                schedule.setprospectid(rs.getInt("prospectid"));
	                schedule.setscheduledate(rs.getString("scheduledate"));
	                schedule.settype(rs.getString("type"));
	                schedule.setremark1(rs.getString("remark1"));
	                schedule.setremark2(rs.getString("remark2"));
	                schedule.setremark3(rs.getString("remark3"));
	                schedule.setremark4(rs.getString("remark4"));
	                schedule.setremark5(rs.getString("remark5"));
	                return schedule;
	            }	 
	            return null;
	        }
        });
    }
}