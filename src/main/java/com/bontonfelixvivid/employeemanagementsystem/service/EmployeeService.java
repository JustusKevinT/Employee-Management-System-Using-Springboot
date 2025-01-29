package com.bontonfelixvivid.employeemanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bontonfelixvivid.employeemanagementsystem.entity.Employee;
import com.bontonfelixvivid.employeemanagementsystem.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public void addEmployee(Employee newEmployee) {
		employeeRepository.save(newEmployee);
	}
	
	public void updateEmployee(Employee updateEmployee) {
		employeeRepository.save(updateEmployee);
	}
	
	public Employee getEmployeeById(long id) {
		Optional<Employee> obj = employeeRepository.findById(id);
		Employee employee = null;
		if(obj.isPresent()) {
			employee = obj.get();
		}
		else {
			throw new RuntimeException("Employee not found with id : " + id);
		}
		return employee;
	}
	
	public void deleteEmployeeById(long id) {
		employeeRepository.deleteById(id);
	}

	public Page<Employee> currentPage(int pageNumber, int pageSize, String sortField, String sortDirection){
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize, sort);
		return employeeRepository.findAll(pageable);
		
	}
	
}
