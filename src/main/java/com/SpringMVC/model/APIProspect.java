package com.SpringMVC.model;

public class APIProspect {
    private String useremail;
    private String prospectname;
    private String mobile;
    private String brandname;
    private String modelname;
    private String source;
    private boolean demo;
    private boolean testdrive;
    private boolean quotation;
    private boolean lost;
    private String lostremark;
    private boolean closed;


    public APIProspect() {
    }
 
    public APIProspect(String useremail, String prospectname, String mobile, 
    		String brandname, String modelname, String source,
    		boolean demo, boolean testdrive, boolean quotation, 
    		boolean lost, String lostremark, boolean closed) {
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

    public boolean getdemo() {
        return demo;
    }
    public void setdemo(boolean demo) {
        this.demo = demo;
    }
    
    public boolean gettestdrive() {
        return testdrive;
    }
    public void settestdrive(boolean testdrive) {
        this.testdrive = testdrive;
    }

    public boolean getquotation() {
        return quotation;
    }
    public void setquotation(boolean quotation) {
        this.quotation = quotation;
    }

    public boolean getlost() {
        return lost;
    }
    public void setlost(boolean lost) {
        this.lost = lost;
    }

    public String getlostremark() {
        return lostremark;
    }
    public void setlostremark(String lostremark) {
        this.lostremark = lostremark;
    }

    public boolean getclosed() {
        return closed;
    }
    public void setclosed(boolean closed) {
        this.closed = closed;
    }
}