package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {
	public static final String BASE_SQL //
			= "Select * From User u ";

	@Override
	public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {

		int id = rs.getInt("user_id");
		String userName = rs.getString("user_name");
		String fullName = rs.getString("full_name");
		String email = rs.getString("email");
		String password = rs.getString("password");
		String createDate = rs.getString("create_date");
		String phone = rs.getString("phone");
		String address = rs.getString("address");
		boolean active = rs.getBoolean("active");
		
		return new UserModel(id, userName, fullName, email, password, createDate, phone, address, active);
	}
}
