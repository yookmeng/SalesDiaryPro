package com.SpringMVC.model;

public class Model {
    private int modelid;
    private String modelname;
    private int brandid;

    public Model() {
    }
 
    public Model(int modelid, String modelname, int brandid) {
        this.modelid = modelid;
        this.modelname = modelname;
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

    public int getbrandid() {
        return brandid;
    }  
    public void setbrandid(int brandid) {
        this.brandid = brandid;
    }
}