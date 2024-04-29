package com.lti.service;

import java.util.ArrayList;
import java.util.List;

import com.lti.dto.Admin;
import com.lti.dto.Course;
import com.lti.dto.Professor;
import com.lti.dto.Student;
import com.lti.exception.AllStudentRegisteredException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UsernameUsedException;

public interface AdminServiceInterface {

	/**
	 * call courseDAO to create a course
	 * 
	 * @param course the course to create
	 */
	public void createCourse(Course course);

	/**
	 * call professorDAO to create a professor
	 * 
	 * @param professor the professor to create
	 * @throws UsernameUsedException if username already exists
	 */
	public void createProfessor(Professor professor) throws UsernameUsedException;

	/**
	 * call courseDAO to update a course
	 * 
	 * @param course the course updated
	 * @throws CourseNotFoundException if courseID provided doesn't exist
	 */
	public void updateCourse(Course course) throws CourseNotFoundException;

	/**
	 * call courseDAO to delete a course
	 * 
	 * @param courseId the course to delete
	 * @throws CourseNotFoundException if courseID provided doesn't exist
	 */
	public void deleteCourse(int courseId) throws CourseNotFoundException;

	/**
	 * call courseDAO to view all course
	 * 
	 * @return Returns a list of courses
	 */
	public List<Course> listAllCourse();
	
	/**
	 * get course by courseID
	 * 
	 * @param id the id of the course
	 * @return Returns the course
	 * @throws throws CourseNotFoundException if courseID provided doesn't exist
	 */
	public Course getCourseByID(int id) throws CourseNotFoundException;
	/**
	 * call studentDAO to set student attribute registrationApproved to true
	 * 
	 * @param student the student to approve
	 * @throws StudentNotFoundException if student provided doesn't exist
	 */
	public void approveStudentRegistration(Student student) throws StudentNotFoundException;

	/**
	 * call adminDAO to get admin by username
	 * 
	 * @param username the username
	 * @return Returns an admin
	 */
	public Admin getAdminByUsername(String username);

	/**
	 * call studentDAO to get list of students that are not registered yet
	 * 
	 * @return list of students
	 * @throws AllStudentRegisteredException if list is empty
	 */
	public List<Student> unregisteredStudent() throws AllStudentRegisteredException;

	/**
	 * call studentDAO to get student by id
	 * 
	 * @param studentId the id of the student
	 * @return student if is not found then returns null
	 * @throws StudentNotFoundException if student was not found
	 */
	public Student getStudentById(int studentId) throws StudentNotFoundException;
}
