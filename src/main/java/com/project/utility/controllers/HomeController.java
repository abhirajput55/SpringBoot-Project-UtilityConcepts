package com.project.utility.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.utility.dto.UserDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	
	@PostMapping("/validatePhoneNum")
	public ResponseEntity<String> validatePhoneNumber(@RequestBody @Valid UserDto user) {
		
		return ResponseEntity.ok(user.getUsername());
	}

}
