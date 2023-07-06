package com.seleniumexpress.employeeservice.feingclient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.seleniumexpress.employeeservice.response.AddressResponse;

// ADDRESS APP URL -> http://localhost:8081/address-app/api/address/1

//@FeignClient(name = "address-feign-client", url = "http://localhost:8081/address-app/api")

//@FeignClient(name = "address-feign-client", url = "http://localhost:8081", path = "/address-app/api")


@FeignClient(name = "address-service", path = "/address-app/api")
@RibbonClient(name = "address-service")
public interface AddressClient {

	@GetMapping("/address/{id}")
	public abstract ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("id") int id);

}
