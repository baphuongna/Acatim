package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.mapper.SubjectExtractor;
import com.acatim.acatimver1.model.Subject;

@Repository
@Transactional
public class SubjectDAO extends JdbcDaoSupport{
	
	@Autowired
	public SubjectDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	public List<Subject> getAllSubject() {
		String sql = "SELECT *\r\n" + 
				"FROM Subject\r\n" + 
				"INNER JOIN Course ON Course.subject_id = Subject.subject_id;";


		List<Subject> subjects = this.getJdbcTemplate().query(sql, new SubjectExtractor());

		return subjects;
	}
}
