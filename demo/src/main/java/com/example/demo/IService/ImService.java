package com.example.demo.IService;

import java.util.List;

import com.example.demo.User.Employee;

public interface ImService {
	
	 Employee createEmployee(Employee emp);
	 List<Employee> getEmployee();
	 Employee deleteEmployee(int id);
	 Employee updateEmployee(Employee emp);
	 Employee findById(int id);
	

}
