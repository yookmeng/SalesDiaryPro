package com.SpringMVC.model;

import java.sql.Date;

public class Review {
    private int reviewid;
    private int userid;
    private int targetid;
    private int teamtargetid;
    private Date reviewdate;
    private int prospect;
    private int sales;
    private float totalsales;
    private String minute;
    private int reviewby;
    public Review() {
    }
 
    public Review(int reviewid, int userid, int targetid, int teamtargetid, Date reviewdate, 
    		int prospect, int sales, float totalsales, String minute, int reviewby) {
        this.reviewid = reviewid;
        this.userid = userid;
        this.targetid = targetid;
        this.teamtargetid = teamtargetid;
        this.reviewdate = reviewdate;
        this.prospect = prospect;
        this.sales = sales;
        this.totalsales = totalsales;
        this.minute = minute;
        this.reviewby = reviewby;
    }
 
    public int getreviewid() {
        return reviewid;
    }  
    public void setreviewid(int reviewid) {
        this.reviewid = reviewid;
    }

    public int getuserid() {
        return userid;
    }  
    public void setuserid(int userid) {
        this.userid = userid;
    }

    public int gettargetid() {
        return targetid;
    }  
    public void settargetid(int targetid) {
        this.targetid = targetid;
    }

    public int getteamtargetid() {
        return teamtargetid;
    }  
    public void setteamtargetid(int teamtargetid) {
        this.teamtargetid = teamtargetid;
    }

    public Date getreviewdate() {
        return reviewdate;
    }  
    public void setreviewdate(Date reviewdate) {
        this.reviewdate = reviewdate;
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

    public String getminute() {
        return minute;
    }  
    public void setminute(String minute) {
        this.minute = minute;
    }

    public int getreviewby() {
        return reviewby;
    }  
    public void setreviewby(int reviewby) {
        this.reviewby = reviewby;
    }
}