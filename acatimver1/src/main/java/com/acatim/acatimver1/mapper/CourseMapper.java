package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.model.Course;

public class CourseMapper implements RowMapper<Course>{

	public static final String BASE_SQL //
	= "Select * From Course c ";
	
	@Override
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		String courseId = rs.getString("course_id");
		String subjectId = rs.getString("subject_id");
		String userName = rs.getString("user_name");
		String courseName = rs.getString("courseName");
		String courseDescription = rs.getString("courseDescription");
		String startTime = rs.getString("start_time");
		String endTime = rs.getString("end_time");
		String startDate = rs.getString("start_date");
		String endDate = rs.getString("end_date");
		float price = rs.getFloat("price");
		String createDate = rs.getString("create_date");
		String updateDate = rs.getString("update_date");
		boolean active = rs.getBoolean("active");
		
		return new Course(courseId, subjectId, userName, courseName, courseDescription, startTime, endTime, startDate, endDate, price, createDate, updateDate, active);
	}

}
