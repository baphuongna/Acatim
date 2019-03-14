package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.CourseDAO;
import com.acatim.acatimver1.model.Course;

@Service
public class CourseServiceImpl {
	
	@Autowired
	private CourseDAO courseDAO;
	
	public List<Course> getAllCourse(){
		return this.courseDAO.getAllCourse();
	}
	
	public void addCourse(Course course) {
		this.courseDAO.addCourse(course);
	}
	
	public void updateCourse(Course course) {
		this.courseDAO.updateCourse(course);
	}
}
