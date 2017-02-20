package com.SpringMVC.model;

import java.sql.Date;

public class APIActivity {
    private String useremail;
    private int activityid;
    private Date activitydate;
    private int prospectid;
    private String brandname;
    private String modelname;
    private boolean demo;
    private boolean testdrive;
    private boolean quotation;
    private boolean followup;
    private String followupremark;
    private boolean lost;
    private String lostremark;
    private boolean closed;
    
    public APIActivity() {
    }
 
    public APIActivity(String useremail, int activityid, Date activitydate, 
    		int prospectid, String brandname, String modelname, 
    		boolean demo, boolean testdrive, boolean quotation, 
    		boolean followup, String followupremark, 
    		boolean lost, String lostremark,
    		boolean closed) {
        this.useremail = useremail;
        this.activityid = activityid;
        this.activitydate = activitydate;
        this.prospectid = prospectid;
        this.brandname = brandname;
        this.modelname = modelname;
        this.demo = demo;
        this.testdrive = testdrive;
        this.quotation = quotation;
        this.followup = followup;
        this.followupremark = followupremark;
        this.lost = lost;
        this.lostremark = lostremark;
        this.closed = closed;
    }
 
    public String getuseremail() {
        return useremail;
    }  
    public void setuseremail(String useremail) {
        this.useremail = useremail;
    }

    public int getactivityid() {
        return activityid;
    }  
    public void setactivityid(int activityid) {
        this.activityid = activityid;
    }

    public Date getactivitydate() {
        return activitydate;
    }  
    public void setactivitydate(Date activitydate) {
        this.activitydate = activitydate;
    }

    public int getprospectid() {
        return prospectid;
    }  
    public void setprospectid(int prospectid) {
        this.prospectid = prospectid;
    }

    public String getbrandname() {
        return brandname;
    }  
    public void setbrandname(String brandname) {
        this.brandname = brandname;
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

    public boolean getfollowup() {
        return followup;
    }  
    public void setfollowup(boolean followup) {
        this.followup = followup;
    }

    public String getfollowupremark() {
        return followupremark;
    }  
    public void setfollowupremark(String followupremark) {
        this.followupremark = followupremark;
    }

    public boolean getlost() {
        return lost;
    }  
    public void setlost(boolean lost) {
        this.lost = lost;
    }

    public String getlostremark() {
        return lostremark;
    }  
    public void setlostremark(String lostremark) {
        this.lostremark = lostremark;
    }

    public boolean getclosed() {
        return closed;
    }  
    public void setclosed(boolean closed) {
        this.closed = closed;
    }
}