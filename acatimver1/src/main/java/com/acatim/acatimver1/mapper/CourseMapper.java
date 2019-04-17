package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.entity.Categories;
import com.acatim.acatimver1.entity.Course;
import com.acatim.acatimver1.entity.Subject;
import com.acatim.acatimver1.entity.UserModel;

public class CourseMapper implements RowMapper<Course> {

	public static final String BASE_SQL //
			= "Select * From Course c ";

	@Override
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

		Course course = null;

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

		course = new Course(courseId, subjectId, userName, courseName, courseDescription, startTime, endTime, startDate,
				endDate, price, createDate, updateDate, active);

		try {
			if (subjectId != null) {
				Subject subject = new Subject();
				int categoryId = rs.getInt("category_id");
				String subjectName = rs.getString("subject_name");
				String createDate1 = rs.getString("Subject.create_date");
				String updateDate1 = rs.getString("Subject.update_date");
				boolean active1 = rs.getBoolean("Subject.active");
				subject = new Subject(subjectId, categoryId, subjectName, createDate1, updateDate1, active1);
				course.setSubject(subject);
			}
		} catch (Exception e) {
		}

		try {
			if (userName != null) {
				UserModel userModel = new UserModel();
				int roleId = rs.getInt("role_id");
				String fullName = rs.getString("full_name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String createDate2 = rs.getString("User.create_date");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				boolean active2 = rs.getBoolean("User.active");

				userModel = new UserModel(userName, roleId, fullName, email, password, createDate2, phone, address,
						active2);

				course.setUserModel(userModel);
			}
		} catch (Exception e) {
		}

		try {
			Categories categories = null;
			int categoryId = rs.getInt("Categories.category_id");
			String categoryName = rs.getString("Categories.category_name");
			String createDate3 = rs.getString("Categories.create_date");
			String updateDate3 = rs.getString("Categories.update_date");
			boolean active3 = rs.getBoolean("Categories.active");

			categories = new Categories(categoryId, categoryName, createDate3, updateDate3, active3);

			course.getSubject().setCategory(categories);

		} catch (Exception e) {
		}

		return course;
	}

}
