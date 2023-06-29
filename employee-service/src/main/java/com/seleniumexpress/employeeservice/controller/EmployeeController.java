package com.seleniumexpress.employeeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.seleniumexpress.employeeservice.entity.Employee;
import com.seleniumexpress.employeeservice.response.EmployeeResponse;
import com.seleniumexpress.employeeservice.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	// GET-BY-ID
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable("id") int id) {
		EmployeeResponse employeeResponse = service.getEmployeeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}

	// ADD
	@PostMapping("/add-employee")
	public Employee addEmployee(@RequestBody Employee emp) {
		return service.addEmployee(emp);
	}

	// UPDATE
	@PutMapping("/update-Employee/{id}")
	public Employee updateEmployee(@RequestBody Employee emp, @PathVariable("id") int id) {
		return service.updateEmployee(emp, id);
	}

}
