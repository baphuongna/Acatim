package com.acatim.acatimver1.dao;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.CountByDate;
import com.acatim.acatimver1.entity.Course;
import com.acatim.acatimver1.entity.SearchValue;
import com.acatim.acatimver1.mapper.CourseByName;
import com.acatim.acatimver1.mapper.CourseExtractor;
import com.acatim.acatimver1.mapper.CourseMapper;
import com.acatim.acatimver1.service.PageableService;

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
	
	public Course getCourseById(String courseId) {
		String sql = "SELECT * FROM Course where course_id = ?;";
		Object[] params = new Object[] { courseId };
		CourseMapper mapper = new CourseMapper();
		Course course = this.getJdbcTemplate().queryForObject(sql, params, mapper);
		return course;
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
	
	public List<Course> getCourseByUserNameWithFullInfo(String userName) {
		String sql = "SELECT * FROM Course,User where (Course.user_name = ?) and (User.user_name = ?);";
		Object[] params = new Object[] { userName, userName };
		List<Course> courses = this.getJdbcTemplate().query(sql, params, new CourseByName());
		return courses;
	}

	public List<Course> searchCourseByCourseName(String courseName) {
		String sql = "SELECT * FROM Course \n" + 
				"INNER JOIN Subject ON Course.subject_id = Subject.subject_id\n" + 
				"INNER JOIN User ON Course.user_name = User.user_name where Course.courseName LIKE ?;";
		Object[] params = new Object[] { "%" + courseName + "%" };
		List<Course> courses = this.getJdbcTemplate().query(sql, params, new CourseExtractor());
		return courses;
	}

	public List<Course> searchCourseBySubjectName(String subjectName) {
		String sql = "SELECT * FROM Course \n" + 
				"INNER JOIN Subject ON Course.subject_id = Subject.subject_id\n" + 
				"INNER JOIN User ON Course.user_name = User.user_name where Subject.subject_name LIKE ?;";
		Object[] params = new Object[] { "%" + subjectName + "%" };
		List<Course> courses = this.getJdbcTemplate().query(sql, params, new CourseExtractor());
		return courses;
	}
	
	/* _______________________________________ */
	
	public static <T> T[] append(T[] arr, T element) {
		final int N = arr.length;
		arr = Arrays.copyOf(arr, N + 1);
		arr[N] = element;
		return arr;
	}
	
	
	public List<Course> getAllCourse(SearchValue search) {
		String sql = "SELECT * FROM Course ";
		
		Object[] params = new Object[] {};
		
		if(search.getCategoryId() != null) {
			sql += " INNER JOIN Subject ON Course.subject_id = Subject.subject_id ";
		}
		
		sql += " where Course.active = 1 ";
		
		if(search.getSubjectId() != null) {
			sql += " and Course.subject_id = ? ";
			params = append(params, search.getSubjectId());
		}
		
		if(search.getCategoryId() != null) {
			sql += " and Subject.category_id = ? ";
			params = append(params, search.getCategoryId());
		}
		
		if(search.getUserName() != null) {
			sql += " and Course.user_name = ? and Course.active = 0";
			params = append(params, search.getUserName());
		}

		if(search.getSearch() != null) {
			if(search.getSearch().trim().length() != 0) {
				sql += " and Course.courseName like ? ";
				params = append(params, "%"+search.getSearch()+"%");
			}
		}

		CourseMapper mapper = new CourseMapper();
		List<Course> courses = this.getJdbcTemplate().query(sql, params, mapper);
		return courses;
	}
	
	
	public List<Course> getAllCoursePaging(PageableService pageable, SearchValue search) {
		String sql = "SELECT * FROM Course ";
		
		Object[] params = new Object[] {};
		
		
		sql += " INNER JOIN Subject ON Course.subject_id = Subject.subject_id ";
		
		
		sql += " where Course.active = 1 ";
		
		if(search.getSubjectId() != null) {
			sql += " and Course.subject_id = ? ";
			params = append(params, search.getSubjectId());
		}
		
		if(search.getCategoryId() != null) {
			sql += " and Subject.category_id = ? ";
			params = append(params, search.getCategoryId());
		}
		
		if(search.getUserName() != null) {
			sql += " and Course.user_name = ? ";
			params = append(params, search.getUserName());
		}
		
		if(search.getSearch() != null) {
			if(search.getSearch().trim().length() != 0) {
				sql += " and Course.courseName like ? ";
				params = append(params, "%"+search.getSearch()+"%");
			}
		}
		
		if(pageable.sort() != null) {
			for (Order o : pageable.sort()) {
				sql += " ORDER BY " + o.getProperty() + " " + o.getDirection().toString() + " ";
			}
		}
		
		sql += " LIMIT ?, ?;";
		
		params = append(params, pageable.getOffset());
		params = append(params, pageable.getPageSize());
		
		CourseMapper mapper = new CourseMapper();
		List<Course> courses = this.getJdbcTemplate().query(sql, params, mapper);
		return courses;
	}
	
	public CountByDate countCourseByDate() {
		CountByDate count = new CountByDate();
		
		String sqlDate = "select count(*) from Course where date(create_date)=date(date_sub(now(),interval 1 day));";
		int countDate = this.getJdbcTemplate().queryForObject(sqlDate, Integer.class);
		
		String sqlMonth = "select count(*) from Course where month(create_date)=month(date_sub(now(),interval 1 day));";
		int countMonth = this.getJdbcTemplate().queryForObject(sqlMonth, Integer.class);
		
		String sqlYear = "select count(*) from Course where year(create_date)=year(now());";
		int countYear = this.getJdbcTemplate().queryForObject(sqlYear, Integer.class);
		
		count.setByDate(countDate);
		count.setByMonth(countMonth);
		count.setByYear(countYear);
		
		return count;
	}
	
	/* _______________________________________ */
	
	public void addCourse(Course course) {
		String sql = "INSERT INTO Course (course_id, subject_id, user_name, courseName, courseDescription, start_time, end_time, start_date, end_date, price, create_date, update_date)\r\n"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		this.getJdbcTemplate().update(sql, course.getCourseId(), course.getSubjectId(), course.getUserName(), course.getCourseName(),
				course.getCourseDescription(), course.getStartTime(), course.getEndTime(), course.getStartDate(),
				course.getEndDate(), course.getPrice(), course.getCreateDate(), course.getUpdateDate());
	}

	public void updateCourse(Course course) {
		String sql = "UPDATE Course\r\n"
				+ "SET subject_id = ?, user_name = ?, courseName = ?, courseDescription = ?, start_time = ?,\r\n"
				+ "end_time = ?, start_date = ?, end_date = ?, price = ?, create_date = ?, update_date = ?\r\n"
				+ "WHERE course_id = ?;";
		this.getJdbcTemplate().update(sql, course.getSubjectId(), course.getUserName(), course.getCourseName(),
				course.getCourseDescription(), course.getStartTime(), course.getEndTime(), course.getStartDate(),
				course.getEndDate(), course.getPrice(), course.getCreateDate(), course.getUpdateDate() , course.getCourseId());
	}

	public void removeCourse(String courseId, boolean active) {
		String sql = "UPDATE Course SET active = ? WHERE course_id = ?;";
		this.getJdbcTemplate().update(sql, active, courseId);
	}
}
