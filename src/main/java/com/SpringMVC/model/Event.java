package com.SpringMVC.model;

import java.sql.Date;
import java.sql.Time;

public class Event {
    private int id;
    private int userid;    
    private int prospectid;
    private String firstname;
    private String lastname;
    private String mobile;
    private int activityid;
    private String quotationpdflink;
    private String title;
    private String remark;    
    private Date startdate;
    private Time starttime;
    private Date enddate;
    private Time endtime;
    private String url;    
    private boolean allDay;
    
    public Event() {
    }
 
    public Event(int id, int userid, 
    		int prospectid, String firstname, String lastname, String mobile,
    		int activityid, String quotationpdflink, String title, String remark, 
    		Date startdate, Time starttime, Date enddate, Time endtime,
    		String url, boolean allDay) {
        this.id = id;
        this.userid = userid;
        this.prospectid = prospectid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.activityid = activityid;
        this.quotationpdflink = quotationpdflink;
        this.title = title;
        this.remark = remark;
        this.startdate = startdate;
        this.starttime = starttime;
        this.enddate = enddate;
        this.endtime = endtime;
        this.url = url;
        this.allDay = allDay;
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

    public int getprospectid() {
        return prospectid;
    }  
    public void setprospectid(int prospectid) {
        this.prospectid = prospectid;
    }

    public String getfirstname() {
        return firstname;
    }  
    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getlastname() {
        return lastname;
    }  
    public void setlastname(String lastname) {
        this.lastname = lastname;
    }

    public String getmobile() {
        return mobile;
    }  
    public void setmobile(String mobile) {
        this.mobile = mobile;
    }

    public int getactivityid() {
        return activityid;
    }  
    public void setactivityid(int activityid) {
        this.activityid = activityid;
    }

    public String getquotationpdflink() {
        return quotationpdflink;
    }  
    public void setquotationpdflink(String quotationpdflink) {
        this.quotationpdflink = quotationpdflink;
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

    public Date getstartdate() {
        return startdate;
    }  
    public void setstartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Time getstarttime() {
        return starttime;
    }  
    public void setstarttime(Time starttime) {
        this.starttime = starttime;
    }

    public Date getenddate() {
        return enddate;
    }  
    public void setenddate(Date enddate) {
        this.enddate = enddate;
    }

    public Time getendtime() {
        return endtime;
    }  
    public void setendtime(Time endtime) {
        this.endtime = endtime;
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
}