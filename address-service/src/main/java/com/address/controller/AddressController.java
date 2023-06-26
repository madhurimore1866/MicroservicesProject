package com.address.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.address.request.CreateAddressRequest;
import com.address.response.AddressResponse;
import com.address.service.AddressService;

@RestController
@RequestMapping("/api/address/")
public class AddressController {

	@Autowired
	AddressService addressService;

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddressResponse> createAddress(@RequestBody CreateAddressRequest createAddressRequest) {

		AddressResponse createAddress = addressService.createAddress(createAddressRequest);
		return new ResponseEntity<AddressResponse>(createAddress, HttpStatus.CREATED);
	}

	@GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddressResponse> getaddress(@PathVariable int id) {

		AddressResponse address = addressService.getAddress(id);

		return new ResponseEntity<AddressResponse>(address, HttpStatus.ACCEPTED);

	}

}
