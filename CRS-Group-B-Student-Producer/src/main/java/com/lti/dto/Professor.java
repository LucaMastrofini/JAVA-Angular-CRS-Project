package com.lti.dto;

import java.io.Serializable;
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
public class Professor extends User implements  Serializable{
	private int professorID;
	private String name;

	/**
	 * creates a professor with the set information
	 * 
	 * @param profID the id of the professor
	 * @param name   the professor's name
	 */
	public Professor(int profID, String name) {
		super();
		this.name = name;
		this.professorID = profID;
	}

	/**
	 * creates a professor with a set name
	 * 
	 * @param name the professor's name
	 */
	public Professor(String name) {
		super();
		this.name = name;
	}

	/**
	 * creates an empty professor
	 */
	public Professor() {
		super();
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

	public int getProfessorID() {
		return professorID;
	}

	public void setProfessorID(int professorID) {
		this.professorID = professorID;
	}

	public String toString() {
		return this.name;
	}
}
