package com.cognizant.springlearn.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.exception.EmployeeNotFoundException;
import com.cognizant.springlearn.model.Employee;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDao employeeDao;
	@Transactional
	public ArrayList<Employee> getAllEmployees(){
		return employeeDao.getAllEmployees();
	}
	@Transactional
	public void updateEmployee(Employee employee) {
		try {
			employeeDao.updateEmployee(employee);
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Transactional
	public void deleteEmployee(int id) {
		try {
			employeeDao.deleteEmployee(id);
		} catch (EmployeeNotFoundException e) {

			e.printStackTrace();
		}

	}
}