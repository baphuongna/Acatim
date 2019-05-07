package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.entity.Categories;
import com.acatim.acatimver1.entity.Course;
import com.acatim.acatimver1.entity.Subject;

public class SubjectMapper implements RowMapper<Subject> {

	public static final String BASE_SQL //
			= "Select * From Subject s ";

	@Override
	public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {

		Subject subject = null;

		String subjectId = rs.getString("subject_id");
		int categoryId = rs.getInt("category_id");
		String subjectName = rs.getString("subject_name");
		String createDate = rs.getString("create_date");
		String updateDate = rs.getString("update_date");
		boolean active = rs.getBoolean("active");
		subject = new Subject(subjectId, categoryId, subjectName, createDate, updateDate, active);
		
		try {
			String courseId = rs.getString("course_id");
			Course course = null;
			String userName = rs.getString("user_name");
			String courseName = rs.getString("courseName");
			String courseDescription = rs.getString("courseDescription");
			String startTime = rs.getString("start_time");
			String endTime = rs.getString("end_time");
			String startDate = rs.getString("start_date");
			String endDate = rs.getString("end_date");
			String deadline = rs.getString("deadline");
			String position = rs.getString("position");
			float price = rs.getFloat("price");
			String createDate1 = rs.getString("User.create_date");
			String updateDate1 = rs.getString("User.update_date");
			boolean active1 = rs.getBoolean("User.active");
			course = new Course(courseId, subjectId, userName, courseName, courseDescription, startTime, endTime,
					startDate, endDate, price, createDate1, updateDate1, deadline, position, active1);
			subject.getCourses().add(course);
		} catch (Exception e) {
		}
		
		try {
			Categories category = null;
			String categoryName = rs.getString("category_name");
			int categoryId1 = rs.getInt("category_id");
			String createDate1 = rs.getString("create_date");
			String updateDate1 = rs.getString("update_date");
			boolean active1 = rs.getBoolean("active");
			
			category = new Categories(categoryId1, categoryName, createDate1, updateDate1, active1);
			subject.setCategory(category);
		}catch (Exception e) {
		}

		return subject;
	}

}
