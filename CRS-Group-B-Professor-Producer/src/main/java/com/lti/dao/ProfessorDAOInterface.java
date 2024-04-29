/**
 * 
 */
package com.lti.dao;

import com.lti.dto.Professor;

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
	public int createProfessor(Professor professor);

	/**
	 * views a professor given the id
	 * 
	 * @param profID the professor to view
	 * @return the professor object
	 */
	public Professor findProfessorByProfessorID(int professorID);

	/**
	 * views a professor given the username
	 * 
	 * @param username the username of the User
	 * @return the professor object associated with the username
	 */
	public Professor findProfessorByUsername(String username);
}
