package com.SpringMVC.model;

import java.sql.Date;

public class Activity {
    private int activityid;
    private int prospectid;
    private Date activitydate;
    private int brandid;
    private String brandname;
    private int modelid;
    private String modelname;
    private boolean demo;
    private boolean testdrive;
    private boolean quotation;
    private String remark1;
    private String remark2;
    private String remark3;
    
    public Activity() {
    }
 
    public Activity(int activityid, int prospectid, Date activitydate, 
    		int brandid, String brandname, int modelid, String modelname, 
    		boolean demo, boolean testdrive, boolean quotation, String remark1, String remark2, String remark3) {
        this.activityid = activityid;
        this.prospectid = prospectid;
        this.activitydate = activitydate;
        this.brandid = brandid;
        this.brandname = brandname;
        this.modelid = modelid;
        this.modelname = modelname;
        this.demo = demo;
        this.testdrive = testdrive;
        this.quotation = quotation;
        this.remark1 = remark1;
        this.remark2 = remark2;
        this.remark3 = remark3;
    }
 
    public int getactivityid() {
        return activityid;
    }  
    public void setactivityid(int activityid) {
        this.activityid = activityid;
    }

    public int getprospectid() {
        return prospectid;
    }  
    public void setprospectid(int prospectid) {
        this.prospectid = prospectid;
    }

    public Date getactivitydate() {
        return activitydate;
    }  
    public void setactivitydate(Date activitydate) {
        this.activitydate = activitydate;
    }

    public int getbrandid() {
        return brandid;
    }  
    public void setbrandid(int brandid) {
        this.brandid = brandid;
    }

    public String getbrandname() {
        return brandname;
    }  
    public void setbrandname(String brandname) {
        this.brandname = brandname;
    }

    public int getmodelid() {
        return modelid;
    }  
    public void setmodelid(int modelid) {
        this.modelid = modelid;
    }

    public String getmodelname() {
        return modelname;
    }  
    public void setmodelname(String modelname) {
        this.modelname = modelname;
    }

    public boolean getdemo() {
        return demo;
    }  
    public void setdemo(boolean demo) {
        this.demo = demo;
    }

    public boolean gettestdrive() {
        return testdrive;
    }  
    public void settestdrive(boolean testdrive) {
        this.testdrive = testdrive;
    }

    public boolean getquotation() {
        return quotation;
    }  
    public void setquotation(boolean quotation) {
        this.quotation = quotation;
    }

    public String getremark1() {
        return remark1;
    }  
    public void setremark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getremark2() {
        return remark2;
    }  
    public void setremark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getremark3() {
        return remark3;
    }  
    public void setremark3(String remark3) {
        this.remark3 = remark3;
    }
}