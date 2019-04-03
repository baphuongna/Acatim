package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.Status;
import com.acatim.acatimver1.mapper.StatusMapper;

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
	
	public List<Status> getAllStatusPageble(Pageable pageable) {
		String sql = "SELECT * FROM Status LIMIT ?, ?;";
		Object[] params = new Object[] { pageable.getOffset(), pageable.getPageSize() };
		List<Status> discountCode = this.getJdbcTemplate().query(sql, params, new StatusMapper());
		return discountCode;
	}

	public void addStatus(Status status) {
		String sql = "INSERT INTO Status (id_change, value_changed, fixer, date_change)\r\n" + "VALUES (?, ?, ?, ?);";
		this.getJdbcTemplate().update(sql, status.getIdChange(), status.getValueChanged(), status.getBy(), status.getDateChange());
	}

	public void updateStatus(Status status) {
		String sql = "UPDATE Status SET id_change= ?, value_changed = ? , fixer = ? , date_change = ? WHERE id = ?;";
		this.getJdbcTemplate().update(sql, status.getIdChange(), status.getValueChanged(), status.getBy(), status.getDateChange() , status.getId());
	}
	
	
}
