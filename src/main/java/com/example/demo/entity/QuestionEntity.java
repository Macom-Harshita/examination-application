package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "questions")
public class QuestionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String quesText;
	private int marks;
	@ManyToOne
	@JoinColumn(name = "exam_id")
	private ExamEntity exam;
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	private List<OptionEntity> options;
	private String answer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setOptions(List<OptionEntity> options) {
		this.options = options;
	}
	public String getQuesText() {
		return quesText;
	}
	public void setQuesText(String quesText) {
		this.quesText = quesText;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public ExamEntity getExam() {
		return exam;
	}
	public void setExam(ExamEntity exam) {
		this.exam = exam;
	}
	public List<OptionEntity> getOptions() {
		return options;
	}
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
