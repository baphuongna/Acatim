package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.CourseDAO;
import com.acatim.acatimver1.entity.CountByDate;
import com.acatim.acatimver1.entity.Course;
import com.acatim.acatimver1.entity.SearchValue;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseDAO courseDAO;

	public List<Course> getAllCourse() {
		return this.courseDAO.getAllCourse();
	}

	public void addCourse(Course course) {
		course.setActive(true);
		this.courseDAO.addCourse(course);
	}

	public void updateCourse(Course course) {
		this.courseDAO.updateCourse(course);
	}

	public void removeCourse(String courseId) {
		boolean active = false;
		this.courseDAO.removeCourse(courseId, active);
	}
	
	public void unlockCourse(String courseId) {
		boolean active = true;
		this.courseDAO.removeCourse(courseId, active);
	}
	
	public Course getCourseById(String courseId) {
		return this.courseDAO.getCourseById(courseId);
	}

	public List<Course> searchCourseByCourseName(String courseName) {
		return this.courseDAO.searchCourseByCourseName(courseName);
	}

	public List<Course> searchCourseBySubjectName(String subjectName) {
		return this.courseDAO.searchCourseBySubjectName(subjectName);
	}

	public List<Course> getCourseBySubjectId(String subjectId) {
		return this.courseDAO.getCourseBySubjectId(subjectId);
	}

	public List<Course> getCourseByUserName(String userName) {
		return this.courseDAO.getCourseByUserName(userName);
	}
	
	public List<Course> getCourseByUserNameWithFullInfo(String userName){
		return this.courseDAO.getCourseByUserNameWithFullInfo(userName);
	}

	public String genCourseId() {
		List<Course> listCourse = this.courseDAO.getListCourse();
		int incNumber = listCourse.size() + 1;
		String newCourseId = "cou" + incNumber;
		return newCourseId;
	}

	@Override
	public List<Course> getAllCoursePaging(PageableService pageable, SearchValue search) {
		return this.courseDAO.getAllCoursePaging(pageable, search);
	}

	@Override
	public List<Course> getAllCourse(SearchValue search) {
		return this.courseDAO.getAllCourse(search);
	}

	@Override
	public CountByDate countCourseByDate() {
		return this.courseDAO.countCourseByDate();
	}
}
