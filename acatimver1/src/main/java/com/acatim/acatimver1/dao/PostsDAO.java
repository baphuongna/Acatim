package com.acatim.acatimver1.dao;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.Posts;
import com.acatim.acatimver1.mapper.PostsMapper;
import com.acatim.acatimver1.service.PageableService;

@Repository
@Transactional
public class PostsDAO extends JdbcDaoSupport {

	@Autowired
	public PostsDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public int countPostsByUserName(String userName) {
		String sqlDate = "select count(*) from posts where user_name = ?";
		Object[] params = new Object[] { userName };
		int count = this.getJdbcTemplate().queryForObject(sqlDate, params, Integer.class);
		return count;
	}

	public List<Posts> getPostsByUserName(PageableService pageable, String userName) {
		String sql = PostsMapper.BASE_SQL;

		Object[] params = new Object[] {};

		PostsMapper mapper = new PostsMapper();

		try {

			if (userName != null) {
				sql += " where user_name = ? ";
				params = append(params, userName);
			}

			if (pageable.sort() != null) {
				for (Order o : pageable.sort()) {
					sql += " ORDER BY " + o.getProperty() + " " + o.getDirection().toString() + " ";
				}
			}

			sql += " LIMIT ?, ?;";
			params = append(params, pageable.getOffset());
			params = append(params, pageable.getPageSize());

			List<Posts> posts = this.getJdbcTemplate().query(sql, params, mapper);
			return posts;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Posts getPostsById(String id) {
		String sql = PostsMapper.BASE_SQL + " where id = ? ";

		Object[] params = new Object[] { id };
		PostsMapper mapper = new PostsMapper();
		try {
			Posts posts = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return posts;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public int countPosts() {
		String sqlDate = "select count(*) from posts";
		int count = this.getJdbcTemplate().queryForObject(sqlDate, Integer.class);
		return count;
	}

	public void addPost(Posts posts) {
		String sql = "INSERT INTO posts (user_name,link_video, title,description, teacher, subject, create_date, update_date,active) VALUES (?,?,?,?,?,?,?,?,?);";
		this.getJdbcTemplate().update(sql, posts.getUserName(), posts.getLinkVideo(), posts.getTitle(),
				posts.getDescription(), posts.getTeacher(), posts.getSubject(), posts.getCreateDate(),
				posts.getUpdateDate(), posts.isActive());
	}

	public void updatePosts(Posts posts) {
		String sql = "UPDATE posts SET link_video = ?, title = ?, description = ?, teacher = ?, subject= ? , update_date = ? WHERE id = ?;";
		this.getJdbcTemplate().update(sql, posts.getLinkVideo(), posts.getTitle(), posts.getDescription(),
				posts.getTeacher(), posts.getSubject(), posts.getUpdateDate(), posts.getId());
	}

	public void activePosts(String id, boolean active) {
		String sql = "UPDATE posts SET isActive = ? WHERE id = ?;";
		this.getJdbcTemplate().update(sql, active, id);
	}

	public void deletePosts(String id) {
		String sql = "DELETE FROM posts WHERE id = ?;";
		this.getJdbcTemplate().update(sql, id);
	}

	public static <T> T[] append(T[] arr, T element) {
		final int N = arr.length;
		arr = Arrays.copyOf(arr, N + 1);
		arr[N] = element;
		return arr;
	}
}
