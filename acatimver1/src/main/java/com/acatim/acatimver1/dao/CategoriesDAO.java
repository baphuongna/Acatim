package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.Categories;
import com.acatim.acatimver1.mapper.CategoriesMapper;

@Repository
@Transactional
public class CategoriesDAO extends JdbcDaoSupport {
	
	@Autowired
	public CategoriesDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	public List<Categories> getAllCategories() {
		try {
			String sql = CategoriesMapper.BASE_SQL;
			CategoriesMapper mapper = new CategoriesMapper();
			List<Categories> userList = this.getJdbcTemplate().query(sql, mapper);
			return userList;
		}catch (Exception e) {
			return null;
		}
	}
	
	public Categories getCategoriesByCategoryId(String categoryId) {
		try {
			String sql = "SELECT * FROM Categories Where category_id = ?;";
			Object[] params = new Object[] { categoryId };
			CategoriesMapper mapper = new CategoriesMapper();
			Categories categories = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return categories;
		}catch (Exception e) {
			return null;
		}
	}
	
	public void addCategories(Categories categories) {
		String sql = "INSERT INTO Categories (category_id, category_name, create_date, update_date, active)\r\n" + 
				"VALUES (?, ?, ?, ?, ?);";
		this.getJdbcTemplate().update(sql, categories.getCategoryId(), categories.getCategoryName(), categories.getCreateDate(), categories.getUpdateDate(), categories.isActive());
	}

	public void updateCategories(Categories categories) {
		String sql = "UPDATE Categories SET category_name = ?, create_date = ?, update_date = ?, active = ?\r\n" + 
				"WHERE category_id = ?;";
		this.getJdbcTemplate().update(sql, categories.getCategoryName(), categories.getCreateDate(), categories.getUpdateDate(), categories.isActive(), categories.getCategoryId());
	}

	public void removeCategories(String categoryId, boolean active) {
		String sql = "UPDATE Categories SET active = ? WHERE category_id = ?;";
		this.getJdbcTemplate().update(sql, active, categoryId);
	}
}
