package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	public List<UserModel> getRoleNames(String id) {
		String sql = UserMapper.BASE_SQL;

		UserMapper mapper = new UserMapper();
		List<UserModel> userList = this.getJdbcTemplate().query(sql, mapper);

		return userList;
	}
}
