package com.acatim.acatimver1.dao;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.History;
import com.acatim.acatimver1.entity.SearchValue;
import com.acatim.acatimver1.mapper.StatusMapper;
import com.acatim.acatimver1.service.PageableService;

@Repository
@Transactional
public class HistoryDAO extends JdbcDaoSupport {

	@Autowired
	public HistoryDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public List<History> getAllHistory() {
		String sql = "SELECT * FROM History";
		try {
			List<History> discountCode = this.getJdbcTemplate().query(sql, new StatusMapper());
			return discountCode;
		} catch (Exception e) {
			return null;
		}
	}
	
	public int countAllHistory(SearchValue search) {
		String sql = "SELECT count(*) FROM History ";
		Object[] params = new Object[] {};
		try {
			
			if (search.getSearch() != null) {
				if (search.getSearch().trim().length() != 0) {
					sql += " where id_change like ? OR value_changed like ? OR fixer like ?";
					params = append(params, "%" + search.getSearch() + "%");
					params = append(params, "%" + search.getSearch() + "%");
					params = append(params, "%" + search.getSearch() + "%");
				}
			}

			int count = this.getJdbcTemplate().queryForObject(sql, params, Integer.class);
			return count;
		} catch (Exception e) {
			e.fillInStackTrace();
			System.out.println(e);
			return 0;
		}
	}

	public List<History> getAllHistoryPageble(PageableService pageable, SearchValue search) {
		String sql = "SELECT * FROM History ";
		Object[] params = new Object[] {};
		try {
			
			if (search.getSearch() != null) {
				if (search.getSearch().trim().length() != 0) {
					sql += " where id_change like ? OR value_changed like ? OR fixer like ?";
					params = append(params, "%" + search.getSearch() + "%");
					params = append(params, "%" + search.getSearch() + "%");
					params = append(params, "%" + search.getSearch() + "%");
				}
			}
			
			if (pageable.sort() != null) {
				for (Order o : pageable.sort()) {
					sql += " ORDER BY " + o.getProperty() + " " + o.getDirection().toString() + " ";
				}
			}

			sql += " LIMIT ?, ?;";
			params = append(params, pageable.getOffset());
			params = append(params, pageable.getPageSize());
			
			List<History> discountCode = this.getJdbcTemplate().query(sql, params, new StatusMapper());
			return discountCode;
		} catch (Exception e) {
			e.fillInStackTrace();
			System.out.println(e);
			return null;
		}
	}

	public void addHistory(History history) {
		String sql = "INSERT INTO History (id_change, value_changed, fixer, date_change)\r\n" + "VALUES (?, ?, ?, ?);";
		this.getJdbcTemplate().update(sql, history.getIdChange(), history.getValueChanged(), history.getBy(),
				history.getDateChange());
	}

	public void updateHistory(History history) {
		String sql = "UPDATE History SET id_change= ?, value_changed = ? , fixer = ? , date_change = ? WHERE id = ?;";
		this.getJdbcTemplate().update(sql, history.getIdChange(), history.getValueChanged(), history.getBy(),
				history.getDateChange(), history.getId());
	}
	
	public static <T> T[] append(T[] arr, T element) {
		final int N = arr.length;
		arr = Arrays.copyOf(arr, N + 1);
		arr[N] = element;
		return arr;
	}

}
