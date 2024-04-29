package com.lti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.CourseDAO;
import com.lti.dao.CourseDAOInterface;
import com.lti.dao.ProfessorDAO;
import com.lti.dao.ProfessorDAOInterface;
import com.lti.dao.RegisteredCourseDAO;
import com.lti.dao.RegisteredCourseDAOInterface;
import com.lti.dao.UserDAO;
import com.lti.dto.*;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.NoStudentsEnrolledException;
import com.lti.exception.StudentNotFoundException;

@Service
public class ProfessorService implements ProfessorServiceInterface {

	Logger logger = LoggerFactory.getLogger(ProfessorService.class);
	@Autowired
	private ProfessorDAO professorDAO;
	@Autowired
	private CourseDAO courseDAO;
	@Autowired
	private RegisteredCourseDAO registeredCourseDAO;
	@Autowired
	private UserDAO userDAO;

	/**
	 * call courseDAO to set professorId to course
	 * 
	 * @param professorId the professor to apply
	 * @param courseId    the course to teach
	 * @throws CourseNotFoundException if courseId doesn't exits
	 */
	public void applyToCourse(int professorId, int courseId) throws CourseNotFoundException {
		logger.info("applyToCourse in ProfessorService");
		// apply to specific course
		if (userDAO.findUser(professorId).getRole() != 2)
			throw new CourseNotFoundException("You are not a professor, Id: " + professorId, professorId);
		if (courseDAO.findCourseByCourseID(courseId).getProf() == null) {
			if (!courseDAO.updateProfessorToCourse(courseId, professorId))
				throw new CourseNotFoundException("This course was not found, Id: " + courseId, courseId);
		} else {
			if (courseDAO.findCourseByCourseID(courseId).getProf().getProfID() != 0)
				throw new CourseNotFoundException("This course is already being taught, ID: " +courseId, courseId);
			if (courseDAO.findCourseByCourseID(courseId).getProf().getProfID() == professorId)
				throw new CourseNotFoundException("You are already enrolled in this course, ID: "+ courseId, courseId);
			if (!courseDAO.updateProfessorToCourse(courseId, professorId))
				throw new CourseNotFoundException("This course was not found, Id: "+ courseId, courseId);
		}
	}

	/**
	 * view students that are enrolled in a course
	 * 
	 * @param courseId the course to view
	 * @return Returns a list of students
	 * @throws CourseNotFoundException     if courseId doesn't exits
	 * @throws NoStudentsEnrolledException if there are not students
	 */
	public List<Student> viewStudents(int courseId) throws CourseNotFoundException, NoStudentsEnrolledException {
		logger.info("viewStudents in ProfessorService");
		List<Student> students = registeredCourseDAO.findStudentsByCourseID(courseId);
		if (courseDAO.findCourseByCourseID(courseId) == null)
			throw new CourseNotFoundException("This course was not found, ID: "+ courseId, courseId);
		if (students.isEmpty())
			throw new NoStudentsEnrolledException("This course has no students, ID: "+courseId, courseId);
		return students;

	}

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
			throws StudentNotFoundException, CourseNotFoundException, NoStudentsEnrolledException {
		logger.info("recordGrade in ProfessorService");
		// record grade for student in class
		if (courseDAO.findCourseByCourseID(courseId) == null)
			throw new CourseNotFoundException("This course was not found, ID: "+courseId, courseId);
		if (registeredCourseDAO.findStudentsByCourseID(courseId).isEmpty())
			throw new NoStudentsEnrolledException("This course has no students, ID: "+courseId, courseId);
		if (!registeredCourseDAO.updateGrade(studentId, courseId, grade))
			throw new StudentNotFoundException("This student was not found, ID: "+studentId, studentId);

	}

	/**
	 * returns a professor by providing a username
	 * 
	 * @param username the username of the professor
	 * @return Returns a professor, null if username doesn't exist
	 */
	@Override
	public Professor getProfessorByUsername(String username) {
		logger.info("getProfessorByUsername in ProfessorService");
		return professorDAO.findProfessorByUsername(username);
	}

	/**
	 * calls courseDAO to get courses that a particular professor is teaching
	 * 
	 * @param professorId the id of the professor
	 * @return Returns a list of courses that a professor is teaching
	 */
	@Override
	public List<Course> viewProfessorCourses(int professorId) {
		logger.info("getProfessorCourses in ProfessorService");
		return courseDAO.findCourseByProfessorID(professorId);
	}

	/**
	 * gets a map of student to grade calls method courseDAO
	 * 
	 * @param courseId    the id of the course
	 * @param professorId the id of the professor
	 * @return Returns a map of student to grade
	 * @throws CourseNotFoundException     if courseId doesn't exist
	 * @throws NoStudentsEnrolledException if there
	 */
	@Override
	public Map<Student, Double> viewStudentsGrades(int professorId, int courseId)
			throws CourseNotFoundException, NoStudentsEnrolledException {
		logger.info("viewStudentsGrades in ProfessorService");
		List<Course> courses = courseDAO.findCourseByProfessorID(professorId);
		/*
		 * int i = 0; for (Course c : courses) { if (c.getCourseID() != courseId) i++; }
		 * if (i == courses.size())
		 */
		List<Course> result = courseDAO.findCourseByProfessorID(professorId).stream()
				.filter(c -> c.getCourseID() == courseId).collect(Collectors.toList());
		if (result.isEmpty())
			throw new CourseNotFoundException("You are not enrolled in this course, ID: "+courseId, courseId);
		if (courseDAO.findCourseByCourseID(courseId) == null)
			throw new CourseNotFoundException("This course was not found, ID: "+courseId, courseId);
		Map<Student, Double> studentsAndGrades = registeredCourseDAO.findStudentsAndGradesByCourseID(courseId);
		if (studentsAndGrades.isEmpty())
			throw new NoStudentsEnrolledException("This course has no students, ID: "+courseId, courseId);
		return studentsAndGrades;

	}

}
