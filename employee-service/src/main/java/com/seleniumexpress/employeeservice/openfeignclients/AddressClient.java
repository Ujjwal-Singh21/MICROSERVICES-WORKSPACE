package com.seleniumexpress.employeeservice.openfeignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.seleniumexpress.employeeservice.response.AddressResponse;


@FeignClient(name = "ADDRESS-SERVICE", path = "/address-app/api")
public interface AddressClient {
	
	@GetMapping("/address/{employeeId}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int employeeId);
	
	@GetMapping("/getAllAddresses")
	public ResponseEntity<List<AddressResponse>> getAllAddresses();


}
