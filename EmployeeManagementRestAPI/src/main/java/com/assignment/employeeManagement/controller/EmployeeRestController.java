package com.assignment.employeeManagement.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.employeeManagement.entity.Employee;
import com.assignment.employeeManagement.entity.Role;
import com.assignment.employeeManagement.entity.User;
import com.assignment.employeeManagement.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
public class EmployeeRestController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return employeeService.saveUser(user);
	}

	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		System.out.println(role.toString());
		return employeeService.saveRole(role);
	}

	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		return employeeService.findAll();
	}

	// add mapping for GET /employees/{employeeId}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable Long employeeId) {

		Employee theEmployee = employeeService.findById(employeeId);

		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}

		return theEmployee;
	}

	// add mapping for POST /employees - add new employee

	@PostMapping("/addemployees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		theEmployee.setId(0);

		employeeService.save(theEmployee);

		return theEmployee;
	}

	// add mapping for PUT /employees - update existing employee

	@PutMapping("/employees/{employeeId}")
	public Employee updateEmployee(@RequestBody Employee theEmployee,@PathVariable long employeeId) {

		Employee tempEmployee = employeeService.findById(employeeId);

		
		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		
		tempEmployee.setFirstName(theEmployee.getFirstName());
		tempEmployee.setLastName(theEmployee.getLastName());
		tempEmployee.setEmail(theEmployee.getEmail());
		
		employeeService.save(tempEmployee);
		
		theEmployee.setId(employeeId);

		return theEmployee;
	}


	// add mapping for DELETE /employees/{employeeId} - delete employee

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable long employeeId) {

		Employee tempEmployee = employeeService.findById(employeeId);

		// throw exception if null

		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}

		employeeService.deleteById(employeeId);

		return "Deleted employee id - " + employeeId;
	}

	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable String firstName) {
		return employeeService.searchByFirstName(firstName);
	}

//	@GetMapping("/employees/sort")
//	public List<Employee> sortByFirstName() {
//		System.out.println("here");
//		return employeeService.sortByFirstNameAsc();
//	}
	
	@GetMapping("/employees/sort")
	public List<Employee> CustomSortByFirstName(@RequestParam(name="order") Direction dir) {
		return employeeService.customSortByFirstName(dir);
	}
	@GetMapping("/listHeaders")
	public String listAllHeaders(@RequestHeader Map<String, String> headers) {
		headers.forEach((key, value) -> {
			log.info(String.format("Header '%s' = %s", key, value));
		});

		return String.format("Open Log to View - Listed %d headers", headers.size());
	}
}
