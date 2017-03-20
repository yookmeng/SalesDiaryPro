package com.SpringMVC.model;

public class APIProspect {
    private String useremail;
    private String prospectname;
    private String mobile;
    private String brandname;
    private String modelname;
    private String source;
    private Boolean demo;
    private Boolean testdrive;
    private Boolean quotation;
    private Boolean lost;
    private String lostremark;
    private Boolean closed;


    public APIProspect() {
    }
 
    public APIProspect(String useremail, String prospectname, String mobile, 
    		String brandname, String modelname, String source,
    		Boolean demo, Boolean testdrive, Boolean quotation, 
    		Boolean lost, String lostremark, Boolean closed) {
        this.useremail = useremail;
        this.prospectname = prospectname;
        this.mobile = mobile;
        this.brandname = brandname;
        this.modelname = modelname;
        this.source = source;
        this.demo = demo;
        this.testdrive = testdrive;
        this.quotation = quotation;
        this.lost = lost;
        this.lostremark = lostremark;
        this.closed = closed;
    }
 
    public String getuseremail() {
        return useremail;
    }  
    public void setuseremail(String useremail) {
        this.useremail = useremail;
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

    public String getbrandname() {
        return brandname;
    }
    public void setbrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getmodelname() {
        return modelname;
    }
    public void setmodelname(String modelname) {
        this.modelname = modelname;
    }

    public String getsource() {
        return source;
    }
    public void setsource(String source) {
        this.source = source;
    }

    public Boolean getdemo() {
        return demo;
    }
    public void setdemo(Boolean demo) {
        this.demo = demo;
    }
    
    public Boolean gettestdrive() {
        return testdrive;
    }
    public void settestdrive(Boolean testdrive) {
        this.testdrive = testdrive;
    }

    public Boolean getquotation() {
        return quotation;
    }
    public void setquotation(Boolean quotation) {
        this.quotation = quotation;
    }

    public Boolean getlost() {
        return lost;
    }
    public void setlost(Boolean lost) {
        this.lost = lost;
    }

    public String getlostremark() {
        return lostremark;
    }
    public void setlostremark(String lostremark) {
        this.lostremark = lostremark;
    }

    public Boolean getclosed() {
        return closed;
    }
    public void setclosed(Boolean closed) {
        this.closed = closed;
    }
}