package com.SpringMVC.model;

public class Brand {
    private int brandid;
    private String brandname;
    private int companyid;

    public Brand() {
    }
 
    public Brand(int brandid, String brandname, int companyid) {
        this.brandid = brandid;
        this.brandname = brandname;
        this.companyid = companyid;
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

    public int getcompanyid() {
        return companyid;
    }  
    public void setcompanyid(int companyid) {
        this.companyid = companyid;
    }
}