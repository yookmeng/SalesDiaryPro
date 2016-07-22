package com.SpringMVC.model;

public class Model {
    private int modelid;
    private String modelname;
    private int companyid;
    private int brandid;

    public Model() {
    }
 
    public Model(int modelid, String modelname, int companyid, int brandid) {
        this.modelid = modelid;
        this.modelname = modelname;
        this.companyid = companyid;
        this.brandid = brandid;
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

    public int getcompanyid() {
        return companyid;
    }  
    public void setcompanyid(int companyid) {
        this.companyid = companyid;
    }

    public int getbrandid() {
        return brandid;
    }  
    public void setbrandid(int brandid) {
        this.brandid = brandid;
    }
}