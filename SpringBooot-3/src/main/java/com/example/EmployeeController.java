package com.example;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeservice;
	@GetMapping("getdata")
	public List<Employee> hello(){
		return (List<Employee>) this.employeeservice.getAllEmployee();
	}
	
}
