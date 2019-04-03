package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.model.Status;

public class StatusMapper implements RowMapper<Status>{

	public static final String BASE_SQL //
	= "Select * From Status sta ";
	
	@Override
	public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		int id = rs.getInt("id");
		String idChange = rs.getString("id_change");
		String valueChanged = rs.getString("value_changed");
		String by = rs.getString("fixer");
		String dateChange = rs.getString("date_change");

		return new Status(id, idChange, valueChanged, by, dateChange);
	}

}
