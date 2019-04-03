package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.acatim.acatimver1.entity.Course;

public interface CourseService {
	
	List<Course> getAllCourse();

	void addCourse(Course course);
	
	void updateCourse(Course course);

	void removeCourse(String courseId);
	
	void unlockCourse(String courseId);
	
	Course getCourseById(String courseId);

	List<Course> searchCourseByCourseName(String courseName);

	List<Course> searchCourseBySubjectName(String subjectName);

	List<Course> getCourseBySubjectId(String subjectId);

	List<Course> getCourseByUserName(String userName);
	
	List<Course> getCourseByUserNameWithFullInfo(String userName);

	String genCourseId();

	List<Course> getAllCoursePaging(Pageable pageable);

	List<Course> searchAllCoursePaging(Pageable pageable, String courseName);

	List<Course> getAllCourseBySujectId(String subjectId);

	List<Course> searchAllCourseBySujectId(String subjectId, String courseName);

	List<Course> getAllCourseBySujectIdPaging(Pageable pageable, String subjectId);

	List<Course> searchAllCourseBySujectIdPaging(Pageable pageable, String subjectId, String courseName);

	List<Course> getAllCourseByCateId(String cateId);

	List<Course> searchAllCourseByCateId(String cateId, String courseName);

	List<Course> getAllCourseByCateIdPaging(Pageable pageable, String cateId);

	List<Course> searchAllCourseByCateIdPaging(Pageable pageable, String cateId, String courseName);
}
