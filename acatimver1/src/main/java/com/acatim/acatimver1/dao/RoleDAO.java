package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class RoleDAO  extends JdbcDaoSupport {
 
    @Autowired
    public RoleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    public List<String> getRoleNames(int i) {
        String sql = "Select r.role_name " //
                + " from User_Role ur, Role r " //
                + " where ur.role_id = r.role_id and ur.user_id = ? ";
 
        Object[] params = new Object[] { i };
 
        List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);
 
        return roles;
    }
}
