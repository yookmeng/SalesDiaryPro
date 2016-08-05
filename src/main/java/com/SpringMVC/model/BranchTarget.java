package com.SpringMVC.model;

import java.sql.Date;

public class BranchTarget {
	private int targetid;
	private int branchid;
    private Date period;
    private int prospect;
    private int sales;
    private float totalsales;

    public BranchTarget() {
    }
 
    public BranchTarget(int targetid, int branchid, Date period, int prospect, int sales, float totalsales) {
        this.targetid = targetid;
        this.branchid = branchid;
        this.period = period;
        this.prospect = prospect;
        this.sales = sales;
        this.totalsales = totalsales;
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

    public Date getperiod() {
        return period;
    }  
    public void setperiod(Date period) {
        this.period = period;
    }

    public int getprospect() {
        return prospect;
    }  
    public void setprospect(int prospect) {
        this.prospect = prospect;
    }

    public int getsales() {
        return sales;
    }  
    public void setsales(int sales) {
        this.sales = sales;
    }

    public float gettotalsales() {
        return totalsales;
    }  
    public void settotalsales(float totalsales) {
        this.totalsales = totalsales;
    }
}