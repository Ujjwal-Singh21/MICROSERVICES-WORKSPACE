package com.seleniumexpress.employeeservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.seleniumexpress.employeeservice.dao.EmployeeRepo;
import com.seleniumexpress.employeeservice.entity.Employee;
import com.seleniumexpress.employeeservice.feingclient.AddressClient;
import com.seleniumexpress.employeeservice.response.AddressResponse;
import com.seleniumexpress.employeeservice.response.EmployeeResponse;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AddressClient addressClient;
	
	
	// GET BY ID
	//----------
	public EmployeeResponse getEmployeeById(int id) {
		
		Employee employee =  repo.findById(id).get();
		
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		
		ResponseEntity<AddressResponse> responseEntity = addressClient.getAddressByEmployeeId(id);
		
		AddressResponse addressResponse = responseEntity.getBody();
		
		employeeResponse.setAddressResponse(addressResponse);
		
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
