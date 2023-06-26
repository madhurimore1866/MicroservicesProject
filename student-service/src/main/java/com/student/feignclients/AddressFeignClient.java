package com.student.feignclients;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.student.response.AddressResponse;

@FeignClient(value = "api-gateway-service")
public interface AddressFeignClient {

	@GetMapping(value = "/address-service/api/address/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddressResponse> getaddress(@PathVariable int id);

}
