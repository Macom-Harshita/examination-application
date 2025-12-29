package com.example.demo.dto;

import java.util.List;

public class QuestionResponse {
	private int id;
	private String quesText;
	private int marks;
	private List<String> options;
	private String answer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
