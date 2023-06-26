package com.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.student.feignclients.AddressFeignClient;
import com.student.response.AddressResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
public class CommonService {
	
	long count=1;

	@Autowired
	private AddressFeignClient addressFeignClient;

	@CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
	public AddressResponse getAddressById(int addressId) {
		
		System.out.println("count = "+count);
		
		count++;

		ResponseEntity<AddressResponse> addressResponse = addressFeignClient.getaddress(addressId);

		AddressResponse body = addressResponse.getBody();

		return body;
	}

	public AddressResponse fallbackGetAddressById(int addressId, Throwable th) {

		System.out.println("Error = "+th);
		
		return new AddressResponse(0, null, null);
	}
}
