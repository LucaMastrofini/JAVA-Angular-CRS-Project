package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lti.bean.Course;
import com.lti.bean.Professor;
import com.lti.bean.User;
import com.lti.constant.SQLConstants;
import com.lti.utils.DBUtils;

public class CourseDAO implements CourseDAOInterface {

	private PreparedStatement stmt = null;
	private ProfessorDAOInterface profDAO = new ProfessorDAO();

	/**
	 * returns all courses in an ArrayList
	 * 
	 * @return all courses
	 */
	@Override
	public ArrayList<Course> viewAllCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		try {

			Connection conn = DBUtils.getConnection();

			// Step 5 create and populate statement

			// String sql = "SELECT courseID, name , department, description, professorID,
			// prereqID FROM Course";
			stmt = conn.prepareStatement(SQLConstants.COURSE_SELECT_ALL_COURSES);

			// Step 6 execute statement
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Retrieve by column name

				int tempCourseID = rs.getInt("courseID");
				Course tempCourse = new Course(tempCourseID);
				String tempName = rs.getString("name");
				tempCourse.setName(tempName);
				String tempDepartment = rs.getString("department");
				tempCourse.setDepartment(tempDepartment);
				String tempDescription = rs.getString("description");
				tempCourse.setDescription(tempDescription);
				int tempProfID = rs.getInt("professorID");
				Professor pro = profDAO.viewProfessor(tempProfID);
				tempCourse.setProf(pro);
				int tempPrereqID = rs.getInt("prereqID");
				tempCourse.setPrereqCourseID(tempPrereqID);
				courses.add(tempCourse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
		boolean created = false;
		try {
			Connection conn = DBUtils.getConnection();

			// Step 5 create and populate statement

			// String sql = "insert into Course values(?,?,?,?,?,?)";
			stmt = conn.prepareStatement(SQLConstants.COURSE_INSERT);
			// stmt.setInt(1, course.getCourseID());
			stmt.setString(1, course.getName());
			stmt.setString(2, course.getDepartment());
			stmt.setString(3, course.getDescription());
			if (course.getPrereqCourseID() != 0)
				stmt.setInt(4, course.getPrereqCourseID());
			else
				stmt.setNull(4, java.sql.Types.INTEGER);

			// Step 6 execute statement

			stmt.executeUpdate();
			created = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return created;
	}

	/**
	 * adds a professor to teach a course
	 * 
	 * @param courseID the course to modify
	 * @param profID   the professor to teach
	 * @return if course was updated
	 */
	@Override
	public boolean addProfessorToCourse(int courseID, int profID) {
		boolean changed = false;
		try {
			Connection conn = DBUtils.getConnection();
			// String sql = "SELECT courseID, professorID FROM Course WHERE courseID='?'";
			stmt = conn.prepareStatement(SQLConstants.COURSE_SELECT_BY_COURSEID);
			stmt.setInt(1, courseID);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int tempCourseID = rs.getInt("courseID");
				int tempProfID = rs.getInt("professorID");
				if (tempProfID == 0) {
					// sql = "UPDATE Course SET professorID='?' WHERE courseID='?'";
					stmt = conn.prepareStatement(SQLConstants.COURSE_UPDATE_PROFESSORID);
					stmt.setInt(1, profID);
					stmt.setInt(2, courseID);
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
	 * provides the courses a professor is teaching
	 * 
	 * @param profID the professor teaching
	 * @return the courses that a professor is teaching
	 */
	public ArrayList<Course> viewCoursesByProfessor(int profID) {
		ArrayList<Course> courses = new ArrayList<Course>();
		Course course = null;
		try {
			Connection conn = DBUtils.getConnection();

			stmt = conn.prepareStatement(SQLConstants.COURSE_SELECT_BY_PROFESSORID);
			stmt.setInt(1, profID);
			ResultSet rs = stmt.executeQuery();
			// SELECT courseID, name , department, description, professorID, prereqID FROM
			// Course WHERE professorID='?'
			while (rs.next()) {
				int tempCourseID = rs.getInt("courseID");
				String tempName = rs.getString("name");
				int tempProfID = rs.getInt("professorID");
				if (tempProfID == profID) {
					course = new Course(tempCourseID);
					course.setName(tempName);
					String tempDepartment = rs.getString("department");
					course.setDepartment(tempDepartment);
					String tempDescription = rs.getString("description");
					course.setDescription(tempDescription);
					Professor pro = profDAO.viewProfessor(tempProfID);
					course.setProf(pro);
					int tempPrereqID = rs.getInt("prereqID");
					course.setPrereqCourseID(tempPrereqID);
					courses.add(course);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;
	}

	/**
	 * deletes a course
	 * 
	 * @param courseID the course to delete
	 * @return the course that was deleted, else null
	 */
	@Override
	public Course deleteCourse(int courseID) {
		Course course = null;
		try {
			Connection conn = DBUtils.getConnection();

			// Step 5 create and populate statement

			// String sql = "SELECT courseID, name , department, description, professorID,
			// prereqID FROM Course WHERE courseID='?'";
			stmt = conn.prepareStatement(SQLConstants.COURSE_SELECT_BY_COURSEID);
			stmt.setInt(1, courseID);

			// Step 6 execute statement
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Retrieve by column name

				int tempCourseID = rs.getInt("courseID");
				course = new Course(tempCourseID);
				String tempName = rs.getString("name");
				course.setName(tempName);
				String tempDepartment = rs.getString("department");
				course.setDepartment(tempDepartment);
				String tempDescription = rs.getString("description");
				course.setDescription(tempDescription);
				int tempProfID = rs.getInt("professorID");
				Professor pro = profDAO.viewProfessor(tempProfID);
				course.setProf(pro);
				int tempPrereqID = rs.getInt("prereqID");
				course.setPrereqCourseID(tempPrereqID);

			}
			// sql = "DELETE FROM Course WHERE courseID='?'";
			stmt = conn.prepareStatement(SQLConstants.COURSE_DELETE);
			stmt.setInt(1, courseID);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return course;
	}

	/**
	 * selects a course with provided id
	 * 
	 * @param courseID the identifier of the course
	 * @return the course with provided id or null if not found
	 */
	public Course viewCourse(int courseID) {
		Course course = null;
		try {
			Connection conn = DBUtils.getConnection();

			// Step 5 create and populate statement

			// String sql = "SELECT courseID, name , department, description, professorID,
			// prereqID FROM Course WHERE courseID='?'";
			stmt = conn.prepareStatement(SQLConstants.COURSE_SELECT_BY_COURSEID);
			stmt.setInt(1, courseID);

			// Step 6 execute statement
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Retrieve by column name

				int tempCourseID = rs.getInt("courseID");
				course = new Course(tempCourseID);
				String tempName = rs.getString("name");
				course.setName(tempName);
				String tempDepartment = rs.getString("department");
				course.setDepartment(tempDepartment);
				String tempDescription = rs.getString("description");
				course.setDescription(tempDescription);
				int tempProfID = rs.getInt("professorID");
				Professor pro = profDAO.viewProfessor(tempProfID);
				course.setProf(pro);
				int tempPrereqID = rs.getInt("prereqID");
				course.setPrereqCourseID(tempPrereqID);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return course;
	}

	/**
	 * updates a courses information
	 * 
	 * @param course the course with the required changes made already
	 * @return if the course was updated
	 */
	public boolean updateCourse(Course course) {
		boolean changed = false;
		try {
			Connection conn = DBUtils.getConnection();
			// String sql = "SELECT courseID, professorID FROM Course WHERE courseID='?'";
			stmt = conn.prepareStatement(SQLConstants.COURSE_SELECT_BY_COURSEID);
			stmt.setInt(1, course.getCourseID());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int tempCourseID = rs.getInt("courseID");
				int tempProfID = rs.getInt("professorID");
				if (tempProfID == -1) {
					// sql = "UPDATE Course SET professorID='?' WHERE courseID='?'";
					stmt = conn.prepareStatement(SQLConstants.COURSE_UPDATE);
					stmt.setString(1, course.getName());
					stmt.setString(2, course.getDepartment());
					stmt.setString(3, course.getDescription());
					stmt.setInt(4, course.getPrereqCourseID());
					stmt.setInt(5, course.getCourseID());
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
}
