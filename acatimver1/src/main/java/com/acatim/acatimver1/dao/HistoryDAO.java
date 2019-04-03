package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.History;
import com.acatim.acatimver1.mapper.StatusMapper;

@Repository
@Transactional
public class HistoryDAO extends JdbcDaoSupport {

	@Autowired
	public HistoryDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public List<History> getAllHistory() {
		String sql = "SELECT * FROM History";
		List<History> discountCode = this.getJdbcTemplate().query(sql, new StatusMapper());
		return discountCode;
	}
	
	public List<History> getAllHistoryPageble(Pageable pageable) {
		String sql = "SELECT * FROM History LIMIT ?, ?;";
		Object[] params = new Object[] { pageable.getOffset(), pageable.getPageSize() };
		List<History> discountCode = this.getJdbcTemplate().query(sql, params, new StatusMapper());
		return discountCode;
	}

	public void addHistory(History history) {
		String sql = "INSERT INTO History (id_change, value_changed, fixer, date_change)\r\n" + "VALUES (?, ?, ?, ?);";
		this.getJdbcTemplate().update(sql, history.getIdChange(), history.getValueChanged(), history.getBy(), history.getDateChange());
	}

	public void updateHistory(History history) {
		String sql = "UPDATE History SET id_change= ?, value_changed = ? , fixer = ? , date_change = ? WHERE id = ?;";
		this.getJdbcTemplate().update(sql, history.getIdChange(), history.getValueChanged(), history.getBy(), history.getDateChange() , history.getId());
	}
	
	
}
