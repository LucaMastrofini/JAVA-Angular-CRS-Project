package com.lti.service;
//professor service interface

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lti.dto.*;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.NoStudentsEnrolledException;
import com.lti.exception.StudentNotFoundException;

public interface ProfessorServiceInterface {

	/**
	 * call courseDAO to set professorId to course
	 * 
	 * @param professorId the professor to apply
	 * @param courseId    the course to teach
	 * @throws CourseNotFoundException if courseId doesn't exits
	 */
	public void applyToCourse(int professorId, int courseId) throws CourseNotFoundException;

	/**
	 * records a grade of a student of a particular course
	 * 
	 * @param courseId  the course to set the grade
	 * @param studentId the student to set the grade for
	 * @param grade     the grade to record
	 * @throws CourseNotFoundException     if courseId doesn't exits
	 * @throws StudentNotFoundException    if provided studentId doesn't exist
	 * @throws NoStudentsEnrolledException if there are not student
	 */
	public void recordGrade(double grade, int studentId, int courseId)
			throws StudentNotFoundException, CourseNotFoundException, NoStudentsEnrolledException;

	/**
	 * view students that are enrolled in a course
	 * 
	 * @param courseId the course to view
	 * @return Returns a list of students
	 * @throws CourseNotFoundException     if courseId doesn't exits
	 * @throws NoStudentsEnrolledException if there are not students
	 */
	public List<Student> viewStudents(int courseId) throws CourseNotFoundException, NoStudentsEnrolledException;

	/**
	 * returns a professor by providing a username
	 * 
	 * @param username the username of the professor
	 * @return Returns a professor, null if username doesn't exist
	 */
	public Professor getProfessorByUsername(String username);

	/**
	 * calls courseDAO to get courses that a particular professor is teaching
	 * 
	 * @param professorId the id of the professor
	 * @return Returns a list of courses that a professor is teaching
	 */
	public List<Course> viewProfessorCourses(int professorId);

	/**
	 * gets a map of student to grade calls method courseDAO
	 * 
	 * @param courseId    the id of the course
	 * @param professorId the id of the professor
	 * @return Returns a list of grades
	 * @throws CourseNotFoundException     if courseId doesn't exist
	 * @throws NoStudentsEnrolledException if there
	 */
	public List<Grade> viewStudentsGrades(int professorId, int courseId)
			throws CourseNotFoundException, NoStudentsEnrolledException;

}
