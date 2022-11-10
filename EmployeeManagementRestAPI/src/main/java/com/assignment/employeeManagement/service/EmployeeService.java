package com.assignment.employeeManagement.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.assignment.employeeManagement.entity.Employee;
import com.assignment.employeeManagement.entity.Role;
import com.assignment.employeeManagement.entity.User;

public interface EmployeeService {
	public List<Employee> findAll();
	
	public Employee findById(long theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(long theId);
	
	public List<Employee> searchByFirstName(String firstName);
	
	public List<Employee> sortByFirstNameAsc();
	public List<Employee> customSortByFirstName(Direction dir);
	
	public User saveUser(User user);
	public Role saveRole(Role role);
	
}
