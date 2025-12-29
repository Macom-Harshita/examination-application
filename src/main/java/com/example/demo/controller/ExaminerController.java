package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ExamRequest;
import com.example.demo.dto.ExamsResponse;
import com.example.demo.dto.QuestionRequest;
import com.example.demo.dto.QuestionResponse;
import com.example.demo.security.SecurityUtils;
import com.example.demo.service.ExaminerService;

@RestController
@RequestMapping("/examiner")
public class ExaminerController {
	
	public ExaminerService examinerService;
	
	public ExaminerController(ExaminerService examinerService) {
		this.examinerService = examinerService;
	}
	
	@PostMapping("/createExam")
	public String CreateExam(@RequestBody ExamRequest request) {
		examinerService.createExam(request);
		return "Exam created Sucessfully";
	}
	
	@PostMapping("/addQuestion")
	public String AddQuestion(@RequestBody QuestionRequest question) {
		examinerService.addQuestion(question);
		return "Question added successfullt";
	}
	
	@GetMapping("/getExams")
	public List<ExamsResponse> getExams() {
		return examinerService.getExamsOf(SecurityUtils.getCurrentUser());
	}
	
	@GetMapping("/getExam/{id}")
	public ExamsResponse getExam(@PathVariable int id) {
		return examinerService.getExamById(id);
	}
	
	@GetMapping("/getQuestions/{exam_id}")
	public List<QuestionResponse> getQuestions(@PathVariable int exam_id){
		return examinerService.getQuestionsOfExam(exam_id);
	}
	
//	@PutMapping("/updateExam")
//	public String UpdateExam(ExamRequest exam) {
//		examinerService.UpdateExam(exam);
//		return "Exam Updated";
//	}
	//@PostMapping("/allowCandidates")
	//@PostMapping("update exam")
}

