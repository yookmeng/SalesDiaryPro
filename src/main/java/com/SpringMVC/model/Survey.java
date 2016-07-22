package com.SpringMVC.model;

public class Survey {
    private int surveyid;
    private int prospectid;
    private String surveydate;
    private String brand1;
    private String model1;
    private String cc1;
    private int mfgyear1;
    private String brand2;
    private String model2;
    private String cc2;
    private int mfgyear2;
    private String brand3;
    private String model3;
    private String cc3;
    private int mfgyear3;
    private String reason;
    private String remark;
    
    public Survey() {
    }
 
    public Survey(int surveyid, int prospectid, String surveydate, 
    		String brand1, String model1, String cc1, int mfgyear1, 
    		String brand2, String model2, String cc2, int mfgyear2, 
    		String brand3, String model3, String cc3, int mfgyear3, 
    		String reason, String remark) {
        this.surveyid = surveyid;
        this.prospectid = prospectid;
        this.surveydate = surveydate;
        this.brand1 = brand1;
        this.model1 = model1;
        this.cc1 = cc1;
        this.mfgyear1 = mfgyear1;
        this.brand2 = brand2;
        this.model2 = model2;
        this.cc2 = cc2;
        this.mfgyear2 = mfgyear2;
        this.brand3 = brand3;
        this.model3 = model3;
        this.cc3 = cc3;
        this.mfgyear3 = mfgyear3;
        this.reason = reason;
        this.remark = remark;
    }
 
    public int getsurveyid() {
        return surveyid;
    }  
    public void setsurveyid(int surveyid) {
        this.surveyid = surveyid;
    }

    public int getprospectid() {
        return prospectid;
    }  
    public void setprospectid(int prospectid) {
        this.prospectid = prospectid;
    }

    public String getsurveydate() {
        return surveydate;
    }  
    public void setsurveydate(String surveydate) {
        this.surveydate = surveydate;
    }

    public String getbrand1() {
        return brand1;
    }  
    public void setbrand1(String brand1) {
        this.brand1 = brand1;
    }

    public String getmodel1() {
        return model1;
    }  
    public void setmodel1(String model1) {
        this.model1 = model1;
    }

    public String getcc1() {
        return cc1;
    }  
    public void setcc1(String cc1) {
        this.cc1 = cc1;
    }
    
    public int getmfgyear1() {
        return mfgyear1;
    }  
    public void setmfgyear1(int mfgyear1) {
        this.mfgyear1 = mfgyear1;
    }

    public String getbrand2() {
        return brand2;
    }  
    public void setbrand2(String brand2) {
        this.brand2 = brand2;
    }

    public String getmodel2() {
        return model2;
    }  
    public void setmodel2(String model2) {
        this.model2 = model2;
    }

    public String getcc2() {
        return cc2;
    }  
    public void setcc2(String cc2) {
        this.cc2 = cc2;
    }
    
    public int getmfgyear2() {
        return mfgyear2;
    }  
    public void setmfgyear2(int mfgyear2) {
        this.mfgyear2 = mfgyear2;
    }

    public String getbrand3() {
        return brand3;
    }  
    public void setbrand3(String brand3) {
        this.brand3 = brand3;
    }

    public String getmodel3() {
        return model3;
    }  
    public void setmodel3(String model3) {
        this.model3 = model3;
    }

    public String getcc3() {
        return cc3;
    }  
    public void setcc3(String cc3) {
        this.cc3 = cc3;
    }
    
    public int getmfgyear3() {
        return mfgyear3;
    }  
    public void setmfgyear3(int mfgyear3) {
        this.mfgyear3 = mfgyear3;
    }

    public String getreason() {
        return reason;
    }  
    public void setreason(String reason) {
        this.reason = reason;
    }

    public String getremark() {
        return remark;
    }  
    public void setremark(String remark) {
        this.remark = remark;
    }
}