package com.lti.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class AllStudentRegisteredException extends Exception {

	/**
	 * creates a new exception with set information
	 * 
	 * @param message the message to be written
	 */
	public AllStudentRegisteredException(String message) {
		super(message);
	}

}
