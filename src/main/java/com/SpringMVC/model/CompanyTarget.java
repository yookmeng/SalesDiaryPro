package com.SpringMVC.model;

import java.sql.Date;

public class CompanyTarget {
    private int targetid;
    private int companyid;
    private Date period;
    private int prospect;
    private int sales;
    private float totalsales;

    public CompanyTarget() {
    }
 
    public CompanyTarget(int targetid, int companyid, Date period, int prospect, int sales, float totalsales) {
        this.targetid = targetid;
        this.companyid = companyid;
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

    public int getcompanyid() {
        return companyid;
    }  
    public void setcompanyid(int companyid) {
        this.companyid = companyid;
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