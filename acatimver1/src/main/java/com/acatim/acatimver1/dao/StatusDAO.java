package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.mapper.StatusMapper;
import com.acatim.acatimver1.model.Status;

@Repository
@Transactional
public class StatusDAO extends JdbcDaoSupport {

	@Autowired
	public StatusDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public List<Status> getAllStatus() {
		String sql = "SELECT * FROM Status";
		List<Status> discountCode = this.getJdbcTemplate().query(sql, new StatusMapper());
		return discountCode;
	}

	public void addStatus(Status status) {
		String sql = "INSERT INTO Status (id, value, lastManagerChangeName)\r\n" + "VALUES (?, ?, ?);";
		this.getJdbcTemplate().update(sql, status.getId(), status.getValue(), status.getLastManagerChangeName());
	}

	public void updateStatus(Status status) {
		String sql = "UPDATE Status SET value = ?, lastManagerChangeName = ? WHERE id = ?;";
		this.getJdbcTemplate().update(sql, status.getValue(), status.getLastManagerChangeName(), status.getId());
	}
}
