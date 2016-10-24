package com.SpringMVC.uriconstant;

public class UserTargetRestURIConstant {
	public static final String Get = "/usertarget/{targetid}";
	public static final String GetByPeriodMember = "/usertarget/{period}/{userid}";
	public static final String GetByPeriodTeam = "/usertarget/{period}/{teamid}";
	public static final String GetByMember = "/usertarget/{userid}";
	public static final String Create = "/usertarget/create";
	public static final String Update = "/usertarget/update/{targetid}";
	public static final String Delete = "/usertarget/delete/{targetid}";
}
