package com.lti.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.lti.dto.User;
public class UserMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		  User user = new User();
		  user.setUserID(rs.getInt("userID"));
		  user.setUsername(rs.getString("username"));
		  user.setPassword(rs.getString("password"));
		  user.setRole(rs.getInt("role"));
	      return user;
		
	}

}