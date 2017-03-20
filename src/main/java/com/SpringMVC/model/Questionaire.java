package com.SpringMVC.model;

public class Questionaire {
    private int userid;
    private String prospectname;
    private String mobile;
    private int brandid;
    private String brandname;
    private int modelid;
    private String modelname;
    private String source;
    private Boolean demo;
    private Boolean testdrive;
    private Boolean quotation;
    private Boolean lost;
    private String lostremark;
    private Boolean closed;

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

    public Boolean getdemo() {
        return demo;
    }
    public void setdemo(Boolean demo) {
        this.demo = demo;
    }
    
    public Boolean gettestdrive() {
        return testdrive;
    }
    public void settestdrive(Boolean testdrive) {
        this.testdrive = testdrive;
    }

    public Boolean getquotation() {
        return quotation;
    }
    public void setquotation(Boolean quotation) {
        this.quotation = quotation;
    }

    public Boolean getlost() {
        return lost;
    }
    public void setlost(Boolean lost) {
        this.lost = lost;
    }

    public String getlostremark() {
        return lostremark;
    }
    public void setlostremark(String lostremark) {
        this.lostremark = lostremark;
    }

    public Boolean getclosed() {
        return closed;
    }
    public void setclosed(Boolean closed) {
        this.closed = closed;
    }
}