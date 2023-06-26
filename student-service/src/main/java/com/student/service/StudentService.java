package com.student.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.student.entity.StudentEntity;
import com.student.feignclients.AddressFeignClient;
import com.student.repository.StudentRepository;
import com.student.request.CreateStudentRequest;
import com.student.response.AddressResponse;
import com.student.response.StudentResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private WebClient webClient;

	@Autowired
	private AddressFeignClient addressFeignClient;
	
	
	@Autowired
	private CommonService commonService;

	public StudentResponse saveStudentData(CreateStudentRequest createStudentRequest) {

		StudentEntity studentEntity = new StudentEntity();
		BeanUtils.copyProperties(createStudentRequest, studentEntity);

		StudentEntity save = studentRepository.save(studentEntity);

		StudentResponse studentResponse = new StudentResponse(0, null, null, null);
		studentResponse.setFirstName(save.getFirstName());
		studentResponse.setLastName(save.getLastName());
		studentResponse.setEmail(save.getEmail());
		studentResponse.setStudentId(save.getStudentId());
		studentResponse.setId(save.getId());

		studentResponse.setAddressResponse(commonService.getAddressById(studentEntity.getId()));

		return studentResponse;

	}

	public StudentResponse getStudentData(int id) {

		StudentResponse studentResponse = new StudentResponse(id, null, null, null);

		Optional<StudentEntity> findById = studentRepository.findById(id);

		StudentEntity studentEntity = findById.get();
		BeanUtils.copyProperties(studentEntity, studentResponse);

		studentResponse.setAddressResponse(commonService.getAddressById(studentEntity.getId()));

		return studentResponse;

	}

//	@CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
//	public AddressResponse getAddressById(int addressId) {

//		ResponseEntity<AddressResponse> addressResponse = addressFeignClient.getaddress(addressId);

//		AddressResponse body = addressResponse.getBody();

//		return body;
//	}

//	public AddressResponse fallbackGetAddressById(int addressId,Throwable th) {

//		return new AddressResponse();
//	}

}
