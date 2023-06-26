package com.address.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.address.entity.AddressEntity;
import com.address.repository.AddressRepository;
import com.address.request.CreateAddressRequest;
import com.address.response.AddressResponse;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;

	public AddressResponse createAddress(CreateAddressRequest createAddressRequest) {
		AddressEntity addressEntity = new AddressEntity();
		BeanUtils.copyProperties(createAddressRequest, addressEntity);

		AddressEntity save = addressRepository.save(addressEntity);

		AddressResponse addressResponse = new AddressResponse();
		addressResponse.setAddressId(save.getId());
		addressResponse.setStreet(save.getStreet());
		addressResponse.setCity(save.getCity());

		return addressResponse;

	}
	
	public AddressResponse getAddress(int id) {

		System.out.print("requestId :- "+id);
		
		AddressResponse addressResponse = new AddressResponse();

		Optional<AddressEntity> findById = addressRepository.findById(id);

		AddressEntity addressEntity = findById.get();
		BeanUtils.copyProperties(addressEntity, addressResponse);
		addressResponse.setAddressId(addressEntity.getId());

		return addressResponse;

	}

}

