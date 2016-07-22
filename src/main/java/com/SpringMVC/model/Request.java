package com.SpringMVC.model;

import java.util.Date;

public class Request {
    private int requestid;
    private int prospectid;
    private Date requestdate;
    private int brandid;
    private String brandname;
    private int modelid;
    private String modelname;
    private String remark;
    private String status;
    
    public Request() {
    }
 
    public Request(int requestid, int prospectid, Date requestdate, int brandid, String brandname, int modelid, String modelname, String remark, String status) {
        this.requestid = requestid;
        this.prospectid = prospectid;
        this.requestdate = requestdate;
        this.brandid = brandid;
        this.brandname = brandname;
        this.modelid = modelid;
        this.modelname = modelname;
        this.remark = remark;
        this.status = status;
    }
 
    public int getrequestid() {
        return requestid;
    }  
    public void setrequestid(int requestid) {
        this.requestid = requestid;
    }

    public int getprospectid() {
        return prospectid;
    }  
    public void setprospectid(int prospectid) {
        this.prospectid = prospectid;
    }

    public Date getrequestdate() {
        return requestdate;
    }  
    public void setrequestdate(Date requestdate) {
        this.requestdate = requestdate;
    }

    public int getbrandid() {
        return brandid;
    }  
    public void setbrandid(int brandid) {
        this.brandid = brandid;
    }

    public String getbrandname() {
        return brandname;
    }  
    public void setbrandname(String brandname) {
        this.brandname = brandname;
    }

    public int getmodelid() {
        return modelid;
    }  
    public void setmodelid(int modelid) {
        this.modelid = modelid;
    }

    public String getmodelname() {
        return modelname;
    }  
    public void setmodelname(String modelname) {
        this.modelname = modelname;
    }

    public String getremark() {
        return remark;
    }  
    public void setremark(String remark) {
        this.remark = remark;
    }

    public String getstatus() {
        return status;
    }  
    public void setstatus(String status) {
        this.status = status;
    }
}