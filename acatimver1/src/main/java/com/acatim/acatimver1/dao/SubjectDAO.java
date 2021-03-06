package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.Subject;
import com.acatim.acatimver1.mapper.SubjectMapper;

@Repository
@Transactional
public class SubjectDAO extends JdbcDaoSupport {

	@Autowired
	public SubjectDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public List<Subject> getAllSubject() {
		String sql = "SELECT * FROM Subject;";
		try {
			List<Subject> subjects = this.getJdbcTemplate().query(sql, new SubjectMapper());
			return subjects;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Subject> getListSubject() {
		String sql = "SELECT * FROM Subject INNER JOIN Categories ON Subject.category_id = Categories.category_id";
		try {
			List<Subject> subjects = this.getJdbcTemplate().query(sql, new SubjectMapper());
			return subjects;
		} catch (Exception e) {
			return null;
		}
	}

	public Subject getSubjectBySubjectId(String subjectId) {
		String sql = "SELECT * FROM Subject Where subject_id = ?;";
		Object[] params = new Object[] { subjectId };
		SubjectMapper mapper = new SubjectMapper();
		try {
			Subject subject = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return subject;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Subject> getSubjectByCategoryId(String categoryId) {
		String sql = "SELECT * FROM Subject INNER JOIN Categories ON Subject.category_id = Categories.category_id Where Subject.category_id = ?;";
		Object[] params = new Object[] { categoryId };
		try {
			List<Subject> subject = this.getJdbcTemplate().query(sql, params, new SubjectMapper());
			return subject;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Subject> getListSubjectPageable(Pageable pageable) {
		String sql = "SELECT * FROM Subject INNER JOIN Categories ON Subject.category_id = Categories.category_id LIMIT ?, ?;";
		Object[] params = new Object[] { pageable.getOffset(), pageable.getPageSize() };
		try {
			List<Subject> subjects = this.getJdbcTemplate().query(sql, params, new SubjectMapper());
			return subjects;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Subject> getSubjectByCategoryIdPageable(Pageable pageable, String categoryId) {
		String sql = "SELECT * FROM Subject INNER JOIN Categories ON Subject.category_id = Categories.category_id Where Subject.category_id = ? LIMIT ?, ?;";
		Object[] params = new Object[] { categoryId, pageable.getOffset(), pageable.getPageSize() };
		try {
			List<Subject> subject = this.getJdbcTemplate().query(sql, params, new SubjectMapper());
			return subject;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean addSubject(Subject subject) {
		try {
			String sql = "INSERT INTO Subject (subject_id, category_id, subject_name, create_date, update_date, active)\r\n"
					+ "VALUES (?, ?, ?, ?, ?, ?);";
			this.getJdbcTemplate().update(sql, subject.getSubjectId(), subject.getCategoryId(),
					subject.getSubjectName(), subject.getCreateDate(), subject.getUpdateDate(), subject.isActive());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateSubject(Subject subject) {
		try {
			String sql = "UPDATE Subject\r\n" + "SET category_id = ?, subject_name = ?, update_date = ?\r\n"
					+ "WHERE subject_id = ?;";
			this.getJdbcTemplate().update(sql, subject.getCategoryId(), subject.getSubjectName(),
					subject.getUpdateDate(), subject.getSubjectId());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean removeSubject(String subjectId, boolean active) {
		try {
			String sql = "UPDATE Subject SET active = ? WHERE subject_id = ?;";
			this.getJdbcTemplate().update(sql, active, subjectId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
