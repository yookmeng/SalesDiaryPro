package com.SpringMVC.model;

public class Schedule {
    private int scheduleid;
    private int prospectid;
    private String scheduledate;
    private String type;
    private String remark1;
    private String remark2;
    private String remark3;
    private String remark4;
    private String remark5;
    
    public Schedule() {
    }
 
    public Schedule(int scheduleid, int prospectid, String scheduledate, String type, 
    		String remark1, String remark2, String remark3, String remark4, String remark5) {
        this.scheduleid = scheduleid;
        this.prospectid = prospectid;
        this.scheduledate = scheduledate;
        this.type = type;
        this.remark1 = remark1;
        this.remark2 = remark2;
        this.remark3 = remark3;
        this.remark4 = remark4;
        this.remark5 = remark5;
    }
 
    public int getscheduleid() {
        return scheduleid;
    }  
    public void setscheduleid(int scheduleid) {
        this.scheduleid = scheduleid;
    }

    public int getprospectid() {
        return prospectid;
    }  
    public void setprospectid(int prospectid) {
        this.prospectid = prospectid;
    }

    public String getscheduledate() {
        return scheduledate;
    }  
    public void setscheduledate(String scheduledate) {
        this.scheduledate = scheduledate;
    }

    public String gettype() {
        return type;
    }  
    public void settype(String type) {
        this.type = type;
    }

    public String getremark1() {
        return remark1;
    }  
    public void setremark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getremark2() {
        return remark2;
    }  
    public void setremark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getremark3() {
        return remark3;
    }  
    public void setremark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getremark4() {
        return remark4;
    }  
    public void setremark4(String remark4) {
        this.remark4 = remark4;
    }

    public String getremark5() {
        return remark5;
    }  
    public void setremark5(String remark5) {
        this.remark5 = remark5;
    }
}