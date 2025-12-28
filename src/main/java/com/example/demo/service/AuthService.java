package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.roles;
import com.example.demo.repository.UserRepository;

@Service
public class AuthService {

    private final JwtService jwtService;
	private final UserRepository repo;
	private final PasswordEncoder passenc;
	
	public AuthService(UserRepository repo, PasswordEncoder passenc, JwtService jwtService){
		this.repo = repo;
		this.passenc = passenc;
		this.jwtService = jwtService;
	}
	
	public void register(RegisterRequest regreq) {
		if(repo.existsByemail(regreq.getEmail())) {
			throw new RuntimeException("Email already registered");
		}
		UserEntity user = new UserEntity();
		user.setName(regreq.getName());
		user.setEmail(regreq.getEmail());
		user.setPassword(passenc.encode(regreq.getPassword()));
		user.setRole(roles.ADMIN);
		repo.save(user);
	}
	
	public String login(LoginRequest logreq) {
		UserEntity user = repo
	            .findByemail(logreq.getEmail())
				.orElseThrow(() -> new RuntimeException("user not found"));
		if(!passenc.matches(logreq.getPassword(), user.getPassword())){
			throw new RuntimeException("Invalid Login Credentials");
		}
		return jwtService.generateToken(user);
	}
}
