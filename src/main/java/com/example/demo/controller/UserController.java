package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

@Controller
@Component
public class UserController {
	@Autowired
	UserRepository repo;
	
	@GetMapping("userbyemail/{email}")
	@ResponseBody
	private List<UserEntity> GetUserByEmail(@PathVariable("email") String email) {
		return (List<UserEntity>) repo.findByemail(email);
	}
}
