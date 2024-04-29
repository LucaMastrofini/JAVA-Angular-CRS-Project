package com.lti.dto;

import java.io.Serializable;
import java.util.*;

import org.springframework.stereotype.Component;

/**
 * Course: Id, name, professor, department, description, prerequisites
 * 
 * @author Nikhil, Luca
 *
 */
@Component
public class Course implements  Serializable{
	private int courseID;
	private String name;
	//private ArrayList<Student> students;
	private Professor professor;
	private String department;
	private String description;
	private int prereqCourseID;
	//private Map<Student, Double> studentGrades;

	/**
	 * creates a course with set id
	 * 
	 * @param courseID the id of the course
	 */
	public Course(int courseID) {
		this.courseID = courseID;
	}

	/**
	 * creates a course with all information set
	 * 
	 * @param courseID       the id of the course
	 * @param name           the name of the course
	 * @param department     the department this course belongs to
	 * @param prereqCourseID the prerequisite course id
	 */
	public Course(int courseID, String name, String department, int prereqCourseID) {
		this.courseID = courseID;
		this.name = name;
		this.department = department;
		this.prereqCourseID = prereqCourseID;

	}

	/**
	 * creates an empty course
	 */
	public Course() {
		super();
	}

	/**
	 * returns the course id
	 * 
	 * @return the courseID
	 */
	public int getCourseID() {
		return courseID;
	}

	/**
	 * sets the course id
	 * 
	 * @param courseID the courseID to set
	 */
	public void setCourseID(int courseID) {
		this.courseID = courseID;
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
	 * sets the name
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * returns the students (unused)
	 * 
	 * @return the students
	 */
	/*
	 * public ArrayList<Student> getStudents() { return students; }
	 */

	/**
	 * sets the students (unused)
	 * 
	 * @param students the students to set
	 */
	/*
	 * public void setStudents(ArrayList<Student> students) { this.students =
	 * students; }
	 */
	/**
	 * returns the professor
	 * 
	 * @return the professor
	 */
	public Professor getProfessor() {
		return professor;
	}

	/**
	 * sets the professor to teach
	 * 
	 * @param prof the prof to set
	 */
	public void setProfessor(Professor prof) {
		this.professor = prof;
	}

	/**
	 * returns the department
	 * 
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * sets the department
	 * 
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * returns the prerequisite course id
	 * 
	 * @return the prereqCourseID
	 */
	public int getPrereqCourseID() {
		return prereqCourseID;
	}

	/**
	 * sets the prerequisite course id
	 * 
	 * @param prereqCourseID the prereqCourseID to set
	 */
	public void setPrereqCourseID(int prereqCourseID) {
		this.prereqCourseID = prereqCourseID;
	}

	/**
	 * creates a string representation of this object
	 */
	@Override
	public String toString() {
		return "Course [courseID=" + courseID + ", students=" + ", prof=" + professor + ", department="
				+ department + ", prereqCourseID=" + prereqCourseID + "]";
	}

//	public Map<Student, Double> getStudentGrades() {
//		return studentGrades;
//	}

//	public void setStudentGrades(Map<Student, Double> studentGrades) {
//		this.studentGrades = studentGrades;
//	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
