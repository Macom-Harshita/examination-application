package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	String email;
	String password;
	@Enumerated(EnumType.STRING)
	roles role;
	LocalDateTime createdAt;
	@OneToMany(mappedBy = "createdBy")
	List<ExamEntity> examsCreated;
	@ManyToMany
	@JoinTable(
		    name = "exam_candidates",
		    joinColumns = @JoinColumn(name = "user_id"),
		    inverseJoinColumns = @JoinColumn(name = "exam_id")
		)
	List<ExamEntity> examsGiven;
	
	public List<ExamEntity> getExamsGiven() {
		return examsGiven;
	}
	public void setExamsGiven(List<ExamEntity> examsGiven) {
		this.examsGiven = examsGiven;
	}
	public List<ExamEntity> getExamsCreated() {
		return examsCreated;
	}
	public void setExamsCreated(List<ExamEntity> exams) {
		this.examsCreated = exams;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public roles getRole() {
		return role;
	}
	public void setRole(roles role) {
		this.role = role;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
