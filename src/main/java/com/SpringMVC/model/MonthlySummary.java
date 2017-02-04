package com.SpringMVC.model;

public class MonthlySummary {
    private Integer userid;
    private String period;
     
    public MonthlySummary()  {         
    }
 
    public MonthlySummary(Integer userid, String period) {
        this.userid = userid;
        this.period = period;
    }
 
    public Integer getuserid() {
        return userid;
    }
    public void setuserid(Integer userid) {
        this.userid = userid;
    }

    public String getperiod() {
        return period;
    }
    public void setperiod(String period) {
        this.period = period;
    }
}