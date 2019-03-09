package com.acatim.acatimver1.dao;

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
}
