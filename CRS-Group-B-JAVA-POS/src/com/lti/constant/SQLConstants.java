package com.lti.constant;

public class SQLConstants {
	
	// Various queries made to the database in the DAO Layer
	
	// Queries for User table
	public static final String USER_INSERT = "INSERT INTO User(username, password, role) VALUES(?,?,?)";
	public static final String USER_SELECT = "SELECT userID, username , password, role FROM User";
	public static final String USER_UPDATE_PASSWORD = "UPDATE User SET password=? WHERE username=?";
	
	// Queries for Student table
	public static final String STUDENT_INSERT = "INSERT INTO Student VALUES(?,?,?)";
	public static final String STUDENT_SELECT = "SELECT studentID, name, registrationApproved FROM Student";
	public static final String STUDENT_SELECT_UNREGISTERED = "SELECT studentID, name, registrationApproved FROM Student WHERE registrationApproved=?";
	public static final String STUDENT_UPDATE = "UPDATE Student SET name=?, registrationApproved=? WHERE studentID=?";
	
	// Queries for Professor table
	public static final String PROFESSOR_INSERT = "INSERT INTO Professor VALUES(?,?)";
	public static final String PROFESSOR_SELECT = "SELECT professorID, name FROM Professor";
	
	// Queries for Course table
	public static final String COURSE_SELECT_BY_COURSEID = "SELECT courseID, name , department, description, professorID, prereqID FROM Course WHERE courseID=?";
	public static final String COURSE_SELECT_BY_PROFESSORID = "SELECT courseID, name , department, description, professorID, prereqID FROM Course WHERE professorID=?";
	public static final String COURSE_SELECT_ALL_COURSES = "SELECT courseID, name , department, description, professorID, prereqID FROM Course";
	public static final String COURSE_DELETE = "DELETE FROM Course WHERE courseID=?";
	public static final String COURSE_UPDATE = "UPDATE Course SET name=?, department=?, description=?, prereqID=? WHERE courseID=?";
	public static final String COURSE_UPDATE_PROFESSORID = "UPDATE Course SET professorID=? WHERE courseID=?";
	public static final String COURSE_INSERT = "INSERT INTO Course(name,department,description,professorID,prereqID) VALUES(?,?,?,NULL,?)";
	
	// Queries for Admin table
	public static final String ADMIN_INSERT = "INSERT INTO Admin VALUES(?)";
	public static final String ADMIN_SELECT = "SELECT adminID FROM Admin WHERE adminID=?";
	
	// Queries for RegisteredCourse table
	public static final String REGISTEREDCOURSE_SELECT_STUDENTS_BY_COURSEID = "SELECT courseID, studentID FROM RegisteredCourse WHERE courseID=?";
	public static final String REGISTEREDCOURSE_SELECT_FEE_UNPAID = "SELECT courseID, studentID, feePaid FROM RegisteredCourse WHERE courseID=? AND studentID=? AND feePaid=0";
	public static final String REGISTEREDCOURSE_SELECT_ALL_FEE_UNPAID = "SELECT courseID, studentID, feePaid FROM RegisteredCourse WHERE studentID=? AND feePaid=0";
	public static final String REGISTEREDCOURSE_UPDATE = "UPDATE RegisteredCourse SET feePaid=? WHERE courseID=? AND studentID=?";
	public static final String REGISTEREDCOURSE_SELECT_GRADES_BY_COURSEID = "SELECT courseID, studentID, grade FROM RegisteredCourse WHERE courseID=?";
	public static final String REGISTEREDCOURSE_SELECT_GRADES_BY_STUDENTID = "SELECT courseID, studentID, grade FROM RegisteredCourse WHERE studentID=?";
	public static final String REGISTEREDCOURSE_SELECT_GRADES_BY_STUDENTID_AND_COURSEID = "SELECT courseID, studentID, grade FROM RegisteredCourse WHERE courseID=? AND studentID=?";
	public static final String REGISTEREDCOURSE_SELECT_BY_STUDENTID_AND_COURSEID = "SELECT courseID, studentID FROM RegisteredCourse WHERE courseID=? AND studentID=?";
	public static final String REGISTEREDCOURSE_UPDATE_GRADES = "UPDATE RegisteredCourse SET grade=? WHERE courseID=? AND studentID=?";
	public static final String REGISTEREDCOURSE_SELECT_BY_STUDENTID = "SELECT courseID, studentID FROM RegisteredCourse WHERE studentID=?";
	public static final String REGISTEREDCOURSE_DELETE = "DELETE FROM RegisteredCourse WHERE courseID=? AND studentID=?";
	public static final String REGISTEREDCOURSE_INSERT = "INSERT INTO RegisteredCourse VALUES(?,?,0,-1)";
}
