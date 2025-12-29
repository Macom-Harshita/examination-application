package com.example.demo.dto;

import java.util.List;



public class QuestionRequest {
	private String quesText;
	private int marks;
	private String exam; //convert this string into the id to store
	private List<String> options;
	private String answer;
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
	public String getExam() {
		return exam;
	}
	public void setExam(String exam) {
		this.exam = exam;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
