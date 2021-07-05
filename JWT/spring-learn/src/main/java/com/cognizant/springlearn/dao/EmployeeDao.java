package com.cognizant.springlearn.dao;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.cognizant.springlearn.exception.EmployeeNotFoundException;
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
	public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
		Employee e= getEmployeeById(employee.getId());
		if(e==null)
			throw new EmployeeNotFoundException("Employee Not Found");
	}
	public void deleteEmployee(int id) throws EmployeeNotFoundException {
		Employee employee=null;
		employee=getEmployeeById(id);
		if(employee==null) {
			throw new EmployeeNotFoundException("EmployeeNotFound");
					
		}
		EMPLOYEE_LIST.remove(employee);
	}
	private Employee getEmployeeById(long l) {
		for(Employee e : EMPLOYEE_LIST)
			if(e.id==l)
				return e;
		return null;
	}
}