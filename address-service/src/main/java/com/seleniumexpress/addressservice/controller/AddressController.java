package com.seleniumexpress.addressservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.seleniumexpress.addressservice.response.AddressResponse;
import com.seleniumexpress.addressservice.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService service;

	// GET BY ID
	@GetMapping("/address/{employeeId}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int employeeId) {

		AddressResponse addressResponse = null;

		addressResponse = service.getAddressByEmployeeId(employeeId);

		return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
	}
	
	// GET ALL
	@GetMapping("/getAllAddresses")
	public ResponseEntity<List<AddressResponse>> getAllAddresses() {
		
		List<AddressResponse> addressList = service.getAllAddress();
		
		return ResponseEntity.status(HttpStatus.OK).body(addressList);
	}

}
