package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.entity.RateStudyCenter;

public class RateStudyCenterMapper implements RowMapper<RateStudyCenter>{

	public static final String BASE_SQL //
	= "Select * From RateStudyCenter rsc ";
	
	@Override
	public RateStudyCenter mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		RateStudyCenter rateStudyCenter = null;
		
		String rateId = rs.getString("rate_id");
		float equipmentQuality = rs.getFloat("equipmentQuality");
		float staffAttitude = rs.getFloat("staffAttitude");
		float reputation = rs.getFloat("reputation");
		float happiness = rs.getFloat("happiness");
		float safety = rs.getFloat("safety");
		float internet = rs.getFloat("internet");
		float location = rs.getFloat("location");
		float teachingQuality = rs.getFloat("teachingQuality");
		String checkSCNull = rs.getString("checkSCNull");
		
		rateStudyCenter = new RateStudyCenter(rateId, equipmentQuality, staffAttitude, reputation, happiness, safety, internet, location, teachingQuality, checkSCNull);
		
		return rateStudyCenter;
	}

}
