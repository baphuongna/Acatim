package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.Contact;
import com.acatim.acatimver1.entity.CountByDate;
import com.acatim.acatimver1.mapper.ContactExtractor;
import com.acatim.acatimver1.mapper.ContactMapper;

@Repository
@Transactional
public class ContactDAO extends JdbcDaoSupport {
	
	@Autowired
	public ContactDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	public void addContact(Contact contact) {
		String sql = "INSERT INTO ContactUs (name, email, title, message, create_date, isActive)\r\n" + 
				"VALUES (?, ?, ?, ?, ?, ?);";
		this.getJdbcTemplate().update(sql, contact.getName(), contact.getEmail(), contact.getTitle(),contact.getMessage(),contact.getCreateDate(),contact.isActive());
	}
	
	// contact

	public List<Contact> getAllContact() {
		try {
			String sql = ContactMapper.BASE_SQL;
			List<Contact> contact = this.getJdbcTemplate().query(sql, new ContactExtractor());
			return contact;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Contact> searchAllContactByUserName(Pageable pageable, String name) {
		try {
			String sql = ContactMapper.BASE_SQL + " WHERE name LIKE ? LIMIT ?, ?;";
			Object[] params = new Object[] { "%" + name + "%", pageable.getOffset(), pageable.getPageSize() };
			List<Contact> contact = this.getJdbcTemplate().query(sql, params, new ContactExtractor());
			return contact;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Contact> getAllContactPageable(Pageable pageable) {
		try {
			String sql = ContactMapper.BASE_SQL + " LIMIT ?, ?;";
			Object[] params = new Object[] { pageable.getOffset(), pageable.getPageSize() };
			List<Contact> contact = this.getJdbcTemplate().query(sql, params, new ContactExtractor());
			return contact;

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Contact> searchAllContactByEmail(Pageable pageable, String email) {
		try {
			String sql = ContactMapper.BASE_SQL + " WHERE email LIKE ? LIMIT ?, ?;";
			Object[] params = new Object[] { "%" + email + "%", pageable.getOffset(), pageable.getPageSize() };
			List<Contact> contact = this.getJdbcTemplate().query(sql, params, new ContactExtractor());
			return contact;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public CountByDate countContactUsByDate() {
		CountByDate count = new CountByDate();
		
		String sqlDate = "select count(*) from ContactUs where date(create_date)=date(date_sub(now(),interval 1 day));";
		int countDate = this.getJdbcTemplate().queryForObject(sqlDate, Integer.class);
		
		String sqlMonth = "select count(*) from ContactUs where month(create_date)=month(date_sub(now(),interval 1 day));";
		int countMonth = this.getJdbcTemplate().queryForObject(sqlMonth, Integer.class);
		
		String sqlYear = "select count(*) from ContactUs where year(create_date)=year(now());";
		int countYear = this.getJdbcTemplate().queryForObject(sqlYear, Integer.class);
		
		count.setByDate(countDate);
		count.setByMonth(countMonth);
		count.setByYear(countYear);
		
		return count;
	}
}
