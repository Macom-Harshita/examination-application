package com.example.demo.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<com.example.demo.entity.UserEntity, Integer>{
	Optional<UserEntity> findByemail(String email);

    @Query("SELECT u.id id FROM UserEntity u WHERE u.email = :email")
    boolean existsByemail(String email);
}
