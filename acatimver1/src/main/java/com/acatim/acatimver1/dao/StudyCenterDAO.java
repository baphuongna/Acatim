package com.acatim.acatimver1.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.StudyCenter;
import com.acatim.acatimver1.form.StudyCenterForm;
import com.acatim.acatimver1.mapper.StudyCenterFormMapper;
import com.acatim.acatimver1.mapper.StudyCenterMapper;

@Repository
@Transactional
public class StudyCenterDAO extends JdbcDaoSupport {
	@Autowired
	public StudyCenterDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public StudyCenter findInfoUserAccount(String userName) {
		String sql = StudyCenterMapper.BASE_SQL + " where user_name = ? ";

		Object[] params = new Object[] { userName };
		StudyCenterMapper mapper = new StudyCenterMapper();
		try {
			StudyCenter userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public int countStudyCenter() {
		String sqlDate = "select count(*) from User INNER JOIN StudyCenter ON User.user_name = StudyCenter.user_name;";
		int count = this.getJdbcTemplate().queryForObject(sqlDate, Integer.class);

		return count;
	}

	public StudyCenterForm getUserStudyCenterByUserName(String userName) {
		String sql = "SELECT * FROM User INNER JOIN StudyCenter ON User.user_name = StudyCenter.user_name Where User.user_name = ?;";
		Object[] params = new Object[] { userName };

		StudyCenterFormMapper mapper = new StudyCenterFormMapper();
		try {

			StudyCenterForm teacher = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return teacher;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void updateStudyCenterInfo(StudyCenter studyCenter) {
		String sql = "UPDATE StudyCenter SET description = ? WHERE user_name = ?;";
		this.getJdbcTemplate().update(sql, studyCenter.getDescription(), studyCenter.getUserName());
	}

	public void updateRateStudyCenter(float rate, String userName) {
		String sql = "UPDATE StudyCenter SET rate = ? WHERE user_name = ?;";
		this.getJdbcTemplate().update(sql, rate, userName);
	}

	public void addStudyCenterInfo(StudyCenter studyCenter) {
		String sql = "INSERT INTO StudyCenter (user_name, description, rate)\r\n" + "VALUES (?, ?, ?);";
		this.getJdbcTemplate().update(sql, studyCenter.getUserName(), studyCenter.getDescription(),
				studyCenter.getRate());
	}
}
