package com.SpringMVC.model;

import java.sql.Date;

public class UserMonthlySummary {
    private Date period;
    private int userid;
    private String username;
    private int targetprospect;
    private int targettestdrive;
    private int targetclosed;
    private int actualprospect;
    private int actualtestdrive;
    private int actualclosed;
    private float percentprospect;
    private float percenttestdrive;
    private float percentclosed;
    
    public UserMonthlySummary() {
    }
 
    public UserMonthlySummary(Date period, int userid, String username, 
    		int targetprospect, int targettestdrive, int targetclosed,
    		int actualprospect, int actualtestdrive, int actualclosed,
    		float percentprospect, float percenttestdrive, float percentclosed) {
        this.period = period;
        this.userid = userid;
        this.username = username;
        this.targetprospect = targetprospect;
        this.targettestdrive = targettestdrive;
        this.targetclosed = targetclosed;
        this.actualprospect = actualprospect;
        this.actualtestdrive = actualtestdrive;
        this.actualclosed = actualclosed;
        this.percentprospect = percentprospect;
        this.percenttestdrive = percenttestdrive;
        this.percentclosed = percentclosed;
    }
 
    public Date getperiod() {
        return period;
    }  
    public void setperiod(Date period) {
        this.period = period;
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

    public int gettargetprospect() {
        return targetprospect;
    }  
    public void settargetprospect(int targetprospect) {
        this.targetprospect = targetprospect;
    }

    public int gettargettestdrive() {
        return targettestdrive;
    }  
    public void settargettestdrive(int targettestdrive) {
        this.targettestdrive= targettestdrive;
    }

    public int gettargetclosed() {
        return targetclosed;
    }  
    public void settargetclosed(int targetclosed) {
        this.targetclosed = targetclosed;
    }

    public int getactualprospect() {
        return actualprospect;
    }  
    public void setactualprospect(int actualprospect) {
        this.actualprospect = actualprospect;
    }

    public int getactualtestdrive() {
        return actualtestdrive;
    }  
    public void setactualtestdrive(int actualtestdrive) {
        this.actualtestdrive = actualtestdrive;
    }

    public int getactualclosed() {
        return actualclosed;
    }  
    public void setactualclosed(int actualclosed) {
        this.actualclosed = actualclosed;
    }

    public float getpercentprospect() {
        return percentprospect;
    }  
    public void setpercentprospect(float percentprospect) {
        this.percentprospect = percentprospect;
    }

    public float getpercenttestdrive() {
        return percenttestdrive;
    }  
    public void setpercenttestdrive(float percenttestdrive) {
        this.percenttestdrive = percenttestdrive;
    }

    public float getpercentclosed() {
        return percentclosed;
    }  
    public void setpercentclosed(float percentclosed) {
        this.percentclosed = percentclosed;
    }
}