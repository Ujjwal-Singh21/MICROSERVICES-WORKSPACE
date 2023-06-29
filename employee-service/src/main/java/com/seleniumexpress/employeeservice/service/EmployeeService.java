package com.seleniumexpress.employeeservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seleniumexpress.employeeservice.dao.EmployeeRepo;
import com.seleniumexpress.employeeservice.entity.Employee;
import com.seleniumexpress.employeeservice.response.EmployeeResponse;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo repo;
	
	@Autowired
	private ModelMapper modelMapper;

	// GET BY ID
	public EmployeeResponse getEmployeeById(int id) {
		
		Employee employee =  repo.findById(id).get();
		
//		EmployeeResponse employeeResponse = new EmployeeResponse();
//		employeeResponse.setId(employee.getId());
//		employeeResponse.setName(employee.getName());
//		employeeResponse.setEmail(employee.getEmail());
//		employeeResponse.setBloodgroup(employee.getBloodgroup());
		
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		return employeeResponse;
	}

	// ADD
	public Employee addEmployee(Employee emp) {
		return repo.save(emp);
	}

	// UPDATE
	public Employee updateEmployee(Employee emp, int id) {
		return repo.save(emp);
	}
}
