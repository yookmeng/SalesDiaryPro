package com.SpringMVC.model;

import java.sql.Date;

public class TeamTarget {
    private int targetid;
    private int teamid;
    private Date period;
    private int prospect;
    private int sales;
    private float totalsales;

    public TeamTarget() {
    }
 
    public TeamTarget(int targetid, int teamid, Date period, int prospect, int sales, float totalsales) {
        this.targetid = targetid;
        this.teamid = teamid;
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

    public int getteamid() {
        return teamid;
    }  
    public void setteamid(int teamid) {
        this.teamid = teamid;
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