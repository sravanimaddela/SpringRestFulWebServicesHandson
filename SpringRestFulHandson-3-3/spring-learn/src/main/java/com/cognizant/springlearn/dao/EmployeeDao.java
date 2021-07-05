package com.cognizant.springlearn.dao;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.cognizant.springlearn.model.Employee;
@Component
public class EmployeeDao {
	public static ArrayList<Employee> EMPLOYEE_LIST;
	public EmployeeDao() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("employee.xml");
		EMPLOYEE_LIST = (ArrayList<Employee>)ctx.getBean("employeeList");
	}
	public ArrayList<Employee> getAllEmployees() {
		return EMPLOYEE_LIST;
	}
}