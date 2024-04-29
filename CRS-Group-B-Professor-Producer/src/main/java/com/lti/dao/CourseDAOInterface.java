/**
 * 
 */
package com.lti.dao;

import java.util.ArrayList;
import java.util.List;

import com.lti.dto.*;

/**
 * @author user101
 *
 */
public interface CourseDAOInterface {

	/**
	 * returns all courses in an ArrayList
	 * 
	 * @return all courses
	 */
	public List<Course> findAllCourses();

	/**
	 * Creates a new course
	 * 
	 * @param course course to create
	 * @return if course was created
	 */
	public boolean createCourse(Course course);

	/**
	 * adds a professor to teach a course
	 * 
	 * @param courseID the course to modify
	 * @param profID   the professor to teach
	 * @return if course was updated
	 */
	public boolean updateProfessorToCourse(int courseID, int professorID);

	/**
	 * provides the courses a professor is teaching
	 * 
	 * @param profID the professor teaching
	 * @return the courses that a professor is teaching
	 */
	public List<Course> findCourseByProfessorID(int professorID);

	/**
	 * deletes a course
	 * 
	 * @param courseID the course to delete
	 * @return the course that was deleted, else null
	 */
	public Course deleteCourse(int courseID);

	/**
	 * selects a course with provided id
	 * 
	 * @param courseID the identifier of the course
	 * @return the course with provided id or null if not found
	 */
	public Course findCourseByCourseID(int courseID);

	/**
	 * updates a courses information
	 * 
	 * @param course the course with the required changes made already
	 * @return if the course was updated
	 */
	public boolean updateCourse(Course course);
}
