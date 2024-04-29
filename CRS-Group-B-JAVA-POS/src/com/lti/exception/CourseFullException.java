package com.lti.exception;

public class CourseFullException extends Exception {

	private int courseID;

	/**
	 * When a course is full of 10 students
	 * 
	 * @param message  the message to be written
	 * @param courseID the course id that is full
	 */
	public CourseFullException(String message, int courseID) {
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
