/**
 * 
 */
package com.lti.dao;

import java.util.List;
import java.util.Map;

import com.lti.dto.*;

/**
 * @author user101
 *
 */
public interface RegisteredCourseDAOInterface {
	// view one, view all, create, update, delete
	/**
	 * view the courses of a student given id
	 * 
	 * @param studentID the id of the student
	 * @return the courses registered by the student
	 */
	public List<Course> findCoursesByStudentID(int studentID);

	/**
	 * view the students in a course
	 * 
	 * @param courseID the course to view
	 * @return the students registered to this course
	 */
	public List<Student> findStudentsByCourseID(int courseID);

	/**
	 * Gives a list of all courses yet to be paid
	 * 
	 * @param studentID the student
	 * @return list of all unpaid courses
	 */
	public List<Course> findUnpaidCoursesByStudentID(int studentID);

	/**
	 * views the students and their grades in a course
	 * 
	 * @param courseID the course to look up
	 * @return a map of students to their grades in the specified class
	 */
	public Map<Student, Grade> findStudentsAndGradesByCourseID(int courseID);

	/**
	 * views the courses and the grades of a student
	 * 
	 * @param studentID the student to look at
	 * @return a map of courses to their grades by the student
	 */
	public Map<Course, Grade> findGradesByStudentID(int studentID);

	/**
	 * updates a student's course registration information
	 * 
	 * @param studentID the student to add
	 * @param courseID  the course to add
	 * @return if the student was registered or not
	 */
	public boolean updateStudentRegistration(int studentID, int courseID);

	/**
	 * pays the student's course fee
	 * 
	 * @param studentID the id of the student that has paid
	 * @param courseID  the course that has been paid for
	 * @return if the update was successful
	 */
	public boolean updateFeePaid(int studentID, int courseID);

	/**
	 * sets the grade of a student in a class
	 * 
	 * @param studentID the id of the student
	 * @param courseID  the id of the course
	 * @param grade     the grade to be added
	 * @return if the grade was set
	 */
	public boolean updateGrade(int studentID, int courseID, double grade);

	/**
	 * removes a student from a course
	 * 
	 * @param studentID the student to remove from class
	 * @param courseID  the id of the class
	 * @return if the student was removed
	 */
	public boolean deleteStudentRegistration(int studentID, int courseID);
}
