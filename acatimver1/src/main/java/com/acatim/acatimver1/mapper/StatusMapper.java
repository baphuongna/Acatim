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
		
		String id = rs.getString("id");
		String value = rs.getString("value");
		String lastManagerChangeName = rs.getString("lastManagerChangeName");

		return new Status(id, value, lastManagerChangeName);
	}

}
