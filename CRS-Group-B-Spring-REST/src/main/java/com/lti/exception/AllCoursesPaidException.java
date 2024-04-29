package com.lti.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class AllCoursesPaidException extends Exception {

	private int studentID;

	/**
	 * creates a new exception with set information
	 * 
	 * @param message   the message to be written
	 * @param studentID the student id associated
	 */
	public AllCoursesPaidException(String message, int studentID) {
		super(message);
		this.studentID = studentID;
	}

	/**
	 * returns the student id
	 * 
	 * @return returns student id
	 */
	public int getStudentID() {
		return studentID;
	}
}
