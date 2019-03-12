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
import com.acatim.acatimver1.model.Subject;

public class CourseExtractor implements ResultSetExtractor<List<Course>> {

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
				course = new Course(courseId, subjectId, userName, courseName, courseDescription, startTime, endTime,
						startDate, endDate, price, createDate, updateDate);
				map.put(courseId, course);
			}

			// continue process
			String subjectId = rs.getString("subject_id");
			if (subjectId != null) {
				Subject subject = new Subject();
				int categoryId = rs.getInt("category_id");
				String subjectName = rs.getString("subject_name");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				subject = new Subject(subjectId, categoryId, subjectName, createDate, updateDate);
				course.setSubject(subject);
			}
		}
		return new ArrayList<Course>(map.values());
	}

}
