package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Request;
import com.SpringMVC.dao.RequestDAO;
import com.SpringMVC.mapper.RequestMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class RequestDAOImpl extends JdbcDaoSupport implements RequestDAO {
 
    @Autowired
    public RequestDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void saveOrUpdate(Request request) {
        if (request.getrequestid() > 0)  {
            // update
            String sql = "UPDATE tblRequest SET requestdate=?, brandid = ?, modelid = ?, remark=?, status = ?  "
            		+ "WHERE requestid=?";
            this.getJdbcTemplate().update(sql, request.getrequestdate(), 
            		request.getbrandid(), request.getmodelid(), request.getremark(), request.getstatus(), 
            		request.getrequestid());
        } else {
            // insert
            String sql = "INSERT INTO tblRequest "
            		+ "(prospectid, requestdate, brandid, modelid, remark, status) "
            		+ "VALUES (?, ?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		request.getprospectid(), request.getrequestdate(), request.getbrandid(), 
            		request.getmodelid(), request.getremark(), request.getstatus());
            }
    }
    
    public void delete(int orderid) {
        String sql = "DELETE FROM tblRequest WHERE requestid=?";
        this.getJdbcTemplate().update(sql, orderid);
    }
    
    public List<Request> list(int prospectid) {
        String sql = "SELECT * FROM tblRequest WHERE prospectid = " + prospectid;
        RequestMapper mapper = new RequestMapper();
        List<Request> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Request get(int requestid) {
	    String sql = "SELECT * FROM tblRequest WHERE requestid=" + requestid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Request>() {
	 
	        @Override
	        public Request extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Request request = new Request();
	                request.setrequestid(rs.getInt("requestid"));
	                request.setprospectid(rs.getInt("prospectid"));
	                request.setrequestdate(rs.getString("requestdate"));
	                request.setbrandid(rs.getInt("brandid"));
	                request.setmodelid(rs.getInt("modelid"));
	                request.setremark(rs.getString("remark"));
	                request.setstatus(rs.getString("status"));
	                return request;
	            }	 
	            return null;
	        }
        });
    }
}