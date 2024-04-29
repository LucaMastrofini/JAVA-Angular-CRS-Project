package com.lti.exception;

public class CourseNotFoundException extends Exception {
	private int courseID;

	/**
	 * creates a new exception with set information
	 * 
	 * @param message  the message to be written
	 * @param courseID the course id that was not found
	 */
	public CourseNotFoundException(String message, int courseID) {
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
