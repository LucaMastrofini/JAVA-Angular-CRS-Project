package com.lti.exception;

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
