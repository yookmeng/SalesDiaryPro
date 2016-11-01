package com.SpringMVC.model;

import java.sql.Date;

public class Review {
    private int reviewid;
    private int userid;
    private String username;
    private int targetid;
    private int teamtargetid;
    private Date reviewdate;
    private int prospect;
    private int testdrive;
    private int closed;
    private String minute;
    private int reviewby;
    public Review() {
    }
 
    public Review(int reviewid, int userid, String username, int targetid, int teamtargetid, Date reviewdate, 
    		int prospect, int testdrive, int closed, String minute, int reviewby) {
        this.reviewid = reviewid;
        this.userid = userid;
        this.username = username;
        this.targetid = targetid;
        this.teamtargetid = teamtargetid;
        this.reviewdate = reviewdate;
        this.prospect = prospect;
        this.testdrive = testdrive;
        this.closed = closed;
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

    public String getusername() {
        return username;
    }  
    public void setusername(String username) {
        this.username = username;
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