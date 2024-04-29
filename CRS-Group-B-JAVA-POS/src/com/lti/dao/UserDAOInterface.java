package com.lti.dao;

import com.lti.bean.User;

public interface UserDAOInterface {
	/**
	 * Creates a new user and returns the userID
	 * 
	 * @param username the users username
	 * @param password the users password
	 * @param role     the role number
	 * @return userID generated or -1 if already exists
	 */
	public int createNewUser(String username, String password, int role);

	/**
	 * returns the user with the associated username
	 * 
	 * @param username the users username
	 * @return the user or null if no user exists
	 */
	public User viewUser(String username);

	/**
	 * changes the password of a specific user
	 * 
	 * @param username the username of the User to change
	 * @param password the new password
	 * @return if the password was changed successfully
	 */
	public boolean updatePassword(String username, String password);
}
