package com.SpringMVC.model;

public class Questionaire {
    private int userid;
    private String prospectname;
    private String mobile;
    private int brandid;
    private String brandname;
    private int modelid;
    private String modelname;
    private String currentbrand;
    private String currentmodel;
    private boolean tradein;
    private boolean testdrive;

    public int getuserid() {
        return userid;
    }  
    public void setuserid(int userid) {
        this.userid = userid;
    }

    public String getprospectname() {
        return prospectname;
    }  
    public void setprospectname(String prospectname) {
        this.prospectname = prospectname;
    }

    public String getmobile() {
        return mobile;
    }  
    public void setmobile(String mobile) {
        this.mobile = mobile;
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

    public String getcurrentbrand() {
        return currentbrand;
    }  
    public void setcurrentbrand(String currentbrand) {
        this.currentbrand = currentbrand;
    }

    public String getcurrentmodel() {
        return currentmodel;
    }  
    public void setcurrentmodel(String currentmodel) {
        this.currentmodel = currentmodel;
    }

    public boolean gettradein() {
        return tradein;
    }  
    public void settradein(boolean tradein) {
        this.tradein = tradein;
    }

    public boolean gettestdrive() {
        return testdrive;
    }  
    public void settestdrive(boolean testdrive) {
        this.testdrive = testdrive;
    }
}