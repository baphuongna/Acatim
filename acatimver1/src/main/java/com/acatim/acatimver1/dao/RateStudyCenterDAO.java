package com.acatim.acatimver1.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.mapper.RateStudyCenterMapper;
import com.acatim.acatimver1.model.RateStudyCenter;

@Repository
@Transactional
public class RateStudyCenterDAO extends JdbcDaoSupport {

	@Autowired
	public RateStudyCenterDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public RateStudyCenter getRateStudyCenterByUserName(String rateId) {
		String sql = "SELECT * FROM RateStudyCenter Where rate_id = ?";
		Object[] params = new Object[] { rateId };
		RateStudyCenter rating = this.getJdbcTemplate().queryForObject(sql, params, new RateStudyCenterMapper());
		return rating;
	}

	public void addRateStudyCenter(RateStudyCenter rateStudyCenter) {
		String sql = "INSERT INTO RateStudyCenter (rate_id, equipmentQuality, staffAttitude, reputation, happiness, safety, internet, location, teachingQuality, checkSCNull)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		this.getJdbcTemplate().update(sql, rateStudyCenter.getRateId(), rateStudyCenter.getEquipmentQuality(),
				rateStudyCenter.getStaffAttitude(), rateStudyCenter.getReputation(), rateStudyCenter.getHappiness(),
				rateStudyCenter.getSafety(), rateStudyCenter.getInternet(), rateStudyCenter.getLocation(),
				rateStudyCenter.getTeachingQuality(), rateStudyCenter.getCheckSCNull());
	}

	public void updateRateStudyCenter(RateStudyCenter rateStudyCenter) {
		String sql = "UPDATE RateStudyCenter\r\n"
				+ "SET equipmentQuality = ?, staffAttitude = ?, reputation =?, happiness = ?, safety = ?, internet = ?, location = ?, teachingQuality = ?\r\n"
				+ "WHERE rate_id = ?;";
		this.getJdbcTemplate().update(sql, rateStudyCenter.getEquipmentQuality(),
				rateStudyCenter.getStaffAttitude(), rateStudyCenter.getReputation(), rateStudyCenter.getHappiness(),
				rateStudyCenter.getSafety(), rateStudyCenter.getInternet(), rateStudyCenter.getLocation(),
				rateStudyCenter.getTeachingQuality(), rateStudyCenter.getRateId());
	}
}
