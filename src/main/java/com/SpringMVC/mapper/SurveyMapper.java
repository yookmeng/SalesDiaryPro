package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.Survey;
import org.springframework.jdbc.core.RowMapper;
 
public class SurveyMapper implements RowMapper<Survey> {
 
    @Override
    public Survey mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int surveyid = rs.getInt("surveyid");
        int prospectid = rs.getInt("prospectid");        
        String surveydate = rs.getString("surveydate");
        String brand1 = rs.getString("brand1");
        String model1 = rs.getString("model1");
        String cc1 = rs.getString("cc1");
        int mfgyear1 = rs.getInt("mfgyear1");
        String brand2 = rs.getString("brand2");
        String model2 = rs.getString("model2");
        String cc2 = rs.getString("cc2");
        int mfgyear2 = rs.getInt("mfgyear2");
        String brand3 = rs.getString("brand3");
        String model3 = rs.getString("model3");
        String cc3 = rs.getString("cc3");
        int mfgyear3 = rs.getInt("mfgyear3");
        String reason = rs.getString("reason");
        String remark = rs.getString("remark");
        
        return new Survey(surveyid, prospectid, surveydate, 
        		brand1, model1, cc1, mfgyear1, 
        		brand2, model2, cc2, mfgyear2, 
        		brand3, model3, cc3, mfgyear3, 
        		reason, remark);
    }
}