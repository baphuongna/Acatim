package com.acatim.acatimver1.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.Student;
import com.acatim.acatimver1.form.StudentForm;
import com.acatim.acatimver1.mapper.StudentFormMapper;
import com.acatim.acatimver1.mapper.StudentMapper;

@Repository
@Transactional
public class StudentDAO extends JdbcDaoSupport {
	@Autowired
	public StudentDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public Student findInfoUserAccount(String userName) {
		String sql = StudentMapper.BASE_SQL + " where user_name = ? ";

		Object[] params = new Object[] { userName };
		StudentMapper mapper = new StudentMapper();
		try {
			Student userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	public int countStudent() {
		String sqlDate = "select count(*) from User INNER JOIN Student ON User.user_name = Student.user_name;";
		try {
			int count = this.getJdbcTemplate().queryForObject(sqlDate, Integer.class);
			return count;
		} catch (Exception e) {
			return 0;
		}
	}

	public StudentForm getUserStudentByUserName(String userName) {
		String sql = "SELECT * FROM User INNER JOIN Student ON User.user_name = Student.user_name Where User.user_name = ?;";
		Object[] params = new Object[] { userName };
		StudentFormMapper mapper = new StudentFormMapper();
		try {
			StudentForm student = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return student;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public boolean updateStudentInfo(Student student) {
		try {
			String sql = "UPDATE Student SET DOB = ?, gender = ? WHERE user_name = ?;";
			this.getJdbcTemplate().update(sql, student.getDob(), student.isGender(), student.getUserName());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean addStudentInfo(Student student) {
		try {
			String sql = "INSERT INTO Student (user_name, DOB, gender)\r\n" + "VALUES (?, ?, ?);";
			this.getJdbcTemplate().update(sql, student.getUserName(), student.getDob(), student.isGender());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
