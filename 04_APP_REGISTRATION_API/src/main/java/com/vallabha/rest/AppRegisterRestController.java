package com.vallabha.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vallabha.bindings.CitizenData;
import com.vallabha.service.CitizenAppService;

@RestController
public class AppRegisterRestController {

	@Autowired
	private CitizenAppService citizenAppService;

	@PostMapping("/register")
	public ResponseEntity<String> createApp(@RequestBody CitizenData data) {
		Integer status = citizenAppService.createApp(data);
		if (status != 0) {
			return new ResponseEntity<>("App Created succesfully with app id :" + status, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("App not created..", HttpStatus.BAD_REQUEST);
		}
	}
}
