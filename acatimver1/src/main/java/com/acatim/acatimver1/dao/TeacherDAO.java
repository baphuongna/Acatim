package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.Teacher;
import com.acatim.acatimver1.entity.UserModel;
import com.acatim.acatimver1.form.TeacherForm;
import com.acatim.acatimver1.mapper.TeacherFormMapper;
import com.acatim.acatimver1.mapper.TeacherMapper;
import com.acatim.acatimver1.mapper.UserMapper;

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

	public List<UserModel> getAllUserTeacher() {
		String sql = "SELECT * FROM User INNER JOIN Role ON User.role_id = Role.role_id INNER JOIN Teacher ON User.user_name = Teacher.user_name;";
		try {
			UserMapper mapper = new UserMapper();
			List<UserModel> userInfo = this.getJdbcTemplate().query(sql, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
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

	public void updateTeacherInfo(Teacher teacher) {
		String sql = "UPDATE Teacher SET DOB = ?, gender = ?, description = ? WHERE user_name = ?;";
		this.getJdbcTemplate().update(sql, teacher.getDob(), teacher.isGender(), teacher.getDescription(),
				teacher.getUserName());
	}

	public void updateTotalRateTeacher(Teacher teacher) {
		String sql = "UPDATE Teacher SET rate = ? WHERE user_name = ?;";
		this.getJdbcTemplate().update(sql, teacher.getRate(), teacher.getUserName());
	}

	public void addTeacherInfo(Teacher teacher) {
		String sql = "INSERT INTO Teacher (user_name, DOB, gender, description, rate)\r\n" + "VALUES (?, ?, ?, ?, ?);";
		this.getJdbcTemplate().update(sql, teacher.getUserName(), teacher.getDob(), teacher.isGender(),
				teacher.getDescription(), teacher.getRate());
	}
}
