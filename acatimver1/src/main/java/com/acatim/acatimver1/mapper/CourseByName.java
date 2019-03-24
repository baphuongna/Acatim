package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.acatim.acatimver1.model.Course;
import com.acatim.acatimver1.model.DiscountCode;
import com.acatim.acatimver1.model.Subject;
import com.acatim.acatimver1.model.UserModel;

public class CourseByName implements ResultSetExtractor<List<Course>> {

	@Override
	public List<Course> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<String, Course> map = new HashMap<String, Course>();
		Course course = null;

		while (rs.next()) {

			String courseId = rs.getString("course_id");
			course = map.get(courseId);

			if (course == null) {
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
				course = new Course(courseId, subjectId, userName, courseName, courseDescription, startTime, endTime,
						startDate, endDate, price, createDate, updateDate, active);
				map.put(courseId, course);
			}
			
			String userName = rs.getString("user_name");
			if (userName != null) {
				UserModel userModel = new UserModel();
				int roleId = rs.getInt("role_id");
				String fullName = rs.getString("full_name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				boolean active = rs.getBoolean("active");
				userModel = new UserModel(userName, roleId, fullName, email, password, createDate, phone, address, active);
				course.setUserModel(userModel);
			}
		}
		return new ArrayList<Course>(map.values());
	}

}
