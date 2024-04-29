package com.lti.restcontroller;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.bean.Student;
import com.lti.bean.User;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UsernameUsedException;
import com.lti.service.StudentService;
import com.lti.service.UserService;

@RestController
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	StudentService studentService;
	/**
	 * Method to Login
	 * 
	 * @param user the user to login
	 * @return ResponseEntity the ResponseEntity if the login is successful or not
	 * @throws StudentNotFoundException if student is not found
	 */
	@RequestMapping(consumes = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value="/login")
	public ResponseEntity login(@RequestBody User user) throws StudentNotFoundException {
		logger.info("login in userController");
		userService.verifyCredetials(user.getUsername(), user.getPassword(), user.getRole());
		if (user == null)
			return new ResponseEntity("User information not provided", HttpStatus.NOT_FOUND);
//		try {
//			userService.verifyCredetials(user.getUsername(), user.getPassword(), user.getRole());
//		} catch (StudentNotFoundException e) {
//			return new ResponseEntity(e.getMessage() + e.getStudentID(), HttpStatus.NOT_FOUND);
//		}
		return new ResponseEntity(HttpStatus.OK);
	}
	
	/**
	 * Method to register a student
	 * 
	 * @param student the student that has to be registered
	 * @return ResponseEntity the ResponseEntity if the login is successful or not
	 * @throws UsernameUsedException if username already taken
	 */
	@RequestMapping(value = "/studentRegistration", method = RequestMethod.POST)
	public ResponseEntity studentRegistration(@RequestBody Student student) throws UsernameUsedException {
		logger.info("studentRegistration in userController");
		studentService.createStudent(student);
		if (student == null)
			return new ResponseEntity("New Student information not provided", HttpStatus.NOT_FOUND);
//		try {
//			studentService.createStudent(student);
//		} catch (UsernameUsedException e) {
//			return new ResponseEntity(e.getMessage() + e.getUsername(), HttpStatus.I_AM_A_TEAPOT);
//		}
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	/**
	 * Method to update the password
	 * 
	 * @param user the user that resets the password
	 * @return ResponseEntity the ResponseEntity if the login is successful or not
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public ResponseEntity updatePassword(@RequestBody User user) {
		logger.info("updatePassword in userController");
		if (user == null)
			return new ResponseEntity("User information not provided", HttpStatus.NOT_FOUND);
		userService.updatePassword(user.getUsername(), user.getPassword());
		return new ResponseEntity(HttpStatus.OK);
	}
}
