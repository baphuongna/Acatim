package com.acatim.acatimver1.dao;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.ConfirmEmail;
import com.acatim.acatimver1.entity.CountByDate;
import com.acatim.acatimver1.entity.SearchValue;
import com.acatim.acatimver1.entity.UserModel;
import com.acatim.acatimver1.mapper.UserMapper;
import com.acatim.acatimver1.service.PageableService;

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

	public static <T> T[] append(T[] arr, T element) {
		final int N = arr.length;
		arr = Arrays.copyOf(arr, N + 1);
		arr[N] = element;
		return arr;
	}

	public List<UserModel> getAllUsers(SearchValue search) {

		try {
			String sql = "SELECT * FROM User INNER JOIN Role ON User.role_id = Role.role_id ";

			Object[] params = new Object[] {};
			UserMapper mapper = new UserMapper();
			if (search.getRoleId() != null) {
				if (search.getRoleId().equals("1")) {
					sql += " INNER JOIN Student ON User.user_name = Student.user_name ";
				} else if (search.getRoleId().equals("2")) {
					sql += " INNER JOIN Teacher ON User.user_name = Teacher.user_name ";
				} else if (search.getRoleId().equals("3")) {
					sql += " INNER JOIN StudyCenter ON User.user_name = StudyCenter.user_name ";
				}
			}

			if (search.isAdmin() == false) {
				sql += " Where User.role_id < 4 and user.active = 1 ";
			} else {
				sql += " Where User.role_id < 4 ";
			}

			if (search.getValue() == null && search.getSearch() != null) {
				if (search.getSearch().trim().length() != 0) {
					sql += " and User.full_name like ? or User.address like ? ";
					params = append(params, "%" + search.getSearch() + "%");
					params = append(params, "%" + search.getSearch() + "%");
				}
			}

			if (search.getRoleId() != null) {
				sql += " and User.role_id = ? ";
				params = append(params, search.getRoleId());
			}

			if (search.getRateFilter() != null) {
				if (Integer.parseInt(search.getRateFilter()) == 5) {
					sql += " and rate >= ? ";
					params = append(params, Integer.parseInt(search.getRateFilter()) - 1);
				} else {
					sql += " and rate >= ? and rate < ?";
					params = append(params, Integer.parseInt(search.getRateFilter()) - 1);
					params = append(params, search.getRateFilter());
				}
			}

			if (search.getValue() != null && search.getValue().equals("userName")) {
				if (search.getSearch() != null && search.getSearch().trim().length() != 0) {
					sql += " and User.user_name like ? ";
					params = append(params, "%" + search.getSearch() + "%");
				}
			}

			if (search.getValue() != null && search.getValue().equals("fullName")) {
				if (search.getSearch() != null && search.getSearch().trim().length() != 0) {
					sql += " and User.full_name like ? ";
					params = append(params, "%" + search.getSearch() + "%");
				}
			}

			if (search.getValue() != null && search.getValue().equals("email")) {
				if (search.getSearch() != null && search.getSearch().trim().length() != 0) {
					sql += " and User.email like ? ";
					params = append(params, "%" + search.getSearch() + "%");
				}
			}

			List<UserModel> userInfo = null;

			userInfo = this.getJdbcTemplate().query(sql, params, mapper);

			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<UserModel> getAllUsersPageable(PageableService pageable, SearchValue search) {

		try {
			String sql = "SELECT * FROM User INNER JOIN Role ON User.role_id = Role.role_id ";

			Object[] params = new Object[] {};
			UserMapper mapper = new UserMapper();
			if (search.getRoleId() != null) {
				if (search.getRoleId().equals("1")) {
					sql += " INNER JOIN Student ON User.user_name = Student.user_name ";
				} else if (search.getRoleId().equals("2")) {
					sql += " INNER JOIN Teacher ON User.user_name = Teacher.user_name ";
				} else if (search.getRoleId().equals("3")) {
					sql += " INNER JOIN StudyCenter ON User.user_name = StudyCenter.user_name ";
				}
			}
			if (search.isAdmin() == false) {
				sql += " Where User.role_id < 4 and user.active = 1 ";
			} else {
				sql += " Where User.role_id < 4 ";
			}

			if (search.getValue() == null && search.getSearch() != null) {
				if (search.getSearch().trim().length() != 0) {
					sql += " and User.full_name like ? or User.address like ? ";
					params = append(params, "%" + search.getSearch() + "%");
					params = append(params, "%" + search.getSearch() + "%");
				}
			}

			if (search.getRoleId() != null) {
				sql += " and User.role_id = ? ";
				params = append(params, search.getRoleId());
			}

			if (search.getRateFilter() != null) {
				if (Integer.parseInt(search.getRateFilter()) == 5) {
					sql += " and rate >= ? ";
					params = append(params, Integer.parseInt(search.getRateFilter()) - 1);
				} else {
					sql += " and rate >= ? and rate < ?";
					params = append(params, Integer.parseInt(search.getRateFilter()) - 1);
					params = append(params, search.getRateFilter());
				}
			}

			if (search.getValue() != null && search.getValue().equals("userName")) {
				if (search.getSearch() != null && search.getSearch().trim().length() != 0) {
					sql += " and User.user_name like ? ";
					params = append(params, "%" + search.getSearch() + "%");
				}
			}

			if (search.getValue() != null && search.getValue().equals("fullName")) {
				if (search.getSearch() != null && search.getSearch().trim().length() != 0) {
					sql += " and User.full_name like ? ";
					params = append(params, "%" + search.getSearch() + "%");
				}
			}

			if (search.getValue() != null && search.getValue().equals("email")) {
				if (search.getSearch() != null && search.getSearch().trim().length() != 0) {
					sql += " and User.email like ? ";
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

			List<UserModel> userInfo = null;

			userInfo = this.getJdbcTemplate().query(sql, params, mapper);

			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public CountByDate countStudentByDate() {
		CountByDate count = new CountByDate();
		try {
			String sqlDate = "select count(*) from User INNER JOIN Student ON User.user_name = Student.user_name where date(create_date)=date(date_sub(now(),interval 1 day));";
			int countDate = this.getJdbcTemplate().queryForObject(sqlDate, Integer.class);

			String sqlMonth = "select count(*) from User INNER JOIN Student ON User.user_name = Student.user_name where month(create_date)=month(date_sub(now(),interval 1 day));";
			int countMonth = this.getJdbcTemplate().queryForObject(sqlMonth, Integer.class);

			String sqlYear = "select count(*) from User INNER JOIN Student ON User.user_name = Student.user_name where year(create_date)=year(now());";
			int countYear = this.getJdbcTemplate().queryForObject(sqlYear, Integer.class);

			count.setByDate(countDate);
			count.setByMonth(countMonth);
			count.setByYear(countYear);

			return count;
		} catch (Exception e) {
			return null;
		}
	}

	public CountByDate countTeacherByDate() {
		CountByDate count = new CountByDate();
		try {
			String sqlDate = "select count(*) from User INNER JOIN Teacher ON User.user_name = Teacher.user_name where date(create_date)=date(date_sub(now(),interval 1 day));";
			int countDate = this.getJdbcTemplate().queryForObject(sqlDate, Integer.class);

			String sqlMonth = "select count(*) from User INNER JOIN Teacher ON User.user_name = Teacher.user_name where month(create_date)=month(date_sub(now(),interval 1 day));";
			int countMonth = this.getJdbcTemplate().queryForObject(sqlMonth, Integer.class);

			String sqlYear = "select count(*) from User INNER JOIN Teacher ON User.user_name = Teacher.user_name where year(create_date)=year(now());";
			int countYear = this.getJdbcTemplate().queryForObject(sqlYear, Integer.class);

			count.setByDate(countDate);
			count.setByMonth(countMonth);
			count.setByYear(countYear);

			return count;
		} catch (Exception e) {
			return null;
		}
	}

	public CountByDate countStudyCentertByDate() {
		CountByDate count = new CountByDate();
		try {
			String sqlDate = "select count(*) from User INNER JOIN StudyCenter ON User.user_name = StudyCenter.user_name where date(create_date)=date(date_sub(now(),interval 1 day));";
			int countDate = this.getJdbcTemplate().queryForObject(sqlDate, Integer.class);

			String sqlMonth = "select count(*) from User INNER JOIN StudyCenter ON User.user_name = StudyCenter.user_name where month(create_date)=month(date_sub(now(),interval 1 day));";
			int countMonth = this.getJdbcTemplate().queryForObject(sqlMonth, Integer.class);

			String sqlYear = "select count(*) from User INNER JOIN StudyCenter ON User.user_name = StudyCenter.user_name where year(create_date)=year(now());";
			int countYear = this.getJdbcTemplate().queryForObject(sqlYear, Integer.class);

			count.setByDate(countDate);
			count.setByMonth(countMonth);
			count.setByYear(countYear);

			return count;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean addUser(UserModel user) {
		try {
			String sql = "INSERT INTO User (user_name,role_id,full_name,email,password,create_date,phone,address,active) VALUES (?,?,?,?,?,?,?,?,?);";
			this.getJdbcTemplate().update(sql, user.getUserName(), user.getRole_id(), user.getFullName(),
					user.getEmail(), user.getPassword(), user.getCreateDate(), user.getPhone(), user.getAddress(),
					user.isActive());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean removeUser(String userName, boolean active) {
		try {
			String sql = "UPDATE User SET active = ? WHERE user_name = ?;";
			this.getJdbcTemplate().update(sql, active, userName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateUser(UserModel user) {
		try {
			String sql = "UPDATE User SET full_name = ?, email = ?, phone = ?, address = ? WHERE user_name = ?;";
			this.getJdbcTemplate().update(sql, user.getFullName(), user.getEmail(), user.getPhone(), user.getAddress(),
					user.getUserName());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateAvatar(String userName, String avatar) {
		try {
			String sql = "UPDATE User SET avatar = ? WHERE user_name = ?;";
			this.getJdbcTemplate().update(sql, avatar, userName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean changePassword(String userName, String password) {
		try {
			String sql = "UPDATE User SET password = ? WHERE user_name = ?;";
			this.getJdbcTemplate().update(sql, password, userName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public UserModel findAccConfirm(String userName, String email) {
		String sql = "Select * From User "
				+ " INNER JOIN confirmemail ON User.user_name = confirmemail.user_name where User.user_name = ? and User.email = ?;";

		Object[] params = new Object[] { userName, email };
		UserMapper mapper = new UserMapper();
		try {
			UserModel userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public boolean addConfirm(ConfirmEmail confirmEmail) {
		try {
			String sql = "INSERT INTO confirmemail (`user_name`, `key`, `status`) VALUES (?,?,?);";
			this.getJdbcTemplate().update(sql, confirmEmail.getUserName(), confirmEmail.getKey(),
					confirmEmail.isStatus());
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean updateConfirm(String userName, boolean status) {
		try {
			String sql = "UPDATE confirmemail SET status = ? WHERE user_name = ?;;";
			this.getJdbcTemplate().update(sql, status, userName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean removeContact(String userName, boolean active) {
		try {
			String sql = "UPDATE ContactUs SET isActive = ? WHERE id = ?;";
			this.getJdbcTemplate().update(sql, active, userName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
