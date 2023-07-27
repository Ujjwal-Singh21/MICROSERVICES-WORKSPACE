package com.seleniumexpress.addressservice.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seleniumexpress.addressservice.dao.AddressRepo;
import com.seleniumexpress.addressservice.entity.Address;
import com.seleniumexpress.addressservice.response.AddressResponse;

@Service
public class AddressService {

	@Autowired
	private AddressRepo repo;

	@Autowired
	private ModelMapper modelMapper;

	// GET BY ID
	public AddressResponse getAddressByEmployeeId(int employeeId) {
		
		System.out.println("Finding employee with employee id " + employeeId);
		
		Address address = repo.getAddressByEmployeeId(employeeId);

		AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);

		return addressResponse;
	}

	
	// GET ALL
	public List<AddressResponse> getAllAddress() {
		
		System.out.println("Finding all employees");
		
		List<Address> addressList = repo.findAll();
		
		AddressResponse[] addressListArr =  modelMapper.map(addressList, AddressResponse[].class);
		
		List<AddressResponse> addressResponseList = Arrays.asList(addressListArr);
		
		return addressResponseList;
	}

}
