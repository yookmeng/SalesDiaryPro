package com.SpringMVC.model;

import java.time.LocalDateTime;

public class Event {
    private int eventid;
    private int userid;
    private String title;
    private String remark;
    private LocalDateTime startdate;
    private LocalDateTime enddate;
    private String url;    
    private boolean allday;
    
    public Event() {
    }
 
    public Event(int eventid, int userid, String title, String remark, 
    		LocalDateTime startdate, LocalDateTime enddate, String url, boolean allday) {
        this.eventid = eventid;
        this.userid = userid;
        this.title = title;
        this.remark = remark;
        this.startdate = startdate;
        this.enddate = enddate;
        this.url = url;
        this.allday = allday;
    }
 
    public int geteventid() {
        return eventid;
    }  
    public void seteventid(int eventid) {
        this.eventid = eventid;
    }

    public int getuserid() {
        return userid;
    }  
    public void setuserid(int userid) {
        this.userid = userid;
    }

    public String gettitle() {
        return title;
    }  
    public void settitle(String title) {
        this.title = title;
    }

    public String getremark() {
        return remark;
    }  
    public void setremark(String remark) {
        this.remark = remark;
    }
    
    public LocalDateTime getstartdate() {
        return startdate;
    }  
    public void setstartdate(LocalDateTime startdate) {
        this.startdate = startdate;
    }

    public LocalDateTime getenddate() {
        return enddate;
    }  
    public void setenddate(LocalDateTime enddate) {
        this.enddate = enddate;
    }

    public String geturl() {
        return url;
    }  
    public void seturl(String url) {
        this.url = url;
    }
    
    public boolean getallday() {
        return allday;
    }  
    public void setallday(boolean allday) {
        this.allday = allday;
    }
}