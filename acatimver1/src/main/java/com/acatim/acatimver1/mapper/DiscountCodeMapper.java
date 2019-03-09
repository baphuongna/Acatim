package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.model.DiscountCode;

public class DiscountCodeMapper implements RowMapper<DiscountCode>{

	public static final String BASE_SQL //
	= "Select * From DiscountCode dc ";
	
	@Override
	public DiscountCode mapRow(ResultSet rs, int rowNum) throws SQLException {

		String codeId = rs.getString("code_id");
		String userName = rs.getString("user_name");
		String courseId = rs.getString("course_id");
		String createDate = rs.getString("create_date");
		String expireDate = rs.getString("expire_date");
		String status = rs.getString("status");
		
		return new DiscountCode(codeId, userName, courseId, createDate, expireDate, status);
	}

}
