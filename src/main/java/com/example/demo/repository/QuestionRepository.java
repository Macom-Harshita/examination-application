package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ExamEntity;
import com.example.demo.entity.QuestionEntity;


@Repository
public interface QuestionRepository extends CrudRepository<com.example.demo.entity.QuestionEntity, Integer>{
	
	@Query("""
	        SELECT e.id
	        FROM ExamEntity e
	        WHERE e.createdBy.email = :username
	          AND e.title = :exam
	    """)
	public int getExamId(@Param("exam") String exam,@Param("username") String username);

	public List<QuestionEntity> findAllByexam(ExamEntity exam);
}
