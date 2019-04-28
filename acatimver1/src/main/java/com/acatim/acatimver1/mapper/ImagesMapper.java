package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.acatim.acatimver1.entity.Images;

public class ImagesMapper implements RowMapper<Images>{
	
	public static final String BASE_SQL //
	= "Select * From images";

	@Override
	public Images mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		int id = rs.getInt("id");
		String linkimage = rs.getString("linkimage");
		String userName = rs.getString("user_name");
		String description = rs.getString("description");
		String createDate = rs.getString("create_date");
		String updateDate = rs.getString("update_date");
		boolean active = rs.getBoolean("active");
		
		return new Images(id, userName, linkimage, description, createDate, updateDate, active);
	}

}
