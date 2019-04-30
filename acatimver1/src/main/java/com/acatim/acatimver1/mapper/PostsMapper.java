package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.entity.Posts;

public class PostsMapper implements RowMapper<Posts> {
	
	public static final String BASE_SQL = "Select * From posts";

	@Override
	public Posts mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Posts post = null;
		
		int id = rs.getInt("id");
		String userName = rs.getString("user_name");
		String title = rs.getString("title");
		String linkVideo = rs.getString("link_video");
		String description = rs.getString("description");
		String teacher = rs.getString("teacher");
		String subject = rs.getString("subject");
		String createDate = rs.getString("create_date");
		String updateDate = rs.getString("update_date");
		boolean active = rs.getBoolean("active");
		
		post = new Posts(id, userName, title, linkVideo, description, teacher, subject, createDate, updateDate, active);
		
		return post;
	}

}
