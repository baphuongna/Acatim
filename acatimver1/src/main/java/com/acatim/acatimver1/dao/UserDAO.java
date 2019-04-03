package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.Contact;
import com.acatim.acatimver1.entity.UserModel;
import com.acatim.acatimver1.mapper.ContactExtractor;
import com.acatim.acatimver1.mapper.ContactMapper;
import com.acatim.acatimver1.mapper.UserExtractor;
import com.acatim.acatimver1.mapper.UserMapper;

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

	public UserModel findUserAccountByEmail(String email) {
		String sql = UserMapper.BASE_SQL + " where u.email = ? ";

		Object[] params = new Object[] { email };
		UserMapper mapper = new UserMapper();
		try {
			UserModel userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public boolean checkUserExist(String userName) {
		String sql = UserMapper.BASE_SQL;

		UserMapper mapper = new UserMapper();
		List<UserModel> userList = this.getJdbcTemplate().query(sql, mapper);
		for (UserModel user : userList) {
			if (user.getUserName().equals(userName)) {
				return true;
			}
		}
		return false;
	}
	
	public List<UserModel> getAllTeacherST() {
		try {
			String sql = "SELECT * FROM User Where User.role_id < 4 and User.role_id > 1;";
			UserMapper mapper = new UserMapper();
			List<UserModel> userInfo = this.getJdbcTemplate().query(sql, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<UserModel> getAllManager() {
		try {
			String sql = "SELECT * FROM User Where User.role_id = 4;";
			UserMapper mapper = new UserMapper();
			List<UserModel> userInfo = this.getJdbcTemplate().query(sql, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<UserModel> getAllUsers(String roleId) {
		try {
			if (roleId == null) {
				String sql = "SELECT * FROM User INNER JOIN Role ON User.role_id = Role.role_id Where User.role_id < 4 ;";
				List<UserModel> userInfo = this.getJdbcTemplate().query(sql, new UserExtractor());
				return userInfo;
			} else {
				String sql = "SELECT * FROM User INNER JOIN Role ON User.role_id = Role.role_id Where User.role_id < 4 and User.role_id = ?;";
				Object[] params = new Object[] { roleId };
				List<UserModel> userInfo = this.getJdbcTemplate().query(sql, params, new UserExtractor());
				return userInfo;
			}

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<UserModel> getAllUsersPageable(Pageable pageable, String roleId) {

		try {

			if (roleId == null) {
				String sql = "SELECT * FROM User INNER JOIN Role ON User.role_id = Role.role_id Where User.role_id < 4 LIMIT ?, ?;";
				Object[] params = new Object[] { pageable.getOffset(), pageable.getPageSize() };
				List<UserModel> userInfo = this.getJdbcTemplate().query(sql, params, new UserExtractor());
				return userInfo;
			} else {
				String sql = "SELECT * FROM User INNER JOIN Role ON User.role_id = Role.role_id Where User.role_id < 4 and User.role_id = ? LIMIT ?, ?;";
				Object[] params = new Object[] { roleId, pageable.getOffset(), pageable.getPageSize() };
				List<UserModel> userInfo = this.getJdbcTemplate().query(sql, params, new UserExtractor());
				return userInfo;
			}

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<UserModel> searchAllUsersByUserName(Pageable pageable, String userName, String roleId) {

		try {

			if (roleId == null) {
				String sql = "SELECT * FROM User INNER JOIN Role ON User.role_id = Role.role_id Where User.user_name LIKE ? and User.role_id < 4 LIMIT ?, ?;";
				Object[] params = new Object[] { "%" + userName + "%", pageable.getOffset(), pageable.getPageSize() };
				List<UserModel> userInfo = this.getJdbcTemplate().query(sql, params, new UserExtractor());
				return userInfo;
			} else {
				String sql = "SELECT * FROM User INNER JOIN Role ON User.role_id = Role.role_id Where User.user_name LIKE ? and User.role_id < 4 and User.role_id = ? LIMIT ?, ?;";
				Object[] params = new Object[] { "%" + userName + "%", roleId, pageable.getOffset(),
						pageable.getPageSize() };
				List<UserModel> userInfo = this.getJdbcTemplate().query(sql, params, new UserExtractor());
				return userInfo;
			}

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<UserModel> searchAllUsersByEmail(Pageable pageable, String email, String roleId) {

		try {

			if (roleId == null) {
				String sql = "SELECT * FROM User INNER JOIN Role ON User.role_id = Role.role_id Where User.email LIKE ? and User.role_id < 4 LIMIT ?, ?;";
				Object[] params = new Object[] { "%" + email + "%", pageable.getOffset(), pageable.getPageSize() };
				List<UserModel> userInfo = this.getJdbcTemplate().query(sql, params, new UserExtractor());
				return userInfo;
			} else {
				String sql = "SELECT * FROM User INNER JOIN Role ON User.role_id = Role.role_id Where User.email LIKE ? and User.role_id < 4 and User.role_id = ? LIMIT ?, ?;";
				Object[] params = new Object[] { "%" + email + "%", roleId, pageable.getOffset(),
						pageable.getPageSize() };
				List<UserModel> userInfo = this.getJdbcTemplate().query(sql, params, new UserExtractor());
				return userInfo;
			}

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<UserModel> searchAllUsersByFullName(Pageable pageable, String fullName, String roleId) {

		try {

			if (roleId == null) {
				String sql = "SELECT * FROM User INNER JOIN Role ON User.role_id = Role.role_id Where User.full_name LIKE ? and User.role_id < 4 LIMIT ?, ?;";
				Object[] params = new Object[] { "%" + fullName + "%", pageable.getOffset(), pageable.getPageSize() };
				List<UserModel> userInfo = this.getJdbcTemplate().query(sql, params, new UserExtractor());
				return userInfo;
			} else {
				String sql = "SELECT * FROM User INNER JOIN Role ON User.role_id = Role.role_id Where User.full_name LIKE ? and User.role_id < 4 and User.role_id = ? LIMIT ?, ?;";
				Object[] params = new Object[] { "%" + fullName + "%", roleId, pageable.getOffset(),
						pageable.getPageSize() };
				List<UserModel> userInfo = this.getJdbcTemplate().query(sql, params, new UserExtractor());
				return userInfo;
			}

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<UserModel> searchUserByName(String fullName) {
		String sql = UserMapper.BASE_SQL + " WHERE full_name LIKE ?";
		Object[] params = new Object[] { "%" + fullName + "%" };
		UserMapper mapper = new UserMapper();
		List<UserModel> user = this.getJdbcTemplate().query(sql, params, mapper);

		return user;
	}

	public List<UserModel> searchUserByEmail(String email) {
		String sql = UserMapper.BASE_SQL + " WHERE email LIKE ?";
		Object[] params = new Object[] { "%" + email + "%" };
		UserMapper mapper = new UserMapper();
		List<UserModel> user = this.getJdbcTemplate().query(sql, params, mapper);
		return user;
	}

	public void addUser(UserModel user) {
		String sql = "INSERT INTO User (user_name,role_id,full_name,email,password,create_date,phone,address,active) VALUES (?,?,?,?,?,?,?,?,?);";
		this.getJdbcTemplate().update(sql, user.getUserName(), user.getRole_id(), user.getFullName(), user.getEmail(),
				user.getPassword(), user.getCreateDate(), user.getPhone(), user.getAddress(), user.isActive());
	}

	public void removeUser(String userName, boolean active) {
		String sql = "UPDATE User SET active = ? WHERE user_name = ?;";
		this.getJdbcTemplate().update(sql, active, userName);
	}

	public void updateUser(UserModel user) {
		String sql = "UPDATE User SET full_name = ?, email = ?, phone = ?, address = ? WHERE user_name = ?;";
		this.getJdbcTemplate().update(sql, user.getFullName(), user.getEmail(), user.getPhone(), user.getAddress(),
				user.getUserName());
	}

	public void changePassword(String userName, String password) {
		String sql = "UPDATE User SET password = ? WHERE user_name = ?;";
		this.getJdbcTemplate().update(sql, password, userName);
	}
	// contact

	public List<Contact> getAllContact() {
		try {
			String sql = ContactMapper.BASE_SQL;
			List<Contact> contact = this.getJdbcTemplate().query(sql, new ContactExtractor());
			return contact;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Contact> searchAllContactByUserName(Pageable pageable, String name) {
		try {
			    String sql = ContactMapper.BASE_SQL + " WHERE name LIKE ? LIMIT ?, ?;";
				Object[] params = new Object[] { "%" + name + "%", pageable.getOffset(), pageable.getPageSize() };
				List<Contact> contact = this.getJdbcTemplate().query(sql, params, new ContactExtractor());
				return contact;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Contact> getAllContactPageable(Pageable pageable) {
		try {
			    String sql = ContactMapper.BASE_SQL + " LIMIT ?, ?;";
				Object[] params = new Object[] {pageable.getOffset(), pageable.getPageSize() };
				List<Contact> contact = this.getJdbcTemplate().query(sql, params, new ContactExtractor());
				return contact;

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Contact> searchAllContactByEmail(Pageable pageable, String email) {
		try {
			    String sql = ContactMapper.BASE_SQL + " WHERE email LIKE ? LIMIT ?, ?;";
				Object[] params = new Object[] { "%" + email + "%", pageable.getOffset(), pageable.getPageSize() };
				List<Contact> contact = this.getJdbcTemplate().query(sql, params, new ContactExtractor());
				return contact;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void removeContact(String userName, boolean active) {
		String sql = "UPDATE ContactUs SET isActive = ? WHERE id = ?;";
		this.getJdbcTemplate().update(sql, active, userName);
	}

}
