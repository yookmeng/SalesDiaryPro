package com.SpringMVC.model;

import java.sql.Date;
import java.sql.Time;

public class Questionaire {
    private int userid;
    private String prospectname;
    private String mobile;
    private String email;
    private int brandid;
    private String brandname;
    private int modelid;
    private String modelname;
    private String source;
    private String status;
    private boolean demo;
    private Date demodate;
    private Time demotime;
    private boolean testdrive;
    private Date testdrivedate;
    private Time testdrivetime;
    private boolean quotation;
    private Date quotationdate;
    private Time quotationtime;

    public int getuserid() {
        return userid;
    }  
    public void setuserid(int userid) {
        this.userid = userid;
    }

    public String getprospectname() {
        return prospectname;
    }  
    public void setprospectname(String prospectname) {
        this.prospectname = prospectname;
    }

    public String getmobile() {
        return mobile;
    }  
    public void setmobile(String mobile) {
        this.mobile = mobile;
    }

    public String getemail() {
        return email;
    }  
    public void setemail(String email) {
        this.email = email;
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

    public String getsource() {
        return source;
    }  
    public void setsource(String source) {
        this.source = source;
    }

    public String getstatus() {
        return status;
    }  
    public void setstatus(String status) {
        this.status = status;
    }

    public boolean getdemo() {
        return demo;
    }
    public void setdemo(boolean demo) {
        this.demo = demo;
    }
    
    public Date getdemodate() {
        return demodate;
    }
    public void setdemodate(Date demodate) {
        this.demodate = demodate;
    }

    public Time getdemotime() {
        return demotime;
    }
    public void setdemotime(Time demotime) {
        this.demotime = demotime;
    }

    public boolean gettestdrive() {
        return testdrive;
    }
    public void settestdrive(boolean testdrive) {
        this.testdrive = testdrive;
    }

    public Date gettestdrivedate() {
        return testdrivedate;
    }
    public void settestdrivedate(Date testdrivedate) {
        this.testdrivedate = testdrivedate;
    }

    public Time gettestdrivetime() {
        return testdrivetime;
    }
    public void settestdrivetime(Time testdrivetime) {
        this.testdrivetime = testdrivetime;
    }

    public boolean getquotation() {
        return quotation;
    }
    public void setquotation(boolean quotation) {
        this.quotation = quotation;
    }

    public Date getquotationdate() {
        return quotationdate;
    }
    public void setquotationdate(Date quotationdate) {
        this.quotationdate = quotationdate;
    }

    public Time getquotationtime() {
        return quotationtime;
    }
    public void setquotationtime(Time quotationtime) {
        this.quotationtime = quotationtime;
    }
}