package com.lti.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.lti.dao.UserDAO;
import com.lti.dto.Professor;
import com.lti.dto.Student;

public class ProfessorMapper implements RowMapper<Professor>{

	@Autowired
	UserDAO userDAO;

	@Override
	public Professor mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Professor professor = new Professor();
		
		professor.setProfessorID(rs.getInt("professorID"));
//		professor.setUsername(userDAO.findUser(rs.getInt("professorID")).getUsername());
//		professor.setPassword(userDAO.findUser(rs.getInt("professorID")).getPassword());
//		professor.setRole(userDAO.findUser(rs.getInt("professorID")).getRole());
		professor.setName(rs.getString("name"));
		return professor;
		
	}
	
}
