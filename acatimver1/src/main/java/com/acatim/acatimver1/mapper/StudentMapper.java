package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.entity.Student;

public class StudentMapper implements RowMapper<Student>{

	public static final String BASE_SQL //
	= "SELECT * FROM Student";
	
	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		String userName = rs.getString("user_name");
		String dob = rs.getString("DOB");
		boolean gender = rs.getBoolean("gender");
		
		return new Student(userName, dob, gender);
	}

}
