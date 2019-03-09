package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.model.RateTeacher;

public class RateTeacherMapper implements RowMapper<RateTeacher>{

	public static final String BASE_SQL //
	= "Select * From RateTeacher rt ";
	
	@Override
	public RateTeacher mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		String rateId = rs.getString("rate_id");
		float easyLevel = rs.getFloat("easyLevel");
		float examDifficulty = rs.getFloat("examDifficulty");
		float textbookUse = rs.getFloat("textbookUse");
		float helpfulLevel = rs.getFloat("helpfulLevel");
		float clarityLevel = rs.getFloat("clarityLevel");
		float knowledgeable = rs.getFloat("knowledgeable");
		
		return new RateTeacher(rateId, easyLevel, examDifficulty, textbookUse, helpfulLevel, clarityLevel, knowledgeable);
	}

}
