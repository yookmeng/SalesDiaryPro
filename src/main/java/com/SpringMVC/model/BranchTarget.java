package com.SpringMVC.model;

import java.sql.Date;

public class BranchTarget {
	private int targetid;
	private int branchid;
	private String branchname;
    private Date period;
    private String displayperiod;        
	private int companytargetid;
    private int prospect;
    private int testdrive;
    private int closed;

    public BranchTarget() {
    }
 
    public BranchTarget(int targetid, int branchid, String branchname, Date period, 
    		String displayperiod, int companytargetid, int prospect, int testdrive, int closed) {
        this.targetid = targetid;
        this.branchid = branchid;
        this.branchname = branchname;
        this.period = period;
        this.displayperiod = displayperiod;
        this.companytargetid = companytargetid;        
        this.prospect = prospect;
        this.testdrive = testdrive;
        this.closed = closed;
    }
 
    public int gettargetid() {
        return targetid;
    }  
    public void settargetid(int targetid) {
        this.targetid = targetid;
    }

    public int getbranchid() {
        return branchid;
    }  
    public void setbranchid(int branchid) {
        this.branchid = branchid;
    }

    public String getbranchname() {
        return branchname;
    }  
    public void setbranchname(String branchname) {
        this.branchname = branchname;
    }

    public Date getperiod() {
        return period;
    }  
    public void setperiod(Date period) {
        this.period = period;
    }

    public String getdisplayperiod() {
        return displayperiod;
    }  
    public void setdisplayperiod(String displayperiod) {
        this.displayperiod = displayperiod;
    }

    public int getcompanytargetid() {
        return companytargetid;
    }  
    public void setcompanytargetid(int companytargetid) {
        this.companytargetid = companytargetid;
    }

    public int getprospect() {
        return prospect;
    }  
    public void setprospect(int prospect) {
        this.prospect = prospect;
    }

    public int gettestdrive() {
        return testdrive;
    }  
    public void settestdrive(int testdrive) {
        this.testdrive = testdrive;
    }

    public int getclosed() {
        return closed;
    }  
    public void setclosed(int closed) {
        this.closed = closed;
    }
}