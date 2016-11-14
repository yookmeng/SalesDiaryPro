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
    		+ "occupation, age, gender, income, email, status) "
    		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
    		prospect.getfirstname(), prospect.getlastname(), prospect.getuserid(), prospect.getsource(), 
    		homeaddress.getcountry(), homeaddress.getzipcode(), homeaddress.getstate(), 
    		homeaddress.getcity(), homeaddress.getstreet(), prospect.getmobile(), prospect.gethtelno(), 
    		workaddress.getcountry(), workaddress.getzipcode(), workaddress.getstate(), 
    		workaddress.getcity(), workaddress.getstreet(), prospect.getwtelno(), 
    		prospect.getoccupation(), prospect.getage(), prospect.getgender(), 
    		prospect.getincome(), prospect.getemail(), prospect.getstatus());

        sql = "INSERT INTO tblContact "
    		+ "(userid, firstname, lastname, mobile, home, work, email, "
    		+ "address.country, address.zipcode, address.state, address.city, address.street) "
    		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
    		prospect.getuserid(), prospect.getfirstname(), prospect.getlastname(), 
    		prospect.getmobile(), prospect.gethtelno(), prospect.getwtelno(), prospect.getemail(), 
    		homeaddress.getcountry(), homeaddress.getzipcode(), homeaddress.getstate(), 
    		homeaddress.getcity(), homeaddress.getstreet());

//        get ctreated prospectid and contactid
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
    		+ "occupation=?, age=?, gender=?, income=?, email=?, status=? "
    		+ "WHERE prospectid=?";
        this.getJdbcTemplate().update(sql, 
    		prospect.getfirstname(), prospect.getlastname(), prospect.getsource(), 
    		homeaddress.getcountry(), homeaddress.getzipcode(), homeaddress.getstate(), 
    		homeaddress.getcity(), homeaddress.getstreet(), prospect.getmobile(), prospect.gethtelno(), 
    		workaddress.getcountry(), workaddress.getzipcode(), workaddress.getstate(), 
    		workaddress.getcity(), workaddress.getstreet(), prospect.getwtelno(), 
    		prospect.getoccupation(), prospect.getage(), prospect.getgender(), 
    		prospect.getincome(), prospect.getemail(), prospect.getstatus(),
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
        String sql = "SELECT prospectid, firstname, lastname, userid, source, "
        		+ "(homeaddress).country AS hcountry, (homeaddress).zipcode AS hzipcode, "
        		+ "(homeaddress).state as hstate, (homeaddress).city AS hcity, "
        		+ "(homeaddress).street AS hstreet, mobile, htelno, contactid, "
        		+ "(workaddress).country AS wcountry, (workaddress).zipcode AS wzipcode, "
        		+ "(workaddress).state as wstate, (workaddress).city AS wcity, "
        		+ "(workaddress).street AS wstreet, wtelno, "
        		+ "occupation, age, gender, income, email, status "
        		+ "FROM tblProspect WHERE userid = " + userid;
        ProspectMapper mapper = new ProspectMapper();
        List<Prospect> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
    public List<Prospect> listByTeam(int teamid) {
        String sql = "SELECT prospectid, firstname, lastname, userid, source, "
        		+ "(homeaddress).country AS hcountry, (homeaddress).zipcode AS hzipcode, "
        		+ "(homeaddress).state as hstate, (homeaddress).city AS hcity, "
        		+ "(homeaddress).street AS hstreet, mobile, htelno, contactid, "
        		+ "(workaddress).country AS wcountry, (workaddress).zipcode AS wzipcode, "
        		+ "(workaddress).state as wstate, (workaddress).city AS wcity, "
        		+ "(workaddress).street AS wstreet, wtelno, "
        		+ "occupation, age, gender, income, email, status "
        		+ "FROM tblProspect WHERE userid IN ("
        		+ "SELECT userid FROM tblUserProfile WHERE teamid = " + teamid + ")";
        ProspectMapper mapper = new ProspectMapper();
        List<Prospect> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Prospect> listByBranch(int branchid) {
        String sql = "SELECT prospectid, firstname, lastname, userid, source, "
        		+ "(homeaddress).country AS hcountry, (homeaddress).zipcode AS hzipcode, "
        		+ "(homeaddress).state as hstate, (homeaddress).city AS hcity, "
        		+ "(homeaddress).street AS hstreet, mobile, htelno, contactid, "
        		+ "(workaddress).country AS wcountry, (workaddress).zipcode AS wzipcode, "
        		+ "(workaddress).state as wstate, (workaddress).city AS wcity, "
        		+ "(workaddress).street AS wstreet, wtelno, "
        		+ "occupation, age, gender, income, email, status "
        		+ "FROM tblProspect WHERE userid IN ("
        		+ "SELECT userid FROM tblUserProfile WHERE teamid IN ("
        		+ "SELECT teamid FROM tblTeam WHERE branchid = " + branchid + ")";
        ProspectMapper mapper = new ProspectMapper();
        List<Prospect> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Prospect> listByCompany(int companyid) {
        String sql = "SELECT prospectid, firstname, lastname, userid, source, "
        		+ "(homeaddress).country AS hcountry, (homeaddress).zipcode AS hzipcode, "
        		+ "(homeaddress).state as hstate, (homeaddress).city AS hcity, "
        		+ "(homeaddress).street AS hstreet, mobile, htelno, contactid, "
        		+ "(workaddress).country AS wcountry, (workaddress).zipcode AS wzipcode, "
        		+ "(workaddress).state as wstate, (workaddress).city AS wcity, "
        		+ "(workaddress).street AS wstreet, wtelno, "
        		+ "occupation, age, gender, income, email, status "
        		+ "FROM tblProspect WHERE userid IN ("
        		+ "SELECT userid FROM tblUserProfile WHERE teamid IN ("
        		+ "SELECT teamid FROM tblTeam WHERE branchid IN ("
        		+ "SELECT branchid FROM tblBranch WHERE companyid = " + companyid + ")";
        ProspectMapper mapper = new ProspectMapper();
        List<Prospect> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Prospect> listfilter(int userid, String status) {
        String sql = "SELECT prospectid, firstname, lastname, userid, source, "
        		+ "(homeaddress).country AS hcountry, (homeaddress).zipcode AS hzipcode, "
        		+ "(homeaddress).state as hstate, (homeaddress).city AS hcity, "
        		+ "(homeaddress).street AS hstreet, mobile, htelno, contactid, "
        		+ "(workaddress).country AS wcountry, (workaddress).zipcode AS wzipcode, "
        		+ "(workaddress).state as wstate, (workaddress).city AS wcity, "
        		+ "(workaddress).street AS wstreet, wtelno, "
        		+ "occupation, age, gender, income, email, status "
        		+ "FROM tblProspect WHERE userid = " + userid + " "
        		+ "AND status = '" + status + "'";
        ProspectMapper mapper = new ProspectMapper();
        List<Prospect> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Prospect> listByTeamfilter(int teamid, String status) {
        String sql = "SELECT prospectid, firstname, lastname, userid, source, "
        		+ "(homeaddress).country AS hcountry, (homeaddress).zipcode AS hzipcode, "
        		+ "(homeaddress).state as hstate, (homeaddress).city AS hcity, "
        		+ "(homeaddress).street AS hstreet, mobile, htelno, contactid, "
        		+ "(workaddress).country AS wcountry, (workaddress).zipcode AS wzipcode, "
        		+ "(workaddress).state as wstate, (workaddress).city AS wcity, "
        		+ "(workaddress).street AS wstreet, wtelno, "
        		+ "occupation, age, gender, income, email, status "
        		+ "FROM tblProspect WHERE userid IN ("
        		+ "SELECT userid FROM tblUserProfile WHERE teamid = " + teamid + ") "
        		+ "AND status = '" + status + "'";
        ProspectMapper mapper = new ProspectMapper();
        List<Prospect> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Prospect> listByBranchfilter(int branchid, String status) {
        String sql = "SELECT prospectid, firstname, lastname, userid, source, "
        		+ "(homeaddress).country AS hcountry, (homeaddress).zipcode AS hzipcode, "
        		+ "(homeaddress).state as hstate, (homeaddress).city AS hcity, "
        		+ "(homeaddress).street AS hstreet, mobile, htelno, contactid, "
        		+ "(workaddress).country AS wcountry, (workaddress).zipcode AS wzipcode, "
        		+ "(workaddress).state as wstate, (workaddress).city AS wcity, "
        		+ "(workaddress).street AS wstreet, wtelno, "
        		+ "occupation, age, gender, income, email, status "
        		+ "FROM tblProspect WHERE userid IN ("
        		+ "SELECT userid FROM tblUserProfile WHERE teamid IN ("
        		+ "SELECT teamid FROM tblTeam WHERE branchid = " + branchid + ") "
        		+ "AND status = '" + status + "'";
        ProspectMapper mapper = new ProspectMapper();
        List<Prospect> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Prospect> listByCompanyfilter(int companyid, String status) {
        String sql = "SELECT prospectid, firstname, lastname, userid, source, "
        		+ "(homeaddress).country AS hcountry, (homeaddress).zipcode AS hzipcode, "
        		+ "(homeaddress).state as hstate, (homeaddress).city AS hcity, "
        		+ "(homeaddress).street AS hstreet, mobile, htelno, contactid, "
        		+ "(workaddress).country AS wcountry, (workaddress).zipcode AS wzipcode, "
        		+ "(workaddress).state as wstate, (workaddress).city AS wcity, "
        		+ "(workaddress).street AS wstreet, wtelno, "
        		+ "occupation, age, gender, income, email, status "
        		+ "FROM tblProspect WHERE userid IN ("
        		+ "SELECT userid FROM tblUserProfile WHERE teamid IN ("
        		+ "SELECT teamid FROM tblTeam WHERE branchid IN ("
        		+ "SELECT branchid FROM tblBranch WHERE companyid = " + companyid + ") "
        		+ "AND status = '" + status + "'";
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
        String sql = "SELECT prospectid, firstname, lastname, userid, source, "
        		+ "(homeaddress).country AS hcountry, (homeaddress).zipcode AS hzipcode, "
        		+ "(homeaddress).state as hstate, (homeaddress).city AS hcity, "
        		+ "(homeaddress).street AS hstreet, mobile, htelno, contactid, "
        		+ "(workaddress).country AS wcountry, (workaddress).zipcode AS wzipcode, "
        		+ "(workaddress).state as wstate, (workaddress).city AS wcity, "
        		+ "(workaddress).street AS wstreet, wtelno, "
        		+ "occupation, age, gender, income, email, status "
        		+ "FROM tblProspect WHERE prospectid=" + prospectid;
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
	            	prospect.setoccupation(rs.getString("occupation"));
	            	prospect.setage(rs.getInt("age"));
	            	prospect.setgender(rs.getString("gender"));
	            	prospect.setincome(rs.getString("income"));
	            	prospect.setemail(rs.getString("email"));
	            	prospect.setstatus(rs.getString("status"));
	                return prospect;
	            }	 
	            return null;
	        }
        });
    }

}