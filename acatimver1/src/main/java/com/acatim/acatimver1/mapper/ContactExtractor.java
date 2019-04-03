package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.acatim.acatimver1.entity.Contact;

public class ContactExtractor implements ResultSetExtractor<List<Contact>> {

	@Override
	public List<Contact> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<String, Contact> map = new HashMap<String, Contact>();
		Contact contact = null;
		while (rs.next()) {
			String idContact = rs.getInt("id")+"";
			contact = map.get(idContact);
			if (contact == null) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String title = rs.getString("title");
				String message = rs.getString("message");
				String createDate = rs.getString("create_date");
				boolean isActive = rs.getBoolean("isActive");
				contact = new Contact(id, name, email, title, message, createDate, isActive);
				map.put(idContact, contact);
			}
		}
		return new ArrayList<Contact>(map.values());
	}

}
