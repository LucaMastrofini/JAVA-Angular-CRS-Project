package com.lti.dto;

public class Grade {
	
	private double grade;
	private char gradeLetter;
	private Student student;
	private Course course;
	
	public Grade(double grade, Student student, Course course) {
		this.grade = grade;
		this.student = student;
		this.setCourse(course);
		setGradeLetter();
	}
	
	public void setGrade(double newGrade) {
		grade = newGrade;
		setGradeLetter();
	}
	
	public double getGrade() {
		return grade;
	}
	
	public char getGradeLetter() {
		return gradeLetter;
	}
	
	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	public String toString() {
		return grade + " " + gradeLetter;
	}
	
	private void setGradeLetter() {
		if (grade >= 90) {
			gradeLetter = 'A';
		}
		else if (grade >= 80) {
			gradeLetter = 'B';
		}
		else if (grade >= 70) {
			gradeLetter = 'C';
		}
		else if (grade >= 60) {
			gradeLetter = 'D';
		}
		else if (grade < 0) {
			gradeLetter = '-';
		}
		else
			gradeLetter = 'F';
	}

}
