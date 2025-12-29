package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ExamEntity;


@Repository
public interface ExamRepository extends CrudRepository<com.example.demo.entity.ExamEntity, Integer>{
	@Query("""
	        SELECT e
	        FROM ExamEntity e
	        WHERE e.createdBy.id = :userId
	    """)
	List<ExamEntity> findAllByCreatorId(@Param("userId") int userId);
}
