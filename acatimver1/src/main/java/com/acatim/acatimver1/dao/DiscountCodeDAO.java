package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.DiscountCode;
import com.acatim.acatimver1.mapper.DiscountCodeMapper;

@Repository
@Transactional
public class DiscountCodeDAO extends JdbcDaoSupport {

	@Autowired
	public DiscountCodeDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public List<DiscountCode> getAllDiscountCode() {
		String sql = "SELECT * FROM DiscountCode";
		List<DiscountCode> discountCode = this.getJdbcTemplate().query(sql, new DiscountCodeMapper());
		return discountCode;
	}

	public void addDiscountCode(DiscountCode discountCode) {
		String sql = "INSERT INTO DiscountCode\r\n"
				+ "(code_id, user_name, course_id, create_date, expire_date, status, active)\r\n"
				+ "VALUES (? ,? ,? ,? ,? ,? ,? );";
		this.getJdbcTemplate().update(sql, discountCode.getCodeId(), discountCode.getUserName(),
				discountCode.getCourseId(), discountCode.getCreateDate(), discountCode.getExpireDate(),
				discountCode.getStatus(), discountCode.isActive());
	}

	public void removeDiscountCode(String codeId, boolean active) {
		String sql = "UPDATE DiscountCode SET active = ? WHERE code_id = ?;";
		this.getJdbcTemplate().update(sql, active, codeId);
	}

	public void updateDiscountCode(DiscountCode discountCode) {
		String sql = "UPDATE DiscountCode\r\n" + 
				"SET user_name = ?, course_id = ?, create_date = ?, expire_date = ?, status = ?\r\n" + 
				"WHERE code_id = ?;";
		this.getJdbcTemplate().update(sql, discountCode.getUserName(),
				discountCode.getCodeId(), discountCode.getCreateDate(), discountCode.getExpireDate(),
				discountCode.getStatus(),  discountCode.getCodeId());
	}
}
