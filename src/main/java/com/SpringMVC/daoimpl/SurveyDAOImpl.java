package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Survey;
import com.SpringMVC.dao.SurveyDAO;
import com.SpringMVC.mapper.SurveyMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class SurveyDAOImpl extends JdbcDaoSupport implements SurveyDAO {
 
    @Autowired
    public SurveyDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void saveOrUpdate(Survey survey) {
        if (survey.getsurveyid() > 0)  {
            // update
            String sql = "UPDATE tblSurvey SET surveydate=?, "
            		+ "brand1=?, model1=?, cc1=?, mfgyear1=?, "
            		+ "brand2=?, model2=?, cc2=?, mfgyear2=?, "
            		+ "brand3=?, model3=?, cc3=?, mfgyear3=?, "
            		+ "reason=?, remark=? "
            		+ "WHERE surveyid=?";
            this.getJdbcTemplate().update(sql, survey.getsurveydate(), 
            		survey.getbrand1(), survey.getmodel1(), survey.getcc1(), survey.getmfgyear1(), 
            		survey.getbrand2(), survey.getmodel2(), survey.getcc2(), survey.getmfgyear2(), 
            		survey.getbrand3(), survey.getmodel3(), survey.getcc3(), survey.getmfgyear3(), 
            		survey.getreason(), survey.getremark(), 
            		survey.getsurveyid());
        } else {
            // insert
            String sql = "INSERT INTO tblSurvey "
            		+ "(prospectid, surveydate, "
            		+ "brand1, model1, cc1, mfgyear1, "
            		+ "brand2, model2, cc2, mfgyear2, "
            		+ "brand3, model3, cc3, mfgyear3, "
            		+ "reason, remark) "
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		survey.getprospectid(), survey.getsurveydate(), 
            		survey.getbrand1(), survey.getmodel1(), survey.getcc1(), survey.getmfgyear1(), 
            		survey.getbrand2(), survey.getmodel2(), survey.getcc2(), survey.getmfgyear2(), 
            		survey.getbrand3(), survey.getmodel3(), survey.getcc3(), survey.getmfgyear3(), 
            		survey.getreason(), survey.getremark());
            }
    }
    
    public void delete(int surveyid) {
        String sql = "DELETE FROM tblSurvey WHERE surveyid=?";
        this.getJdbcTemplate().update(sql, surveyid);
    }
    
    public List<Survey> list(int prospectid) {
        String sql = "SELECT * FROM tblSurvey WHERE prospectid = " + prospectid;
        SurveyMapper mapper = new SurveyMapper();
        List<Survey> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Survey get(int surveyid) {
	    String sql = "SELECT * FROM tblSurvey WHERE surveyid=" + surveyid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Survey>() {
	 
	        @Override
	        public Survey extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Survey survey = new Survey();
	                survey.setsurveyid(rs.getInt("surveyid"));
	                survey.setprospectid(rs.getInt("prospectid"));
	                survey.setsurveydate(rs.getString("surveydate"));
	                survey.setbrand1(rs.getString("brand1"));
	                survey.setmodel1(rs.getString("model1"));
	                survey.setcc1(rs.getString("cc1"));
	                survey.setmfgyear1(rs.getInt("mfgyear1"));
	                survey.setbrand2(rs.getString("brand2"));
	                survey.setmodel2(rs.getString("model2"));
	                survey.setcc2(rs.getString("cc2"));
	                survey.setmfgyear2(rs.getInt("mfgyear2"));
	                survey.setbrand3(rs.getString("brand3"));
	                survey.setmodel3(rs.getString("model3"));
	                survey.setcc3(rs.getString("cc3"));
	                survey.setmfgyear3(rs.getInt("mfgyear3"));
	                survey.setreason(rs.getString("reason"));
	                survey.setremark(rs.getString("remark"));
	                return survey;
	            }	 
	            return null;
	        }
        });
    }
}