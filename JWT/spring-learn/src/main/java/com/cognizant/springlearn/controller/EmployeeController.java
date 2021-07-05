package com.cognizant.springlearn.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.exception.EmployeeNotFoundException;
import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.EmployeeService;
@RestController
@RequestMapping("/employees")
public class EmployeeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	EmployeeService employeeService;
	@GetMapping("/")
	public ArrayList<Employee> getAllEmployees(){
		LOGGER.info("START");
		LOGGER.info("END");
		return employeeService.getAllEmployees();
	}
	@PutMapping("/{employee}")
	public void updateEmployee(@RequestBody @Valid Employee employee) throws EmployeeNotFoundException{
		LOGGER.info("START");
		employeeService.updateEmployee(employee);
		LOGGER.info("END");
	}
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable int id) {
		LOGGER.info("START");
		employeeService.deleteEmployee(id);
		LOGGER.debug("Employee List After Deletions:{}", employeeService.getAllEmployees());
		LOGGER.info("End");
	}
}
