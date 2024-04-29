package com.lti.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Nikhil, Luca
 *
 */
@Component
public class User implements  Serializable{
	private int userID;
	private String username;
	private String password;
	private int role;

	/**
	 * creates an empty user with an invalid id
	 */
	public User() {
		userID = -1;
	}

	/**
	 * creates a user with a set id
	 * 
	 * @param userID the user's ID
	 */
	public User(int userID) {
		this.userID = userID;
	}

	/**
	 * returns the username
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * sets the username
	 * 
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * returns the role
	 * 
	 * @return the role
	 */
	public int getRole() {
		return role;
	}

	/**
	 * sets the role
	 * 
	 * @param role the role to set
	 */
	public void setRole(int role) {
		this.role = role;
	}

	/**
	 * returns the password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * sets the password
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * returns the user id
	 * 
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		// TODO Auto-generated method stub
		this.userID = userID;
	}

}
