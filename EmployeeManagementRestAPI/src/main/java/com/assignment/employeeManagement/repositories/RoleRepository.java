package com.assignment.employeeManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.employeeManagement.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
