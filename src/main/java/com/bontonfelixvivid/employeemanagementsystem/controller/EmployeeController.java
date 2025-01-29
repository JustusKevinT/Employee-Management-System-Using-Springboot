package com.bontonfelixvivid.employeemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bontonfelixvivid.employeemanagementsystem.entity.Employee;
import com.bontonfelixvivid.employeemanagementsystem.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return currentPageView(1,"firstName","asc",model);
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		Employee newEmployee = new Employee();
		model.addAttribute("newEmployee", newEmployee);
		return "showNewEmployeeForm";
	}

	@PostMapping("/addEmployee")
	public String addEmployee(@ModelAttribute Employee newEmployee) {
		employeeService.addEmployee(newEmployee);
		return "redirect:/";
	}
	
	@GetMapping("/showUpdateForm/{id}")
	public String showUpdateForm(@PathVariable long id, Model model) {
		Employee updateEmployee = employeeService.getEmployeeById(id);
		model.addAttribute("updateEmployee", updateEmployee);
		return "showUpdateForm";
	}
	
	@PostMapping("/updateEmployee")
	public String updateEmployee(@ModelAttribute Employee updateEmployee) {
		employeeService.updateEmployee(updateEmployee);
		return "redirect:/";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable long id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNumber}")
	public String currentPageView(@PathVariable int pageNumber, 
			@RequestParam String sortField,
			@RequestParam String sortDirection,Model model) {
		int pageSize = 5;
		Page<Employee> page = employeeService.currentPage(pageNumber, pageSize, sortField, sortDirection);
		List<Employee> employeeList = page.getContent();
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("sortField", sortField);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
		model.addAttribute("employeeList", employeeList);
		return "home";
	}
	
}
