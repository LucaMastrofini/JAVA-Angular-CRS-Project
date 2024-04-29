package com.lti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.lti.bean.Course;
import com.lti.bean.Student;
import com.lti.dao.CourseDAO;
import com.lti.dao.CourseDAOInterface;
import com.lti.dao.RegisteredCourseDAO;
import com.lti.dao.RegisteredCourseDAOInterface;
import com.lti.dao.StudentDAO;
import com.lti.dao.StudentDAOInterface;
import com.lti.dao.UserDAO;
import com.lti.dao.UserDAOInterface;
import com.lti.exception.AllCoursesPaidException;
import com.lti.exception.CourseFullException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UsernameUsedException;

public class StudentService implements StudentServiceInterface {

	private RegisteredCourseDAOInterface registeredCourseDAO = new RegisteredCourseDAO();
	private CourseDAOInterface courseDAO = new CourseDAO();
	private StudentDAOInterface studentDAO = new StudentDAO();
	private UserDAOInterface userDAO = new UserDAO();

	/**
	 * calls DAO method to register student in registeredcourse
	 * 
	 * @param courseId  courseId to be applied
	 * @param studentId studentId of the student that applies to course
	 * @throws CourseNotFoundException if courseId doesn't exist
	 * @throws CourseFullException     if there are more then 10 students
	 */
	public void applyToCourse(int studentId, int courseId) throws CourseNotFoundException, CourseFullException {

		if (courseDAO.viewCourse(courseId) == null)
			throw new CourseNotFoundException("This course was not found, ID: ", courseId);
		if (registeredCourseDAO.viewAllStudents(courseId).size() >= 10)
			throw new CourseFullException("This course is full, ID: ", courseId);
		registeredCourseDAO.addStudentRegistration(studentId, courseId);

	}

	/**
	 * calls DAO method to remove student in registeredCourse
	 * 
	 * @param courseId  the courseId of what course is to be dropped
	 * @param studentId the studentId that is dropping a course
	 * @throws CourseNotFoundException if courseId doesn't exist
	 */
	public void dropCourse(int studentId, int courseId) throws CourseNotFoundException {
		List<Course> courses = registeredCourseDAO.viewStudentCourses(studentId);
//		int i = 0;
//		for (Course c : courses) {
//			if (c.getCourseID() != courseId) i++;
//		}
		List<Course> result = registeredCourseDAO.viewStudentCourses(studentId).stream()
				.filter(c -> c.getCourseID() == courseId).collect(Collectors.toList());
		if (result.isEmpty())
			throw new CourseNotFoundException("You are not enrolled in this course, ID: ", courseId);
		if (courseDAO.viewCourse(courseId) == null)
			throw new CourseNotFoundException("This course was not found, ID: ", courseId);
		registeredCourseDAO.removeStudentRegistration(studentId, courseId);
	}

	/**
	 * calls DAO method to view all courses student have applied
	 * 
	 * @param studentId the studentId of the courses that he applied to
	 * @return a list of courses where the student is enrolled
	 */
	public List<Course> viewAppliedCourses(int studentId) {

		return registeredCourseDAO.viewStudentCourses(studentId);
	}

	/**
	 * calls DAO method to payFee
	 * 
	 * @param courseId  the courseId to pay the fee
	 * @param studentId the student who is paying
	 * @throws CourseNotFoundException if courseId doesn't exist
	 */
	public void makePayment(int studentId, int courseId) throws CourseNotFoundException {

//		List <Course> courses = registeredCourseDAO.viewStudentCourses(studentId);
//		int i = 0;
//		for (Course c : courses) {
//			if (c.getCourseID() != courseId) i++;
//		}
//		if (courses.size() == i) throw new CourseNotFoundException("This course was not found, ID: " , courseId);

		List<Course> result = registeredCourseDAO.viewStudentCourses(studentId).stream()
				.filter(c -> c.getCourseID() == courseId).collect(Collectors.toList());
		if (result.isEmpty())
			throw new CourseNotFoundException("You are not enrolled in this course, ID: ", courseId);
		result = registeredCourseDAO.viewUnpaidCourses(studentId).stream().filter(c -> c.getCourseID() == courseId)
				.collect(Collectors.toList());
		if (result.isEmpty())
			throw new CourseNotFoundException("You already paid for this course, ID: ", courseId);
		if (courseDAO.viewCourse(courseId) == null)
			throw new CourseNotFoundException("This course was not found, ID: ", courseId);
		registeredCourseDAO.payFee(studentId, courseId);
	}

	/**
	 * calls DAO method to view grades for a student
	 * 
	 * @param studentId the studentId that wants to check his grades
	 * @return Returns a map course to grade
	 */
	public Map<Course, Double> checkGrades(int studentId) {

		return registeredCourseDAO.viewGrades(studentId);
	}

	/**
	 * calls DAO method to view unpaid courses
	 * 
	 * @param studentId the studentId that views the unpaid courses
	 * @return a list of courses to be paid
	 * @throws AllCoursesPaidException if all courses are already paid for
	 */
	public List<Course> viewUnpayedCourses(int studentId) throws AllCoursesPaidException {

		List<Course> courses = registeredCourseDAO.viewUnpaidCourses(studentId);
		if (courses.isEmpty())
			throw new AllCoursesPaidException("There are no more courses to pay, student ID: ", studentId);
		return registeredCourseDAO.viewUnpaidCourses(studentId);
	}

	/**
	 * gets a student by username
	 * 
	 * @param username the user's username
	 * @return student the student object
	 * @throws StudentNotFoundException if student is not found
	 */
	public Student getStudentByUsername(String username) throws StudentNotFoundException {

		Student student = studentDAO.viewStudent(username);

		if (student == null)
			throw new StudentNotFoundException("The student does not exist", 0);
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
		if (userDAO.viewUser(student.getUsername()) != null)
			throw new UsernameUsedException("Username already taken, username: ", student.getUsername());
		student.setRegistered(false);
		studentDAO.createStudent(student);

	}

}
