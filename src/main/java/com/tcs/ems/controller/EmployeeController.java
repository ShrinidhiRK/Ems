package com.tcs.ems.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.ems.entity.Employee;
import com.tcs.ems.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping
	public String insert(@Valid @RequestBody Employee employee) {
		return employeeService.insertEmployee(employee);
	}
	
	@GetMapping("/{id}")
	public Object findByEmail(@PathVariable String id) {
		return employeeService.findbyEmail(id);
	}
	
	@GetMapping
	public Object findall() {
		return employeeService.fetchAll();
	}
	
	@DeleteMapping("/{id}")
	public String deleteByEmail(@PathVariable String id) {
		return employeeService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public String updateByEmail(@PathVariable String id, @RequestBody Employee employee) {
		return employeeService.updateByEmail(id, employee);
	}
}
