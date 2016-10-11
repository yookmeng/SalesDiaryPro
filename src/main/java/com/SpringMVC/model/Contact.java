package com.SpringMVC.model;

import java.sql.Date;

public class Contact {
    private int contactid;
    private int userid;
    private String firstname;
    private String lastname;
    private String mobile;
    private String home;
    private String work;
    private String email;
    private Date birthday;
    private String country;
    private String zipcode;
    private String state;
    private String city;
    private String street;
    private String company;
    private String title;    
    private String note;
    private String website;

    public Contact() {
    }
 
    public Contact(int contactid, int userid, String firstname, String lastname, 
    		String mobile, String home, String work, String email, Date birthday, 
    		String country, String zipcode, String state, String city, String street, 
    		String company, String title, String note, String website) {
        this.contactid = contactid;
        this.userid = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.home = home;
        this.work = work;
        this.email = email;
        this.birthday = birthday;
        this.country = country;        
        this.zipcode = zipcode;
        this.state = state;
        this.city = city;
        this.street = street;        
        this.company = company;
        this.title = title;
        this.note = note;
        this.website= website;
    }
 
    public int getcontactid() {
        return contactid;
    }
    public void setcontactid(int contactid) {
        this.contactid = contactid;
    }

    public int getuserid() {
        return userid;
    }
    public void setuserid(int userid) {
        this.userid = userid;
    }

    public String getfirstname() {
        return firstname;
    }  
    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getlastname() {
        return lastname;
    }  
    public void setlastname(String lastname) {
        this.lastname = lastname;
    }

    public String getmobile() {
        return mobile;
    }
    public void setmobile(String mobile) {
        this.mobile = mobile;
    }

    public String gethome() {
        return home;
    }  
    public void sethome(String home) {
        this.home= home;
    }

    public String getwork() {
        return work;
    }
    public void setwork(String work) {
        this.work = work;
    }

    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }

    public Date getbirthday() {
        return birthday;
    }
    public void setbirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getcountry() {
        return country;
    }
    public void setcountry(String country) {
        this.country = country;
    }

    public String getzipcode() {
        return zipcode;
    }
    public void setzipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getstate() {
        return state;
    }
    public void setstate(String state) {
        this.state = state;
    }

    public String getcity() {
        return city;
    }
    public void setcity(String city) {
        this.city = city;
    }

    public String getstreet() {
        return street;
    }  
    public void setstreet(String street) {
        this.street = street;
    }

    public String getcompany() {
        return company;
    }
    public void setcompany(String company) {
        this.company = company;
    }

    public String gettitle() {
        return title;
    }
    public void settitle(String title) {
        this.title = title;
    }

    public String getnote() {
        return note;
    }
    public void setnote(String note) {
        this.note = note;
    }

    public String getwebsite() {
        return website;
    }
    public void setwebsite(String website) {
        this.website = website;
    }
    
    @Override
    public String toString() {
        return "Contact {contactid:" + contactid + ", userid:" + userid 
        		+ ", firstname:" + firstname + ", lastname:" + lastname
                + ", mobile:" + mobile + ", home:" + home + ", work:" + work 
                + ", email:" + email + ", birthday:" + birthday 
                + ", country:" + country + ", zipcode:" + zipcode + ", state:" + state
                + ", city:" + city + ", street:" + street 
                + ", company:" + company+ ", title:" + title + ", note:" + note + ", website:" + website
                + "}";
    }
}