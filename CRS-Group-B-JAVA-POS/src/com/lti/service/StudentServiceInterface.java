package com.lti.service;

import java.util.List;
import java.util.Map;

import com.lti.bean.Course;
import com.lti.bean.Student;
import com.lti.exception.AllCoursesPaidException;
import com.lti.exception.CourseFullException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UsernameUsedException;

//student service interface
public interface StudentServiceInterface {

	/**
	 * calls DAO method to register student in registeredcourse
	 * 
	 * @param courseId  courseId to be applied
	 * @param studentId studentId of the student that applies to course
	 * @throws CourseNotFoundException if courseId doesn't exist
	 * @throws CourseFullException     if there are more then 10 students
	 */
	public void applyToCourse(int studentId, int courseId) throws CourseNotFoundException, CourseFullException;

	/**
	 * calls DAO method to remove student in registeredCourse
	 * 
	 * @param courseId  the courseId of what course is to be dropped
	 * @param studentId the studentId that is dropping a course
	 * @throws CourseNotFoundException if courseId doesn't exist
	 */
	public void dropCourse(int studentId, int courseId) throws CourseNotFoundException;

	/**
	 * calls DAO method to view all courses student have applied
	 * 
	 * @param studentId the studentId of the courses that he applied to
	 * @return a list of courses where the student is enrolled
	 */
	public List<Course> viewAppliedCourses(int studentId);

	/**
	 * calls DAO method to payFee
	 * 
	 * @param courseId  the courseId to pay the fee
	 * @param studentId the student who is paying
	 * @throws CourseNotFoundException if courseId doesn't exist
	 */
	public void makePayment(int studentId, int courseId) throws CourseNotFoundException;

	/**
	 * calls DAO method to view grades for a student
	 * 
	 * @param studentId the studentId that wants to check his grades
	 * @return Returns a map course to grade
	 */
	public Map<Course, Double> checkGrades(int studentId);

	/**
	 * calls DAO method to view unpaid courses
	 * 
	 * @param studentId the studentId that views the unpaid courses
	 * @return a list of courses to be paid
	 * @throws AllCoursesPaidException if all courses are already paid for
	 */
	public List<Course> viewUnpayedCourses(int studentId) throws AllCoursesPaidException;

	/**
	 * gets a student by username
	 * 
	 * @param username the user's username
	 * @return student the student object
	 * @throws StudentNotFoundException if student is not found
	 */
	public Student getStudentByUsername(String username) throws StudentNotFoundException;

	/**
	 * creates a student
	 * 
	 * @param student the student to create
	 * @throws UsernameUsedException if user already exists
	 */
	public void createStudent(Student student) throws UsernameUsedException;
}
