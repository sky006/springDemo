package com.dao;

import java.util.List;

import com.model.Employee;

public interface EmployeeDao {
	public void saveOrUpdate(Employee employee);
	
	public void delete(int employeeID);
	
	public Employee get(int employeeID);
	
	public List<Employee> list();
}
