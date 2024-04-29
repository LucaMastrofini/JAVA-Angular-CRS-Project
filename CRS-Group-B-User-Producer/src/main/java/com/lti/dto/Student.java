package com.lti.dto;

import java.io.Serializable;
import java.util.*;

import org.springframework.stereotype.Component;

/**
 * 
 * Student: Id, courses, alternateCourses, gradeCourses, username, password
 * 
 * @author Nikhil, Luca
 *
 */
@Component
public class Student extends User implements  Serializable{
	
	private int studentID;
	private String name;
//	private ArrayList<Course> registeredCourses = new ArrayList<Course>();
//	private ArrayList<Course> alternateCourses = new ArrayList<Course>();
	//private ArrayList<Double> grades = new ArrayList<Double>(); // move elsewhere in the future
	private boolean registrationApproved = false;
	//private int coursesPaid = 0;

	/**
	 * creates a student with set id
	 * 
	 * @param studentID ID number of the student
	 * 
	 */
	public Student(int studentID) {
		this.studentID = studentID;

	}

	/**
	 * creates an empty student
	 */
	public Student() {
		super();
	}

	/**
	 * returns the name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the student name
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * returns the registered courses (unused)
	 * 
	 * @return the registeredCourses
	 */
//	public ArrayList<Course> getRegisteredCourses() {
//		return registeredCourses;
//	}

	/**
	 * sets the registered courses (unused)
	 * 
	 * @param registeredCourses the registeredCourses to set
	 */
//	public void setRegisteredCourses(ArrayList<Course> registeredCourses) {
//		this.registeredCourses = registeredCourses;
//	}

	/**
	 * returns the alternate courses (unused)
	 * 
	 * @return the alternateCourses
	 */
//	public ArrayList<Course> getAlternateCourses() {
//		return alternateCourses;
//	}

	/**
	 * sets the alternate courses (unused)
	 * 
	 * @param alternateCourses the alternateCourses to set
	 */
//	public void setAlternateCourses(ArrayList<Course> alternateCourses) {
//		this.alternateCourses = alternateCourses;
//	}

	/**
	 * returns the student id
	 * 
	 * @return the iD
	 */
	public int getStudentID() {
		return studentID;
	}

	/**
	 * returns the grades (unused)
	 * 
	 * @return the grades
	 */
//	public ArrayList<Double> getGrades() {
//		return grades;
//	}

	/**
	 * returns if the student is registered
	 * 
	 * @return the registered
	 */
	public boolean isRegistrationApproved() {
		return registrationApproved;
	}

	/**
	 * sets the student registered
	 * 
	 * @param registered the registered to set
	 */
	public void setRegistrationApproved(boolean registered) {
		this.registrationApproved = registered;
	}


	public void setStudentID(int studentID) {
		// TODO Auto-generated method stub
		this.studentID = studentID;
	}



	/**
	 * returns the courses paid (unused)
	 * 
	 * @return the coursesPaid
	 */
//	public int getCoursesPaid() {
//		return coursesPaid;
//	}
//
//	/**
//	 * sets the courses paid (unused)
//	 * 
//	 * @param coursesPaid the coursesPaid to set
//	 */
//	public void setCoursesPaid(int coursesPaid) {
//		this.coursesPaid = coursesPaid;
//	}

	/**
	 * creates a string to represent the object
	 */
//	@Override
//	public String toString() {
//		return "Student [studentID=" + studentID + ", registeredCourses=" + registeredCourses + ", alternateCourses="
//				+ alternateCourses + ", grades=" + grades + "]";
//	}

}
