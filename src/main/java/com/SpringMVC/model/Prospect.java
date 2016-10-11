package com.SpringMVC.model;

public class Prospect {
    private int prospectid;
    private String firstname;
    private String lastname;
    private int userid;
    private String source;
    private String haddress;
    private String hzipcode;
    private String hcity;
    private String hstate;
    private String hcountry;
    private String mobile;
    private String htelno;
    private String waddress;
    private String wzipcode;
    private String wcity;
    private String wstate;
    private String wcountry;
    private String wtelno;
    private String occupation;
    private int age;
    private String gender;    
    private String income;
    private String email;

    public Prospect() {
    }
 
    public Prospect(int prospectid, String firstname, String lastname, int userid, String source,  
    		String haddress, String hzipcode, String hcity, 
    		String hstate, String hcountry, String mobile, String htelno, 
    		String waddress, String wzipcode, String wcity, 
    		String wstate, String wcountry, String wtelno, 
    		String occupation, int age, String gender, String income, String email) {
        this.prospectid = prospectid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userid = userid;
        this.source = source;
        this.haddress = haddress;
        this.hzipcode = hzipcode;
        this.hcity = hcity;
        this.hstate = hstate;
        this.hcountry = hcountry;        
        this.mobile = mobile;
        this.htelno = htelno;
        this.waddress = waddress;
        this.wzipcode = wzipcode;
        this.wcity = wcity;
        this.wstate = wstate;
        this.wcountry = wcountry;        
        this.wtelno = wtelno;
        this.occupation = occupation;
        this.age = age;
        this.gender = gender;
        this.income = income;
        this.email = email;
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

    public String gethaddress() {
        return haddress;
    }  
    public void sethaddress(String haddress) {
        this.haddress = haddress;
    }

    public String gethzipcode() {
        return hzipcode;
    }
    public void sethzipcode(String hzipcode) {
        this.hzipcode = hzipcode;
    }

    public String gethcity() {
        return hcity;
    }
    public void sethcity(String hcity) {
        this.hcity = hcity;
    }

    public String gethstate() {
        return hstate;
    }
    public void sethstate(String hstate) {
        this.hstate = hstate;
    }

    public String gethcountry() {
        return hcountry;
    }
    public void sethcountry(String hcountry) {
        this.hcountry = hcountry;
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

    public String getwaddress() {
        return waddress;
    }  
    public void setwaddress(String waddress) {
        this.waddress = waddress;
    }

    public String getwzipcode() {
        return wzipcode;
    }
    public void setwzipcode(String wzipcode) {
        this.wzipcode = wzipcode;
    }

    public String getwcity() {
        return wcity;
    }
    public void setwcity(String wcity) {
        this.wcity = wcity;
    }

    public String getwstate() {
        return wstate;
    }
    public void setwstate(String wstate) {
        this.wstate = wstate;
    }

    public String getwcountry() {
        return wcountry;
    }
    public void setwcountry(String wcountry) {
        this.wcountry = wcountry;
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
    
    @Override
    public String toString() {
        return "Prospect [prospectid=" + prospectid + ", firstname=" + firstname + ", lastname=" + lastname
                + ", userid=" + userid + ", source=" + source 
                + ", haddress=" + haddress + ", hzipcode=" + hzipcode + ", hcity=" + hcity
                + ", hstate=" + hstate + ", hcountry=" + hcountry + ", mobile=" + mobile + ", htelno=" + htelno 
                + ", waddress=" + waddress + ", wzipcode=" + wzipcode + ", wcity=" + wcity
                + ", wstate=" + wstate + ", wcountry=" + wcountry + ", wtelno=" + wtelno 
                + ", occupation=" + occupation + ", age=" + age + ", gender=" + gender 
                + ", income=" + income + ", email=" + email
                + "]";
    }
}