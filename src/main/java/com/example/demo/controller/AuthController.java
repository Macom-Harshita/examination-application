package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private final AuthService authService; 
	
	public AuthController(AuthService authService){
		this.authService = authService;
	}
	
	@PostMapping("/register")
	public String Register(@RequestBody RegisterRequest request){
		authService.register(request);
		return "User Registered Sucessfully";
	}
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request){
		String token = authService.login(request);
		return token;
	}
}
