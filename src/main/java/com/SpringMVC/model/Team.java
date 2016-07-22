package com.SpringMVC.model;

public class Team {
    private int teamid;
    private String teamname;
    private int branchid;
    private String pic;

    public Team() {
    }
 
    public Team(int teamid, String teamname, int branchid, String pic) {
        this.teamid = teamid;
        this.teamname = teamname;
        this.branchid = branchid;
        this.pic = pic;
    }
 
    public int getteamid() {
        return teamid;
    }  
    public void setteamid(int teamid) {
        this.teamid = teamid;
    }

    public String getteamname() {
        return teamname;
    }  
    public void setteamname(String teamname) {
        this.teamname = teamname;
    }

    public int getbranchid() {
        return branchid;
    }  
    public void setbranchid(int branchid) {
        this.branchid = branchid;
    }

    public String getpic() {
        return pic;
    }  
    public void setpic(String pic) {
        this.pic = pic;
    }
}