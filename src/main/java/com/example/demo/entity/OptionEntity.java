package com.example.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "options")
public class OptionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@ManyToOne
	@JoinColumn(name = "question_id")
	QuestionEntity question;
	String text;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public QuestionEntity getQuestion() {
		return question;
	}
	public void setQuestion(QuestionEntity quesId) {
		this.question = quesId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
