package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.model.Student;

public class StudentMapper implements RowMapper<Student>{

	public static final String BASE_SQL //
	= "SELECT u.user_name, u.role_id, u.full_name, u.email, u.password, u.create_date, u.phone, u.address, u.phone, u.active, st.DOB, st.gender\r\n" + 
			"FROM User u\r\n" + 
			"INNER JOIN Student st\r\n" + 
			"ON u.user_name=st.user_name";
	
	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		
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
		
		return new Student(userName, roleId, fullName, email, password, createDate, phone, address, active, dob, gender);
	}

}
