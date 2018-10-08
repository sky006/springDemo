package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao{

	private JdbcTemplate jdbcTemplate;
	
	public EmployeeDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveOrUpdate(Employee employee) {
		if(employee.getEmployeeID()>0) {
			//update
			String sql="update EMPLOYEES set NAME=?, PHONE_NUMBER=?,  SUPERVISORS=? where EMPLOYEE_ID=?";
			jdbcTemplate.update(sql, employee.getName(),employee.getPhoneNumber(),employee.getSupervisors(),employee.getEmployeeID());
		}else {
			//insert
			String sql="insert into EMPLOYEES (NAME, PHONE_NUMBER, SUPERVISORS) values(?, ?, ?)";
			jdbcTemplate.update(sql, employee.getName(),employee.getPhoneNumber(),employee.getSupervisors());	
		}	
	}

	@Override
	public void delete(int employeeID) {
		String sql = "delete from EMPLOYEES where EMPLOYEE_ID=?";
		jdbcTemplate.update(sql, employeeID);
		
	}

	@Override
	public Employee get(int employeeID) {
		String sql="select * from EMPLOYEES where EMPLOYEE_ID="+employeeID;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Employee>() {
			@Override
			public Employee extractData(ResultSet rs) throws SQLException, DataAccessException{
				if(rs.next()) {
					Employee employee=new Employee();
					employee.setEmployeeID(rs.getInt("EMPLOYEE_ID"));
					employee.setName(rs.getString("NAME"));
					employee.setPhoneNumber(rs.getString("PHONE_NUMBER"));
					employee.setSupervisors(rs.getString("SUPERVISORS"));
					return employee;
				}
				return null;
			}
		});
	}

	@Override
	public List<Employee> list() {
		String sql="select * from EMPLOYEES";
		System.out.println("Hello world");
		List<Employee> listEmployee=jdbcTemplate.query(sql, new RowMapper<Employee>(){
			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException{
				Employee employee=new Employee();
				
				employee.setEmployeeID(rs.getInt("EMPLOYEE_ID"));
				employee.setName(rs.getString("NAME"));
				employee.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				employee.setSupervisors(rs.getString("SUPERVISORS"));
				return employee;
			}
		});
		System.out.println(listEmployee.size());
		return listEmployee;
	}
}
