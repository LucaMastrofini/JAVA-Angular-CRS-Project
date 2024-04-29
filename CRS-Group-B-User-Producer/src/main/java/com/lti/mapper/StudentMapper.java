package com.lti.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.lti.dao.StudentDAO;
import com.lti.dao.UserDAO;
import com.lti.dto.Student;
import com.lti.dto.User;

public class StudentMapper implements RowMapper<Student>{

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Student student = new Student();
		
		student.setStudentID(rs.getInt("studentID"));
		student.setName(rs.getString("name"));
		student.setRegistrationApproved(rs.getBoolean("registrationApproved"));
		return student;
		
	}
	
}
