package com.lti.service;

import java.util.List;

import com.lti.bean.Course;

public interface CourseServiceInterface {
	
	public void getNumberStudents();
	public void checkAvailability();
	public void notifyStudent();
	public List<Course> viewAllCourses();
	public List<Course> viewUnpayedCourses();
	public Course viewCourseByID(int id);
}
