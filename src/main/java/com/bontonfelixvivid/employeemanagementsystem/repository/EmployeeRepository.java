package com.bontonfelixvivid.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bontonfelixvivid.employeemanagementsystem.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	

}
