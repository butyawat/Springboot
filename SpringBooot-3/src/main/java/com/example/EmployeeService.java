package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class EmployeeService {
	private static List<Employee> list = new ArrayList<>();
	static {
		list.add(new Employee(12,"harsh1", "Bhopal1"));
		list.add(new Employee(2,"harsh1", "Bhopal2"));
		list.add(new Employee(3,"harsh1", "Bhopal3"));
		list.add(new Employee(4,"harsh1", "Bhopal4"));
		list.add(new Employee(5,"harsh1", "Bhopal5"));
	}
	public List<Employee> getAllEmployee() {
		return list;
		
	}

}
