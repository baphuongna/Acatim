package com.acatim.acatimver1.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.model.Contact;

@Repository
@Transactional
public class ContactDAO extends JdbcDaoSupport {
	
	@Autowired
	public ContactDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	public void addContact(Contact contact) {
		String sql = "INSERT INTO Contact (name, email, title, message, create_date, isActive)\r\n" + 
				"VALUES (?, ?, ?, ?, ?, ?);";
		this.getJdbcTemplate().update(sql, contact.getName(), contact.getEmail(), contact.getTitle(),contact.getMessage(),contact.getCreateDate(),contact.isActive());
	}
}
