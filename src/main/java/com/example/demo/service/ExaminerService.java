package com.example.demo.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.dto.QuestionResponse;
import com.example.demo.dto.ExamRequest;
import com.example.demo.dto.ExamsResponse;
import com.example.demo.dto.QuestionRequest;
import com.example.demo.entity.ExamEntity;
import com.example.demo.entity.OptionEntity;
import com.example.demo.entity.QuestionEntity;
import com.example.demo.entity.Status;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.ExamRepository;
import com.example.demo.repository.OptionRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.SecurityUtils;


@Service
public class ExaminerService {
	
	UserRepository userRepo;
	ExamRepository examRepo;
	QuestionRepository quesRepo;
	OptionRepository optRepo;
	
	public ExaminerService(UserRepository userRepo, ExamRepository examRepo, QuestionRepository quesRepo, OptionRepository optRepo) {
		this.userRepo = userRepo;
		this.examRepo = examRepo;
		this.quesRepo = quesRepo;
		this.optRepo = optRepo;
	}
	
	public void createExam(ExamRequest request) {
		if (request.getEndTime().isBefore(request.getStartTime())) {
			throw new RuntimeException("Exam ends before it starts??? wdym huh");
		}
		if (request.getPassScore() > request.getTotalMarks()) {
			throw new RuntimeException("How tf you expect me to do that? Pass marks > total marks");
		}
		Duration duration = Duration.between(request.getStartTime(), request.getEndTime());
		ExamEntity exam = new ExamEntity();
		LocalDateTime now = LocalDateTime.now();
		UserEntity user = userRepo
	            .findByemail(SecurityUtils.getCurrentUser())
				.orElseThrow(() -> new RuntimeException("user not found"));
		exam.setTitle(request.getTitle());
		exam.setDescription(request.getDescription());
		exam.setDurationInMinutes(duration.toMinutes());
		if (now.isBefore(request.getStartTime())) {
			exam.setStatus(Status.UPCOMING);
		}
		else if (now.isAfter(request.getEndTime())) {
			exam.setStatus(Status.ENDED);
		}
		else {
			exam.setStatus(Status.LIVE);
		}
		exam.setCreatedBy(user);
		exam.setCreatedAt(now);
		exam.setStartTime(request.getStartTime());
		exam.setEndTime(request.getEndTime());
		exam.setTotalMarks(request.getTotalMarks());
		exam.setPassScore(request.getPassScore());
		examRepo.save(exam);
	}
	
	public void addQuestion(QuestionRequest question) {
		int examId = (quesRepo.getExamId(question.getExam(), SecurityUtils.getCurrentUser()));
		ExamEntity exam = examRepo
				.findById(examId)
				.orElseThrow(() -> new RuntimeException("No exam with the given id"));
		if(exam.getStatus() != Status.UPCOMING) {
			throw new RuntimeException("The exam has either ended or is ongoing. Can't make changes.");
		}
		QuestionEntity ques = new QuestionEntity();
		ques.setQuesText(question.getQuesText());
		ques.setMarks(question.getMarks());
		ques.setExam(exam);
		ques.setAnswer(question.getAnswer()); //need to handle when the answer is not in the option
		QuestionEntity savedQues = quesRepo.save(ques);
		for(String option: question.getOptions()) {
			OptionEntity o = new OptionEntity();
			o.setQuestion(savedQues);
			o.setText(option);
			optRepo.save(o);
		}	
	}
	
	public List<ExamsResponse> getExamsOf(String UserEmail) {
		UserEntity user = userRepo
	            .findByemail(SecurityUtils.getCurrentUser())
				.orElseThrow(() -> new RuntimeException("user not found"));
		int userId = user.getId();
		List<ExamEntity> exams = examRepo.findAllByCreatorId(userId);
		List<ExamsResponse> response = new ArrayList<>();
		for(ExamEntity exam: exams) {
			ExamsResponse e = new ExamsResponse(); 
			e.setId(exam.getId());
			e.setTitle(exam.getTitle());
			e.setDescription(exam.getDescription());
			e.setDurationInMinutes(exam.getDurationInMinutes());
			e.setStatus(exam.getStatus().toString());
			e.setCreatedAt(exam.getCreatedAt().toString());
			if(exam.getUpdatedAt() != null) {
				e.setUpdatedAt(exam.getUpdatedAt().toString());
			}
			e.setStartTime(exam.getStartTime().toString());
			e.setEndTime(exam.getEndTime().toString());
			e.setTotalMarks(exam.getTotalMarks());
			e.setPassScore(exam.getPassScore());
			response.add(e);
		}
		return response;
	}
	
	public ExamsResponse getExamById(int id) {
		ExamEntity exam = examRepo
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Can't find exam with the id"));
		ExamsResponse e = new ExamsResponse();
		e.setId(exam.getId());
		e.setTitle(exam.getTitle());
		e.setDescription(exam.getDescription());
		e.setDurationInMinutes(exam.getDurationInMinutes());
		e.setStatus(exam.getStatus().toString());
		e.setCreatedAt(exam.getCreatedAt().toString());
		if(exam.getUpdatedAt() != null) {
			e.setUpdatedAt(exam.getUpdatedAt().toString());
		}
		e.setStartTime(exam.getStartTime().toString());
		e.setEndTime(exam.getEndTime().toString());
		e.setTotalMarks(exam.getTotalMarks());
		e.setPassScore(exam.getPassScore());
		return e;
	}
	
	public List<QuestionResponse> getQuestionsOfExam(int id) {
		ExamEntity exam = examRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("No Exam found with the id"));
		List<QuestionEntity> allQues = quesRepo.findAllByexam(exam);
		List<QuestionResponse> response = new ArrayList<>();
		for(QuestionEntity question : allQues) {
			QuestionResponse q = new QuestionResponse();
			q.setId(question.getId());
			q.setQuesText(question.getQuesText());
			q.setMarks(question.getMarks());
			List<String> options = new ArrayList<>();
			for(OptionEntity option: question.getOptions()) {
				options.add(option.getText());
			}
			q.setOptions(options);
			q.setAnswer(question.getAnswer());
			response.add(q);
			}
		return response;
		
	}
	
}

