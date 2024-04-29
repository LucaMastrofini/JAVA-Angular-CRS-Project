package com.lti.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.AdminDAO;
import com.lti.dao.CourseDAO;
import com.lti.dao.ProfessorDAO;
import com.lti.dao.StudentDAO;
import com.lti.dao.UserDAO;
import com.lti.dto.Admin;
import com.lti.dto.Course;
import com.lti.dto.Professor;
import com.lti.dto.Student;
import com.lti.exception.AllStudentRegisteredException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UsernameUsedException;

@Service
public class AdminService implements AdminServiceInterface {

	Logger logger = LoggerFactory.getLogger(AdminService.class);
	@Autowired
	private CourseDAO courseDAO;
	@Autowired
	private ProfessorDAO professorDAO;
	@Autowired
	private StudentDAO studentDAO;
	@Autowired
	private AdminDAO adminDAO;
	@Autowired
	private UserDAO userDAO;

	/**
	 * call courseDAO to create a course
	 * 
	 * @param course the course to create
	 */
	@Override
	public void createCourse(Course course) {
		logger.info("createCourse in AdminService");
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
		logger.info("createProfessor in AdminService");
		if (userDAO.findUser(professor.getUsername()) != null)
			throw new UsernameUsedException("Username " + professor.getUsername() + " already taken",
					professor.getUsername());
		professorDAO.createProfessor(professor);
	}

	public List<Professor> viewProfessors() {
		return professorDAO.viewProfessors();
	}

	/**
	 * call courseDAO to update a course
	 * 
	 * @param course the course updated
	 * @throws CourseNotFoundException if courseID provided doesn't exist
	 */
	@Override
	public void updateCourse(Course course) throws CourseNotFoundException {
		logger.info("updateCourse in AdminService");
		if (courseDAO.findCourseByCourseID(course.getCourseID()) == null)
			throw new CourseNotFoundException("This course was not found, ID: " + course.getCourseID(),
					course.getCourseID());
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
		logger.info("deleteCourse in AdminService");
		if (courseDAO.findCourseByCourseID(courseId) == null)
			throw new CourseNotFoundException("This course was not found, ID: " + courseId, courseId);
		courseDAO.deleteCourse(courseId);
	}

	/**
	 * call courseDAO to view all course
	 * 
	 * @return Returns a list of courses
	 */
	@Override
	public List<Course> listAllCourse() {
		logger.info("listAllCourses in AdminService");
		return courseDAO.findAllCourses();
	}

	@Override
	public Course getCourseByID(int courseId) throws CourseNotFoundException {
		if (courseDAO.findCourseByCourseID(courseId) == null)
			throw new CourseNotFoundException("This course was not found, ID: " + courseId, courseId);
		return courseDAO.findCourseByCourseID(courseId);
	}

	/**
	 * call studentDAO to set student attribute registrationApproved to true
	 * 
	 * @param student the student to approve
	 * @throws StudentNotFoundException if student provided doesn't exist
	 */
	@Override
	public void approveStudentRegistration(Student student) throws StudentNotFoundException {
		logger.info("approveStudentRegistration in AdminService");
		if (studentDAO.findStudent(student.getStudentID()) == null)
			throw new StudentNotFoundException("This student was not found, ID: " + student.getStudentID(),
					student.getStudentID());
//		Old method without streams
//		int i = 0;
//		for (Student s : studentDAO.viewUnregisteredStudents()) {
//			if (s.getStudentID() != student.getStudentID())
//				i++;
//		}
		List<Student> result = studentDAO.findUnregisteredStudents().stream()
				.filter(s -> s.getStudentID() == student.getStudentID()).collect(Collectors.toList());
		if (result.isEmpty())
			throw new StudentNotFoundException("This student is already registerd, ID: " + student.getStudentID(),
					student.getStudentID());
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
		logger.info("getAdminByUsername in AdminService");
		return adminDAO.findAdminByUsername(username);

	}

	/**
	 * call studentDAO to get list of students that are not registered yet
	 * 
	 * @return list of students
	 * @throws AllStudentRegisteredException if list is empty
	 */
	@Override
	public List<Student> unregisteredStudent() throws AllStudentRegisteredException {
		logger.info("unregisteredStudent in AdminService");
		List<Student> students = studentDAO.findUnregisteredStudents();
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
		logger.info("getStudentById in AdminService");
		Student student = studentDAO.findStudent(studentId);
		if (student == null)
			throw new StudentNotFoundException("This student was not found, ID: " + studentId, studentId);
		return student;
	}

	public void deleteStudent(int studentID) {
		userDAO.deleteUser(studentID);
	}

}
