package com.SpringMVC.model;

public class Event {
    private int id;
    private int userid;    
    private String period;
    private String title;
    private String remark;    
    private String start;
    private String end;
    private String url;    
    private boolean allDay;
    private int activityid;        
    
    public Event() {
    }
 
    public Event(int id, int userid, String period, String title, String remark, 
    		String start, String end, String url, boolean allDay, int activityid) {
        this.id = id;
        this.userid = userid;
        this.period = period;
        this.title = title;
        this.remark = remark;
        this.start= start;
        this.end = end;
        this.url = url;
        this.allDay = allDay;
        this.activityid = activityid;
    }
 
    public int getid() {
        return id;
    }  
    public void setid(int id) {
        this.id = id;
    }

    public int getuserid() {
        return userid;
    }  
    public void setuserid(int userid) {
        this.userid = userid;
    }

    public String getperiod() {
        return period;
    }  
    public void setperiod(String period) {
        this.period = period;
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

    public String getstart() {
        return start;
    }  
    public void setstart(String start) {
        this.start = start;
    }

    public String getend() {
        return end;
    }  
    public void setend(String end) {
        this.end = end;
    }

    public String geturl() {
        return url;
    }  
    public void seturl(String url) {
        this.url = url;
    }
    
    public boolean getallDay() {
        return allDay;
    }  
    public void setallDay(boolean allDay) {
        this.allDay = allDay;
    }
    public int getactivityid() {
        return activityid;
    }  
    public void setactivityid(int activityid) {
        this.activityid = activityid;
    }
}