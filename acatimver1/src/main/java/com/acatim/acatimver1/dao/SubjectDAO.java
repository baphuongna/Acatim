package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.mapper.SubjectExtractor;
import com.acatim.acatimver1.mapper.SubjectMapper;
import com.acatim.acatimver1.model.Subject;

@Repository
@Transactional
public class SubjectDAO extends JdbcDaoSupport {

	@Autowired
	public SubjectDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public List<Subject> getAllSubject() {
		String sql = "SELECT * FROM Subject INNER JOIN Course ON Course.subject_id = Subject.subject_id;";

		List<Subject> subjects = this.getJdbcTemplate().query(sql, new SubjectExtractor());
		return subjects;
	}

	public Subject getSubjectBySubjectId(String subjectId) {
		String sql = "SELECT * FROM Subject Where subject_id = ?;";
		Object[] params = new Object[] { subjectId };
		SubjectMapper mapper = new SubjectMapper();
		Subject subject = this.getJdbcTemplate().queryForObject(sql, params, mapper);
		return subject;
	}

	public void addCourse(Subject subject) {
		String sql = "INSERT INTO Subject (subject_id, category_id, subject_name, create_date, update_date, active)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?);";
		this.getJdbcTemplate().update(sql, subject.getSubjectId(), subject.getCategoryId(), subject.getSubjectName(),
				subject.getCreateDate(), subject.getUpdateDate(), subject.isActive());
	}

	public void updateCourse(Subject subject) {
		String sql = "UPDATE Subject\r\n"
				+ "SET subject_id = ?, category_id = ?, subject_name = ?, create_date = ?, update_date = ?, active = ?\r\n"
				+ "WHERE subject_id = ?;";
		this.getJdbcTemplate().update(sql, subject.getCategoryId(), subject.getSubjectName(), subject.getCreateDate(),
				subject.getUpdateDate(), subject.isActive(), subject.getSubjectId());
	}

	public void removeCourse(String subjectId, boolean active) {
		String sql = "UPDATE Subject SET active = ? WHERE subject_id = ?;";
		this.getJdbcTemplate().update(sql, active, subjectId);
	}

}
