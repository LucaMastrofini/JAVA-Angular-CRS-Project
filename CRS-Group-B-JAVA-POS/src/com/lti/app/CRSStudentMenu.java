package com.lti.app;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.lti.bean.*;
import com.lti.exception.AllCoursesPaidException;
import com.lti.exception.CourseFullException;
import com.lti.exception.CourseNotFoundException;
import com.lti.service.CourseService;
import com.lti.service.CourseServiceInterface;
import com.lti.service.StudentService;

public class CRSStudentMenu {

	public boolean studentMenu(Student student, Scanner scan) {

		StudentService studentService = new StudentService();
		CourseServiceInterface courseService = new CourseService();
		int studentId = student.getStudentID();

		try {
			System.out.println("*****Welcome Student " + student.getName() + " " + LocalDateTime.now() + "*****");
			System.out.println("Enter your choice: ");
			System.out.println("1. Apply to course");
			System.out.println("2. Drop course");
			System.out.println("3. View applied courses");
			System.out.println("4. Make payment");
			System.out.println("5. Check grades");
			System.out.println("6. View all courses");
			System.out.println("7. Log out");
			int studentChoice = scan.nextInt();
			switch (studentChoice) {
			case 1:

				System.out.println("You have selected apply to course");
				this.displayCourses(courseService.viewAllCourses());
				System.out.println("Please select the course ID");
				int courseId = scan.nextInt();
				// Parameters (studentId, courseId)
				try {
					studentService.applyToCourse(studentId, courseId);
				} catch (CourseNotFoundException e) {
					System.out.println(e.getMessage() + e.getCourseID());
				} catch (CourseFullException e) {
					System.out.println(e.getMessage() + e.getCourseID());
				}
				break;

			case 2:
				System.out.println("You have selected Drop Course");
				this.displayCourses(studentService.viewAppliedCourses(studentId));
				System.out.println("Please select the course ID");
				courseId = scan.nextInt();
				// Parameters (studentId, courseId)
				try {
					studentService.dropCourse(studentId, courseId);
				} catch (CourseNotFoundException e) {
					System.out.println(e.getMessage() + e.getCourseID());
				}
				break;

			case 3:
				System.out.println("You have selected Display applied Courses");
				this.displayCourses(studentService.viewAppliedCourses(studentId));
				break;

			case 4:
				System.out.println("You have selected Make Payment");
				System.out.println("List of unpayed courses");
				try {
					this.displayCourses(studentService.viewUnpayedCourses(studentId));
					System.out.println("Please select what course to pay");
					courseId = scan.nextInt();

					studentService.makePayment(studentId, courseId);
				} catch (AllCoursesPaidException e) {
					System.out.println(e.getMessage() + e.getStudentID());
				} catch (CourseNotFoundException e) {
					System.out.println(e.getMessage() + e.getCourseID());
				}
				break;

			case 5:
				System.out.println("You selected Check Grades");
				Map<Course, Double> courseAndGrades = studentService.checkGrades(studentId);
				if (courseAndGrades.isEmpty())
					System.out.println("You have no courses");
				else {
					for (Course c : courseAndGrades.keySet()) {
						System.out.println("Id: " + c.getCourseID() + "\tName: " + c.getName() + "\tDepartment: "
								+ c.getDepartment() + "\tGrade" + courseAndGrades.get(c));
					}
				}
				break;
			case 6:
				System.out.println("You selected Display all Courses");
				List<Course> courses = courseService.viewAllCourses();
				displayCourses(courses);

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

}
