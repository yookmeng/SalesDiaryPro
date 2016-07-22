package com.SpringMVC.model;

public class UserProfile {	 
    private int userid;
    private String username;
    private String password;    
    private String role;
    private int teamid;
    private String mobile;
    private String email;
    
    public UserProfile(){    	
    }
    
    public UserProfile(int userid, String username, String password, String role, int teamid, String mobile, String email) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.role = role;        
        this.teamid = teamid;
        this.mobile = mobile;
        this.email = email;
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

    public int getteamid() {
        return teamid;
    } 
    public void setteamid(int teamid) {
        this.teamid = teamid;
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
}