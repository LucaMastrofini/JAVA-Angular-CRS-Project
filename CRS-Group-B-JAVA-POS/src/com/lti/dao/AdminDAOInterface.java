package com.lti.dao;

import com.lti.bean.Admin;

public interface AdminDAOInterface {
	/**
	 * Creates a new Admin
	 * 
	 * @param admin the admin to create
	 * @return new ID if created successfully, else -1
	 */
	public int createAdmin(Admin admin);

	/**
	 * finds the admin with the supplied username
	 * 
	 * @param username username associated with the admin
	 * @return the Admin object
	 */
	public Admin viewAdmin(String username);

	/**
	 * finds the admin with the supplied id
	 * 
	 * @param adminID the id of the admin
	 * @return the Admin object
	 */
	public Admin viewAdmin(int adminID);
}
