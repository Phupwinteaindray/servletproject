package com.jdc.students.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import lombok.Data;

@Data
@Entity
@NamedQuery(name = "Account.findbyloginid", query = "select a from Account a where a.logInId=:lgid")
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true,nullable = false)
	private String logInId;
	private String password;
	private String name;
	@Enumerated(EnumType.STRING)
	private Role role;
	public enum Role{
		OFFICE,STUDENT,TEACHER;	
	}
	public String getLogInId() {
		return logInId;
	}
	public void setLogInId(String logInId) {
		this.logInId = logInId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
