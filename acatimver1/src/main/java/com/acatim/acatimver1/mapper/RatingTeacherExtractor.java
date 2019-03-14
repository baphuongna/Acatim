package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.acatim.acatimver1.model.RateTeacher;
import com.acatim.acatimver1.model.Rating;

public class RatingTeacherExtractor implements ResultSetExtractor<List<Rating>> {

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

			// continue process
			String checkTeaNull = rs.getString("checkTeaNull");
			if (checkTeaNull != null) {
				RateTeacher rateTeacher = new RateTeacher();
				float easyLevel = rs.getFloat("easyLevel");
				float examDifficulty = rs.getFloat("examDifficulty");
				float textbookUse = rs.getFloat("textbookUse");
				float helpfulLevel = rs.getFloat("helpfulLevel");
				float clarityLevel = rs.getFloat("clarityLevel");
				float knowledgeable = rs.getFloat("knowledgeable");
				rateTeacher = new RateTeacher(rateId, easyLevel, examDifficulty, textbookUse, helpfulLevel,
						clarityLevel, knowledgeable, checkTeaNull);
				rating.setRateTeacher(rateTeacher);
			}
		}
		return new ArrayList<Rating>(map.values());
	}
}
