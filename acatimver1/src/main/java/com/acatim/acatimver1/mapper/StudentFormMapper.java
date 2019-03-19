package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.form.StudentForm;

public class StudentFormMapper implements RowMapper<StudentForm>{

	@Override
	public StudentForm mapRow(ResultSet rs, int rowNum) throws SQLException {
		
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
		
		StudentForm student = new StudentForm(userName, roleId, fullName, email, password, createDate, phone, address, active, dob, gender);
				
		return student;
	}

}
