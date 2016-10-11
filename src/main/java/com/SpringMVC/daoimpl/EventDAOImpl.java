package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
	
    public void saveOrUpdate(Event event) {
        if (event.geteventid() > 0)  {
            // update
            String sql = "UPDATE tblEvent SET title=?, remark=?, startdate=?, enddate=?, url=?, allday=? "
            		+ "WHERE eventid=?";
            this.getJdbcTemplate().update(sql, event.gettitle(), event.getremark(), 
            		event.getstartdate(), event.getenddate(), event.geturl(), event.getallday(),
            		event.geteventid());
        } else {
            // insert
            String sql = "INSERT INTO tblEvent "
            		+ "(eventid, userid, title, remark, startdate, enddate, url, allday) "
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		event.geteventid(), event.getuserid(), event.gettitle(), event.getremark(),
            		event.getstartdate(), event.getenddate(), event.geturl(), event.getallday());
            }
    }
    
    public void delete(int eventid) {
        String sql = "DELETE FROM tblEvent WHERE eventid=?";
        this.getJdbcTemplate().update(sql, eventid);
    }
    
    public List<Event> list(int userid, Date period) {
        String sql = "SELECT eventid, userid, title, remark, "
	    		+ "startdate, enddate, url, allday "	    		
        		+ "FROM tblEvent "
        		+ "WHERE userid = " + userid + " "
        		+ "AND YEAR(startdate) = YEAR(" + period + ") "
        		+ "AND MONTH(startdate) = MONTH(" + period + ") ";
        EventMapper mapper = new EventMapper();
        List<Event> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Event get(int eventid) {
	    String sql = "SELECT eventid, userid, title, remark, "
	    		+ "startdate, enddate, url, allday "	    		
        		+ "FROM tblEvent "
	    		+ "WHERE eventid=" + eventid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Event>() {
	 
	        @Override
	        public Event extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

	            if (rs.next()) {
	            	Event event = new Event();
	            	event.seteventid(rs.getInt("eventid"));
	            	event.setuserid(rs.getInt("userid"));
	            	event.settitle(rs.getString("title"));
	            	event.setremark(rs.getString("remark"));	            	
	            	event.setstartdate(LocalDateTime.parse((rs.getDate("startdate")+" "+rs.getTime("startdate")), sdf));
	            	event.setenddate(LocalDateTime.parse((rs.getDate("enddate")+" "+rs.getTime("enddate")), sdf));
	            	event.seturl(rs.getString("url"));
	            	event.setallday(rs.getBoolean("allday"));
	                return event;
	            }	 
	            return null;
	        }
        });
    }

}