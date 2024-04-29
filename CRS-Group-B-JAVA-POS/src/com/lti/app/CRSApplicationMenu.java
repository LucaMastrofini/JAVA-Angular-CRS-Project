/**
 * 
 */
package com.lti.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.lti.bean.Admin;
import com.lti.bean.Course;
import com.lti.bean.Professor;
import com.lti.bean.Student;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UsernameUsedException;
import com.lti.service.AdminService;
import com.lti.service.AdminServiceInterface;
import com.lti.service.ProfessorService;
import com.lti.service.ProfessorServiceInterface;
import com.lti.service.StudentService;
import com.lti.service.StudentServiceInterface;
import com.lti.service.UserService;
import com.lti.service.UserServiceInterface;
import com.lti.utils.DBUtils;

/**
 * @author Nikhil, Luca, Muhammad
 *
 */
public class CRSApplicationMenu {

	/**
	 * @param args unused
	 */
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		boolean homeMenu = true;
		CRSAdminMenu adminMenu = new CRSAdminMenu();
		CRSProfessorMenu professorMenu = new CRSProfessorMenu();
		CRSStudentMenu studentMenu = new CRSStudentMenu();
		UserServiceInterface userService = new UserService();
		StudentServiceInterface studentService = new StudentService();
		ProfessorServiceInterface professorService = new ProfessorService();
		AdminServiceInterface adminService = new AdminService();

		while (homeMenu) {
			boolean loginMenu = true;
			Student student = new Student();
			Professor professor = new Professor();
			Admin admin = new Admin();
			System.out.println("*****Welcome to CRS Application*****");
			System.out.println("Enter your choice: ");
			System.out.println("1. Login");
			System.out.println("2. Student registration");
			System.out.println("3. Update password");
			System.out.println("4. Exit");
			try {
				int choice = Integer.parseInt(scan.nextLine());

				switch (choice) {
				// Case statements
				case 1:
					while (loginMenu) {
						boolean userMenu = true;
						System.out.println("Enter Role: ");
						System.out.println("1. Admin");
						System.out.println("2. Professor");
						System.out.println("3. Student");
						System.out.println("4. Back");
						int role = Integer.parseInt(scan.nextLine());
						if (role == 4) {
							System.out.println("Press enter to go back");
							scan.nextLine();
							loginMenu = false;
							break;
						}
						System.out.println("Enter Username: ");
						String username = scan.nextLine();
						System.out.println("Enter Password: ");
						String password = scan.nextLine();
						try {
							boolean logInSuccess = userService.verifyCredetials(username, password, role);
							if (logInSuccess) {
								while (userMenu) {
									switch (role) {
									case 1:
										admin = adminService.getAdminByUsername(username);
										userMenu = adminMenu.adminMenu(admin, scan);
										break;
									case 2:
										professor = professorService.getProfessorByUsername(username);
										userMenu = professorMenu.professorMenu(professor, scan);
										break;
									case 3:
										student = studentService.getStudentByUsername(username);
										userMenu = studentMenu.studentMenu(student, scan);
										break;
									default:
										System.out.println("Invalid Role");
									}

									loginMenu = false;
								}
							} else {
								System.out.println("You entered the wrong credentials");
							}
						} catch (StudentNotFoundException e) {
							System.out.println(e.getMessage() + e.getStudentID());
						}
					}
					break;
				case 2:
					System.out.println("*****Welcome to student registration*****");
					System.out.println("Enter Username: ");
					student.setUsername(scan.nextLine());
					System.out.println("Enter Password: ");
					student.setPassword(scan.nextLine());
					System.out.println("Enter Name: ");
					student.setName(scan.nextLine());
					try {
						studentService.createStudent(student);
					} catch (UsernameUsedException e) {
						System.out.println(e.getMessage() + e.getUsername());
					}
					break;
				case 3:
					System.out.println("*****Welcome to password reset*****");
					System.out.println("Please enter your ID: ");
					int userId = Integer.parseInt(scan.nextLine());
					System.out.println("Please enter your username: ");
					String username = scan.nextLine();
					boolean passwordReset = userService.verifyPasswordResetCredentials(userId, username);
					if (passwordReset) {
						System.out.println("Please enter new password: ");
						String newPassword = scan.nextLine();
						userService.updatePassword(username, newPassword);
					} else {
						System.out.println("Credentials are invalid");
					}
					break;
				case 4:
					System.out.println("Press enter to exit");
					scan.nextLine();
					homeMenu = false;
					// Default case statement
					break;
				default:
					System.out.println("Invalid Operation");
				}
			} catch (NumberFormatException e) {
				System.out.println("Bad input");
			}
		}
		System.out.println("Thank you for using CRS, have a great day!");
		try {
			DBUtils.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		scan.close();
	}

}
