package org.fkit.dym.domain;

public class Appointment {

	private Integer id;
	private String realname;
	private String realclass;
	private String phonenum;
	private String appointdate;
	private String detaildescribe;
	private String outlinedescribe;
	private String appointstatus;
	private User user;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getRealclass() {
		return realclass;
	}
	public void setRealclass(String realclass) {
		this.realclass = realclass;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getAppointdate() {
		return appointdate;
	}
	public void setAppointdate(String appointdate) {
		this.appointdate = appointdate;
	}
	public String getDetaildescribe() {
		return detaildescribe;
	}
	public void setDetaildescribe(String detaildescribe) {
		this.detaildescribe = detaildescribe;
	}
	public String getOutlinedescribe() {
		return outlinedescribe;
	}
	public void setOutlinedescribe(String outlinedescribe) {
		this.outlinedescribe = outlinedescribe;
	}
	public String getAppointstatus() {
		return appointstatus;
	}
	public void setAppointstatus(String appointstatus) {
		this.appointstatus = appointstatus;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
