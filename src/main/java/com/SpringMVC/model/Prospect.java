package com.SpringMVC.model;

public class Prospect {
    private int prospectid;
    private String firstname;
    private String lastname;
    private int userid;
    private String source;
    private Address homeaddress;
    private String mobile;
    private String htelno;
    private int contactid;
    private Address workaddress;
    private String wtelno;
    private String occupation;
    private int age;
    private String gender;    
    private String income;
    private String email;
    private String status;

    public Prospect() {
    }
 
    public Prospect(int prospectid, String firstname, String lastname, int userid, String source,  
    		Address homeaddress, String mobile, String htelno, int contactid,
    		Address workaddress, String wtelno, 
    		String occupation, int age, String gender, String income, String email, String status) {
        this.prospectid = prospectid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userid = userid;
        this.source = source;
        this.homeaddress = homeaddress;
        this.mobile = mobile;
        this.htelno = htelno;
        this.contactid = contactid;
        this.workaddress = workaddress;
        this.wtelno = wtelno;
        this.occupation = occupation;
        this.age = age;
        this.gender = gender;
        this.income = income;
        this.email = email;
        this.status = status;
    }
 
    public int getprospectid() {
        return prospectid;
    }
    public void setprospectid(int prospectid) {
        this.prospectid = prospectid;
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

    public int getuserid() {
        return userid;
    }
    public void setuserid(int userid) {
        this.userid = userid;
    }

    public String getsource() {
        return source;
    }
    public void setsource(String source) {
        this.source = source;
    }

    public Address gethomeaddress() {
        return homeaddress;
    }  
    public void sethomeaddress(Address homeaddress) {
        this.homeaddress = homeaddress;
    }

    public String getmobile() {
        return mobile;
    }
    public void setmobile(String mobile) {
        this.mobile = mobile;
    }

    public String gethtelno() {
        return htelno;
    }
    public void sethtelno(String htelno) {
        this.htelno = htelno;
    }

    public int getcontactid() {
        return contactid;
    }
    public void setcontactid(int contactid) {
        this.contactid = contactid;
    }

    public Address getworkaddress() {
        return workaddress;
    }  
    public void setworkaddress(Address workaddress) {
        this.workaddress = workaddress;
    }

    public String getwtelno() {
        return wtelno;
    }
    public void setwtelno(String wtelno) {
        this.wtelno = wtelno;
    }

    public String getoccupation() {
        return occupation;
    }
    public void setoccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getage() {
        return age;
    }
    public void setage(int age) {
        this.age = age;
    }

    public String getincome() {
        return income;
    }
    public void setincome(String income) {
        this.income = income;
    }

    public String getgender() {
        return gender;
    }
    public void setgender(String gender) {
        this.gender = gender;
    }

    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }    

    public String getstatus() {
        return status;
    }  
    public void setstatus(String status) {
        this.status = status;
    }
}