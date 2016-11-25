package com.SpringMVC.model;

import java.sql.Date;

public class Activity {
    private int activityid;
    private int prospectid;
    private String prospectname;
    private Date activitydate;
    private int brandid;
    private String brandname;
    private int modelid;
    private String modelname;
    private boolean demo;
    private boolean testdrive;
    private boolean quotation;
    private boolean followup;
    private boolean closed;
    private boolean lost;
    private Boolean demostatus;
    private Boolean testdrivestatus;
    private String followupremark;
    private Boolean followupstatus;
    private int quotationid;
    private String quotationpdflink;
    private int closedid;
    private String lostremark;
    
    public Activity() {
    }
 
    public Activity(int activityid, int prospectid, String prospectname, Date activitydate, 
    		int brandid, String brandname, int modelid, String modelname, 
    		boolean demo, boolean testdrive, boolean quotation, 
    		boolean followup, boolean closed, boolean lost, 
    		boolean demostatus, boolean testdrivestatus, 
    		String followupremark, boolean followupstatus,    		
    		int quotationid, String quotationpdflink, int closedid, String lostremark) {
        this.activityid = activityid;
        this.prospectid = prospectid;
        this.prospectname = prospectname;
        this.activitydate = activitydate;
        this.brandid = brandid;
        this.brandname = brandname;
        this.modelid = modelid;
        this.modelname = modelname;
        this.demo = demo;
        this.testdrive = testdrive;
        this.quotation = quotation;
        this.followup = followup;
        this.closed= closed;
        this.lost = lost;
        this.demostatus = demostatus;
        this.testdrivestatus = testdrivestatus;
        this.followupremark = followupremark;
        this.followupstatus = followupstatus;
        this.quotationid = quotationid;
        this.quotationpdflink = quotationpdflink;
        this.closedid = closedid;
        this.lostremark = lostremark;
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

    public String getprospectname() {
        return prospectname;
    }  
    public void setprospectname(String prospectname) {
        this.prospectname = prospectname;
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

    public boolean getfollowup() {
        return followup;
    }  
    public void setfollowup(boolean followup) {
        this.followup = followup;
    }

    public boolean getclosed() {
        return closed;
    }  
    public void setclosed(boolean closed) {
        this.closed = closed;
    }

    public boolean getlost() {
        return lost;
    }  
    public void setlost(boolean lost) {
        this.lost = lost;
    }

    public Boolean getdemostatus() {
        return demostatus;
    }  
    public void setdemostatus(Boolean demostatus) {
        this.demostatus = demostatus;
    }

    public Boolean gettestdrivestatus() {
        return testdrivestatus;
    }  
    public void settestdrivestatus(Boolean testdrivestatus) {
        this.testdrivestatus = testdrivestatus;
    }

    public String getfollowupremark() {
        return followupremark;
    }  
    public void setfollowupremark(String followupremark) {
        this.followupremark = followupremark;
    }

    public Boolean getfollowupstatus() {
        return followupstatus;
    }  
    public void setfollowupstatus(Boolean followupstatus) {
        this.followupstatus = followupstatus;
    }
    
    public int getquotationid() {
        return quotationid;
    }  
    public void setquotationid(int quotationid) {
        this.quotationid = quotationid;
    }

    public String getquotationpdflink() {
        return quotationpdflink;
    }  
    public void setquotationpdflink(String quotationpdflink) {
        this.quotationpdflink = quotationpdflink;
    }

    public int getclosedid() {
        return closedid;
    }  
    public void setclosedid(int closedid) {
        this.closedid = closedid;
    }

    public String getlostremark() {
        return lostremark;
    }  
    public void setlostremark(String lostremark) {
        this.lostremark = lostremark;
    }
}