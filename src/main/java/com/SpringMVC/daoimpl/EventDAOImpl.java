package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import com.SpringMVC.model.Calendar;
import com.SpringMVC.model.Event;

import com.SpringMVC.dao.EventDAO;
import com.SpringMVC.mapper.CalendarMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class EventDAOImpl extends JdbcDaoSupport implements EventDAO {
 
    @Autowired
    public EventDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Event event) {
        String sql = "INSERT INTO tblEvent "
        		+ "(userid, period, title, remark, starts, ends, url, allDay, activityid) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		event.getid(), event.getuserid(), event.getperiod(), event.gettitle(), 
        		event.getremark(), event.getstart(), event.getend(), event.geturl(), 
        		event.getallDay(), event.getactivityid());
        }
    
    public void update(Event event) {
        String sql = "UPDATE tblEvent SET title=?, remark=?, starts=?, ends=?, url=?, allDay=? "
        		+ "WHERE id=?";
        this.getJdbcTemplate().update(sql, event.gettitle(), event.getremark(), 
        		event.getstart(), event.getend(), event.geturl(), event.getallDay(),
        		event.getid());
    }

    public void delete(int id) {
        String sql = "DELETE FROM tblEvent WHERE id=?";
        this.getJdbcTemplate().update(sql, id);
    }
    
    public List<Calendar> list(int userid, String period) {
        String sql = "SELECT id, title, starts, ends, url, allDay "	    		
        		+ "FROM tblEvent "
        		+ "WHERE userid = " + userid + " "
        		+ "AND period = '" + period + "'";
        CalendarMapper mapper = new CalendarMapper();
        List<Calendar> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Event get(int eventid) {
	    String sql = "SELECT id, userid, period, title, remark, "
	    		+ "starts, ends, url, allDay, activityid "	    		
        		+ "FROM tblEvent "
	    		+ "WHERE eventid=" + eventid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Event>() {
	 
	        @Override
	        public Event extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Event event = new Event();
	            	event.setid(rs.getInt("id"));
	            	event.setuserid(rs.getInt("userid"));
	            	event.setperiod(rs.getString("period"));
	            	event.settitle(rs.getString("title"));
	            	event.setremark(rs.getString("remark"));	            	
	            	event.setstart(rs.getString("starts"));
	            	event.setend(rs.getString("ends"));
	            	event.seturl(rs.getString("url"));
	            	event.setallDay(rs.getBoolean("allDay"));
	            	event.setactivityid(rs.getInt("activityid"));
	                return event;
	            }	 
	            return null;
	        }
        });
    }

}