package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.model.StudyCenter;

public class StudyCenterMapper implements RowMapper<StudyCenter>{

	public static final String BASE_SQL //
	= "Select * From StudyCenter sc ";
	
	@Override
	public StudyCenter mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		String userName = rs.getString("user_name");
		String description = rs.getString("description");
		float rate = rs.getFloat("rate");
		
		return new StudyCenter(userName, description, rate);
	}

}
