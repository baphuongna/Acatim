package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.mapper.TeacherMapper;
import com.acatim.acatimver1.model.Teacher;

@Repository
@Transactional
public class TeacherDAO extends JdbcDaoSupport {

	@Autowired
	public TeacherDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public Teacher getTeacherByAccount(String userName) {
		String sql = TeacherMapper.BASE_SQL + " where u.user_name = ? ";

		Object[] params = new Object[] { userName };
		TeacherMapper mapper = new TeacherMapper();
		try {
			Teacher userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<Teacher> getAllTeacher() {
		String sql = TeacherMapper.BASE_SQL;

		TeacherMapper mapper = new TeacherMapper();
		try {
			List<Teacher> userInfo = this.getJdbcTemplate().query(sql, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
}
