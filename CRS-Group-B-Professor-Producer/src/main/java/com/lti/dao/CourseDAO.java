package com.lti.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.lti.configuration.JDBCConfiguration;
import com.lti.constants.SQLConstants;
import com.lti.dto.Admin;
import com.lti.dto.Course;
import com.lti.dto.Professor;
import com.lti.mapper.CourseMapper;
import com.lti.mapper.StudentMapper;

@Repository
public class CourseDAO implements CourseDAOInterface {

	@Autowired
	JDBCConfiguration jdbcTemplateObject;

	@Autowired
	ProfessorDAO professorDAO;

	
	/**
	 * returns all courses in an ArrayList
	 * 
	 * @return all courses
	 */
	@Override
	public List<Course> findAllCourses() {
		List<Course> courses = new ArrayList<Course>();
		try {
			for (Course c : jdbcTemplateObject.jdbcTemplate().query(SQLConstants.COURSE_SELECT_ALL_COURSES, new CourseMapper())) {
				if (c.getProfessor() != null)
					courses.add(saveProfessorIntoCourse(c, c.getProfessor().getProfessorID()));
				else
					courses.add(c);
			}
		} catch (DataAccessException e) {
			return null;
		}
		return courses;
	}
	
	/**
	 * Creates a new course
	 * 
	 * @param course course to create
	 * @return if course was created
	 */
	@Override
	public boolean createCourse(Course course) {
		if (course.getPrereqCourseID() == 0) {
			jdbcTemplateObject.jdbcTemplate().update(SQLConstants.COURSE_INSERT, course.getName(), course.getDepartment(),
					course.getDescription(), null);
		}
		else {
		jdbcTemplateObject.jdbcTemplate().update(SQLConstants.COURSE_INSERT, course.getName(), course.getDepartment(),
				course.getDescription(), course.getPrereqCourseID());
		}
		return true;
	}

	/**
	 * adds a professor to teach a course
	 * 
	 * @param courseID the course to modify
	 * @param profID   the professor to teach
	 * @return if course was updated
	 */
	@Override
	public boolean updateProfessorToCourse(int courseID, int professorID) {
		jdbcTemplateObject.jdbcTemplate().update(SQLConstants.COURSE_UPDATE_PROFESSORID, professorID, courseID);
		return true;
	}

	/**
	 * provides the courses a professor is teaching
	 * 
	 * @param profID the professor teaching
	 * @return the courses that a professor is teaching
	 */
	@Override
	public List<Course> findCourseByProfessorID(int professorID) {
		try {
			List<Course> courses = jdbcTemplateObject.jdbcTemplate().query(SQLConstants.COURSE_SELECT_BY_PROFESSORID,
					new CourseMapper(), professorID);
			for (int i = 0; i < courses.size(); i++) {
				Course c = saveProfessorIntoCourse(courses.get(i), professorID);
				courses.set(i, c);
			}
			return courses;
		} catch (DataAccessException e) {
			return null;
		}
	}

	/**
	 * saves professor information in a course
	 * 
	 * @param course the course to save information
	 * @param professorID the professorID to save information
	 * @return the newly updated course
	 */
	private Course saveProfessorIntoCourse(Course course, int professorID) {
		Professor professor = professorDAO.findProfessorByProfessorID(professorID);
		course.setProfessor(professor);
		return course;
	}

	/**
	 * deletes a course
	 * 
	 * @param courseID the course to delete
	 * @return the course that was deleted, else null
	 */
	@Override
	public Course deleteCourse(int courseID) {
		Course course = findCourseByCourseID(courseID);
		jdbcTemplateObject.jdbcTemplate().update(SQLConstants.COURSE_DELETE, courseID);
		return course;
	}

	/**
	 * selects a course with provided id
	 * 
	 * @param courseID the identifier of the course
	 * @return the course with provided id or null if not found
	 */
	@Override
	public Course findCourseByCourseID(int courseID) {
		try {
			Course course = jdbcTemplateObject.jdbcTemplate().queryForObject(SQLConstants.COURSE_SELECT_BY_COURSEID,
					new CourseMapper(), courseID);
			course = saveProfessorIntoCourse(course, course.getProfessor().getProfessorID());
			return course;
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	/**
	 * updates a courses information
	 * 
	 * @param course the course with the required changes made already
	 * @return if the course was updated
	 */
	@Override
	public boolean updateCourse(Course course) {
		if (course.getPrereqCourseID() == 0) {
			jdbcTemplateObject.jdbcTemplate().update(SQLConstants.COURSE_UPDATE, course.getName(), course.getDepartment(),
					course.getDescription(), null, course.getCourseID());
		}
		else {
		jdbcTemplateObject.jdbcTemplate().update(SQLConstants.COURSE_UPDATE, course.getName(), course.getDepartment(),
				course.getDescription(), course.getPrereqCourseID(), course.getCourseID());
		}
		return true;

	}

}
