package com.acatim.acatimver1.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.mapper.StudentMapper;
import com.acatim.acatimver1.model.Student;

@Repository
@Transactional
public class StudentDAO extends JdbcDaoSupport {
	@Autowired
	public StudentDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public Student findInfoUserAccount(String userName) {
		String sql = StudentMapper.BASE_SQL + " where u.user_name = ? ";

		Object[] params = new Object[] { userName };
		StudentMapper mapper = new StudentMapper();
		try {
			Student userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
}
