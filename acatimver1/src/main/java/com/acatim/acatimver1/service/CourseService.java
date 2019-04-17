package com.acatim.acatimver1.service;

import java.util.List;

import com.acatim.acatimver1.entity.Course;
import com.acatim.acatimver1.entity.SearchValue;

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

	List<Course> getAllCoursePaging(PageableService pageable);

	List<Course> searchAllCoursePaging(PageableService pageable, String courseName);

	List<Course> getAllCourseBySujectId(String subjectId);

	List<Course> searchAllCourseBySujectId(String subjectId, String courseName);

	List<Course> getAllCourseBySujectIdPaging(PageableService pageable, String subjectId);

	List<Course> searchAllCourseBySujectIdPaging(PageableService pageable, String subjectId, String courseName);

	List<Course> getAllCourseByCateId(String cateId);

	List<Course> searchAllCourseByCateId(String cateId, String courseName);

	List<Course> getAllCourseByCateIdPaging(PageableService pageable, String cateId);

	List<Course> searchAllCourseByCateIdPaging(PageableService pageable, String cateId, String courseName);
	
	List<Course> getAllCourseByUserName(PageableService pageable, String userName, String subjectId);
	
	List<Course> getAllCourse(PageableService pageable, SearchValue search);
}
