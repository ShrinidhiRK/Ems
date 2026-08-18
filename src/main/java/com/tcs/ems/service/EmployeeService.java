package com.tcs.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tcs.ems.entity.Employee;
import com.tcs.ems.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public String insertEmployee(Employee employee) {
		employeeRepository.save(employee);
		return "The data saved successfully";
	}
	
	public Object findbyEmail(String id) {
		Optional<Employee> oe=employeeRepository.findById(id);
		if(oe.isPresent()) {
			return oe;
		}else {
			return "mail is not registered";
		}
	}
	
	public Object fetchAll() {
		List<Employee> list=employeeRepository.findAll();
		if(list.isEmpty()) {
			return "user not found";
		}else {
			return list;
		}
	}
	
	public String deleteById(String id) {
		Optional<Employee> oe=employeeRepository.findById(id);
		if(oe.isPresent()) {
			employeeRepository.deleteById(id);
			return "mail is deleted successfully";
		}else {
			return "mail is not exist";
		}
	}
	
	public String updateByEmail(String id,Employee employee) {
		Optional<Employee> oe=employeeRepository.findById(id);
		if(oe.isPresent()) {
			Employee employee2=oe.get();
			employee2.setDepartment(employee.getDepartment());
			employee2.setName(employee.getName());
			employee2.setSalary(employee.getSalary());
			employeeRepository.save(employee2);
			return "date updated successfully";
		}else {
			return "the user not found";
		}
	}
}
