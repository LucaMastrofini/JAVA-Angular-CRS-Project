/**
 * 
 */
package com.lti.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.lti.configuration.JDBCConfiguration;
import com.lti.constants.SQLConstants;
import com.lti.dto.Student;
import com.lti.dto.User;
import com.lti.mapper.StudentMapper;

/**
 * @author user101
 *
 */
@Repository
public class StudentDAO implements StudentDAOInterface {

	@Autowired
	private JDBCConfiguration jdbcTemplateObject;
	@Autowired
	private UserDAO userDAO;
	// Queries for Student table

	/**
	 * Creates a new student
	 * 
	 * @param student the student to add
	 * @return new ID if created successfully, else -1
	 */
	@Override
	public int createStudent(Student student) {
		int userID = userDAO.createUser(student.getUsername(), student.getPassword(), 3);
		jdbcTemplateObject.jdbcTemplate().update(SQLConstants.STUDENT_INSERT, userID, student.getName(),
				student.isRegistrationApproved());
		return userID;
	}

	/**
	 * find a student based on username
	 * 
	 * @param username the username of the User bean
	 * @return the Student associated with the username
	 */
	@Override
	public Student findStudent(String username) {
		User user = userDAO.findUser(username);
		return findStudent(user.getUserID());
	}

	/**
	 * find a student based on ID
	 * 
	 * @param studentID the id of the student to find
	 * @return the student associated with the id
	 */
	@Override
	public Student findStudent(int studentID) {
		try {
			return jdbcTemplateObject.jdbcTemplate().queryForObject(SQLConstants.STUDENT_SELECT, new StudentMapper(),
					studentID);
		} catch (DataAccessException e) {
			return null;
		}
	}

	/**
	 * updates information of a student
	 * 
	 * @param student student with updated information associated with studentID
	 * @return if update occurred
	 */
	@Override
	public boolean updateStudent(Student student) {
		jdbcTemplateObject.jdbcTemplate().update(SQLConstants.STUDENT_UPDATE, student.getName(), student.isRegistrationApproved(),
				student.getStudentID());
		return true;
	}

	/**
	 * finds all unregistered students
	 * 
	 * @return list of all unregistered students
	 */
	@Override
	public List<Student> findUnregisteredStudents() {
		return jdbcTemplateObject.jdbcTemplate().query(SQLConstants.STUDENT_SELECT_UNREGISTERED, new StudentMapper(),
				false);
	}

}
