package com.lti.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class UsernameUsedException extends Exception {
	private String username;

	/**
	 * creates a new exception with set information
	 * 
	 * @param message  the message to be written
	 * @param username the username that is in use
	 */
	public UsernameUsedException(String message, String username) {
		super(message);
		this.username = username;
	}

	/**
	 * returns the username
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
}
