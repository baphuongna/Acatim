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

import com.acatim.acatimver1.entity.Images;
import com.acatim.acatimver1.mapper.ImagesMapper;
import com.acatim.acatimver1.service.PageableService;

@Repository
@Transactional
public class ImagesDAO extends JdbcDaoSupport {

	@Autowired
	public ImagesDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	public static <T> T[] append(T[] arr, T element) {
		final int N = arr.length;
		arr = Arrays.copyOf(arr, N + 1);
		arr[N] = element;
		return arr;
	}
	
	public int countImagesByUserName(String userName) {		
		String sqlDate = "select count(*) from images where user_name = ?";
		Object[] params = new Object[] { userName };
		int count = this.getJdbcTemplate().queryForObject(sqlDate, params, Integer.class);
		return count;
	}
	
	public List<Images> getImagesByUserName(PageableService pageable ,String userName) {
		String sql = ImagesMapper.BASE_SQL;

		Object[] params = new Object[] {};
		
		ImagesMapper mapper = new ImagesMapper();
		
		try {
			
			if(userName != null) {
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
			
			List<Images> image = this.getJdbcTemplate().query(sql, params, mapper);
			return image;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public Images getImagesById(String id) {
		String sql = ImagesMapper.BASE_SQL + " where id = ? ";

		Object[] params = new Object[] { id };
		ImagesMapper mapper = new ImagesMapper();
		try {
			Images image = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return image;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public int countImages() {		
		String sqlDate = "select count(*) from images";
		int count = this.getJdbcTemplate().queryForObject(sqlDate, Integer.class);
		return count;
	}
	
	public void addImage(Images images) {
		String sql = "INSERT INTO images (user_name,linkimage,description, create_date, update_date,active) VALUES (?,?,?,?,?,?);";
		this.getJdbcTemplate().update(sql, images.getUserName(), images.getLinkimage(), images.getDescription(), images.getCreateDate(), images.getUpdateDate(), images.isActive());
	}
	
	public void updateImages(Images images) {
		String sql = "UPDATE images SET user_name = ?, linkimage = ?, description = ?, update_date = ? WHERE id = ?;";
		this.getJdbcTemplate().update(sql, images.getUserName(), images.getLinkimage(), images.getDescription(), images.getUpdateDate(), images.getId());
	}
	
	public void activeImages(String id, boolean active) {
		String sql = "UPDATE images SET isActive = ? WHERE id = ?;";
		this.getJdbcTemplate().update(sql, active, id);
	}
	
	public void deleteImages(String id) {
		String sql = "UPDATE images SET isActive = ? WHERE id = ?;";
		this.getJdbcTemplate().update(sql, id);
	}
	
}
