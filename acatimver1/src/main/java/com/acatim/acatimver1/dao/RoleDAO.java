package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.Role;
import com.acatim.acatimver1.mapper.RoleMapper;

@Repository
@Transactional
public class RoleDAO extends JdbcDaoSupport {

	@Autowired
	public RoleDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public List<String> getRoleNames(String id) {
		String sql = "Select r.role_name " //
				+ " from User ur, Role r " //
				+ " where ur.role_id = r.role_id and ur.user_name = ? ";

		Object[] params = new Object[] { id };
		try {
			List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);

			return roles;
		} catch (Exception e) {
			return null;
		}
	}

	public String getRoleNameByUserName(String userName) {
		String sql = "Select r.role_name, r.role_id " //
				+ " from User ur, Role r " //
				+ " where ur.role_id = r.role_id and ur.user_name = ?;";

		Object[] params = new Object[] { userName };
		RoleMapper mapper = new RoleMapper();
		try {
			Role roleInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return roleInfo.getRoleName();
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
}
