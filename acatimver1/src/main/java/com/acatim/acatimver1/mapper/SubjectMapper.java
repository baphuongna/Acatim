package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.entity.Subject;

public class SubjectMapper implements RowMapper<Subject>{
	
	public static final String BASE_SQL //
	= "Select * From Subject s ";
	
	@Override
	public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {

		String subjectId = rs.getString("subject_id");
		int categoryId = rs.getInt("category_id");
		String subjectName = rs.getString("subject_name");
		String createDate = rs.getString("create_date");
		String updateDate = rs.getString("update_date");
		boolean active = rs.getBoolean("active");
		
		return new Subject(subjectId, categoryId, subjectName, createDate, updateDate, active);
	}

}
