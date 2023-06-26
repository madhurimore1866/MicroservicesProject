package com.student.controller;

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

import com.student.request.CreateStudentRequest;
import com.student.response.StudentResponse;
import com.student.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> createStudentData(@RequestBody CreateStudentRequest createStudentRequest) {

		StudentResponse saveStudentData = studentService.saveStudentData(createStudentRequest);

		return new ResponseEntity<StudentResponse>(saveStudentData, HttpStatus.CREATED);

	}

	@GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> getStudent(@PathVariable int id) {

		StudentResponse studentData = studentService.getStudentData(id);

		return new ResponseEntity<StudentResponse>(studentData, HttpStatus.ACCEPTED);

	}

}
