package com.lti.restcontroller;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Admin;
import com.lti.dto.Professor;
import com.lti.dto.Student;
import com.lti.dto.User;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UsernameUsedException;
import com.lti.service.StudentService;
import com.lti.service.UserService;

@RestController
@CrossOrigin
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentService studentService;
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
		if (userService.verifyCredetials(user.getUsername(), user.getPassword(), user.getRole())) {
			switch(user.getRole()) {
			case 1:
				Admin result = new Admin();
				result.setUsername(user.getUsername());
				result.setRole(user.getRole());
				result.setUserID(userService.getUserID(user.getUsername()));
				result.setAdminID(result.getUserID());
				return new ResponseEntity(result, HttpStatus.OK);
			case 2:
				Professor resultP = new Professor();
				resultP.setUsername(user.getUsername());
				resultP.setRole(user.getRole());
				resultP.setUserID(userService.getUserID(user.getUsername()));
				resultP.setProfessorID(resultP.getUserID());
				resultP.setName(userService.getProfessor(resultP.getUserID()).getName());
				return new ResponseEntity(resultP, HttpStatus.OK);
			case 3:
				Student resultS = studentService.getStudentByUsername(user.getUsername());
				resultS.setUsername(user.getUsername());
				resultS.setRole(user.getRole());
				resultS.setUserID(userService.getUserID(user.getUsername()));
				return new ResponseEntity(resultS, HttpStatus.OK);
			}
			User newUser = new User();
			newUser.setUsername(user.getUsername());
			newUser.setRole(user.getRole());
			newUser.setUserID(userService.getUserID(user.getUsername()));
			return new ResponseEntity(newUser, HttpStatus.OK);
		}
		
		if (user == null)
			return new ResponseEntity("User information not provided", HttpStatus.NOT_FOUND);
//		try {
//			userService.verifyCredetials(user.getUsername(), user.getPassword(), user.getRole());
//		} catch (StudentNotFoundException e) {
//			return new ResponseEntity(e.getMessage() + e.getStudentID(), HttpStatus.NOT_FOUND);
//		}
		return new ResponseEntity("Login failed",HttpStatus.UNAUTHORIZED);
	}
	
	/**
	 * Method to register a student
	 * 
	 * @param student the student that has to be registered
	 * @return ResponseEntity the ResponseEntity if the login is successful or not
	 * @throws UsernameUsedException if username already taken
	 * @throws StudentNotFoundException 
	 */
	@RequestMapping(value = "/studentRegistration", method = RequestMethod.POST)
	public ResponseEntity studentRegistration(@RequestBody Student student) throws UsernameUsedException, StudentNotFoundException {
		logger.info("studentRegistration in userController");
		studentService.createStudent(student);
		Student newStudent = studentService.getStudentByUsername(student.getUsername());
		newStudent.setUsername(student.getUsername());
		newStudent.setRole(3);
		newStudent.setUserID(userService.getUserID(student.getUsername()));
		if (student == null)
			return new ResponseEntity("New Student information not provided", HttpStatus.NOT_FOUND);
//		try {
//			studentService.createStudent(student);
//		} catch (UsernameUsedException e) {
//			return new ResponseEntity(e.getMessage() + e.getUsername(), HttpStatus.I_AM_A_TEAPOT);
//		}
		return new ResponseEntity(newStudent, HttpStatus.CREATED);
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
