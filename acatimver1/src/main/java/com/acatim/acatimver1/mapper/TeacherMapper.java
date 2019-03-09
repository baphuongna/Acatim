package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.model.Teacher;

public class TeacherMapper implements RowMapper<Teacher>{
	
	public static final String BASE_SQL //
	= "Select * From teacher t ";
	
	@Override
	public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {

		String userName = rs.getString("user_name");
		String dob = rs.getString("DOB");
		boolean gender = rs.getBoolean("gender");
		String description = rs.getString("description");
		float rate = rs.getFloat("rate");
		
		return new Teacher(userName, dob, gender, description, rate);
	}
	
}
