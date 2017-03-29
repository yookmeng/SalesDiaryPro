package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;

import com.SpringMVC.model.Address;
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
	
    public void save(Prospect prospect) {
    	Address homeaddress = prospect.gethomeaddress();
    	Address workaddress = prospect.getworkaddress();
    	String sql = "";
    	
        sql = "INSERT INTO tblProspect "
    		+ "(firstname, lastname, userid, source, "
    		+ "homeaddress.country, homeaddress.zipcode, homeaddress.state, "
    		+ "homeaddress.city, homeaddress.street, mobile, htelno, "
    		+ "workaddress.country, workaddress.zipcode, workaddress.state, "
    		+ "workaddress.city, workaddress.street, wtelno, "
    		+ "gender, email, status) "
    		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
    		prospect.getfirstname(), prospect.getlastname(), prospect.getuserid(), prospect.getsource(), 
    		homeaddress.getcountry(), homeaddress.getzipcode(), homeaddress.getstate(), 
    		homeaddress.getcity(), homeaddress.getstreet(), prospect.getmobile(), prospect.gethtelno(), 
    		workaddress.getcountry(), workaddress.getzipcode(), workaddress.getstate(), 
    		workaddress.getcity(), workaddress.getstreet(), prospect.getwtelno(), 
    		prospect.getgender(), prospect.getemail(), prospect.getstatus());

        sql = "INSERT INTO tblContact "
    		+ "(userid, firstname, lastname, mobile, home, work, email, "
    		+ "address.country, address.zipcode, address.state, address.city, address.street) "
    		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
    		prospect.getuserid(), prospect.getfirstname(), prospect.getlastname(), 
    		prospect.getmobile(), prospect.gethtelno(), prospect.getwtelno(), prospect.getemail(), 
    		homeaddress.getcountry(), homeaddress.getzipcode(), homeaddress.getstate(), 
    		homeaddress.getcity(), homeaddress.getstreet());

//        get created prospectid and contactid
        sql = "Select prospectid from tblProspect "
    		+ "where userid=? AND firstname=? AND lastname=? AND mobile=?";
        int prospectid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {
        		prospect.getuserid(), prospect.getfirstname(), prospect.getlastname(),
        		prospect.getmobile()}, int.class); 
        sql = "Select contactid from tblContact "
    		+ "where userid=? AND firstname=? AND lastname=? AND mobile=?";
        int contactid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {
        		prospect.getuserid(), prospect.getfirstname(), prospect.getlastname(),
        		prospect.getmobile()}, int.class); 
        sql = "UPDATE tblProspect SET contactid=? WHERE prospectid=?";
        this.getJdbcTemplate().update(sql, contactid, prospectid);
    }
    
    public void update(Prospect prospect) {
    	Address homeaddress = prospect.gethomeaddress();
    	Address workaddress = prospect.getworkaddress();
    	String sql = "";

    	sql = "UPDATE tblProspect SET firstname=?, lastname=?, source=?, "
    		+ "homeaddress.country=?, homeaddress.zipcode=?, homeaddress.state=?, "
    		+ "homeaddress.city=?, homeaddress.street=?, mobile=?, htelno=?, "
    		+ "workaddress.country=?, workaddress.zipcode=?, workaddress.state=?, "
    		+ "workaddress.city=?, workaddress.street=?, wtelno=?, "
    		+ "gender=?, email=?, status=? "
    		+ "WHERE prospectid=?";
        this.getJdbcTemplate().update(sql, 
    		prospect.getfirstname(), prospect.getlastname(), prospect.getsource(), 
    		homeaddress.getcountry(), homeaddress.getzipcode(), homeaddress.getstate(), 
    		homeaddress.getcity(), homeaddress.getstreet(), prospect.getmobile(), prospect.gethtelno(), 
    		workaddress.getcountry(), workaddress.getzipcode(), workaddress.getstate(), 
    		workaddress.getcity(), workaddress.getstreet(), prospect.getwtelno(), 
    		prospect.getgender(), prospect.getemail(), prospect.getstatus(),
    		prospect.getprospectid());

        sql = "UPDATE tblContact SET firstname=?, lastname=?, mobile=?, "
    		+ "home=?, work=?, email=?, address.country=?, "
    		+ "address.zipcode=?, address.state=?, address.city=?, address.street=? "
    		+ "WHERE contactid=?";
        this.getJdbcTemplate().update(sql, 
    		prospect.getfirstname(), prospect.getlastname(), prospect.getmobile(), 
    		prospect.gethtelno(), prospect.getwtelno(), prospect.getemail(), 
    		homeaddress.getcountry(), homeaddress.getzipcode(), homeaddress.getstate(), 
    		homeaddress.getcity(), homeaddress.getstreet(), prospect.getcontactid());
	}

    public void delete(int prospectid) {
        String sql = "DELETE FROM tblProspect WHERE prospectid=?";
        this.getJdbcTemplate().update(sql, prospectid);
    }
    
    public List<Prospect> list(int userid) {
        String sql = "SELECT p.prospectid, firstname, lastname, "
        		+ "p.userid, source, a.activitydate AS datecreated, "
        		+ "a.brandid, b.brandname, a.modelid, m.modelname, "
        		+ "(homeaddress).country AS hcountry, (homeaddress).zipcode AS hzipcode, "
        		+ "(homeaddress).state as hstate, (homeaddress).city AS hcity, "
        		+ "(homeaddress).street AS hstreet, mobile, htelno, contactid, "
        		+ "(workaddress).country AS wcountry, (workaddress).zipcode AS wzipcode, "
        		+ "(workaddress).state as wstate, (workaddress).city AS wcity, "
        		+ "(workaddress).street AS wstreet, wtelno, "
        		+ "gender, email, status, cm.codename AS statusname "
        		+ "FROM tblProspect p "
        		+ "LEFT JOIN tblActivity a ON p.prospectid = a.prospectid "
        		+ "LEFT JOIN tblBrand b ON a.brandid = b.brandid "
        		+ "LEFT JOIN tblModel m ON a.modelid = m.modelid "
        		+ "LEFT JOIN tblCodeMaster cm ON cm.codetype = 'STATUS' AND codeid = p.status "
        		+ "WHERE p.userid = " + userid + " "
				+ "AND (a.activitydate = (SELECT MIN(c.activitydate) FROM tblActivity c WHERE c.prospectid = p.prospectid) OR a.activitydate IS NULL) "
				+ "ORDER BY firstname, lastname";
        ProspectMapper mapper = new ProspectMapper();
        List<Prospect> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
    public List<Prospect> listByTeam(int teamid) {
        String sql = "SELECT p.prospectid, firstname, lastname, "
        		+ "p.userid, source, a.activitydate AS datecreated, "
        		+ "a.brandid, b.brandname, a.modelid, m.modelname, "
        		+ "(homeaddress).country AS hcountry, (homeaddress).zipcode AS hzipcode, "
        		+ "(homeaddress).state as hstate, (homeaddress).city AS hcity, "
        		+ "(homeaddress).street AS hstreet, p.mobile, htelno, contactid, "
        		+ "(workaddress).country AS wcountry, (workaddress).zipcode AS wzipcode, "
        		+ "(workaddress).state as wstate, (workaddress).city AS wcity, "
        		+ "(workaddress).street AS wstreet, wtelno, "
        		+ "occupation, age, gender, income, p.email, status, cm.codename AS statusname "
        		+ "FROM tblProspect p "
        		+ "LEFT JOIN tblActivity a ON p.prospectid = a.prospectid "
        		+ "LEFT JOIN tblBrand b ON a.brandid = b.brandid "
        		+ "LEFT JOIN tblModel m ON a.modelid = m.modelid "
        		+ "LEFT JOIN tblUser u ON u.userid = p.userid "
        		+ "LEFT JOIN tblCodeMaster cm ON cm.codetype = 'STATUS' AND codeid = p.status "
        		+ "WHERE u.teamid = " + teamid + " "
				+ "AND (a.activitydate = (SELECT MIN(c.activitydate) FROM tblActivity c WHERE c.prospectid = p.prospectid) OR a.activitydate IS NULL) "
				+ "ORDER BY firstname, lastname";
        ProspectMapper mapper = new ProspectMapper();
        List<Prospect> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Prospect> listByBranch(int branchid) {
        String sql = "SELECT p.prospectid, firstname, lastname, "
        		+ "p.userid, source, a.activitydate AS datecreated, "
        		+ "a.brandid, b.brandname, a.modelid, m.modelname, "
        		+ "(homeaddress).country AS hcountry, (homeaddress).zipcode AS hzipcode, "
        		+ "(homeaddress).state as hstate, (homeaddress).city AS hcity, "
        		+ "(homeaddress).street AS hstreet, p.mobile, htelno, contactid, "
        		+ "(workaddress).country AS wcountry, (workaddress).zipcode AS wzipcode, "
        		+ "(workaddress).state as wstate, (workaddress).city AS wcity, "
        		+ "(workaddress).street AS wstreet, wtelno, "
        		+ "occupation, age, gender, income, p.email, status, cm.codename AS statusname "
        		+ "FROM tblProspect p "
        		+ "LEFT JOIN tblActivity a ON p.prospectid = a.prospectid "
        		+ "LEFT JOIN tblBrand b ON a.brandid = b.brandid "
        		+ "LEFT JOIN tblModel m ON a.modelid = m.modelid "
        		+ "LEFT JOIN tblUser u ON u.userid = p.userid "
        		+ "LEFT JOIN tblCodeMaster cm ON cm.codetype = 'STATUS' AND codeid = p.status "
        		+ "WHERE u.branchid = " + branchid + " "
				+ "AND (a.activitydate = (SELECT MIN(c.activitydate) FROM tblActivity c WHERE c.prospectid = p.prospectid) OR a.activitydate IS NULL) "
        		+ "ORDER BY firstname, lastname";
        ProspectMapper mapper = new ProspectMapper();
        List<Prospect> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Prospect> listByCompany(int companyid) {
        String sql = "SELECT p.prospectid, firstname, lastname, "
        		+ "p.userid, source, a.activitydate AS datecreated, "
        		+ "a.brandid, b.brandname, a.modelid, m.modelname, "
        		+ "(homeaddress).country AS hcountry, (homeaddress).zipcode AS hzipcode, "
        		+ "(homeaddress).state as hstate, (homeaddress).city AS hcity, "
        		+ "(homeaddress).street AS hstreet, p.mobile, htelno, contactid, "
        		+ "(workaddress).country AS wcountry, (workaddress).zipcode AS wzipcode, "
        		+ "(workaddress).state as wstate, (workaddress).city AS wcity, "
        		+ "(workaddress).street AS wstreet, wtelno, "
        		+ "occupation, age, gender, income, p.email, status, cm.codename AS statusname "
        		+ "FROM tblProspect p "
        		+ "LEFT JOIN tblActivity a ON p.prospectid = a.prospectid "
        		+ "LEFT JOIN tblBrand b ON a.brandid = b.brandid "
        		+ "LEFT JOIN tblModel m ON a.modelid = m.modelid "
        		+ "LEFT JOIN tblUser u ON u.userid = p.userid "
        		+ "LEFT JOIN tblCodeMaster cm ON cm.codetype = 'STATUS' AND codeid = p.status "
        		+ "WHERE u.companyid = " + companyid + " "
				+ "AND a.activitydate = (SELECT MIN(c.activitydate) FROM tblActivity c WHERE c.prospectid = p.prospectid) "
        		+ "ORDER BY firstname, lastname";
        ProspectMapper mapper = new ProspectMapper();
        List<Prospect> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<String> prospectList() {
        String sql = "SELECT lastname+', '+firstname FROM tblProspect";
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
        return list;
    }

    public Prospect get(int prospectid) {
        String sql = "SELECT p.prospectid, firstname, lastname, "
        		+ "p.userid, source, a.activitydate AS datecreated, "
        		+ "a.brandid, b.brandname, a.modelid, m.modelname, "
        		+ "(homeaddress).country AS hcountry, (homeaddress).zipcode AS hzipcode, "
        		+ "(homeaddress).state as hstate, (homeaddress).city AS hcity, "
        		+ "(homeaddress).street AS hstreet, mobile, htelno, contactid, "
        		+ "(workaddress).country AS wcountry, (workaddress).zipcode AS wzipcode, "
        		+ "(workaddress).state as wstate, (workaddress).city AS wcity, "
        		+ "(workaddress).street AS wstreet, wtelno, "
        		+ "occupation, age, gender, income, email, status, cm.codename "
        		+ "FROM tblProspect p "
        		+ "LEFT JOIN tblActivity a ON p.prospectid = a.prospectid "
        		+ "LEFT JOIN tblBrand b ON a.brandid = b.brandid "
        		+ "LEFT JOIN tblModel m ON a.modelid = m.modelid "
        		+ "LEFT JOIN tblCodeMaster cm ON cm.codetype = 'STATUS' AND codeid = p.status "
        		+ "WHERE p.prospectid=" + prospectid + " "
        		+ "AND (a.activitydate = (SELECT MIN(c.activitydate) FROM tblActivity c WHERE c.prospectid = p.prospectid) OR a.activitydate IS NULL) ";
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Prospect>() {
	 
	        @Override
	        public Prospect extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Prospect prospect = new Prospect();
	            	prospect.setprospectid(rs.getInt("prospectid"));
	            	prospect.setfirstname(rs.getString("firstname"));
	            	prospect.setlastname(rs.getString("lastname"));
	            	prospect.setuserid(rs.getInt("userid"));
	            	prospect.setsource(rs.getString("source"));
	            	prospect.setdatecreated(rs.getDate("datecreated"));
	            	prospect.setbrandid(rs.getInt("brandid"));
	            	prospect.setbrandname(rs.getString("brandname"));
	            	prospect.setmodelid(rs.getInt("modelid"));
	            	prospect.setmodelname(rs.getString("modelname"));
	                Address homeaddress = new Address();
	                homeaddress.setcountry(rs.getString("hcountry"));
	                homeaddress.setzipcode(rs.getString("hzipcode"));
	                homeaddress.setstate(rs.getString("hstate"));
	                homeaddress.setcity(rs.getString("hcity"));
	                homeaddress.setstreet(rs.getString("hstreet"));
	                prospect.sethomeaddress(homeaddress);
	            	prospect.setmobile(rs.getString("mobile"));
	            	prospect.sethtelno(rs.getString("htelno"));
	            	prospect.setcontactid(rs.getInt("contactid"));
	                Address workaddress = new Address();
	                workaddress.setcountry(rs.getString("wcountry"));
	                workaddress.setzipcode(rs.getString("wzipcode"));
	                workaddress.setstate(rs.getString("wstate"));
	                workaddress.setcity(rs.getString("wcity"));
	                workaddress.setstreet(rs.getString("wstreet"));
	                prospect.setworkaddress(workaddress);
	            	prospect.setwtelno(rs.getString("wtelno"));
	            	prospect.setgender(rs.getString("gender"));
	            	prospect.setemail(rs.getString("email"));
	            	prospect.setstatus(rs.getString("status"));
	            	prospect.setstatusname(rs.getString("codename"));
	                return prospect;
	            }	 
	            return null;
	        }
        });
    }

    public int getlastprospectid(int userid) {
    	String sql = "SELECT MAX(prospectid) FROM tblProspect WHERE userid = ?";
        int prospectid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {userid}, int.class);
        return prospectid;
    }
}