/**
 * 
 */
package com.lti.dao;

import com.lti.bean.Professor;

/**
 * @author user101
 *
 */
public interface ProfessorDAOInterface {

	/**
	 * creates a new professor
	 * 
	 * @param prof new professor to add
	 * @return new ID if created successfully, else -1
	 */
	public int createProfessor(Professor prof);

	/**
	 * views a professor given the id
	 * 
	 * @param profID the professor to view
	 * @return the professor object
	 */
	public Professor viewProfessor(int profID);

	/**
	 * views a professor given the username
	 * 
	 * @param username the username of the User
	 * @return the professor object associated with the username
	 */
	public Professor viewProfessor(String username);
}
