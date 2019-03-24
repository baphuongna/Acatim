package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.CourseDAO;
import com.acatim.acatimver1.model.Course;

@Service
public class CourseServiceImpl {

	@Autowired
	private CourseDAO courseDAO;

	public List<Course> getAllCourse() {
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
	
	public void unlockCourse(String courseId) {
		boolean active = true;
		this.courseDAO.removeCourse(courseId, active);
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

	public List<Course> getAllCoursePaging(Pageable pageable) {
		return this.courseDAO.getAllCoursePaging(pageable);
	}

	public List<Course> searchAllCoursePaging(Pageable pageable, String courseName) {
		return this.courseDAO.searchAllCoursePaging(pageable, courseName);
	}

	public List<Course> getAllCourseBySujectId(String subjectId) {
		return this.courseDAO.getAllCourseBySujectId(subjectId);
	}

	public List<Course> searchAllCourseBySujectId(String subjectId, String courseName) {

		return this.courseDAO.searchAllCourseBySujectId(subjectId, courseName);
	}

	public List<Course> getAllCourseBySujectIdPaging(Pageable pageable, String subjectId) {

		return this.courseDAO.getAllCourseBySujectIdPaging(pageable, subjectId);
	}

	public List<Course> searchAllCourseBySujectIdPaging(Pageable pageable, String subjectId, String courseName) {

		return this.courseDAO.searchAllCourseBySujectIdPaging(pageable, subjectId, courseName);
	}

	public List<Course> getAllCourseByCateId(String cateId) {

		return this.courseDAO.getAllCourseByCateId(cateId);
	}

	public List<Course> searchAllCourseByCateId(String cateId, String courseName) {

		return this.courseDAO.searchAllCourseByCateId(cateId, courseName);
	}

	public List<Course> getAllCourseByCateIdPaging(Pageable pageable, String cateId) {

		return this.courseDAO.getAllCourseByCateIdPaging(pageable, cateId);
	}

	public List<Course> searchAllCourseByCateIdPaging(Pageable pageable, String cateId, String courseName) {

		return this.courseDAO.searchAllCourseByCateIdPaging(pageable, cateId, courseName);
	}
}
