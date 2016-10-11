package com.SpringMVC.model;

public class UserLogin {
    private int userid;
    private String username;
    private String password;
    private String role;
    private int companyid;
     
    public UserLogin()  {         
    }
 
    public UserLogin(int userid, String username, String password, String role, int companyid) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.role = role;
        this.companyid = companyid;
    }
 
    public int getuserid() {
        return userid;
    }
    public void setuserid(int userid) {
        this.userid = userid;
    }
 
    public String getusername() {
        return username;
    }
    public void setusername(String username) {
        this.username = username;
    }

    public String getpassword() {
        return password;
    }
    public void setpassword(String password) {
        this.password = password;
    }
 
    public String getrole() {
        return role;
    }
    public void setrole(String role) {
        this.role = role;
    }
 
    public int getcompanyid() {
        return companyid;
    }
    public void setcompanyid(int companyid) {
        this.companyid = companyid;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserLogin other = (UserLogin) obj;
        if (userid != other.userid)
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return "UserLogin [userid=" + userid + ", username=" + username + ", password=" + password
                + ", role=" + role + ", companyid=" + companyid + "]";
    }    
}