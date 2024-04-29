package com.lti.dto;

public class Grade {
	
	private double grade;
	private char gradeLetter;
	
	public Grade(double grade) {
		this.grade = grade;
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
		else
			gradeLetter = 'F';
	}

}
