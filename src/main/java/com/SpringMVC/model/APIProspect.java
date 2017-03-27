package com.SpringMVC.model;

public class APIProspect {
    private String useremail;
    private String prospectname;
    private String mobile;
    private String email;
    private String brandname;
    private String modelname;
    private String source;
    private String status;
    private boolean demo;
    private String demodate;
    private boolean testdrive;
    private String testdrivedate;
    private boolean quotation;
    private String quotationdate;
    private String statusdate;

    public APIProspect() {
    }
 
    public APIProspect(String useremail, String prospectname, String mobile, String email,
    		String brandname, String modelname, String source, String status,
    		boolean demo, String demodate, boolean testdrive, String testdrivedate, 
    		boolean quotation, String quotationdate, String statusdate) {
        this.useremail = useremail;
        this.prospectname = prospectname;
        this.mobile = mobile;
        this.email = email;
        this.brandname = brandname;
        this.modelname = modelname;
        this.source = source;
        this.status = status;
        this.demo = demo;
        this.demodate = demodate;
        this.testdrive = testdrive;
        this.testdrivedate = testdrivedate;
        this.quotation = quotation;
        this.quotationdate = quotationdate;
        this.statusdate = statusdate;
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

    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
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

    public String getstatus() {
        return status;
    }
    public void setstatus(String status) {
        this.status = status;
    }

    public boolean getdemo() {
        return demo;
    }
    public void setdemo(boolean demo) {
        this.demo = demo;
    }
    
    public String getdemodate() {
        return demodate;
    }
    public void setdemodate(String demodate) {
        this.demodate = demodate;
    }

    public boolean gettestdrive() {
        return testdrive;
    }
    public void settestdrive(boolean testdrive) {
        this.testdrive = testdrive;
    }

    public String gettestdrivedate() {
        return testdrivedate;
    }
    public void settestdrivedate(String testdrivedate) {
        this.testdrivedate = testdrivedate;
    }

    public boolean getquotation() {
        return quotation;
    }
    public void setquotation(boolean quotation) {
        this.quotation = quotation;
    }

    public String getquotationdate() {
        return quotationdate;
    }
    public void setquotationdate(String quotationdate) {
        this.quotationdate = quotationdate;
    }

    public String getstatusdate() {
        return statusdate;
    }
    public void setstatusdate(String statusdate) {
        this.statusdate = statusdate;
    }
}