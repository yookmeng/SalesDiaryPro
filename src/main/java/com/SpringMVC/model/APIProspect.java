package com.SpringMVC.model;

public class APIProspect {
    private String useremail;
    private String prospectname;
    private String mobile;
    private String brandname;
    private String modelname;
    private String source;

    public APIProspect() {
    }
 
    public APIProspect(String useremail, String prospectname, String mobile, 
    		String brandname, String modelname, String source) {
        this.useremail = useremail;
        this.prospectname = prospectname;
        this.mobile = mobile;
        this.brandname = brandname;
        this.modelname = modelname;
        this.source = source;
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
}