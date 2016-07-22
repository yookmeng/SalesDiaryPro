package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Prospect;
import com.SpringMVC.dao.ProspectDAO;
import com.SpringMVC.mapper.ProspectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class ProspectDAOImpl extends JdbcDaoSupport implements ProspectDAO {
 
    @Autowired
    public ProspectDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void saveOrUpdate(Prospect prospect) {
        if (prospect.getprospectid() > 0)  {
            // update
            String sql = "UPDATE tblProspect SET prospectname=?, source=?, "
            		+ "haddress=?, hzipcode=?, hcity=?, hstate=?, hcountry=?, mobile=?, htelno=?, "
            		+ "waddress=?, wzipcode=?, wcity=?, wstate=?, wcountry=?, wtelno=?, "
            		+ "occupation=?, age=?, income=?, email=? WHERE prospectid=?";
            this.getJdbcTemplate().update(sql, 
            		prospect.getprospectname(), prospect.getsource(), 
            		prospect.gethaddress(), prospect.gethzipcode(), prospect.gethcity(), 
            		prospect.gethstate(), prospect.gethcountry(), prospect.getmobile(), prospect.gethtelno(), 
            		prospect.getwaddress(), prospect.getwzipcode(), prospect.getwcity(), 
            		prospect.getwstate(), prospect.getwcountry(), prospect.getwtelno(), 
            		prospect.getoccupation(), prospect.getage(), prospect.getincome(), prospect.getemail(),
            		prospect.getprospectid());
        } else {
            // insert
            String sql = "INSERT INTO tblProspect "
            		+ "(prospectname, userid, source, "
            		+ "haddress, hzipcode, hcity, hstate, hcountry, mobile, htelno, "
            		+ "waddress, wzipcode, wcity, wstate, wcountry, wtelno, "
            		+ "occupation, age, income, email) "
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		prospect.getprospectname(), prospect.getuserid(), prospect.getsource(), 
            		prospect.gethaddress(), prospect.gethzipcode(), prospect.gethcity(), 
            		prospect.gethstate(), prospect.gethcountry(), prospect.getmobile(), prospect.gethtelno(), 
            		prospect.getwaddress(), prospect.getwzipcode(), prospect.getwcity(), 
            		prospect.getwstate(), prospect.getwcountry(), prospect.getwtelno(), 
            		prospect.getoccupation(), prospect.getage(), prospect.getincome(), prospect.getemail());
            }
    }
    
    public void delete(int prospectid) {
        String sql = "DELETE FROM tblProspect WHERE prospectid=?";
        this.getJdbcTemplate().update(sql, prospectid);
    }
    
    public List<Prospect> list(int userid) {
        String sql = "SELECT * FROM tblProspect WHERE userid = " + userid;
        ProspectMapper mapper = new ProspectMapper();
        List<Prospect> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
    public List<String> prospectList() {
        String sql = "SELECT prospectname FROM tblProspect";
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
        return list;
    }

    public Prospect get(int prospectid) {
        String sql = "SELECT * FROM tblProspect WHERE prospectid=" + prospectid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Prospect>() {
	 
	        @Override
	        public Prospect extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Prospect prospect = new Prospect();
	            	prospect.setprospectid(rs.getInt("prospectid"));
	            	prospect.setprospectname(rs.getString("prospectname"));
	            	prospect.setuserid(rs.getInt("userid"));
	            	prospect.setsource(rs.getString("source"));
	            	prospect.sethaddress(rs.getString("haddress"));
	            	prospect.sethzipcode(rs.getString("hzipcode"));
	            	prospect.sethcity(rs.getString("hcity"));
	            	prospect.sethstate(rs.getString("hstate"));
	            	prospect.sethcountry(rs.getString("hcountry"));
	            	prospect.setmobile(rs.getString("mobile"));
	            	prospect.sethtelno(rs.getString("htelno"));
	            	prospect.setwaddress(rs.getString("waddress"));
	            	prospect.setwzipcode(rs.getString("wzipcode"));
	            	prospect.setwcity(rs.getString("wcity"));
	            	prospect.setwstate(rs.getString("wstate"));
	            	prospect.setwcountry(rs.getString("wcountry"));
	            	prospect.setwtelno(rs.getString("wtelno"));
	            	prospect.setoccupation(rs.getString("occupation"));
	            	prospect.setage(rs.getInt("age"));
	            	prospect.setincome(rs.getString("income"));
	            	prospect.setemail(rs.getString("email"));
	                return prospect;
	            }	 
	            return null;
	        }
        });
    }
}