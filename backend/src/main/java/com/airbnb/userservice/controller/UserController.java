package com.airbnb.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airbnb.userservice.dto.LoginRequestDTO;
import com.airbnb.userservice.dto.LoginResponseDTO;
import com.airbnb.userservice.dto.UserDTO;
import com.airbnb.userservice.dto.UserRequestDTO;
import com.airbnb.userservice.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRequestDTO request) {

		UserDTO response = userService.registerUser(request);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO request) {

	    LoginResponseDTO response = userService.loginUser(request);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/profile")
	public ResponseEntity<UserDTO> getProfile(HttpServletRequest request) {

	    String email = (String) request.getAttribute("email");

	    UserDTO user = userService.getUserProfile(email);

	    return ResponseEntity.ok(user);
	}
	
}