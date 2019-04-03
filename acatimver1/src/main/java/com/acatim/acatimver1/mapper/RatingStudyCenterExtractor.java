package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.acatim.acatimver1.entity.RateStudyCenter;
import com.acatim.acatimver1.entity.Rating;

public class RatingStudyCenterExtractor implements ResultSetExtractor<List<Rating>>{

	@Override
	public List<Rating> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<String, Rating> map = new HashMap<String, Rating>();
		Rating rating = null;

		while (rs.next()) {

			String rateId = rs.getString("rate_id");
			rating = map.get(rateId);

			if (rating == null) {
				String userName = rs.getString("user_name");
				String recieverName = rs.getString("reciever_name");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				float rate = rs.getFloat("rate");
				String comment = rs.getString("comment");
				boolean active = rs.getBoolean("active");

				rating = new Rating(rateId, userName, recieverName, createDate, updateDate, rate, comment, active);

				map.put(rateId, rating);
			}

			String checkSCNull = rs.getString("checkSCNull");
			if (checkSCNull != null) {
				RateStudyCenter rateStudyCenter = new RateStudyCenter();
				float equipmentQuality = rs.getFloat("equipmentQuality");
				float staffAttitude = rs.getFloat("staffAttitude");
				float reputation = rs.getFloat("reputation");
				float happiness = rs.getFloat("happiness");
				float safety = rs.getFloat("safety");
				float internet = rs.getFloat("internet");
				float location = rs.getFloat("location");
				float teachingQuality = rs.getFloat("teachingQuality");
				rateStudyCenter = new RateStudyCenter(rateId, equipmentQuality, staffAttitude, reputation, happiness,
						safety, internet, location, teachingQuality, checkSCNull);
				rating.setRateStudyCenter(rateStudyCenter);
			}
		}
		return new ArrayList<Rating>(map.values());
	}

}
