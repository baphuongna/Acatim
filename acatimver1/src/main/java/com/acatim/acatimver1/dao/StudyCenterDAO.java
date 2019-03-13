package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.mapper.StudyCenterMapper;
import com.acatim.acatimver1.model.StudyCenter;

@Repository
@Transactional
public class StudyCenterDAO extends JdbcDaoSupport {
	@Autowired
	public StudyCenterDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public StudyCenter findInfoUserAccount(String userName) {
		String sql = StudyCenterMapper.BASE_SQL + " where u.user_name = ? ";

		Object[] params = new Object[] { userName };
		StudyCenterMapper mapper = new StudyCenterMapper();
		try {
			StudyCenter userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<StudyCenter> getAllStudyCenter() {
		String sql = StudyCenterMapper.BASE_SQL;

		StudyCenterMapper mapper = new StudyCenterMapper();
		try {
			List<StudyCenter> userInfo = this.getJdbcTemplate().query(sql, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
}
