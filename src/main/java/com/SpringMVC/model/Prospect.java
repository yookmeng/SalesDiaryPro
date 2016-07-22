package com.SpringMVC.model;

public class Prospect {
    private int prospectid;
    private String prospectname;
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
    private String income;
    private String email;

    public Prospect() {
    }
 
    public Prospect(int prospectid, String prospectname, int userid, String source,  
    		String haddress, String hzipcode, String hcity, 
    		String hstate, String hcountry, String mobile, String htelno, 
    		String waddress, String wzipcode, String wcity, 
    		String wstate, String wcountry, String wtelno, 
    		String occupation, int age, String income, String email) {
        this.prospectid = prospectid;
        this.prospectname = prospectname;
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
        this.income = income;
        this.email = email;
    }
 
    public int getprospectid() {
        return prospectid;
    }
    public void setprospectid(int prospectid) {
        this.prospectid = prospectid;
    }

    public String getprospectname() {
        return prospectname;
    }  
    public void setprospectname(String prospectname) {
        this.prospectname = prospectname;
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

    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (prospectid ^ (prospectid >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prospect other = (Prospect) obj;
		if (prospectid != other.prospectid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Prospect [prospectid=" + prospectid + ", prospectname=" + prospectname 
				+ ", address=" + haddress + ", zipcode=" + hzipcode
				+ ", city=" + hcity + ", state=" + hstate + ", country=" + hcountry 
				+ ", mobile=" + mobile + ", age=" + age + ", income=" + income 
				+ ", email=" + email+ "]";
	}
}