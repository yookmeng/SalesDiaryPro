package com.SpringMVC.daoimpl;
 
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
    	String sql = "EXEC spQuestionaire ?, ?, ?, ?, ?, ?, ?, ?, ?";
        this.getJdbcTemplate().update(sql, questionaire.getuserid(), 
        		questionaire.getprospectname(), questionaire.getmobile(), 
        		questionaire.getbrandid(), questionaire.getmodelid(), 
        		questionaire.getcurrentbrand(), questionaire.getcurrentmodel(), 
        		questionaire.gettradein(), questionaire.gettestdrive());
    }    
}