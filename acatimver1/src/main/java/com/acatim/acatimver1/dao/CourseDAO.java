package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.mapper.CourseExtractor;
import com.acatim.acatimver1.model.Course;

@Repository
@Transactional
public class CourseDAO extends JdbcDaoSupport{

	
	@Autowired
	public CourseDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	public List<Course> getAllCourse() {
		String sql = "SELECT *\r\n" + 
				"FROM Course\r\n" + 
				"INNER JOIN Subject ON Course.subject_id = Subject.subject_id;";


		List<Course> courses = this.getJdbcTemplate().query(sql, new CourseExtractor());

		return courses;
	}
}
