package com.lti.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.lti.dao.StudentDAO;
import com.lti.dao.StudentDAOInterface;
import com.lti.dao.UserDAO;
import com.lti.dao.UserDAOInterface;
import com.lti.dto.Student;
import com.lti.dto.User;
import com.lti.exception.StudentNotFoundException;
import com.lti.restcontroller.StudentController;

@Service
@ComponentScan(basePackages = "...")
public class UserService implements UserServiceInterface {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private StudentDAO studentDAO;

	private User user;
	
	private Student student;

	Logger logger = LoggerFactory.getLogger(UserService.class);

	/**
	 * verify log in credentials
	 * 
	 * @param username the username of the user
	 * @param password the password of the user
	 * @param role     the role of the user
	 * @return true if credentials are correct, false if not
	 * @throws StudentNotFoundException if the student needs to be approved
	 */
	public boolean verifyCredetials(String username, String password, int role) throws StudentNotFoundException {
		logger.info("verifyCredetials in userService");
		try {
		//	logger.debug(userDAO.toString());
			user = userDAO.findUser(username);
			logger.debug(user.getUsername() + user.getUserID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user == null)
			return false;
		if (role == 3 && user.getRole() == 3) {
			student = studentDAO.findStudent(user.getUserID());
			// System.out.println(student.isRegistered());
			if (password.equals(user.getPassword()) && student.isRegistered()) {
				logger.info("student succesfully logged in");
				return true;
			} else
			if (!student.isRegistered()) {
				throw new StudentNotFoundException(
						"This student is not registered. Please inform Admin and supply StudentID: ",
						student.getStudentID());
			}
			else {
				throw new StudentNotFoundException(
						"You entered the wrong credentials: ",
						student.getStudentID());
			}
		} else {
			if (password.equals(user.getPassword()) && role == user.getRole()) {
				return true;
			}
		}
		//should throw exception called properly instead of returning a boolean
		return false;
	}

	public void logOut() {

	}

	/**
	 * verify password reset credentials
	 * 
	 * @param username the username of the user
	 * @param id       the users id
	 * @return true if credentials are correct, false if not
	 */
	@Override
	public boolean verifyPasswordResetCredentials(int id, String username) {
		logger.info("verifyPasswordResetCredetials in userService");
		user = userDAO.findUser(username);
		if (user == null)
			return false;
		if (username.equals(user.getUsername()) && id == user.getUserID()) {
			return true;
		}

		return false;

	}

	/**
	 * update password of user
	 * 
	 * @param username    the username of the user
	 * @param newPassword the password of the user
	 */
	@Override
	public void updatePassword(String username, String newPassword) {
		// TODO Auto-generated method stub
		logger.info("updatePassword in userService");
		userDAO.updatePassword(username, newPassword);
	}
}
