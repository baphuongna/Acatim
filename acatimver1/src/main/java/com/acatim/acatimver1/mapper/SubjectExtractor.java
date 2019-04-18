package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.acatim.acatimver1.entity.Course;
import com.acatim.acatimver1.entity.Subject;

public class SubjectExtractor implements ResultSetExtractor<List<Subject>>{

	@Override
	public List<Subject> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<String, Subject> map = new HashMap<String, Subject>();
		Subject subject = null;
		while (rs.next()) {

			
			String subjectId = rs.getString("subject_id");
			subject = map.get(subjectId);

			if (subject == null) {
				int categoryId = rs.getInt("category_id");
				String subjectName = rs.getString("subject_name");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				boolean active = rs.getBoolean("active");
				List<Course> courses = new ArrayList<Course>();
				subject = new Subject(subjectId, categoryId, subjectName, createDate, updateDate, active, courses);
				
				
				map.put(subjectId, subject);
			}

			// continue process
			try {
				String courseId = rs.getString("course_id");
				if (subjectId != null) {
					Course course = null;
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
					subject.getCourses().add(course);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		return new ArrayList<Subject>(map.values());
	}

}
