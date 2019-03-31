package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.acatim.acatimver1.model.Categories;
import com.acatim.acatimver1.model.Subject;

public class SubjectCateExtractor implements ResultSetExtractor<List<Subject>> {

	@Override
	public List<Subject> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<String, Subject> map = new HashMap<String, Subject>();
		Subject subject = null;
		while (rs.next()) {

			
			String subjectId = rs.getString("subject_id");
			subject = map.get(subjectId);

			if (subject == null) {
				int categoryId = rs.getInt("category_id");
				String subjectName = rs.getString("subject_name");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				boolean active = rs.getBoolean("active");
				subject = new Subject(subjectId, categoryId, subjectName, createDate, updateDate, active);
				
				
				map.put(subjectId, subject);
			}

			// continue process
			String categoryName = rs.getString("category_name");
			if (categoryName != null) {
				Categories category = null;
				int categoryId = rs.getInt("category_id");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				boolean active = rs.getBoolean("active");
				
				category = new Categories(categoryId, categoryName, createDate, updateDate, active);
				subject.setCategory(category);
			}
		}
		return new ArrayList<Subject>(map.values());
	}

}
