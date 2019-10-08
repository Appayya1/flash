package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.IService.ImService;
import com.example.demo.User.Employee;

@RestController
public class EmployeeController {

	@Autowired
	ImService empService;

	@PostMapping("/Employee")
	public ResponseEntity<Employee> creatEmployee(@RequestBody Employee emp) {

		Employee employee = empService.createEmployee(emp);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	@GetMapping("/Employee")
	public ResponseEntity<List<Employee>> getEmployee() {
		return new ResponseEntity<List<Employee>>(empService.getEmployee(), HttpStatus.OK);
	}

	@DeleteMapping("Employee/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int id) {

		Employee employee = empService.deleteEmployee(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);

	}

	@PutMapping("/Employee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp) {

		Employee employee = empService.updateEmployee(emp);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@GetMapping("/Employee/{id}")
	public ResponseEntity<Employee> findById(@PathVariable("id") int id) {

		Employee employee = empService.findById(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

}
