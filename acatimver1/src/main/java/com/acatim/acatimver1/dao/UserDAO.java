package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.mapper.UserExtractor;
import com.acatim.acatimver1.mapper.UserMapper;
import com.acatim.acatimver1.model.UserModel;

@Repository
@Transactional
public class UserDAO extends JdbcDaoSupport {

	@Autowired
	public UserDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public UserModel findUserAccount(String userName) {
		String sql = UserMapper.BASE_SQL + " where u.user_name = ? ";

		Object[] params = new Object[] { userName };
		UserMapper mapper = new UserMapper();
		try {
			UserModel userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	public List<UserModel> getRoleNames() {
		String sql = UserMapper.BASE_SQL;

		UserMapper mapper = new UserMapper();
		List<UserModel> userList = this.getJdbcTemplate().query(sql, mapper);

		return userList;
	}

//	public List<UserModel> getAllUser() {
//		String sql = "SELECT * FROM User INNER JOIN Role ON User.role_id = Role.role_id;";
//		List<UserModel> user = this.getJdbcTemplate().query(sql, new UserExtractor());
//
//		return user;
//	}

	public void addUser(UserModel user) {
		String sql = "INSERT INTO User (user_name,role_id,full_name,email,password,create_date,phone,address,active) VALUES (?,?,?,?,?,?,?,?,?);";
		UserMapper mapper = new UserMapper();
		this.getJdbcTemplate().update(sql, user, mapper);
	}

	public void removeUser(String userName, boolean active) {
		String sql = "UPDATE User SET active = ? WHERE user_name = ?;";
		this.getJdbcTemplate().update(sql, userName, active);
	}

	public void updateUser(UserModel user) {
		String sql = "UPDATE User SET full_name = ?, email = ?, create_date = ?, phone = ?, address = ? WHERE user_name = ?;";
		this.getJdbcTemplate().update(sql, user.getFullName(), user.getEmail(), user.getCreateDate(), user.getPhone(),
				user.getAddress(), user.getUserName());
	}

	public void changePassword(String userName, String password) {
		String sql = "UPDATE User SET password = ? WHERE user_name = ?;";
		this.getJdbcTemplate().update(sql, userName, password);
	}
}
