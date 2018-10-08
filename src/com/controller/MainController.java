package com.controller;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import java.io.IOException;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dao.EmployeeDao;
import com.dao.UserDao;
import com.model.Employee;
import com.model.User;

@Controller
public class MainController {
	@Autowired
	private UserDao userDao;
	@Autowired
	private EmployeeDao employeeDao;
	
	@RequestMapping(value="/")
	public ModelAndView showHomePage(ModelAndView model) throws IOException {
		model.setViewName("index");
		System.out.println("login");
		return model;
	}
	
	@RequestMapping(value="/error")
	public ModelAndView showErrorPage(ModelAndView model) throws IOException {
		model.setViewName("error");
		System.out.println("error");
		return model;
	}
	
	@RequestMapping(value="/main")
	public ModelAndView showMainPage(ModelAndView model) throws IOException {
		model.setViewName("main");
		System.out.println("main");
		return model;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ResponseEntity<User> login(HttpServletRequest request) {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user=userDao.get(username);
		ModelAndView model=new ModelAndView();
		if(user!=null && user.getPassword().equals(password)) {
			return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
		}else {
			user.setUserName("error");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		//return model;
	}
	
	
	
	@RequestMapping(value="/list" , method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> listEmployees(){
		List<Employee> employees=employeeDao.list();
		if(employees.isEmpty()) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		System.out.println("return");
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	@RequestMapping(value="/search")
	public ModelAndView searchEmployee(ModelAndView model) {
		List<Employee> listEmployee=employeeDao.list();
		model.addObject("listEmployee", listEmployee);
		model.setViewName("main");
		return model;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request) {
		Integer employeeID=Integer.parseInt(request.getParameter("ID"));
		employeeDao.delete(employeeID);
		return new ModelAndView("main");
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addEmployee(@ModelAttribute Employee employee) {
		employeeDao.saveOrUpdate(employee);
		return new ModelAndView("main");
	}
}
