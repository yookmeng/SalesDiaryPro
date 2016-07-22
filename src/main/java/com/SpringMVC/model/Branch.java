package com.SpringMVC.model;

public class Branch {
    private int branchid;
    private String branchname;
    private int companyid;
    private String regno;
    private String pic;
    private String address;
    private String zipcode;
    private String city;
    private String state;
    private String country;
    private String telephone;
    private String fax;
    private String email;
    private String website;

    public Branch() {
    }
 
    public Branch(int branchid, String branchname, 
    		int companyid, String regno, String pic, 
    		String address, String zipcode, String city, 
    		String state, String country, String telephone, 
    		String fax,String email, String website) {
        this.branchid = branchid;
        this.branchname = branchname;
        this.companyid = companyid;
        this.regno = regno;
        this.pic = pic;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
        this.country = country;        
        this.telephone = telephone;
        this.fax = fax;
        this.email = email;
        this.website = website;
    }
 
    public int getbranchid() {
        return branchid;
    }
    public void setbranchid(int branchid) {
        this.branchid = branchid;
    }

    public String getbranchname() {
        return branchname;
    }  
    public void setbranchname(String branchname) {
        this.branchname = branchname;
    }

    public int getcompanyid() {
        return companyid;
    }
    public void setcompanyid(int companyid) {
        this.companyid = companyid;
    }

    public String getregno() {
        return regno;
    }
    public void setregno(String regno) {
        this.regno = regno;
    }

    public String getpic() {
        return pic;
    }
    public void setpic(String pic) {
        this.pic = pic;
    }

    public String getaddress() {
        return address;
    }
    public void setaddress(String address) {
        this.address = address;
    }

    public String getzipcode() {
        return zipcode;
    }
    public void setzipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getcity() {
        return city;
    }
    public void setcity(String city) {
        this.city = city;
    }

    public String getstate() {
        return state;
    }
    public void setstate(String state) {
        this.state = state;
    }

    public String getcountry() {
        return country;
    }
    public void setcountry(String country) {
        this.country = country;
    }

    public String gettelephone() {
        return telephone;
    }
    public void settelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getfax() {
        return fax;
    }
    public void setfax(String fax) {
        this.fax = fax;
    }

    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }

    public String getwebsite() {
        return website;
    }
    public void setwebsite(String website) {
        this.website = website;
    }
}