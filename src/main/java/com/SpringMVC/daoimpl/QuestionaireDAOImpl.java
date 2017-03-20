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
	    	CallableStatement proc = conn.prepareCall("{ ? = call spQuestionaire(?, ?, ?, ?, ?, ?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setInt(2, questionaire.getuserid());
	    	proc.setString(3, questionaire.getprospectname());
	    	proc.setString(4, questionaire.getmobile());
	    	proc.setInt(5, questionaire.getbrandid());
	    	proc.setInt(6, questionaire.getmodelid());
	    	proc.setString(7, questionaire.getsource());
	    	proc.setBoolean(8, questionaire.getdemo());
	    	proc.setBoolean(9, questionaire.gettestdrive());
	    	proc.setBoolean(10, questionaire.getquotation());
	    	proc.setBoolean(11, questionaire.getlost());
	    	proc.setString(12, questionaire.getlostremark());
	    	proc.setBoolean(13, questionaire.getclosed());
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }    
}