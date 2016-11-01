package com.SpringMVC.model;

public class Model {
    private int modelid;
    private String modelname;
    private int brandid;
    private float price;
    private float commission;
    
    public Model() {
    }
 
    public Model(int modelid, String modelname, int brandid, 
    		float price, float commission) {
        this.modelid = modelid;
        this.modelname = modelname;
        this.brandid = brandid;
        this.price = price;
        this.commission = commission;
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

    public float getprice() {
        return price;
    }  
    public void setprice(float price) {
        this.price = price;
    }

    public float getcommission() {
        return commission;
    }  
    public void setcommission(float commission) {
        this.commission = commission;
    }
}