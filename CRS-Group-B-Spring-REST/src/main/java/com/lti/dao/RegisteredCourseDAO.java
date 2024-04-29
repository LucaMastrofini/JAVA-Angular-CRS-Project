package com.lti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.lti.bean.Course;
import com.lti.bean.Student;
import com.lti.constant.SQLConstants;
import com.lti.utils.DBUtils;

public class RegisteredCourseDAO implements RegisteredCourseDAOInterface {

	private PreparedStatement stmt = null;

	/**
	 * view the courses of a student given id
	 * 
	 * @param studentID the id of the student
	 * @return the courses registered by the student
	 */
	@Override
	public ArrayList<Course> viewStudentCourses(int studentID) {
		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			Connection conn = DBUtils.getConnection();

			stmt = conn.prepareStatement(SQLConstants.REGISTEREDCOURSE_SELECT_BY_STUDENTID);
			stmt.setInt(1, studentID);

			ResultSet rs = stmt.executeQuery();
			CourseDAO cou = new CourseDAO();
			Course course = null;
			while (rs.next()) {
				int tempCourseID = rs.getInt("courseID");
				course = cou.viewCourse(tempCourseID);
				int tempStudentID = rs.getInt("studentID");
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
	public ArrayList<Student> viewAllStudents(int courseID) {
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			Connection conn = DBUtils.getConnection();

			// String sql_SELECT_STUDENTS_BY_COURSEID = "SELECT courseID, studentID WHERE
			// courseID='?'";
			stmt = conn.prepareStatement(SQLConstants.REGISTEREDCOURSE_SELECT_STUDENTS_BY_COURSEID);
			stmt.setInt(1, courseID);

			ResultSet rs = stmt.executeQuery();
			StudentDAO stu = new StudentDAO();
			Student student = null;
			while (rs.next()) {
				int tempCourseID = rs.getInt("courseID");
				student = stu.viewStudent(tempCourseID);
				int tempStudentID = rs.getInt("studentID");
				student = stu.viewStudent(tempStudentID);
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
	public ArrayList<Course> viewUnpaidCourses(int studentID) {
		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			Connection conn = DBUtils.getConnection();

			// String sql_SELECT_GRADES = "SELECT courseID, studentID, feePaid WHERE
			// studentID='?' AND feePaid='0'";
			stmt = conn.prepareStatement(SQLConstants.REGISTEREDCOURSE_SELECT_ALL_FEE_UNPAID);
			stmt.setInt(1, studentID);

			ResultSet rs = stmt.executeQuery();
			CourseDAO cou = new CourseDAO();
			Course course = null;
			while (rs.next()) {
				int tempCourseID = rs.getInt("courseID");
				course = cou.viewCourse(tempCourseID);
				int tempStudentID = rs.getInt("studentID");
				boolean tempPaid = rs.getBoolean("feePaid");
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
	public Map<Student, Double> viewStudentsAndGrades(int courseID) {
		HashMap<Student, Double> map = new HashMap<Student, Double>();
		try {
			Connection conn = DBUtils.getConnection();

			// String sql_SELECT_GRADES = "SELECT courseID, studentID, grade WHERE
			// courseID='?'";
			stmt = conn.prepareStatement(SQLConstants.REGISTEREDCOURSE_SELECT_GRADES_BY_COURSEID);
			stmt.setInt(1, courseID);

			ResultSet rs = stmt.executeQuery();
			StudentDAO stu = new StudentDAO();
			Student student = null;
			while (rs.next()) {
				int tempCourseID = rs.getInt("courseID");
				int tempStudentID = rs.getInt("studentID");
				student = stu.viewStudent(tempCourseID);
				double tempGrade = rs.getDouble("grade");
				map.put(student, tempGrade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * views the courses and the grades of a student
	 * 
	 * @param studentID the student to look at
	 * @return a map of courses to their grades by the student
	 */
	@Override
	public Map<Course, Double> viewGrades(int studentID) {
		HashMap<Course, Double> map = new HashMap<Course, Double>();
		try {
			Connection conn = DBUtils.getConnection();

			// String sql_SELECT_GRADES = "SELECT courseID, studentID, grade WHERE
			// studentID=?";
			stmt = conn.prepareStatement(SQLConstants.REGISTEREDCOURSE_SELECT_GRADES_BY_STUDENTID);
			stmt.setInt(1, studentID);

			ResultSet rs = stmt.executeQuery();
			CourseDAO cou = new CourseDAO();
			Course course = null;
			while (rs.next()) {
				int tempCourseID = rs.getInt("courseID");
				course = cou.viewCourse(tempCourseID);
				int tempStudentID = rs.getInt("studentID");
				double tempGrade = rs.getDouble("grade");
				map.put(course, tempGrade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * updates a student's course registration information
	 * 
	 * @param studentID the student to add
	 * @param courseID  the course to add
	 * @return if the student was registered or not
	 */
	@Override
	public boolean addStudentRegistration(int studentID, int courseID) {
		boolean added = false;
		try {
			Connection conn = DBUtils.getConnection();

			// String sql_INSERT = "INSERT INTO RegisteredCourse VALUES(?,?,0,-1)";
			stmt = conn.prepareStatement(SQLConstants.REGISTEREDCOURSE_INSERT);
			stmt.setInt(1, studentID);
			stmt.setInt(2, courseID);

			stmt.executeUpdate();
			added = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return added;
	}

	/**
	 * pays the student's course fee
	 * 
	 * @param studentID the id of the student that has paid
	 * @param courseID  the course that has been paid for
	 * @return if the update was successful
	 */
	@Override
	public boolean payFee(int studentID, int courseID) {
		boolean changed = false;
		try {

			Connection conn = DBUtils.getConnection();

			// Step 5 create and populate statement

			// String sql = "SELECT courseID, studentID, feePaid FROM RegisteredCourse WHERE
			// courseID='?' AND studentID='?'";
			stmt = conn.prepareStatement(SQLConstants.REGISTEREDCOURSE_SELECT_FEE_UNPAID);
			stmt.setInt(1, courseID);
			stmt.setInt(2, studentID);
			// Step 6 execute statement
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Retrieve by column name

				int tempCourseID = rs.getInt("courseID");
				int tempStudentID = rs.getInt("studentID");

				if (courseID == tempCourseID && studentID == tempStudentID) {
					// sql = "UPDATE RegisteredCourse SET feePaid='?' WHERE courseID='?' AND
					// studentID='?'";
					stmt = conn.prepareStatement(SQLConstants.REGISTEREDCOURSE_UPDATE);
					stmt.setBoolean(1, true);
					stmt.setInt(2, courseID);
					stmt.setInt(3, studentID);

					stmt.executeUpdate();
					changed = true;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return changed;
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
	public boolean setGrade(int studentID, int courseID, double grade) {
		boolean changed = false;
		try {

			Connection conn = DBUtils.getConnection();

			// Step 5 create and populate statement

			// String sql = "SELECT courseID, studentID, grade FROM RegisteredCourse WHERE
			// courseID='?' AND studentID='?'";
			stmt = conn.prepareStatement(SQLConstants.REGISTEREDCOURSE_SELECT_GRADES_BY_STUDENTID_AND_COURSEID);
			stmt.setInt(1, courseID);
			stmt.setInt(2, studentID);
			// Step 6 execute statement
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Retrieve by column name

				int tempCourseID = rs.getInt("courseID");
				int tempStudentID = rs.getInt("studentID");

				if (courseID == tempCourseID && studentID == tempStudentID) {
					// sql = "UPDATE RegisteredCourse SET grade='?' WHERE courseID='?' AND
					// studentID='?'";
					stmt = conn.prepareStatement(SQLConstants.REGISTEREDCOURSE_UPDATE_GRADES);
					stmt.setDouble(1, grade);
					stmt.setInt(2, courseID);
					stmt.setInt(3, studentID);

					stmt.executeUpdate();
					changed = true;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return changed;
	}

	/**
	 * removes a student from a course
	 * 
	 * @param studentID the student to remove from class
	 * @param courseID  the id of the class
	 * @return if the student was removed
	 */
	@Override
	public boolean removeStudentRegistration(int studentID, int courseID) {
		boolean removed = false;
		try {

			Connection conn = DBUtils.getConnection();

			// Step 5 create and populate statement

			// String sql = "SELECT courseID, studentID FROM RegisteredCourse WHERE
			// courseID='?' AND studentID='?'";
			stmt = conn.prepareStatement(SQLConstants.REGISTEREDCOURSE_SELECT_BY_STUDENTID_AND_COURSEID);
			stmt.setInt(1, courseID);
			stmt.setInt(2, studentID);
			// Step 6 execute statement
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Retrieve by column name

				int tempCourseID = rs.getInt("courseID");
				int tempStudentID = rs.getInt("studentID");

				if (courseID == tempCourseID && studentID == tempStudentID) {
					// sql = "DELETE FROM RegisteredCourse WHERE courseID='?' AND studentID='?'";
					stmt = conn.prepareStatement(SQLConstants.REGISTEREDCOURSE_DELETE);
					stmt.setInt(1, courseID);
					stmt.setInt(2, studentID);

					stmt.executeUpdate();
					removed = true;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return removed;
	}

}
