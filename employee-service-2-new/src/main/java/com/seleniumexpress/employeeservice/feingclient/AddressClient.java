package com.seleniumexpress.employeeservice.feingclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.seleniumexpress.employeeservice.response.AddressResponse;

// http://localhost:8181/address-app/api/address/1
@FeignClient(name = "address-feign-client", url = "http://localhost:8181/address-app/api")
public interface AddressClient {

	@GetMapping("/address/{id}")
	AddressResponse getAddressByEmployeeId(@PathVariable("id") int id);

}
