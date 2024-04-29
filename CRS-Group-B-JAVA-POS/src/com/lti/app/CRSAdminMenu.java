package com.lti.app;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.lti.bean.Admin;
import com.lti.bean.Course;
import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.exception.AllStudentRegisteredException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UsernameUsedException;
import com.lti.service.AdminService;
import com.lti.service.AdminServiceInterface;
import com.lti.service.CourseService;
import com.lti.service.CourseServiceInterface;

public class CRSAdminMenu {

	public boolean adminMenu(Admin admin, Scanner scan) {

		AdminServiceInterface adminService = new AdminService();
		CourseServiceInterface courseService = new CourseService();
		Professor professor = new Professor();
		Course course = new Course();
		Student student = new Student();
		String courseName;
		String courseDepartment;
		String courseDescription;
		String prereqId;

		try {
			System.out.println("*****Welcome Admin " + LocalDateTime.now() + "*****");
			System.out.println("Enter your choice: ");
			System.out.println("1. Create course");
			System.out.println("2. Update course");
			System.out.println("3. Delete course");
			System.out.println("4. View all courses");
			System.out.println("5. Create professor");
			System.out.println("6. Approve registration");
			System.out.println("7. Log out");
			int adminChoice = Integer.parseInt(scan.nextLine());
			switch (adminChoice) {
			case 1:
				course = new Course();
				System.out.println("You have selected Create course");
				System.out.println("Please provide course name");
				courseName = scan.nextLine();
				if (!courseName.isBlank())
					course.setName(courseName);
				System.out.println("Please provide course department");
				courseDepartment = scan.nextLine();
				if (!courseDepartment.isBlank())
					course.setDepartment(courseDepartment);
				System.out.println("Please provide course description");
				courseDescription = scan.nextLine();
				if (!courseDescription.isBlank())
					course.setDescription(courseDescription);
				System.out.println("Please provide prerequisite ID");
				prereqId = scan.nextLine();
				if (!prereqId.isBlank())
					course.setPrereqCourseID(Integer.parseInt(prereqId));
				adminService.createCourse(course);
				break;
			case 2:
				course = new Course();
				System.out.println("You have selected Update course");
				this.displayCourses(courseService.viewAllCourses());
				System.out.println("Please select the course to edit");
				course.setCourseID(scan.nextInt());
				System.out.println("Please provide course name");
				courseName = scan.nextLine();
				if (!courseName.isBlank())
					course.setName(courseName);
				System.out.println("Please provide course department");
				courseDepartment = scan.nextLine();
				if (!courseDepartment.isBlank())
					course.setDepartment(courseDepartment);
				System.out.println("Please provide course description");
				courseDescription = scan.nextLine();
				if (!courseDescription.isBlank())
					course.setDescription(courseDescription);
				System.out.println("Please provide prerequisite ID");
				prereqId = scan.nextLine();
				if (!prereqId.isBlank())
					course.setPrereqCourseID(Integer.parseInt(prereqId));
				try {
					adminService.updateCourse(course);
				} catch (CourseNotFoundException e) {
					System.out.println(e.getMessage() + e.getCourseID());
				}
				break;
			case 3:
				System.out.println("You have selected Delete course");
				this.displayCourses(courseService.viewAllCourses());
				System.out.println("Please select the course to edit");
				try {
					adminService.deleteCourse(scan.nextInt());
				} catch (CourseNotFoundException e) {
					System.out.println(e.getMessage() + e.getCourseID());
				}
				break;
			case 4:
				System.out.println("You have selected View all courses");
				this.displayCourses(courseService.viewAllCourses());
				break;
			case 5:
				professor = new Professor();
				System.out.println("You have selected Create professor");
				System.out.println("Please provide professor's username");
				professor.setUsername(scan.nextLine());
				System.out.println("Please provide professor's password");
				professor.setPassword(scan.nextLine());
				System.out.println("Please provide professor's name");
				professor.setName(scan.nextLine());
				try {
					adminService.createProfessor(professor);
				} catch (UsernameUsedException e) {
					System.out.println(e.getMessage() + e.getUsername());
				}
				// TODO all cases with method called from service layer
				break;
			case 6:
				System.out.println("You have selected Approve student registration");
				try {
					this.displayStudents(adminService.unregisteredStudent());
					System.out.println("Select the student you want to register");
					student = adminService.getStudentById(Integer.parseInt(scan.nextLine()));
					adminService.approveStudentRegistration(student);
				} catch (StudentNotFoundException e) {
					System.out.println(e.getMessage() + e.getStudentID());
				} catch (AllStudentRegisteredException e) {
					System.out.println(e.getMessage()); // All students are already registered
				}
				break;
			case 7:
				System.out.println("Please press enter to log out");
				scan.nextLine();
				return false;
			default:
				System.out.println("Method is not implemented or invalid input");

			}
		} catch (NumberFormatException e) {
			System.out.println("Bad input");
		}
		return true;
	}
	//
	private void displayCourses(List<Course> courses) {
		System.out.println(
				"CourseID \t Course Name \t Department \t Description \t\t Professor \t Prerequisite CourseID");
		for (Course c : courses) {
			if (c.getProf() != null)
				System.out.println(c.getCourseID() + "\t\t" + c.getName() + "\t" + c.getDepartment() + "\t\t"
						+ c.getDescription() + "\t" + c.getProf().getName() + "\t" + c.getPrereqCourseID());
			else
				System.out.println(c.getCourseID() + "\t\t" + c.getName() + "\t" + c.getDepartment() + "\t\t"
						+ c.getDescription() + "\t" + "No Professor" + "\t" + c.getPrereqCourseID());
		}
	}

	private void displayStudents(List<Student> students) {
		System.out.println("StudentID \t Student Name");
		for (Student s : students) {
			System.out.println(s.getStudentID() + "\t\t" + s.getName());
		}
	}

}
