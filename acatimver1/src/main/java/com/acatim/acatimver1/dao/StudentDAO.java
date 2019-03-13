package com.acatim.acatimver1.dao;

import java.util.List;

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
	
	public List<Student> getAllStudent() {
		String sql = StudentMapper.BASE_SQL;
		
		StudentMapper mapper = new StudentMapper();
		try {
			List<Student> userInfo = this.getJdbcTemplate().query(sql, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void updateTeacherInfo(Student student) {
		String sql = "UPDATE Student SET DOB = ?, gender = ? WHERE user_name = ?;";
		this.getJdbcTemplate().update(sql, student.getDob(), student.isGender(),
				student.getUserName());
	}
}
