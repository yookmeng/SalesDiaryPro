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
	
    public void save(Review review) {
        String sql = "INSERT INTO tblReview "
        		+ "(userid, targetid, teamtargetid, reviewdate, "
        		+ "prospect, testdrive, closed, minute, reviewby) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		review.getuserid(), review.gettargetid(), review.getteamtargetid(), review.getreviewdate(), 
        		review.getprospect(), review.gettestdrive(), review.getclosed(), 
        		review.getminute(), review.getreviewby());
    }
    
    public void update(Review review) {
        String sql = "UPDATE tblReview SET reviewdate=?, prospect=?, testdrive=?, closed=?, minute=?, reviewby=?"
        		+ "WHERE reviewid=?";
        this.getJdbcTemplate().update(sql, review.getreviewdate(), 
        		review.getprospect(), review.gettestdrive(), review.getclosed(), 
        		review.getminute(), review.getreviewby(), 
        		review.getreviewid());
    }
    
    public void delete(int reviewid) {
        String sql = "DELETE FROM tblReview WHERE reviewid=?";
        this.getJdbcTemplate().update(sql, reviewid);
    }
    
    public List<Review> list(int userid) {
	    String sql = "SELECT r.reviewid AS reviewid, "
	    		+ "r.userid AS userid, "
	    		+ "up.username AS username, "
	    		+ "r.targetid AS targetid, "
	    		+ "r.teamtargetid AS teamtargetid, "
	    		+ "r.reviewdate AS reviewdate, "
        		+ "r.prospect AS prospect, "
        		+ "r.testdrive AS testdrive, "
        		+ "r.closed AS closed, "
        		+ "r.minute AS minute, "
        		+ "r.reviewby AS reviewby "
        		+ "FROM tblReview r "
        		+ "LEFT JOIN tblUserProfile up on up.userid = r.userid "
        		+ "WHERE r.userid = " + userid;
	    ReviewMapper mapper = new ReviewMapper();
        List<Review> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Review> listByBranch(int branchid) {
	    String sql = "SELECT r.reviewid AS reviewid, "
	    		+ "r.userid AS userid, up.username AS username, "
	    		+ "r.targetid AS targetid, "
	    		+ "r.teamtargetid AS teamtargetid, "
	    		+ "r.reviewdate AS reviewdate, "
        		+ "r.prospect AS prospect, "
        		+ "r.testdrive AS testdrive, "
        		+ "r.closed AS closed, "
        		+ "r.minute AS minute, "
        		+ "r.reviewby AS reviewby "
        		+ "FROM tblReview r "
        		+ "LEFT JOIN tblUserProfile up on up.userid = r.userid "
        		+ "LEFT JOIN tblTeam t on t.teamid = up.teamid "
        		+ "LEFT JOIN tblBranch b on b.branchid = t.branchid "        		
        		+ "WHERE b.branchid = " + branchid;
	    ReviewMapper mapper = new ReviewMapper();
        List<Review> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Review> listByCompany(int companyid) {
	    String sql = "SELECT r.reviewid AS reviewid, "
	    		+ "r.userid AS userid, "
	    		+ "up.username AS username, "
	    		+ "r.targetid AS targetid, "
	    		+ "r.teamtargetid AS teamtargetid, "
	    		+ "r.reviewdate AS reviewdate, "
        		+ "r.prospect AS prospect, "
        		+ "r.testdrive AS testdrive, "
        		+ "r.closed AS closed, "
        		+ "r.minute AS minute, "
        		+ "r.reviewby AS reviewby "
        		+ "FROM tblReview r "
        		+ "LEFT JOIN tblUserProfile up on up.userid = r.userid "
        		+ "LEFT JOIN tblTeam t on t.teamid = up.teamid "
        		+ "LEFT JOIN tblBranch b on b.branchid = t.branchid "
        		+ "LEFT JOIN tblCompany c on c.companyid = b.companyid "
        		+ "WHERE c.companyid = " + companyid;
	    ReviewMapper mapper = new ReviewMapper();
        List<Review> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Review get(int reviewid) {
	    String sql = "SELECT r.reviewid AS reviewid, "
	    		+ "r.userid AS userid, "
	    		+ "up.username AS username, "
	    		+ "r.targetid AS targetid, "
	    		+ "r.teamtargetid AS teamtargetid, "
	    		+ "r.reviewdate AS reviewdate, "
        		+ "r.prospect AS prospect, "
        		+ "r.testdrive AS testdrive, "
        		+ "r.closed AS closed, "
        		+ "r.minute AS minute, "
        		+ "r.reviewby AS reviewby "
        		+ "FROM tblReview r "
        		+ "LEFT JOIN tblUserProfile up on up.userid = r.userid "
	    		+ "WHERE r.reviewid=" + reviewid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Review>() {
	 
	        @Override
	        public Review extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Review review = new Review();
	                review.setreviewid(rs.getInt("reviewid"));
	                review.setuserid(rs.getInt("userid"));
	                review.setusername(rs.getString("username"));
	                review.settargetid(rs.getInt("targetid"));
	                review.setteamtargetid(rs.getInt("teamtargetid"));
	                review.setreviewdate(rs.getDate("reviewdate"));
	                review.setprospect(rs.getInt("prospect"));
	                review.settestdrive(rs.getInt("testdrive"));
	                review.setclosed(rs.getInt("closed"));
	                review.setminute(rs.getString("minute"));
	                review.setreviewby(rs.getInt("reviewby"));
	                return review;
	            }	 
	            return null;
	        }
        });
    }
}