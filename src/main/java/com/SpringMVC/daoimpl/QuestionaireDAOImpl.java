package com.SpringMVC.daoimpl;
 
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;
 
import com.SpringMVC.model.Questionaire;
import com.SpringMVC.dao.QuestionaireDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class QuestionaireDAOImpl extends JdbcDaoSupport implements QuestionaireDAO {
 
    @Autowired
    public QuestionaireDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Questionaire questionaire) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spQuestionaire("
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	    			+ "?, ?, ?, ?, ?, ?, ?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setInt(2, questionaire.getuserid());
	    	proc.setString(3, questionaire.getprospectname());
	    	proc.setString(4, questionaire.getmobile());
	    	proc.setString(5, questionaire.getemail());
	    	proc.setInt(6, questionaire.getbrandid());
	    	proc.setInt(7, questionaire.getmodelid());
	    	proc.setString(8, questionaire.getsource());
	    	proc.setString(9, questionaire.getstatus());
	    	proc.setBoolean(10, questionaire.getdemo());
	    	proc.setDate(11, questionaire.getdemodate());
	    	proc.setTime(12, questionaire.getdemotime());
	    	proc.setBoolean(13, questionaire.gettestdrive());
	    	proc.setDate(14, questionaire.gettestdrivedate());
	    	proc.setTime(15, questionaire.gettestdrivetime());
	    	proc.setBoolean(16, questionaire.getquotation());
	    	proc.setDate(17, questionaire.getquotationdate());
	    	proc.setTime(18, questionaire.getquotationtime());
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }    
}