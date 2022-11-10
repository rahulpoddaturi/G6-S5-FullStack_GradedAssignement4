package com.assignment.employeeManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.employeeManagement.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findByFirstNameContainsAllIgnoreCase(String firstName);

	List<Employee> findAllByOrderByFirstNameAsc();
	
	List<Employee> findAllByOrderByFirstNameDesc();

	

}
