package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.model.Teacher;

public class TeacherMapper implements RowMapper<Teacher>{
	
	public static final String BASE_SQL //
	= "SELECT u.user_name, u.role_id, u.full_name, u.email, u.password, u.create_date, u.phone, u.address, u.phone, u.active, t.DOB, t.gender, t.description, t.rate\r\n" + 
			"FROM User u\r\n" + 
			"INNER JOIN Teacher t\r\n" + 
			"ON u.user_name=t.user_name";
	
	@Override
	public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {

		String userName = rs.getString("user_name");
		int roleId = rs.getInt("role_id");
		String fullName = rs.getString("full_name");
		String email = rs.getString("email");
		String password = rs.getString("password");
		String createDate = rs.getString("create_date");
		String phone = rs.getString("phone");
		String address = rs.getString("address");
		boolean active = rs.getBoolean("active");
		String dob = rs.getString("DOB");
		boolean gender = rs.getBoolean("gender");
		String description = rs.getString("description");
		float rate = rs.getFloat("rate");
		
		return new Teacher(userName, roleId, fullName, email, password, createDate, phone, address, active, dob, gender, description, rate);
	}
	
}
