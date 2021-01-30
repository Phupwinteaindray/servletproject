package com.jdc.students.exception;




public class StudentRegistrationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public StudentRegistrationException(String msg) {
		super(msg);
	}
}
