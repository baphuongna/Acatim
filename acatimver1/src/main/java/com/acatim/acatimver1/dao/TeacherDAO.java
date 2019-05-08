package com.acatim.acatimver1.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.Teacher;
import com.acatim.acatimver1.form.TeacherForm;
import com.acatim.acatimver1.mapper.TeacherFormMapper;
import com.acatim.acatimver1.mapper.TeacherMapper;

@Repository
@Transactional
public class TeacherDAO extends JdbcDaoSupport {

	@Autowired
	public TeacherDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public Teacher getTeacherByAccount(String userName) {
		String sql = TeacherMapper.BASE_SQL + " where user_name = ? ";

		Object[] params = new Object[] { userName };
		TeacherMapper mapper = new TeacherMapper();
		try {
			Teacher teacher = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return teacher;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	public int countTeacher() {
		String sqlDate = "select count(*) from User INNER JOIN Teacher ON User.user_name = Teacher.user_name;";
		try {
			int count = this.getJdbcTemplate().queryForObject(sqlDate, Integer.class);
			return count;
		} catch (Exception e) {
			return 0;
		}
	}

	public TeacherForm getUserTeacherByUserName(String userName) {
		String sql = "SELECT * FROM User INNER JOIN Teacher ON User.user_name = Teacher.user_name Where User.user_name = ?;";
		Object[] params = new Object[] { userName };

		TeacherFormMapper mapper = new TeacherFormMapper();
		try {

			TeacherForm teacher = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return teacher;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public boolean updateTeacherInfo(Teacher teacher) {
		try {
			String sql = "UPDATE Teacher SET DOB = ?, gender = ?, description = ? WHERE user_name = ?;";
			this.getJdbcTemplate().update(sql, teacher.getDob(), teacher.isGender(), teacher.getDescription(),
					teacher.getUserName());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateRateTeacher(float rate, String userName) {
		try {
			String sql = "UPDATE Teacher SET rate = ? WHERE user_name = ?;";
			this.getJdbcTemplate().update(sql, rate, userName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean addTeacherInfo(Teacher teacher) {
		try {
			String sql = "INSERT INTO Teacher (user_name, DOB, gender, description, rate)\r\n"
					+ "VALUES (?, ?, ?, ?, ?);";
			this.getJdbcTemplate().update(sql, teacher.getUserName(), teacher.getDob(), teacher.isGender(),
					teacher.getDescription(), teacher.getRate());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
