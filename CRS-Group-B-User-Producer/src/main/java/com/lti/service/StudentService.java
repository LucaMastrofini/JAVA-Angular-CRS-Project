package com.lti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.CourseDAO;
import com.lti.dao.CourseDAOInterface;
import com.lti.dao.RegisteredCourseDAO;
import com.lti.dao.RegisteredCourseDAOInterface;
import com.lti.dao.StudentDAO;
import com.lti.dao.StudentDAOInterface;
import com.lti.dao.UserDAO;
import com.lti.dao.UserDAOInterface;
import com.lti.dto.Course;
import com.lti.dto.Student;
import com.lti.exception.AllCoursesPaidException;
import com.lti.exception.CourseFullException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UsernameUsedException;

@Service
public class StudentService implements StudentServiceInterface {

	@Autowired
	private RegisteredCourseDAO registeredCourseDAO;
	@Autowired
	private CourseDAO courseDAO;
	@Autowired
	private StudentDAO studentDAO;
	@Autowired
	private UserDAO userDAO;
	
	Logger logger = LoggerFactory.getLogger(StudentService.class);
	/**
	 * calls DAO method to register student in registeredcourse
	 * 
	 * @param courseId  courseId to be applied
	 * @param studentId studentId of the student that applies to course
	 * @throws CourseNotFoundException if courseId doesn't exist
	 * @throws CourseFullException     if there are more then 10 students
	 */
	public void applyToCourse(int studentId, int courseId) throws CourseNotFoundException, CourseFullException {
		logger.info("applyToCourse in StudentService");
		List<Course> result = registeredCourseDAO.findCoursesByStudentID(studentId).stream()
				.filter(c -> c.getCourseID() == courseId).collect(Collectors.toList());
		if (!result.isEmpty())
			throw new CourseNotFoundException("You are enrolled in this course, ID: " + courseId, courseId);
		if (courseDAO.findCourseByCourseID(courseId) == null)
			throw new CourseNotFoundException("This course was not found, ID: " + courseId, courseId);
		if (registeredCourseDAO.findStudentsByCourseID(courseId).size() >= 10)
			throw new CourseFullException("This course is full, ID: " + courseId, courseId);
		registeredCourseDAO.updateStudentRegistration(studentId, courseId);

	}

	/**
	 * calls DAO method to remove student in registeredCourse
	 * 
	 * @param courseId  the courseId of what course is to be dropped
	 * @param studentId the studentId that is dropping a course
	 * @throws CourseNotFoundException if courseId doesn't exist
	 */
	public void dropCourse(int studentId, int courseId) throws CourseNotFoundException {
		logger.info("dropCourse in StudentService");
		List<Course> courses = registeredCourseDAO.findCoursesByStudentID(studentId);
//		int i = 0;
//		for (Course c : courses) {
//			if (c.getCourseID() != courseId) i++;
//		}
		List<Course> result = registeredCourseDAO.findCoursesByStudentID(studentId).stream()
				.filter(c -> c.getCourseID() == courseId).collect(Collectors.toList());
		if (result.isEmpty())
			throw new CourseNotFoundException("You are not enrolled in this course, ID: " + courseId, courseId);
		if (courseDAO.findCourseByCourseID(courseId) == null)
			throw new CourseNotFoundException("This course was not found, ID: " + courseId, courseId);
		registeredCourseDAO.deleteStudentRegistration(studentId, courseId);
	}

	/**
	 * calls DAO method to view all courses student have applied
	 * 
	 * @param studentId the studentId of the courses that he applied to
	 * @return a list of courses where the student is enrolled
	 */
	public List<Course> viewAppliedCourses(int studentId) {
		logger.info("viewAppliedCourses in StudentService");
		return registeredCourseDAO.findCoursesByStudentID(studentId);
	}

	/**
	 * calls DAO method to payFee
	 * 
	 * @param courseId  the courseId to pay the fee
	 * @param studentId the student who is paying
	 * @throws CourseNotFoundException if courseId doesn't exist
	 */
	public void makePayment(int studentId, int courseId) throws CourseNotFoundException {
		logger.info("makePayment in StudentService");
//		List <Course> courses = registeredCourseDAO.viewStudentCourses(studentId);
//		int i = 0;
//		for (Course c : courses) {
//			if (c.getCourseID() != courseId) i++;
//		}
//		if (courses.size() == i) throw new CourseNotFoundException("This course was not found, ID: " , courseId);

		List<Course> result = registeredCourseDAO.findCoursesByStudentID(studentId).stream()
				.filter(c -> c.getCourseID() == courseId).collect(Collectors.toList());
		if (result.isEmpty())
			throw new CourseNotFoundException("You are not enrolled in this course, ID: " + courseId, courseId);
		result = registeredCourseDAO.findUnpaidCoursesByStudentID(studentId).stream().filter(c -> c.getCourseID() == courseId)
				.collect(Collectors.toList());
		if (result.isEmpty())
			throw new CourseNotFoundException("You already paid for this course, ID: " + courseId, courseId);
		if (courseDAO.findCourseByCourseID(courseId) == null)
			throw new CourseNotFoundException("This course was not found, ID: " + courseId, courseId);
		registeredCourseDAO.updateFeePaid(studentId, courseId);
	}

	/**
	 * calls DAO method to view grades for a student
	 * 
	 * @param studentId the studentId that wants to check his grades
	 * @return Returns a map course to grade
	 */
	public Map<Course, Double> checkGrades(int studentId) {
		logger.info("checkGrades in StudentService");
		return registeredCourseDAO.findGradesByStudentID(studentId);
	}

	/**
	 * calls DAO method to view unpaid courses
	 * 
	 * @param studentId the studentId that views the unpaid courses
	 * @return a list of courses to be paid
	 * @throws AllCoursesPaidException if all courses are already paid for
	 */
	public List<Course> viewUnpayedCourses(int studentId) throws AllCoursesPaidException {
		logger.info("viewUnpayedCourses in StudentService");
		List<Course> courses = registeredCourseDAO.findUnpaidCoursesByStudentID(studentId);
		if (courses.isEmpty())
			throw new AllCoursesPaidException("There are no more courses to pay, student ID: "+ studentId, studentId);
		return registeredCourseDAO.findUnpaidCoursesByStudentID(studentId);
	}

	/**
	 * gets a student by username
	 * 
	 * @param username the user's username
	 * @return student the student object
	 * @throws StudentNotFoundException if student is not found
	 */
	public Student getStudentByUsername(String username) throws StudentNotFoundException {
		logger.info("getStudentByUsername in StudentService");
		Student student = studentDAO.findStudent(username);

		if (student == null)
			throw new StudentNotFoundException("The student"+username+" does not exist", 0);
		return student;

	}

	/**
	 * creates a student
	 * 
	 * @param student the student to create
	 * @throws UsernameUsedException if user already exists
	 */
	@Override
	public void createStudent(Student student) throws UsernameUsedException {
		logger.info("createStudent in StudentService");
		if (userDAO.findUser(student.getUsername()) != null)
			throw new UsernameUsedException("Username " + student.getUsername() + " already taken", student.getUsername());
		student.setRegistrationApproved(false);
		studentDAO.createStudent(student);

	}

}
