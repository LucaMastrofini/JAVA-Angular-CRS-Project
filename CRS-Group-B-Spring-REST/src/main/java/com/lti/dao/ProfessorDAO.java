/**
 * 
 */
package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.constant.SQLConstants;
import com.lti.utils.DBUtils;

/**
 * @author user101
 *
 */
public class ProfessorDAO implements ProfessorDAOInterface {

	Logger logger = LoggerFactory.getLogger(ProfessorDAO.class);
	private PreparedStatement stmt = null;

	private UserDAOInterface userDAO = new UserDAO();

	/**
	 * creates a new professor
	 * 
	 * @param prof new professor to add
	 * @return new ID if created successfully, else -1
	 */
	@Override
	public int createProfessor(Professor prof) {
		logger.info("createProfessor in ProfessorDAO");
		int userID = userDAO.createNewUser(prof.getUsername(), prof.getPassword(), 2);

		try {
			Connection conn = DBUtils.getConnection();
			// Step 5 create and populate statement

			// String sql = "insert into Professor values(?,?)";
			stmt = conn.prepareStatement(SQLConstants.PROFESSOR_INSERT);
			stmt.setInt(1, userID);
			stmt.setString(2, prof.getName());

			// Step 6 execute statement

			stmt.executeUpdate();
			
			//return userID;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userID;
	}

	/**
	 * views a professor given the id
	 * 
	 * @param profID the professor to view
	 * @return the professor object
	 */
	@Override
	public Professor viewProfessor(int profID) {
		logger.info("viewProfessor in ProfessorDAO");
		Professor prof = null;
		try {
			Connection conn = DBUtils.getConnection();
			// Step 5 create and populate statement

			// String sql = "SELECT professorID, name FROM Professor";

			stmt = conn.prepareStatement(SQLConstants.PROFESSOR_SELECT);
			// Step 6 execute statement
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Retrieve by column name
				int tempUserID = rs.getInt("professorID");
				String tempName = rs.getString("name");
				if (tempUserID == profID) {
					prof = new Professor(tempUserID, tempName);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prof;
	}

	/**
	 * views a professor given the username
	 * 
	 * @param username the username of the User
	 * @return the professor object associated with the username
	 */
	@Override
	public Professor viewProfessor(String username) {
		logger.info("viewProfessor in ProfessorDAO");
		UserDAO userDAO = new UserDAO();
		int profID = userDAO.viewUser(username).getUserID();
		return viewProfessor(profID);
	}

}
