package com.lti.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.lti.configuration.JDBCConfiguration;
import com.lti.constants.SQLConstants;
import com.lti.dto.Professor;
import com.lti.dto.User;
import com.lti.mapper.ProfessorMapper;
import com.lti.mapper.StudentMapper;

@Repository
public class ProfessorDAO implements ProfessorDAOInterface {

	@Autowired
	private JDBCConfiguration jdbcTemplateObject;

	@Autowired
	private UserDAO userDAO;

	/**
	 * creates a new professor
	 * 
	 * @param prof new professor to add
	 * @return new ID if created successfully, else -1
	 */
	@Override
	public int createProfessor(Professor professor) {
		int userID = userDAO.createUser(professor.getUsername(), professor.getPassword(), 2);
		jdbcTemplateObject.jdbcTemplate().update(SQLConstants.PROFESSOR_INSERT, userID, professor.getName());
		return userID;
	}

	public List<Professor> viewProfessors() {
		return jdbcTemplateObject.jdbcTemplate().query(SQLConstants.PROFESSOR_SELECT_ALL, new ProfessorMapper());
	}

	/**
	 * views a professor given the id
	 * 
	 * @param profID the professor to view
	 * @return the professor object
	 */
	@Override
	public Professor findProfessorByProfessorID(int professorID) {
		try {
			return jdbcTemplateObject.jdbcTemplate().queryForObject(SQLConstants.PROFESSOR_SELECT,
					new ProfessorMapper(), professorID);
		} catch (DataAccessException e) {
			return null;
		}
	}

	/**
	 * views a professor given the username
	 * 
	 * @param username the username of the User
	 * @return the professor object associated with the username
	 */
	@Override
	public Professor findProfessorByUsername(String username) {
		User user = userDAO.findUser(username);
		return findProfessorByProfessorID(user.getUserID());
	}

}
