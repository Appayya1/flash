package com.example.demo.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Employee;

@Repository
public interface EmployeeDataRepositry extends JpaRepository<Employee, Integer>
{

	public Employee findByEmail(String email);
} 
