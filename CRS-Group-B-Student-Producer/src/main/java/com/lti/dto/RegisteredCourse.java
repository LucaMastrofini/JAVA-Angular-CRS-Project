package com.lti.dto;

import java.io.Serializable;

public class RegisteredCourse implements Serializable {

	private Student student;
	private Course course;
	private boolean feePaid;
	private double grade;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student studentID) {
		this.student = studentID;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public boolean isFeePaid() {
		return feePaid;
	}

	public void setFeePaid(boolean feePaid) {
		this.feePaid = feePaid;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

}
