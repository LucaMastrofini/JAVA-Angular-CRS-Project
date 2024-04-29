package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.CourseDAO;
import com.lti.dao.CourseDAOInterface;
import com.lti.dto.Course;

/**
 * unused class
 * @author Nikhil, Luca
 *
 */
@Service
public class CourseService implements CourseServiceInterface{
	
	private Course course;
	@Autowired
	private CourseDAO courseDAO;
	
	public void getNumberStudents() {
		//get number of students that applied on this course
		//this.course.getStudents().size();
	}
	
	/**
	 * unused method
	 */
	public void checkAvailability() {
		//check if course is not full (>10)
		//if(this.course.getStudents().size() < 10) {
			System.out.println("This course is available");
		//}
		//else {
			System.out.println("This course is not available");
		//}
	}
	
	public void notifyStudent() {
		//notify student if course isn't available
		
	}
	
	public List <Course> viewAllCourses() {
		
		return courseDAO.findAllCourses();
		
	}

	@Override
	public List<Course> viewUnpayedCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course viewCourseByID(int id) {
		return courseDAO.findCourseByCourseID(id);
	}
	

}
