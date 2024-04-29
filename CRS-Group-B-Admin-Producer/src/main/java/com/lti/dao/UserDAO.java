/**
 * 
 */
package com.lti.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import com.lti.configuration.JDBCConfiguration;
import com.lti.constants.SQLConstants;
import com.lti.dto.User;
import com.lti.mapper.UserMapper;

/**
 * @author user101
 *
 */
@Repository
public class UserDAO implements UserDAOInterface {

	@Autowired
	private JDBCConfiguration jdbcTemplateObject;

	// Queries for User table
	

	public UserDAO() {
		// used for testing only
	}

	/**
	 * Creates a new user and returns the userID
	 * 
	 * @param username the users username
	 * @param password the users password
	 * @param role     the role number
	 * @return userID generated or -1 if already exists
	 */
	@Override
	public int createUser(String username, String password, int role) {
		jdbcTemplateObject.jdbcTemplate().update(SQLConstants.USER_INSERT, username, password, role);
		User user = findUser(username);
		return user.getUserID();
	}

	/**
	 * returns the user with the associated username
	 * 
	 * @param username the users username
	 * @return the user or null if no user exists
	 */
	@Override
	public User findUser(String username) {
		// String sql = "SELECT * FROM User WHERE username = ?";
		User user = null;
		try {
			user = jdbcTemplateObject.jdbcTemplate().queryForObject(SQLConstants.USER_SELECT, new UserMapper(), username);
		} catch (DataAccessException e) {
			return null;
		}
		return user;
	}

	/**
	 * returns the user with the associated id
	 * 
	 * @param userID the users id
	 * @return the user or null if no user exists
	 */
	public User findUser(int userID) {
		User user = null;
		try {
			user = jdbcTemplateObject.jdbcTemplate().queryForObject(SQLConstants.USER_SELECT_ID, new UserMapper(), userID);
		} catch (DataAccessException e) {
			return null;
		}
		return user;
	}

	/**
	 * changes the password of a specific user
	 * 
	 * @param username the username of the User to change
	 * @param password the new password
	 * @return if the password was changed successfully
	 */
	@Override
	public boolean updatePassword(String username, String password) {
		jdbcTemplateObject.jdbcTemplate().update(SQLConstants.USER_UPDATE_PASSWORD, password, username);
		return true;
	}
	
	public void deleteUser(int userID) {
		jdbcTemplateObject.jdbcTemplate().update(SQLConstants.USER_DELETE, userID);
	}

}
