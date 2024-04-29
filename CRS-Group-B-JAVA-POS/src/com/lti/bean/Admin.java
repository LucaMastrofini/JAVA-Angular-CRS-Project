package com.lti.bean;

/**
 * 
 * @author Nikhil, Luca, Muhammad
 *
 */
public class Admin extends User {
	private int adminID;

	/**
	 * creates a new admin with set id
	 * 
	 * @param adminID the id to set
	 */
	public Admin(int adminID) {
		this.adminID = adminID;
	}

	/**
	 * creates an empty admin
	 */
	public Admin() {
		super();
	}

	/**
	 * returns the admin's id
	 * 
	 * @return the adminID
	 */
	public int getAdminID() {
		return adminID;
	}

}
