package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.mapper.CourseExtractor;
import com.acatim.acatimver1.mapper.CourseMapper;
import com.acatim.acatimver1.mapper.DiscountCodeMapper;
import com.acatim.acatimver1.model.Course;

@Repository
@Transactional
public class CourseDAO extends JdbcDaoSupport {

	@Autowired
	public CourseDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public List<Course> getAllCourse() {
		String sql = "SELECT *\r\n" + 
				"FROM Course\r\n" + 
				"INNER JOIN Subject ON Course.subject_id = Subject.subject_id\r\n" +
				"INNER JOIN User ON Course.user_name = User.user_name";

		List<Course> courses = this.getJdbcTemplate().query(sql, new CourseExtractor());
		return courses;
	}
	
	public List<Course> getListCourse() {
		String sql = "SELECT * FROM Course";
		CourseMapper mapper = new CourseMapper();
		List<Course> courses = this.getJdbcTemplate().query(sql, mapper);
		return courses;
	}
	
	public List<Course> getCourseBySubjectId(String subjectId) {
		String sql = "SELECT * FROM Course where subject_id = ?;";
		Object[] params = new Object[] { subjectId };
		CourseMapper mapper = new CourseMapper();
		List<Course> courses = this.getJdbcTemplate().query(sql, params, mapper);
		return courses;
	}
	
	public List<Course> getCourseByUserName(String userName) {
		String sql = "SELECT * FROM Course where user_name = ?;";
		Object[] params = new Object[] { userName };
		CourseMapper mapper = new CourseMapper();
		List<Course> courses = this.getJdbcTemplate().query(sql, params, mapper);
		return courses;
	}

	public List<Course> searchCourseByCourseName(String courseName) {
		String sql = "SELECT * FROM Course INNER JOIN Subject ON Course.subject_id = Subject.subject_id where Course.courseName LIKE ?;";
		Object[] params = new Object[] { "%" + courseName + "%" };
		List<Course> courses = this.getJdbcTemplate().query(sql, params, new CourseExtractor());
		return courses;
	}

	public List<Course> searchCourseBySubjectName(String subjectName) {
		String sql = "SELECT * FROM Course INNER JOIN Subject ON Course.subject_id = Subject.subject_id where Subject.subject_name LIKE ?;";
		Object[] params = new Object[] { "%" + subjectName + "%" };
		List<Course> courses = this.getJdbcTemplate().query(sql, params, new CourseExtractor());
		return courses;
	}

	public void addCourse(Course course) {
		String sql = "INSERT INTO Course (course_id, subject_id, user_name, courseName, courseDescription, start_time, end_time, start_date, end_date, price, create_date, update_date)\r\n"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		this.getJdbcTemplate().update(sql, course.getCourseId(), course.getSubjectId(), course.getUserName(),
				course.getCourseDescription(), course.getStartTime(), course.getEndTime(), course.getStartDate(),
				course.getEndDate(), course.getPrice(), course.getCreateDate(), course.getUpdateDate());
	}

	public void updateCourse(Course course) {
		String sql = "UPDATE Course\r\n"
				+ "SET subject_id = ?, user_name = ?, courseName = ?, courseDescription = ?, start_time = ?,\r\n"
				+ "end_time = ?, start_date = ?, end_date = ?, price = ?, create_date = ?, update_date = ?\r\n"
				+ "WHERE course_id = ?;";
		this.getJdbcTemplate().update(sql, course.getSubjectId(), course.getUserName(),
				course.getCourseDescription(), course.getStartTime(), course.getEndTime(), course.getStartDate(),
				course.getEndDate(), course.getPrice(), course.getCreateDate(), course.getUpdateDate() , course.getCourseId());
	}

	public void removeCourse(String courseId, boolean active) {
		String sql = "UPDATE Course SET active = ? WHERE user_name = ?;";
		this.getJdbcTemplate().update(sql, active, courseId);
	}
}
