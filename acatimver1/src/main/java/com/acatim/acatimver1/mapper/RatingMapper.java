package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.entity.RateStudyCenter;
import com.acatim.acatimver1.entity.RateTeacher;
import com.acatim.acatimver1.entity.Rating;

public class RatingMapper implements RowMapper<Rating>{

	public static final String BASE_SQL //
	= "Select * From Rating ra ";
	
	@Override
	public Rating mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Rating rating = null;
		
		String rateId = rs.getString("rate_id");
		String userName = rs.getString("user_name");
		String recieverName = rs.getString("reciever_name");
		String createDate = rs.getString("create_date");
		String updateDate = rs.getString("update_date");
		float rate = rs.getFloat("rate");
		String comment = rs.getString("comment");
		boolean active = rs.getBoolean("active");
		
		rating = new Rating(rateId, userName, recieverName, createDate, updateDate, rate, comment, active);
		
		
		try {
			RateStudyCenter rateStudyCenter = new RateStudyCenter();
			float equipmentQuality = rs.getFloat("equipmentQuality");
			float staffAttitude = rs.getFloat("staffAttitude");
			float reputation = rs.getFloat("reputation");
			float happiness = rs.getFloat("happiness");
			float safety = rs.getFloat("safety");
			float internet = rs.getFloat("internet");
			float location = rs.getFloat("location");
			float teachingQuality = rs.getFloat("teachingQuality");
			String checkSCNull = rs.getString("checkSCNull");
			rateStudyCenter = new RateStudyCenter(rateId, equipmentQuality, staffAttitude, reputation, happiness,
					safety, internet, location, teachingQuality, checkSCNull);
			rating.setRateStudyCenter(rateStudyCenter);
		}catch (Exception e) {
		}
		
		try {
			RateTeacher rateTeacher = new RateTeacher();
			float easyLevel = rs.getFloat("easyLevel");
			float examDifficulty = rs.getFloat("examDifficulty");
			float textbookUse = rs.getFloat("textbookUse");
			float helpfulLevel = rs.getFloat("helpfulLevel");
			float clarityLevel = rs.getFloat("clarityLevel");
			float knowledgeable = rs.getFloat("knowledgeable");
			String checkTeaNull = rs.getString("checkTeaNull");
			rateTeacher = new RateTeacher(rateId, easyLevel, examDifficulty, textbookUse, helpfulLevel,
					clarityLevel, knowledgeable, checkTeaNull);
			rating.setRateTeacher(rateTeacher);
		}catch (Exception e) {
		}
		
		return rating;
	}

}
