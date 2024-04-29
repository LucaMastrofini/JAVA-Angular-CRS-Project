package com.lti.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.lti.dao.UserDAO;
import com.lti.dto.Admin;
import com.lti.dto.Admin;

public class AdminMapper implements RowMapper<Admin>{

	@Autowired
	UserDAO userDAO;

	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Admin admin = new Admin();
		
		admin.setAdminID(rs.getInt("professorID"));
		admin.setUsername(userDAO.findUser(rs.getInt("professorID")).getUsername());
		admin.setPassword(userDAO.findUser(rs.getInt("professorID")).getPassword());
		admin.setRole(userDAO.findUser(rs.getInt("professorID")).getRole());
		return admin;
		
	}
	
}
