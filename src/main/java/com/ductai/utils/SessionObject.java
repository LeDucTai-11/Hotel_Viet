package com.ductai.utils;

public class SessionObject {
	private String userName;
	private String fullName;
	
	public SessionObject() {
		
	}
	
	public SessionObject(String userName,String fullName) {
		this.userName = userName;
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
}
