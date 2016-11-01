package com.SpringMVC.uriconstant;

public class ReviewRestURIConstant {
	public static final String Get = "/review/{reviewid}";
	public static final String GetByMember = "/review/{userid}";
	public static final String GetByBranch = "/review/{branchid}";
	public static final String GetByCompany = "/review/{companyid}";	
	public static final String Create = "/review/create";
	public static final String Update = "/review/update/{reviewid}";
	public static final String Delete = "/review/delete/{reviewid}";
}
