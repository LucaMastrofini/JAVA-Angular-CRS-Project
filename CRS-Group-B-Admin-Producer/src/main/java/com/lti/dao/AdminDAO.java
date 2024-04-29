package com.lti.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.lti.configuration.JDBCConfiguration;
import com.lti.constants.SQLConstants;
import com.lti.dto.Admin;
import com.lti.dto.User;
import com.lti.mapper.AdminMapper;
import com.lti.mapper.StudentMapper;

@Repository
public class AdminDAO implements AdminDAOInterface{
	
	@Autowired
	JDBCConfiguration jdbcTemplateObject;
	
	@Autowired
	UserDAO userDAO;
	
	
	/**
	 * Creates a new Admin
	 * 
	 * @param admin the admin to create
	 * @return new ID if created successfully, else -1
	 */
	@Override
	public int createAdmin(Admin admin) {
		int userID = userDAO.createUser(admin.getUsername(), admin.getPassword(), 3);
		jdbcTemplateObject.jdbcTemplate()
				.update(SQLConstants.ADMIN_INSERT, userID);
		return userID;
	}
	
	/**
	 * finds the admin with the supplied username
	 * 
	 * @param username username associated with the admin
	 * @return the Admin object
	 */
	@Override
	public Admin findAdminByUsername(String username) {
		User user = userDAO.findUser(username);
		return findAdminByAdminID(user.getUserID());
	}

	/**
	 * finds the admin with the supplied id
	 * 
	 * @param adminID the id of the admin
	 * @return the Admin object
	 */
	@Override
	public Admin findAdminByAdminID(int adminID) {
		try {
			return jdbcTemplateObject.jdbcTemplate().queryForObject(SQLConstants.ADMIN_SELECT, new AdminMapper(), adminID);
		} catch (DataAccessException e) {
			return null;
		}
	}

}
