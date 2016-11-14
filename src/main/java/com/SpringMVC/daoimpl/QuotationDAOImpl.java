package com.SpringMVC.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
 
import javax.sql.DataSource;

import com.SpringMVC.model.Quotation;
import com.SpringMVC.dao.QuotationDAO;
import com.SpringMVC.mapper.QuotationMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class QuotationDAOImpl extends JdbcDaoSupport implements QuotationDAO {
 
    @Autowired
    public QuotationDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Quotation quotation) {
		Connection conn = this.getConnection();
		try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spQuotationInsUpd("
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setString(2, "0");
	    	proc.setInt(3, quotation.getquotationid());
	    	proc.setDate(4, quotation.getquotationdate());
	    	proc.setInt(5, quotation.getprospectid());
	    	proc.setInt(6, quotation.getactivityid());
	    	proc.setInt(7, quotation.getbrandid());
	    	proc.setInt(8, quotation.getmodelid());
	    	proc.setFloat(9, quotation.getretailprice());
	    	proc.setFloat(10, quotation.getsuminsured());
	    	proc.setString(11, quotation.getncd());
	    	proc.setFloat(12, quotation.getpremium());
	    	proc.setFloat(13, quotation.getregistrationfee());
	    	proc.setFloat(14, quotation.gethandlingcharges());
	    	proc.setFloat(15, quotation.getextendedwarranty());
	    	proc.setFloat(16, quotation.getothercharges());
	    	proc.setFloat(17, quotation.getdiscount());
	    	proc.setFloat(18, quotation.getquoteamount());
	    	proc.setString(19, quotation.getterm());
	    	proc.setString(20, quotation.getremark());
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public void update(Quotation quotation) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spQuotationInsUpd("
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setString(2, "1");
	    	proc.setInt(3, quotation.getquotationid());
	    	proc.setDate(4, quotation.getquotationdate());
	    	proc.setInt(5, quotation.getprospectid());
	    	proc.setInt(6, quotation.getactivityid());
	    	proc.setInt(7, quotation.getbrandid());
	    	proc.setInt(8, quotation.getmodelid());
	    	proc.setFloat(9, quotation.getretailprice());
	    	proc.setFloat(10, quotation.getsuminsured());
	    	proc.setString(11, quotation.getncd());
	    	proc.setFloat(12, quotation.getpremium());
	    	proc.setFloat(13, quotation.getregistrationfee());
	    	proc.setFloat(14, quotation.gethandlingcharges());
	    	proc.setFloat(15, quotation.getextendedwarranty());
	    	proc.setFloat(16, quotation.getothercharges());
	    	proc.setFloat(17, quotation.getdiscount());
	    	proc.setFloat(18, quotation.getquoteamount());
	    	proc.setString(19, quotation.getterm());
	    	proc.setString(20, quotation.getremark());
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }

    public void delete(int quotationid) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spQuotationDel(?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setInt(2, quotationid);

	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public Quotation get(int quotationid) {
	    String sql = "SELECT quotationid, quotationdate, q.prospectid, p.firstname AS prospectname, "
	    		+ "activityid, q.brandid, b.brandname, q.modelid, m.modelname, "
	    		+ "retailprice, q.suminsured, ncd, q.premium, "
	    		+ "registrationfee, handlingcharges, extendedwarranty, othercharges,"
	    		+ "discount, quoteamount, term, remark "
	    		+ "FROM tblQuotation q "
	    		+ "LEFT JOIN tblProspect p ON p.prospectid = q.prospectid "
	    		+ "LEFT JOIN tblBrand b ON b.brandid = q.brandid "
	    		+ "LEFT JOIN tblModel m ON m.brandid = q.brandid AND m.modelid = q.modelid "
	    		+ "WHERE quotationid="+quotationid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Quotation>() {
	 
			@Override
	        public Quotation extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Quotation quotation = new Quotation();
	            	quotation.setquotationid(rs.getInt("quotationid"));
	            	quotation.setquotationdate(rs.getDate("quotationdate"));
	            	quotation.setprospectid(rs.getInt("prospectid"));
	            	quotation.setprospectname(rs.getString("prospectname"));
	            	quotation.setactivityid(rs.getInt("activityid"));
	            	quotation.setbrandid(rs.getInt("brandid"));
	            	quotation.setbrandname(rs.getString("brandname"));
	            	quotation.setmodelid(rs.getInt("modelid"));
	            	quotation.setmodelname(rs.getString("modelname"));
	            	quotation.setretailprice(rs.getFloat("retailprice"));
	            	quotation.setsuminsured(rs.getFloat("suminsured"));
	            	quotation.setncd(rs.getString("ncd"));
	            	quotation.setpremium(rs.getFloat("premium"));
	            	quotation.setregistrationfee(rs.getFloat("registrationfee"));
	            	quotation.sethandlingcharges(rs.getFloat("handlingcharges"));
	            	quotation.setextendedwarranty(rs.getFloat("extendedwarranty"));
	            	quotation.setothercharges(rs.getFloat("othercharges"));
	            	quotation.setdiscount(rs.getFloat("discount"));
	            	quotation.setquoteamount(rs.getFloat("quoteamount"));
	            	quotation.setterm(rs.getString("term"));
	            	quotation.setremark(rs.getString("remark"));
	                return quotation;
	            }	 
	            return null;
	        }
        });
    }

    public List<Quotation> list(int prospectid) {
	    String sql = "SELECT quotationid, quotationdate, q.prospectid, p.firstname AS prospectname, "
	    		+ "activityid, q.brandid, b.brandname, q.modelid, m.modelname, "
	    		+ "retailprice, q.suminsured, ncd, q.premium, "
	    		+ "registrationfee, handlingcharges, extendedwarranty, othercharges,"
	    		+ "discount, quoteamount, term, remark "
	    		+ "FROM tblQuotation q "
	    		+ "LEFT JOIN tblProspect p ON p.prospectid = q.prospectid "
	    		+ "LEFT JOIN tblBrand b ON b.brandid = q.brandid "
	    		+ "LEFT JOIN tblModel m ON m.brandid = q.brandid AND m.modelid = q.modelid "
	    		+ "WHERE q.prospectid="+prospectid+" "
				+ "ORDER BY quotationid ";
	    QuotationMapper mapper = new QuotationMapper();
        List<Quotation> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
}