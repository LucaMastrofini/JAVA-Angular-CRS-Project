package com.lti.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Nikhil, Luca, Muhammad
 *
 */
@Component
public class Admin extends User implements  Serializable{
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

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

}
