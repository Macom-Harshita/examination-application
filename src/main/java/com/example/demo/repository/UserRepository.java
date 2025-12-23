package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.demo.entity.UserEntity;

@Component
public interface UserRepository extends CrudRepository<com.example.demo.entity.UserEntity, Integer>{
	public List<UserEntity> findByemail(String email);
}
