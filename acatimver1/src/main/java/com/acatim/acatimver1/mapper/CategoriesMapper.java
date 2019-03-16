package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.model.Categories;

public class CategoriesMapper implements RowMapper<Categories>{

	public static final String BASE_SQL //
	= "Select * From Categories cate ";
	
	@Override
	public Categories mapRow(ResultSet rs, int rowNum) throws SQLException {

		int categoryId = rs.getInt("category_id");
		String categoryName = rs.getString("category_name");
		String createDate = rs.getString("create_date");
		String updateDate = rs.getString("update_date");
		boolean active = rs.getBoolean("active");
		
		return new Categories(categoryId, categoryName, createDate, updateDate, active);
	}

}
