package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lti.bean.Admin;
import com.lti.constant.SQLConstants;
import com.lti.utils.DBUtils;

public class AdminDAO implements AdminDAOInterface {
	Logger logger = LoggerFactory.getLogger(AdminDAO.class);
	private PreparedStatement stmt = null;

	private UserDAOInterface userDAO = new UserDAO();

	/**
	 * Creates a new Admin
	 * 
	 * @param admin the admin to create
	 * @return new ID if created successfully, else -1
	 */
	@Override
	public int createAdmin(Admin admin) {
		logger.info("createAdmin in AdminDAO");
		int userID = userDAO.createNewUser(admin.getUsername(), admin.getPassword(), 1);
		logger.debug("Admin created with ID:" + userID);
		try {
			Connection conn = DBUtils.getConnection();
			// Step 5 create and populate statement

			// String sql = "insert into Admin values(?)";
			stmt = conn.prepareStatement(SQLConstants.ADMIN_INSERT);
			stmt.setInt(1, userID);

			// Step 6 execute statement

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userID;
	}

	/**
	 * finds the admin with the supplied username
	 * 
	 * @param username username associated with the admin
	 * @return the Admin object
	 */

	@Override
	public Admin viewAdmin(String username) {
		logger.info("viewAdmin in AdminDAO");
		UserDAO userDAO = new UserDAO();
		int adminID = userDAO.viewUser(username).getUserID();
		return viewAdmin(adminID);
	}

	/**
	 * finds the admin with the supplied id
	 * 
	 * @param adminID the id of the admin
	 * @return the Admin object
	 */
	@Override
	public Admin viewAdmin(int adminID) {
		logger.info("viewAdmin in AdminDAO");
		Admin admin = null;
		try {
			Connection conn = DBUtils.getConnection();

			stmt = conn.prepareStatement(SQLConstants.ADMIN_SELECT);
			stmt.setInt(1, adminID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int tempID = rs.getInt("adminID");
				admin = new Admin(tempID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}
}
