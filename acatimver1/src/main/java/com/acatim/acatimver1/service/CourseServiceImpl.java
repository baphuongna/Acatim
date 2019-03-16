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
	
	public void removeCourse(String courseId) {
		boolean active = false;
		this.courseDAO.removeCourse(courseId, active);
	}
	
	public List<Course> searchCourseByCourseName(String courseName){
		return this.courseDAO.searchCourseByCourseName(courseName);
	}
	
	public List<Course> searchCourseBySubjectName(String subjectName){
		return this.courseDAO.searchCourseBySubjectName(subjectName);
	}
	
	public List<Course> getCourseBySubjectId(String subjectId){
		return this.courseDAO.getCourseBySubjectId(subjectId);
	}
	
	public List<Course> getCourseByUserName(String userName){
		return this.courseDAO.getCourseByUserName(userName);
	}
	
	public String genCourseId() {
		List<Course> listCourse = this.courseDAO.getListCourse();
		int incNumber = listCourse.size() + 1;
		String newCourseId = "cou" + incNumber;
		return newCourseId;
	}
}
