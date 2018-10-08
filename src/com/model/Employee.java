package com.model;

public class Employee {
	private Integer employeeID;
	private String name;
	private String phoneNumber;
	private String supervisors;
	
	public Employee() {
		
	}
	public Employee(String name, String phoneNumber, String supervisors) {
		this.setName(name);
		this.setPhoneNumber(phoneNumber);
		this.setSupervisors(supervisors);
	}
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSupervisors() {
		return supervisors;
	}
	public void setSupervisors(String supervisors) {
		this.supervisors = supervisors;
	}
}
