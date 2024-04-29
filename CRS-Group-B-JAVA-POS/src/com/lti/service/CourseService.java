package com.lti.service;

import java.util.List;

import com.lti.bean.Course;
import com.lti.dao.CourseDAO;
import com.lti.dao.CourseDAOInterface;

public class CourseService implements CourseServiceInterface{
	
	private Course course;
	private CourseDAOInterface courseDAO = new CourseDAO();
	
	public void getNumberStudents() {
		//get number of students that applied on this course
		this.course.getStudents().size();
	}
	
	public void checkAvailability() {
		//check if course is not full (>10)
		if(this.course.getStudents().size() < 10) {
			System.out.println("This course is available");
		}
		else {
			System.out.println("This course is not available");
		}
	}
	
	public void notifyStudent() {
		//notify student if course isn't available
		
	}
	
	public List <Course> viewAllCourses() {
		
		return courseDAO.viewAllCourses();
		
	}

	@Override
	public List<Course> viewUnpayedCourses() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
