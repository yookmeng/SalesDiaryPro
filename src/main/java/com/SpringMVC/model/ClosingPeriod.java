package com.SpringMVC.model;

import java.sql.Date;

public class ClosingPeriod {
    private int id;
    private int companyid;
    private int controlyear;
    private int controlmonth;
    private Date opendate;
    private Date closedate;

    public ClosingPeriod() {
    }
 
    public ClosingPeriod(int id, int companyid, int controlyear, int controlmonth, 
    			Date opendate, Date closedate) {
        this.id = id;
        this.companyid = companyid;
        this.controlyear = controlyear;
        this.controlmonth = controlmonth;
        this.opendate = opendate;        
        this.closedate = closedate;
    }
 
    public int getid() {
        return id;
    }  
    public void setid(int id) {
        this.id = id;
    }

    public int getcompanyid() {
        return companyid;
    }  
    public void setcompanyid(int companyid) {
        this.companyid = companyid;
    }

    public int getcontrolyear() {
        return controlyear;
    }  
    public void setcontrolyear(int controlyear) {
        this.controlyear = controlyear;
    }

    public int getcontrolmonth() {
        return controlmonth;
    }  
    public void setcontrolmonth(int controlmonth) {
        this.controlmonth = controlmonth;
    }

    public Date getopendate() {
        return opendate;
    }  
    public void setopendate(Date opendate) {
        this.opendate = opendate;
    }

    public Date getclosedate() {
        return closedate;
    }  
    public void setclosedate(Date closedate) {
        this.closedate = closedate;
    }
}