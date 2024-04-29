/**
 * 
 */
package com.lti.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.lti.configuration.JDBCConfiguration;
import com.lti.constants.SQLConstants;
import com.lti.dto.Course;
import com.lti.dto.RegisteredCourse;
import com.lti.dto.Student;
import com.lti.mapper.CourseMapper;
import com.lti.mapper.RegisteredCourseMapper;
import com.lti.mapper.StudentMapper;
/**
 * @author user101
 *
 */
@Repository
public class RegisteredCourseDAO implements RegisteredCourseDAOInterface {

	@Autowired
	JDBCConfiguration jdbcConfiguration;
	@Autowired
	private CourseDAO courseDAO;
	@Autowired
	private StudentDAO studentDAO;

	// Queries for RegisteredCourse table
	

	/**
	 * view the courses of a student given id
	 * 
	 * @param studentID the id of the student
	 * @return the courses registered by the student
	 */
	@Override
	public List<Course> findCoursesByStudentID(int studentID) {
		List<Course> courses = new ArrayList<Course>();
		List<Integer> courseIDs = jdbcConfiguration.jdbcTemplate()
				.queryForList(SQLConstants.REGISTEREDCOURSE_SELECT_COURSES_BY_STUDENTID, Integer.class, studentID);
		for (int courseID : courseIDs) {
			courses.add(courseDAO.findCourseByCourseID(courseID));
		}
		return courses;
		
	}

	/**
	 * view the students in a course
	 * 
	 * @param courseID the course to view
	 * @return the students registered to this course
	 */
	@Override
	public List<Student> findStudentsByCourseID(int courseID) {
		List<Student> students = new ArrayList<Student>();
		List<Integer> studentIDs = jdbcConfiguration.jdbcTemplate()
				.queryForList(SQLConstants.REGISTEREDCOURSE_SELECT_STUDENTS_BY_COURSEID, Integer.class, courseID);
		for (int studentID : studentIDs) {
			students.add(studentDAO.findStudent(studentID));
		}
		return students;
	}

	/**
	 * Gives a list of all courses yet to be paid
	 * 
	 * @param studentID the student
	 * @return list of all unpaid courses
	 */
	@Override
	public List<Course> findUnpaidCoursesByStudentID(int studentID) {
		List<Course> courses = new ArrayList<Course>();
		List<Integer> courseIDs = jdbcConfiguration.jdbcTemplate()
				.queryForList(SQLConstants.REGISTEREDCOURSE_SELECT_ALL_FEE_UNPAID, Integer.class, studentID);
		for (int courseID : courseIDs) {
			courses.add(courseDAO.findCourseByCourseID(courseID));
		}
		return courses;
	}

	/**
	 * views the students and their grades in a course
	 * 
	 * @param courseID the course to look up
	 * @return a map of students to their grades in the specified class
	 */
	@Override
	public Map<Student, Double> findStudentsAndGradesByCourseID(int courseID) {
		Map<Student, Double> studentGrades = new HashMap<Student, Double>();
		List<RegisteredCourse> registeredCourses = jdbcConfiguration.jdbcTemplate()
				.query(SQLConstants.REGISTEREDCOURSE_SELECT_GRADES_BY_COURSEID, new RegisteredCourseMapper(), courseID);

		for (RegisteredCourse registeredCourse : registeredCourses) {
			studentGrades.put(studentDAO.findStudent(registeredCourse.getStudentID()), registeredCourse.getGrade());
		}

		return studentGrades;
	}

	/**
	 * views the courses and the grades of a student
	 * 
	 * @param studentID the student to look at
	 * @return a map of courses to their grades by the student
	 */
	@Override
	public Map<Course, Double> findGradesByStudentID(int studentID) {
		Map<Course, Double> courseGrades = new HashMap<Course, Double>();
		List<RegisteredCourse> registeredCourses = jdbcConfiguration.jdbcTemplate()
				.query(SQLConstants.REGISTEREDCOURSE_SELECT_GRADES_BY_STUDENTID, new RegisteredCourseMapper(), studentID);

		for (RegisteredCourse registeredCourse : registeredCourses) {
			courseGrades.put(courseDAO.findCourseByCourseID(registeredCourse.getCourseID()),
					registeredCourse.getGrade());
		}

		return courseGrades;
	}

	/**
	 * updates a student's course registration information
	 * 
	 * @param studentID the student to add
	 * @param courseID  the course to add
	 * @return if the student was registered or not
	 */
	@Override
	public boolean updateStudentRegistration(int studentID, int courseID) {
		jdbcConfiguration.jdbcTemplate().update(SQLConstants.REGISTEREDCOURSE_INSERT, studentID, courseID);
		return true;
	}

	/**
	 * pays the student's course fee
	 * 
	 * @param studentID the id of the student that has paid
	 * @param courseID  the course that has been paid for
	 * @return if the update was successful
	 */
	@Override
	public boolean updateFeePaid(int studentID, int courseID) {
		jdbcConfiguration.jdbcTemplate().update(SQLConstants.REGISTEREDCOURSE_UPDATE, true, courseID, studentID);
		return true;
	}

	/**
	 * sets the grade of a student in a class
	 * 
	 * @param studentID the id of the student
	 * @param courseID  the id of the course
	 * @param grade     the grade to be added
	 * @return if the grade was set
	 */
	@Override
	public boolean updateGrade(int studentID, int courseID, double grade) {
		jdbcConfiguration.jdbcTemplate()
				.update(SQLConstants.REGISTEREDCOURSE_UPDATE_GRADES, grade, courseID, studentID);
		return true;
	}

	/**
	 * removes a student from a course
	 * 
	 * @param studentID the student to remove from class
	 * @param courseID  the id of the class
	 * @return if the student was removed
	 */
	@Override
	public boolean deleteStudentRegistration(int studentID, int courseID) {
		jdbcConfiguration.jdbcTemplate().update(SQLConstants.REGISTEREDCOURSE_DELETE, courseID, studentID);
		return true;
	}

}
