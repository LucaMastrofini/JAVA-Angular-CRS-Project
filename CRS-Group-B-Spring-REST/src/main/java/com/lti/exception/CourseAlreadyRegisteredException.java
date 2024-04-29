package com.lti.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CourseAlreadyRegisteredException extends Exception {

	private int courseID;

	/**
	 * creates a new exception with set information
	 * 
	 * @param message  the message to be written
	 * @param courseID the course id that is registered for
	 */
	public CourseAlreadyRegisteredException(String message, int courseID) {
		super(message);
		this.courseID = courseID;
	}

	/**
	 * returns the course id
	 * 
	 * @return the course id
	 */
	public int getCourseID() {
		return courseID;
	}
}
