package com.neonci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neonci.repository.TestUserRepository;

@RestController
public class GrallController {

	@Autowired
	private TestUserRepository testUserRepository;

	@GetMapping("/getRecords")
	public Object getRecords() {
		return testUserRepository.findAll();
	}
}
