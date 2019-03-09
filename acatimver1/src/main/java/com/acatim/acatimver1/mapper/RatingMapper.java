package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.model.Rating;

public class RatingMapper implements RowMapper<Rating>{

	public static final String BASE_SQL //
	= "Select * From Rating ra ";
	
	@Override
	public Rating mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		String rateId = rs.getString("rate_id");
		String userName = rs.getString("user_name");
		String recieverName = rs.getString("reciever_name");
		String createDate = rs.getString("create_date");
		String updateDate = rs.getString("update_date");
		float rate = rs.getFloat("rate");
		String comment = rs.getString("comment");
		
		return new Rating(rateId, userName, recieverName, createDate, updateDate, rate, comment);
	}

}
