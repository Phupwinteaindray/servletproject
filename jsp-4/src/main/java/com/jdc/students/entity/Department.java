package com.jdc.students.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
@Entity
@NamedQuery(name = "Department.findByName", query = "select c from Department c where c.deptName = :name")
@NamedQuery(name = "Department.getAll", query = "select c from Department c")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String officeMail;
	private String deptName;
	private String deptPhone;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOfficeMail() {
		return officeMail;
	}
	public void setOfficeMail(String officeMail) {
		this.officeMail = officeMail;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public void setDeptPhone(String deptPhone) {
		this.deptPhone = deptPhone;
	}
	public String getDeptPhone() {
		return deptPhone;
	}
	
}
