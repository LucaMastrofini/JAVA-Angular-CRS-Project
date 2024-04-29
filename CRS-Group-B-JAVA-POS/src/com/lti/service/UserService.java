package com.lti.service;

import com.lti.bean.Student;
import com.lti.bean.User;
import com.lti.dao.StudentDAO;
import com.lti.dao.StudentDAOInterface;
import com.lti.dao.UserDAO;
import com.lti.dao.UserDAOInterface;
import com.lti.exception.StudentNotFoundException;

public class UserService implements UserServiceInterface {

	private UserDAOInterface userDAO = new UserDAO();
	private StudentDAOInterface studentDAO = new StudentDAO();
	private User user;
	private Student student;

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

		user = userDAO.viewUser(username);

		if (user == null)
			return false;
		if (role == 3) {
			student = studentDAO.viewStudent(user.getUserID());
			// System.out.println(student.isRegistered());
			if (role == user.getRole())
				if (password.equals(user.getPassword()) && student.isRegistered()) {
					return true;
				} else
					throw new StudentNotFoundException(
							"This student is not registered. Please inform Admin and supply StudentID: ",
							student.getStudentID());
			else
				return false;
		} else {
			if (password.equals(user.getPassword()) && role == user.getRole()) {
				return true;
			}
		}

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

		user = userDAO.viewUser(username);
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
		userDAO.updatePassword(username, newPassword);
	}
}
