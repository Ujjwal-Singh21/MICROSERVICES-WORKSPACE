package com.seleniumexpress.employeeservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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
	private EmployeeRepo repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
//	@Value("${address-service.base.url}")
//	private String baseUrl;
	
//	public EmployeeService(@Value("${address-service.base.url}") String baseUrl, RestTemplateBuilder builder) {
//		this.restTemplate = builder.rootUri(baseUrl).build();
//	}
	

	// GET BY ID
	//==========
	public EmployeeResponse getEmployeeById(int id) {
		
		Employee employee =  repo.findById(id).get();
		
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		
//		AddressResponse addressResponse = restTemplate.getForObject(baseUrl+"/address/{id}", AddressResponse.class, id);
		
		AddressResponse addressResponse = usingRestTemplate(id);
		
		
		
//		AddressResponse addressResponse = webClient
//				                          .get()
//				                          .uri("/address/"+id) 
//				                          .retrieve()
//				                          .bodyToMono(AddressResponse.class)
//				                          .block();
		
//		AddressResponse addressResponse = usingWebClient(id);
		
		
		
		employeeResponse.setAddressResponse(addressResponse);
		return employeeResponse;
	}
	
	
	
	// rest-template
	//==============
	private AddressResponse usingRestTemplate(int id) {
		
		// INITIALLY JUST USING REST-TEMPLATE
		//-----------------------------------
//		return restTemplate.getForObject("/address/{id}", AddressResponse.class, id); 

		// USING DISCOVER CLIENT
		//----------------------
//		List<ServiceInstance> instances = discoveryClient.getInstances("address-service");
//		ServiceInstance serviceInstance = instances.get(0);
//	    String uri = serviceInstance.getUri().toString();
//	    System.out.println("URI >>>>>>>>>>>>>>" + uri); // http://LIN76004068.corp.capgemini.com:8081
//	    return restTemplate.getForObject(uri + "/address-app/api/address/{id}", AddressResponse.class, id);
		
		
		// USING LOAD-BALANCER CLIENT
		//----------------------------
//		ServiceInstance serviceInstance = loadBalancerClient.choose("address-service");
//		String uri = serviceInstance.getUri().toString();
		
//		String contextPath = serviceInstance.getMetadata().get("configPath");
//		System.out.println("URI + contextPath >>>>>>>>>>>>>>" + uri + contextPath); //-> http://LIN76004068.corp.capgemini.com:8083/address-app/api
		
//		System.out.println("USERNAME>> " + serviceInstance.getMetadata().get("username") + 
//				           " PASSWORD>>> " + serviceInstance.getMetadata().get("password"));
		
//	    return restTemplate.getForObject(uri + contextPath + "/address/{id}", AddressResponse.class, id);
	    
	    
	    // DOING IT IN MOST SIMPLE WAY BY USING JUST SERVICE-NAME FROM DISCOVER-SERVICE-REGISTRY AND THEN ADDING CONTEXT PATH BY OURSELF
	    //-------------------------------------------------------------------------------------------------------------------------------
		return restTemplate.getForObject("http://address-service/address-app/api/address/{id}", AddressResponse.class, id);
	}

	
	
	// web-client
	//===========
	private AddressResponse usingWebClient(int id) {
		return webClient
                .get()
                .uri("/address/"+id) 
                .retrieve()
                .bodyToMono(AddressResponse.class)
                .block();
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
