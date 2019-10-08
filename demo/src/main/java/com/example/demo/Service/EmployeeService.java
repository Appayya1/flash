package com.example.demo.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.IService.ImService;
import com.example.demo.Repositry.EmployeeDataRepositry;
import com.example.demo.User.Employee;
import com.example.demo.exception.EmployeeException;

@Service
public class EmployeeService implements ImService {

	@Autowired
	EmployeeDataRepositry employeeDataRepositry;

	List<Employee> arr = new ArrayList<>();

	SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd hh:mm");

	@Override
	public Employee createEmployee(Employee emp) {
		com.example.demo.Entity.Employee employee = employeeDataRepositry.findByEmail(emp.getEmail());

		if (employee != null) {
			if (employee.getEmail().equals(emp.getEmail())) {
				throw new EmployeeException("Employee already exists withn this email..");
			} else {
				employee.setFirstName(emp.getFirstName());
				employee.setLastName(emp.getLastName());
				employee.setEmail(emp.getEmail());
				Date cratedDate = new Date();
				employee.setCratedDate(cratedDate);
				employee.setPhone(emp.getPhone());
				employee.setPassword(emp.getPassword());
				employeeDataRepositry.save(employee);

				System.out.println("Created Employee");
				String date = formate.format(cratedDate);
				emp.setCreatedDate(date);
				return emp;
			}
		} else {
			employee = new com.example.demo.Entity.Employee();
			employee.setFirstName(emp.getFirstName());
			employee.setLastName(emp.getLastName());
			employee.setEmail(emp.getEmail());
			Date cratedDate = new Date();
			employee.setCratedDate(cratedDate);
			employee.setPhone(emp.getPhone());
			employee.setPassword(emp.getPassword());
			employeeDataRepositry.save(employee);

			System.out.println("Created Employee");
			String date = formate.format(cratedDate);
			emp.setCreatedDate(date);
			emp.setId(employee.getId());

		}
		return emp;
	}

	@Override
	public List<Employee> getEmployee() {

		List<com.example.demo.Entity.Employee> get = employeeDataRepositry.findAll();
		List<Employee> employee = new ArrayList<>();
		Employee emp = null;
		for (com.example.demo.Entity.Employee employee1 : get) {
			emp = new Employee();
			emp.setCreatedDate(formate.format(employee1.getCratedDate()));
			if (employee1.getUpdatedDate() != null) {
				emp.setUpdatedDate(formate.format(employee1.getUpdatedDate()));
			}
			emp.setId(employee1.getId());
			emp.setFirstName(employee1.getFirstName());
			emp.setLastName(employee1.getLastName());
			emp.setEmail(employee1.getEmail());
			emp.setPassword(employee1.getPassword());
			emp.setPhone(employee1.getPhone());
			employee.add(emp);
		}

		return employee;
	}

	@Override
	public Employee deleteEmployee(int id) {

		com.example.demo.Entity.Employee employee = employeeDataRepositry.findOne(id);
		if (employee != null) {
			employeeDataRepositry.delete(id);
		} else {
			throw new EmployeeException("Employee does not exists with the id " + id);
		}
		Employee emp = new Employee();
		emp.setId(employee.getId());
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		emp.setPhone(employee.getPhone());
		emp.setPassword(employee.getPassword());
		emp.setCreatedDate(formate.format(employee.getCratedDate()));
		if (employee.getUpdatedDate() != null) {
			emp.setUpdatedDate(formate.format(employee.getUpdatedDate()));
		}
		return emp;
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		com.example.demo.Entity.Employee employee = employeeDataRepositry.findOne(emp.getId());
		if (employee != null) {

			Date date = new Date();
			employee.setFirstName(emp.getFirstName());
			employee.setLastName(emp.getLastName());
			employee.setEmail(emp.getEmail());
			employee.setPhone(emp.getPhone());
			employee.setPassword(emp.getPassword());
			employee.setUpdatedDate(date);

			employeeDataRepositry.save(employee);
			emp.setCreatedDate(formate.format(employee.getCratedDate()));
			String updatedDate = formate.format(date);
			emp.setUpdatedDate(updatedDate);
			System.out.println("Updated Employee");
			return emp;
		} else {
			throw new EmployeeException("Employee does not exists with the id " + emp.getId());
		}

	}

	@Override
	public Employee findById(int id) {

		Employee emp = null;
		com.example.demo.Entity.Employee employee = employeeDataRepositry.findOne(id);
		if (employee != null) {
			emp = new Employee();
			emp.setCreatedDate(formate.format(employee.getCratedDate()));
			if (employee.getUpdatedDate() != null) {
				emp.setUpdatedDate(formate.format(employee.getUpdatedDate()));
			}
			emp.setId(employee.getId());
			emp.setFirstName(employee.getFirstName());
			emp.setLastName(employee.getLastName());
			emp.setEmail(employee.getEmail());
			emp.setPhone(employee.getPhone());
			emp.setPassword(employee.getPassword());

			return emp;

		}
		throw new EmployeeException("Employee does not exists with the id " + id);
	}

}
