package com.acatim.acatimver1.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.RateTeacher;
import com.acatim.acatimver1.mapper.RateTeacherMapper;

@Repository
@Transactional
public class RateTeacherDAO extends JdbcDaoSupport {

	@Autowired
	public RateTeacherDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public RateTeacher getRateTeacherByUserName(String rateId) {
		String sql = "SELECT * FROM RateTeacher Where rate_id = ?";
		Object[] params = new Object[] { rateId };
		try {
			RateTeacher rating = this.getJdbcTemplate().queryForObject(sql, params, new RateTeacherMapper());
			return rating;
		} catch (Exception e) {
			return null;
		}
	}

	public void addRateTeacher(RateTeacher rateTeacher) {
		String sql = "INSERT INTO RateTeacher (rate_id, easyLevel, examDifficulty, textbookUse, helpfulLevel, clarityLevel, knowledgeable, checkTeaNull)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		this.getJdbcTemplate().update(sql, rateTeacher.getRateId(), rateTeacher.getEasyLevel(),
				rateTeacher.getExamDifficulty(), rateTeacher.getTextbookUse(), rateTeacher.getHelpfulLevel(),
				rateTeacher.getClarityLevel(), rateTeacher.getKnowledgeable(), rateTeacher.getCheckTeaNull());
	}

	public void updateRateTeacher(RateTeacher rateTeacher) {
		String sql = "UPDATE RateTeacher SET easyLevel = ?, examDifficulty = ?, textbookUse = ?, helpfulLevel = ?, clarityLevel = ?, knowledgeable = ?\r\n"
				+ "WHERE rate_id = ?;";
		this.getJdbcTemplate().update(sql, rateTeacher.getEasyLevel(), rateTeacher.getExamDifficulty(),
				rateTeacher.getTextbookUse(), rateTeacher.getHelpfulLevel(), rateTeacher.getClarityLevel(),
				rateTeacher.getKnowledgeable(), rateTeacher.getRateId());
	}
}
