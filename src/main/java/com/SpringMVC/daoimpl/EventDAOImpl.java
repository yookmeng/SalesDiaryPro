package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import com.SpringMVC.model.Event;

import com.SpringMVC.dao.EventDAO;
import com.SpringMVC.mapper.EventMapper;

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
        		+ "(userid, prospect, activityid, "
        		+ "title, remark, "
        		+ "startdate, starttime, enddate, endtime, "
        		+ "url, allDay) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		event.getuserid(), event.getprospectid(), event.getactivityid(), 
        		event.gettitle(), event.getremark(), 
        		event.getstartdate(), event.getstarttime(), event.getenddate(), event.getendtime(), 
        		event.geturl(), event.getallDay());
        }
    
    public void update(Event event) {
        String sql = "UPDATE tblEvent "
        		+ "SET title=?, remark=?, startdate=?, starttime=?, enddate=?, endtime=?, url=?, allDay=? "
        		+ "WHERE id=?";
        this.getJdbcTemplate().update(sql, event.gettitle(), event.getremark(), 
        		event.getstartdate(), event.getstarttime(), event.getenddate(), event.getendtime(), 
        		event.geturl(), event.getallDay(),event.getid());
    }

    public void delete(int id) {
        String sql = "DELETE FROM tblEvent WHERE id=?";
        this.getJdbcTemplate().update(sql, id);
    }
    
    public List<Event> list(int userid) {
        String sql = "SELECT e.id, e.userid, e.prospectid, p.firstname, p.lastname, p.mobile, "
        		+ "e.activityid, a.quotationpdflink, e.title, e.remark, "
        		+ "e.startdate, e.starttime, e.enddate, e.endtime, e.url, e.allDay "	    		
        		+ "FROM tblEvent e "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = e.prospectid "
        		+ "LEFT JOIN tblActivity a ON a.activityid = e.activityid "
        		+ "WHERE e.userid = " + userid;
        EventMapper mapper = new EventMapper();
        List<Event> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Event get(int eventid) {
        String sql = "SELECT e.id, e.userid, e.prospectid, p.firstname, p.lastname, p.mobile, "
        		+ "e.activityid, a.quotationpdflink, e.title, e.remark, "
        		+ "e.startdate, e.starttime, e.enddate, e.endtime, e.url, e.allDay "	    		
        		+ "FROM tblEvent e "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = e.prospectid "
        		+ "LEFT JOIN tblActivity a ON a.activityid = e.activityid "
	    		+ "WHERE e.id=" + eventid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Event>() {
	 
	        @Override
	        public Event extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Event event = new Event();
	            	event.setid(rs.getInt("id"));
	            	event.setuserid(rs.getInt("userid"));
	            	event.setprospectid(rs.getInt("prospectid"));
	            	event.setfirstname(rs.getString("firstname"));
	            	event.setlastname(rs.getString("lastname"));
	            	event.setmobile(rs.getString("mobile"));
	            	event.setactivityid(rs.getInt("activityid"));
	            	event.setquotationpdflink(rs.getString("quotationpdflink"));
	            	event.settitle(rs.getString("title"));
	            	event.setremark(rs.getString("remark"));
	            	event.setstartdate(rs.getDate("startdate"));
	            	event.setstarttime(rs.getTime("starttime"));
	            	event.setenddate(rs.getDate("enddate"));
	            	event.setendtime(rs.getTime("endtime"));
	            	event.seturl(rs.getString("url"));
	            	event.setallDay(rs.getBoolean("allDay"));
	                return event;
	            }	 
	            return null;
	        }
        });
    }

}