package com.acatim.acatimver1.service;

import java.util.List;

import com.acatim.acatimver1.entity.CountByDate;
import com.acatim.acatimver1.entity.Course;
import com.acatim.acatimver1.entity.SearchValue;

public interface CourseService {
	
	List<Course> getAllCourse();

	boolean addCourse(Course course);
	
	boolean updateCourse(Course course);

	boolean removeCourse(String courseId);
	
	boolean unlockCourse(String courseId);
	
	Course getCourseById(String courseId);

	List<Course> searchCourseByCourseName(String courseName);

	List<Course> searchCourseBySubjectName(String subjectName);

	List<Course> getCourseBySubjectId(String subjectId);

	List<Course> getCourseByUserName(String userName);
	
	List<Course> getCourseByUserNameWithFullInfo(String userName);

	String genCourseId();
	
	List<Course> getAllCoursePaging(PageableService pageable, SearchValue search);
	
	List<Course> getAllCourse(SearchValue search);
	
	CountByDate countCourseByDate();
}
