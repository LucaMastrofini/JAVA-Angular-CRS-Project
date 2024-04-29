package com.lti.bean;

import java.util.*;

import org.springframework.stereotype.Component;

/**
 * 
 * Professor: Id, courses, username, password
 * 
 * @author Nikhil, Luca
 *
 */
@Component
public class Professor extends User {
	private int profID;
	private String name;
	private ArrayList<Course> courses;

	/**
	 * creates a professor with the set information
	 * 
	 * @param profID the id of the professor
	 * @param name   the professor's name
	 */
	public Professor(int profID, String name) {
		super();
		this.name = name;
		this.profID = profID;
		courses = new ArrayList<Course>();
	}

	/**
	 * creates a professor with a set name
	 * 
	 * @param name the professor's name
	 */
	public Professor(String name) {
		super();
		this.name = name;
		courses = new ArrayList<Course>();
	}

	/**
	 * creates an empty professor
	 */
	public Professor() {
		super();
	}

	/**
	 * returns the courses taught (unused)
	 * 
	 * @return the courses
	 */
	public ArrayList<Course> getCourses() {
		return courses;
	}

	/**
	 * sets the courses taught (unused)
	 * 
	 * @param courses the courses to set
	 */
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	/**
	 * returns the professor id
	 * 
	 * @return the profID
	 */
	public int getProfID() {
		return profID;
	}

	/**
	 * returns the name of the professor
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name of the professor
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
