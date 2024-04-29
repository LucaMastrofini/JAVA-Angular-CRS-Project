/**
 * 
 */
package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lti.bean.Student;
import com.lti.bean.User;
import com.lti.constant.SQLConstants;
import com.lti.restcontroller.UserController;
import com.lti.utils.DBUtils;


/**
 * @author user101
 *
 */
public class StudentDAO implements StudentDAOInterface {
	Logger logger = LoggerFactory.getLogger(StudentDAO.class);
	private PreparedStatement stmt = null;

	/**
	 * Creates a new student
	 * 
	 * @param student the student to add
	 * @return new ID if created successfully, else -1
	 */
	@Override
	public int createStudent(Student student) {
		logger.info("createStudent in studentDAO");
		UserDAO userDAO = new UserDAO();
		int userID = userDAO.createNewUser(student.getUsername(), student.getPassword(), 3);

		try {
			Connection conn = DBUtils.getConnection();
			// Step 5 create and populate statement

			// String sql = "INSERT INTO Student VALUES(?,?)";
			stmt = conn.prepareStatement(SQLConstants.STUDENT_INSERT);
			stmt.setInt(1, userID);
			stmt.setString(2, student.getName());
			stmt.setBoolean(3, false);

			// Step 6 execute statement

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("New user userID: " + userID);
		return userID;
	}

	/**
	 * find a student based on ID
	 * 
	 * @param studentID the id of the student to find
	 * @return the student associated with the id
	 */
	public Student viewStudent(int studentID) {
		logger.info("viewStudent by ID in StudentDAO");
		Student student = null;
		try {
			Connection conn = DBUtils.getConnection();
			// Step 5 create and populate statement

			// String sql = "SELECT studentID, name FROM Student"; // TODO add where clause

			stmt = conn.prepareStatement(SQLConstants.STUDENT_SELECT);
			// Step 6 execute statement
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Retrieve by column name
				int tempUserID = rs.getInt("studentID");
				String tempName = rs.getString("name");
				boolean tempRegistered = rs.getBoolean("registrationApproved");
				if (tempUserID == studentID) {
					student = new Student(studentID);
					student.setName(tempName);
					student.setRegistered(tempRegistered);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("student: " + student);
		return student;
	}

	/**
	 * find a student based on username
	 * 
	 * @param username the username of the User bean
	 * @return the Student associated with the username
	 */
	@Override
	public Student viewStudent(String username) {
		logger.info("viewStudent by username in StudentDAO");
		UserDAO userDAO = new UserDAO();
		User us = userDAO.viewUser(username);
		int studentID = us.getUserID();
		return viewStudent(studentID);

	}

	/**
	 * updates information of a student
	 * 
	 * @param student student with updated information associated with studentID
	 * @return if update occurred
	 */
	public boolean updateStudent(Student student) {
		boolean changed = false;
		try {
			Connection conn = DBUtils.getConnection();

			// UPDATE Student SET name='?', registrationApproved='?' WHERE studentID='?'
			stmt = conn.prepareStatement(SQLConstants.STUDENT_UPDATE);
			stmt.setString(1, student.getName());
			stmt.setBoolean(2, student.isRegistered());
			stmt.setInt(3, student.getStudentID());
			stmt.executeUpdate();
			changed = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("student: " + student);
		return changed;
	}

	/**
	 * finds all unregistered students
	 * 
	 * @return list of all unregistered students
	 */
	public List<Student> viewUnregisteredStudents() {
		logger.info("viewUnregisteredStudents by username in StudentDAO");
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			Connection conn = DBUtils.getConnection();

			stmt = conn.prepareStatement(SQLConstants.STUDENT_SELECT_UNREGISTERED);
			stmt.setBoolean(1, false);
			Student student = null;

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int tempStudentID = rs.getInt("studentID");
				String tempStudentName = rs.getString("name");
				boolean tempRegistered = rs.getBoolean("registrationApproved");
				student = new Student(tempStudentID);
				student.setName(tempStudentName);
				student.setRegistered(tempRegistered);
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}
}
