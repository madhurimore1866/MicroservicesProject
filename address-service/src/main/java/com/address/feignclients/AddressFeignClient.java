package com.address.feignclients;




import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.address.response.AddressResponse;

@FeignClient(url = "${address.service.url}", value = "address-feign-clent", path = "/api/address")
public interface AddressFeignClient {

	@GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddressResponse> getaddress(@PathVariable int id);

}
