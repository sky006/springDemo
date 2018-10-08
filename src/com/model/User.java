package com.model;

public class User {
	private Integer userID;
	private String userName;
	private String password;
	
	public User() {
		
	}
	public User(String userName, String password) {
		this.setUserName(userName);
		this.setPassword(password);
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
