package com.lti.app;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.lti.bean.Course;
import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.NoStudentsEnrolledException;
import com.lti.exception.StudentNotFoundException;
import com.lti.service.CourseService;
import com.lti.service.CourseServiceInterface;
import com.lti.service.ProfessorService;
import com.lti.service.ProfessorServiceInterface;
import com.lti.service.StudentService;
import com.lti.service.StudentServiceInterface;

public class CRSProfessorMenu {

	public boolean professorMenu(Professor professor, Scanner scan) {

		StudentServiceInterface studentService = new StudentService();
		CourseServiceInterface courseService = new CourseService();
		ProfessorServiceInterface professorService = new ProfessorService();
		int professorId = professor.getProfID();

		try {
			System.out.println("*****Welcome Professor " + LocalDateTime.now() + "*****");
			System.out.println("Enter your choice: ");
			System.out.println("1. Apply to course");
			System.out.println("2. Record grade");
			System.out.println("3. View students");
			System.out.println("4. Log out");
			int professorChoice = Integer.parseInt(scan.nextLine());
			switch (professorChoice) {
			case 1:
				System.out.println("You have selected Apply to course");
				this.displayCourses(courseService.viewAllCourses());
				System.out.println("Please select the course ID");
				int courseId = Integer.parseInt(scan.nextLine());
				try {
					professorService.applyToCourse(professor.getProfID(), courseId);
				} catch (CourseNotFoundException e) {
					System.out.println(e.getMessage() + e.getCourseID());
				}
				break;
			case 2:
				System.out.println("You have selected Record grade");
				this.displayCourses(professorService.viewProfessorCourses(professorId));
				System.out.println("Please select the course ID to view students");
				courseId = Integer.parseInt(scan.nextLine());
				try {
					this.displayStudents(professorService.viewStudents(courseId));
					System.out.println("Please select the student ID to add grade");
					int studentId = Integer.parseInt(scan.nextLine());
					System.out.println("Please enter the grade");
					double grade = Double.parseDouble(scan.nextLine());

					try {
						professorService.recordGrade(grade, studentId, courseId);
					} catch (StudentNotFoundException e) {
						System.out.println(e.getMessage() + e.getStudentID());
					} catch (CourseNotFoundException e) {
						System.out.println(e.getMessage() + e.getCourseID());
					}
				} catch (CourseNotFoundException e) {
					System.out.println(e.getMessage() + e.getCourseID());
				} catch (NoStudentsEnrolledException e) {
					System.out.println(e.getMessage() + e.getCourseID());
				}
				break;
			// TODO all cases with method called from service layer
			case 3:
				System.out.println("You have selected View students");
				List<Course> courses = professorService.viewProfessorCourses(professorId);
				if (courses.isEmpty()) {
					System.out.println("You have no courses");
					break;
				}
				this.displayCourses(courses);
				System.out.println("Please select the course ID to view students");
				courseId = Integer.parseInt(scan.nextLine());
				try {
					Map<Student, Double> viewStudentGrades = professorService.viewStudentsGrades(professorId, courseId);
					if (!viewStudentGrades.isEmpty()) {
						for (Student s : viewStudentGrades.keySet()) {
							System.out.println("Id: " + s.getStudentID() + "\nName: " + s.getName() + "\nGrade"
									+ viewStudentGrades.get(s));
						}
					}
				} catch (CourseNotFoundException e) {
					System.out.println(e.getMessage() + e.getCourseID());
				} catch (NoStudentsEnrolledException e) {
					System.out.println(e.getMessage() + e.getCourseID());
				}
				break;
			case 4:
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

	private void displayStudents(List<Student> students) {
		System.out.println("StudentID \t Student Name");
		for (Student s : students) {
			System.out.println(s.getStudentID() + "\t\t" + s.getName());
		}
	}

}
