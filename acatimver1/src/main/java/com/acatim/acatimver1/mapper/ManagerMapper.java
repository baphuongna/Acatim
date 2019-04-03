package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.entity.Manager;

public class ManagerMapper implements RowMapper<Manager>{

	public static final String BASE_SQL //
	= "Select * From Manager m ";
	
	@Override
	public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {

		String userName = rs.getString("user_name");
		String dob = rs.getString("DOB");
		boolean gender = rs.getBoolean("gender");
		String description = rs.getString("description");
		
		return new Manager(userName, dob, gender, description);
	}

}
