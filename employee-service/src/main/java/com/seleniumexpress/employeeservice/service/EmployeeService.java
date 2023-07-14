package com.seleniumexpress.employeeservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.seleniumexpress.employeeservice.dao.EmployeeRepo;
import com.seleniumexpress.employeeservice.entity.Employee;
import com.seleniumexpress.employeeservice.response.AddressResponse;
import com.seleniumexpress.employeeservice.response.EmployeeResponse;

@Service
public class EmployeeService {
	
	@Autowired
	private WebClient webClient;

	@Autowired
	private EmployeeRepo repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
//	@Autowired
	private RestTemplate restTemplate;
	
//	@Value("${address-service.base.url}")
//	private String baseUrl;
	
//	public EmployeeService(@Value("${address-service.base.url}") String baseUrl, RestTemplateBuilder builder) {
//		this.restTemplate = builder.rootUri(baseUrl).build();
//	}
//	

	// GET BY ID
	//----------
	public EmployeeResponse getEmployeeById(int id) {
		
		Employee employee =  repo.findById(id).get();
		
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		
//		AddressResponse addressResponse = restTemplate.getForObject(baseUrl+"/address/{id}", AddressResponse.class, id);
		
//		AddressResponse addressResponse = usingRestTemplate(id);
		
		AddressResponse addressResponse = webClient
				                          .get()
				                          .uri("/address/"+id) 
				                          .retrieve()
				                          .bodyToMono(AddressResponse.class)
				                          .block();
		
		employeeResponse.setAddressResponse(addressResponse);
		
		return employeeResponse;
	}

	
	
	
	private AddressResponse usingRestTemplate(int id) {
		return restTemplate.getForObject("/address/{id}", AddressResponse.class, id);
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
