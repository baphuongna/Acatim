package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.entity.History;

public class StatusMapper implements RowMapper<History>{

	public static final String BASE_SQL //
	= "Select * From Status sta ";
	
	@Override
	public History mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		int id = rs.getInt("id");
		String idChange = rs.getString("id_change");
		String valueChanged = rs.getString("value_changed");
		String by = rs.getString("fixer");
		String dateChange = rs.getString("date_change");

		return new History(id, idChange, valueChanged, by, dateChange);
	}

}
