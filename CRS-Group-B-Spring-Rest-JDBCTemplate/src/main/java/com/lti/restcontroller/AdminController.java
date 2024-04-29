package com.lti.restcontroller;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.service.*;
import com.lti.dto.*;
import com.lti.exception.*;

/**
 * createCourse, updateCourse, deleteCourse, createProfessor,
 * approveRegistration
 * 
 * @author Nikhil, Luca
 *
 */
@RestController
public class AdminController {

	Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	AdminService adminService;

	@Autowired
	CourseService courseService;

	/**
	 * creates a new course
	 * 
	 * @param course the course to create
	 * @return an HTTP response
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/createCourse")
	public ResponseEntity createCourse(@RequestBody Course course) {

		adminService.createCourse(course);
		logger.info("createCourse in AdminController");
		return new ResponseEntity(course, HttpStatus.CREATED);
	}

	/**
	 * updates a course with specific id
	 * 
	 * @param course   the updated information
	 * @param courseID the course to update
	 * @return an HTTP response
	 * @throws CourseNotFoundException if course was not found
	 */
	@ExceptionHandler(CourseNotFoundException.class)
	@RequestMapping(method = RequestMethod.PUT, value = "/updateCourse/{courseID}")
	public ResponseEntity updateCourse(@RequestBody Course course, @PathVariable int courseID)
			throws CourseNotFoundException {
		logger.info("updateCourse in AdminController");

		Course oldCourse = courseService.viewCourseByID(courseID);
		if (oldCourse != null) {
			oldCourse.setDescription(course.getDescription());
			oldCourse.setDepartment(course.getDepartment());
			oldCourse.setName(course.getName());
			oldCourse.setPrereqCourseID(course.getPrereqCourseID());

			adminService.updateCourse(oldCourse);
			return new ResponseEntity(oldCourse, HttpStatus.OK);

		} else {
			return new ResponseEntity(courseID, HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * deletes a course
	 * 
	 * @param courseID the course
	 * @return an HTTP response whether deleted
	 * @throws CourseNotFoundException if course is not found
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteCourse/{courseID}")
	public ResponseEntity deleteCourse(@PathVariable int courseID) throws CourseNotFoundException {
		logger.info("deleteCourse in AdminController");

		adminService.deleteCourse(courseID);
		return new ResponseEntity(courseID, HttpStatus.OK);

	}

	/**
	 * creates a professor and sets the username and password
	 * 
	 * @param professor the professor to create
	 * @return an HTTP response
	 * @throws UsernameUsedException if the username is already in use
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/createProfessor")
	public ResponseEntity createProfessor(@RequestBody Professor professor) throws UsernameUsedException {
		logger.info("createProfessor in AdminController");

		adminService.createProfessor(professor);
		return new ResponseEntity(HttpStatus.CREATED);

	}

	/**
	 * Approves a student's registration
	 * 
	 * @param studentID the student to registered
	 * @return an HTTP response
	 * @throws StudentNotFoundException if the student is not found
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/approveRegistration/{studentID}")
	public ResponseEntity approveRegistration(@PathVariable int studentID) throws StudentNotFoundException {
		logger.info("approveRegistration in AdminController");

		Student student = adminService.getStudentById(studentID);

		adminService.approveStudentRegistration(student);
		return new ResponseEntity(student, HttpStatus.CREATED);

	}
}
