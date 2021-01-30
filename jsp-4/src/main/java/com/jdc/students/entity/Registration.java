package com.jdc.students.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Registration implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate applyDate;
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Student student;
//	@ManyToMany(cascade = {
//	        CascadeType.PERSIST,
//	        CascadeType.MERGE
//	    })
//	    @JoinTable(name = "reg_course",
//	        joinColumns = @JoinColumn(name = "reg_id"),
//	        inverseJoinColumns = @JoinColumn(name = "course_id")
//	    )
//	private List<Course> course;
	
	@ManyToOne
	private Course course;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(LocalDate applyDate) {
		this.applyDate = applyDate;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
//	public void setCourse(List<Course> course) {
//		this.course = course;
//	}
//	public List<Course> getCourse() {
//		return course;
//	}

	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
}
