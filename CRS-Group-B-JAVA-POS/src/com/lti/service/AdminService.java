package com.lti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.lti.bean.Admin;
import com.lti.bean.Course;
import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.dao.AdminDAO;
import com.lti.dao.AdminDAOInterface;
import com.lti.dao.CourseDAO;
import com.lti.dao.CourseDAOInterface;
import com.lti.dao.ProfessorDAO;
import com.lti.dao.ProfessorDAOInterface;
import com.lti.dao.StudentDAO;
import com.lti.dao.StudentDAOInterface;
import com.lti.dao.UserDAOInterface;
import com.lti.dao.UserDAO;
import com.lti.exception.AllStudentRegisteredException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UsernameUsedException;

public class AdminService implements AdminServiceInterface {

	private CourseDAOInterface courseDAO = new CourseDAO();
	private ProfessorDAOInterface professorDAO = new ProfessorDAO();
	private StudentDAOInterface studentDAO = new StudentDAO();
	private AdminDAOInterface adminDAO = new AdminDAO();
	private UserDAOInterface userDAO = new UserDAO();

	/**
	 * call courseDAO to create a course
	 * 
	 * @param course the course to create
	 */
	@Override
	public void createCourse(Course course) {

		courseDAO.createCourse(course);

	}

	/**
	 * call professorDAO to create a professor
	 * 
	 * @param professor the professor to create
	 * @throws UsernameUsedException if username already exists
	 */
	@Override
	public void createProfessor(Professor professor) throws UsernameUsedException {

		if (userDAO.viewUser(professor.getUsername()) != null)
			throw new UsernameUsedException("Username already taken, username: ", professor.getUsername());
		professorDAO.createProfessor(professor);
	}

	/**
	 * call courseDAO to update a course
	 * 
	 * @param course the course updated
	 * @throws CourseNotFoundException if courseID provided doesn't exist
	 */
	@Override
	public void updateCourse(Course course) throws CourseNotFoundException {

		if (courseDAO.viewCourse(course.getCourseID()) == null)
			throw new CourseNotFoundException("This course was not found, ID: ", course.getCourseID());
		courseDAO.updateCourse(course);
	}

	/**
	 * call courseDAO to delete a course
	 * 
	 * @param courseId the course to delete
	 * @throws CourseNotFoundException if courseID provided doesn't exist
	 */
	@Override
	public void deleteCourse(int courseId) throws CourseNotFoundException {

		if (courseDAO.viewCourse(courseId) == null)
			throw new CourseNotFoundException("This course was not found, ID: ", courseId);
		courseDAO.deleteCourse(courseId);
	}

	/**
	 * call courseDAO to view all course
	 * 
	 * @return Returns a list of courses
	 */
	@Override
	public List<Course> listAllCourse() {

		return courseDAO.viewAllCourses();
	}

	/**
	 * call studentDAO to set student attribute registrationApproved to true
	 * 
	 * @param student the student to approve
	 * @throws StudentNotFoundException if student provided doesn't exist
	 */
	@Override
	public void approveStudentRegistration(Student student) throws StudentNotFoundException {

		if (studentDAO.viewStudent(student.getStudentID()) == null)
			throw new StudentNotFoundException("This student was not found, ID: ", student.getStudentID());
//		Old method without streams
//		int i = 0;
//		for (Student s : studentDAO.viewUnregisteredStudents()) {
//			if (s.getStudentID() != student.getStudentID())
//				i++;
//		}
		List<Student> result = studentDAO.viewUnregisteredStudents().stream()
				.filter(s -> s.getStudentID() == student.getStudentID()).collect(Collectors.toList());
		if (result.isEmpty())
			throw new StudentNotFoundException("This student is already registerd, ID: ", student.getStudentID());
		student.setRegistered(true);
		studentDAO.updateStudent(student);
	}

	/**
	 * call adminDAO to get admin by username
	 * 
	 * @param username the username
	 * @return Returns an admin
	 */
	@Override
	public Admin getAdminByUsername(String username) {
		return adminDAO.viewAdmin(username);

	}

	/**
	 * call studentDAO to get list of students that are not registered yet
	 * 
	 * @return list of students
	 * @throws AllStudentRegisteredException if list is empty
	 */
	@Override
	public List<Student> unregisteredStudent() throws AllStudentRegisteredException {

		List<Student> students = studentDAO.viewUnregisteredStudents();
		if (students.isEmpty())
			throw new AllStudentRegisteredException("There are no students to be registered");
		return students;
	}

	/**
	 * call studentDAO to get student by id
	 * 
	 * @param studentId the id of the student
	 * @return student if is not found then returns null
	 * @throws StudentNotFoundException if student was not found
	 */
	@Override
	public Student getStudentById(int studentId) throws StudentNotFoundException {
		Student student = studentDAO.viewStudent(studentId);
		if (student == null)
			throw new StudentNotFoundException("This student was not found, ID: ", studentId);
		return student;
	}

}
