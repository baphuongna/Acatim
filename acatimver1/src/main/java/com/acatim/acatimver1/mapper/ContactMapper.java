package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.model.Contact;

public class ContactMapper implements RowMapper<Contact> {

	public static final String BASE_SQL //
			= "SELECT * FROM ContactUs";

	@Override
	public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String email = rs.getString("email");
		String title = rs.getString("title");
		String message = rs.getString("message");
		String createDate = rs.getString("create_date");
		boolean isActive = rs.getBoolean("isActive");

		return new Contact(id, name, email, title, message, createDate, isActive);

	}

}
