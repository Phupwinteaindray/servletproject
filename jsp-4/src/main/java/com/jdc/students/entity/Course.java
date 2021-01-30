package com.jdc.students.entity;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.NamedQuery;




@Entity
@NamedQuery(name = "Course.getAll", query = "select c from Course c")
public class Course implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Level level;
	private int fees;
	private int hours;
	private String description;
//	@ManyToMany(mappedBy = "course")
//	private List<Registration> registration;
	public enum Level{
		FIRST,SECOND,THIRD,FOURTH,FIFTH,FINAL
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
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
//	public void setRegistration(List<Registration> registration) {
//		this.registration = registration;
//	}
//	public List<Registration> getRegistration() {
//		return registration;
//	}
	
}
