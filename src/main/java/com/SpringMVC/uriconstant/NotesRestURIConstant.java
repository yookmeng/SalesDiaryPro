package com.SpringMVC.uriconstant;

public class NotesRestURIConstant {
	public static final String Get = "/notes/{noteid}";
	public static final String GetByMember = "/notes/{userid}";
	public static final String GetByBranch = "/notes/{branchid}";
	public static final String GetByCompany = "/notes/{companyid}";	
	public static final String Create = "/notes/create";
	public static final String Update = "/notes/update/{noteid}";
	public static final String Delete = "/notes/delete/{noteid}";
}
