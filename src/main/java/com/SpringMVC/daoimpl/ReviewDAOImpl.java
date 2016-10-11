package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Review;
import com.SpringMVC.dao.ReviewDAO;
import com.SpringMVC.mapper.ReviewMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class ReviewDAOImpl extends JdbcDaoSupport implements ReviewDAO {
 
    @Autowired
    public ReviewDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void saveOrUpdate(Review review) {
        if (review.getreviewid() > 0)  {
            // update
            String sql = "UPDATE tblReview SET reviewdate=?, prospect=?, sales=?, totalsales=?, minute=?, reviewby=?"
            		+ "WHERE reviewid=?";
            this.getJdbcTemplate().update(sql, review.getreviewdate(), 
            		review.getprospect(), review.getsales(), review.gettotalsales(), 
            		review.getminute(), review.getreviewby(), 
            		review.getreviewid());
        } else {
            // insert
            String sql = "INSERT INTO tblReview "
            		+ "(userid, targetid, teamtargetid, reviewdate, "
            		+ "prospect, sales, totalsales, minute, reviewby) "
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		review.getuserid(), review.gettargetid(), review.getteamtargetid(), review.getreviewdate(), 
            		review.getprospect(), review.getsales(), review.gettotalsales(), 
            		review.getminute(), review.getreviewby());
            }
    }
    
    public void delete(int reviewid) {
        String sql = "DELETE FROM tblReview WHERE reviewid=?";
        this.getJdbcTemplate().update(sql, reviewid);
    }
    
    public List<Review> list(int userid) {
	    String sql = "SELECT reviewid, userid, targetid, teamtargetid, reviewdate, "
        		+ "prospect, sales, totalsales, minute, reviewby "
        		+ "FROM tblReview "
        		+ "WHERE userid = " + userid;
	    ReviewMapper mapper = new ReviewMapper();
        List<Review> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Review get(int reviewid) {
	    String sql = "SELECT reviewid, userid, targetid, teamtargetid, reviewdate, "
        		+ "prospect, sales, totalsales, minute, reviewby "
        		+ "FROM tblReview "
	    		+ "WHERE activityid=" + reviewid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Review>() {
	 
	        @Override
	        public Review extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Review review = new Review();
	                review.setreviewid(rs.getInt("reviewid"));
	                review.setuserid(rs.getInt("userid"));
	                review.settargetid(rs.getInt("targetid"));
	                review.setteamtargetid(rs.getInt("teamtargetid"));
	                review.setreviewdate(rs.getDate("reviewdate"));
	                review.setprospect(rs.getInt("prospect"));
	                review.setsales(rs.getInt("sales"));
	                review.settotalsales(rs.getFloat("totalsales"));
	                review.setminute(rs.getString("minute"));
	                review.setreviewby(rs.getInt("reviewby"));
	                return review;
	            }	 
	            return null;
	        }
        });
    }
}