package com.acatim.acatimver1.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acatim.acatimver1.entity.CountByDate;
import com.acatim.acatimver1.entity.Rating;
import com.acatim.acatimver1.mapper.RatingMapper;

@Repository
@Transactional
public class RatingDAO extends JdbcDaoSupport {

	@Autowired
	public RatingDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public List<Rating> getAllRatingTeacher() {
		String sql = "SELECT * FROM Rating\r\n" + "INNER JOIN RateTeacher ON Rating.rate_id = RateTeacher.rate_id";
		try {
			List<Rating> rating = this.getJdbcTemplate().query(sql, new RatingMapper());
			return rating;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Rating> getAllRatingStudyCenter() {
		String sql = "SELECT * FROM Rating\r\n"
				+ "INNER JOIN RateStudyCenter ON Rating.rate_id = RateStudyCenter.rate_id";
		try {
			List<Rating> rating = this.getJdbcTemplate().query(sql, new RatingMapper());
			return rating;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Rating> getAllRatingTeacherByRecieverName(String recieverName) {
		String sql = "SELECT * FROM Rating\r\n"
				+ "INNER JOIN RateTeacher ON Rating.rate_id = RateTeacher.rate_id Where Rating.reciever_name = ?";
		Object[] params = new Object[] { recieverName };
		try {
			List<Rating> rating = this.getJdbcTemplate().query(sql, params, new RatingMapper());
			return rating;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Rating> getAllRatingStudyCenterByRecieverName(String recieverName) {
		String sql = "SELECT * FROM Rating\r\n"
				+ "INNER JOIN RateStudyCenter ON Rating.rate_id = RateStudyCenter.rate_id Where Rating.reciever_name = ?";
		Object[] params = new Object[] { recieverName };
		try {
			List<Rating> rating = this.getJdbcTemplate().query(sql, params, new RatingMapper());
			return rating;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Rating> getAllRatingByUserName(String userName) {
		String sql = "SELECT * FROM Rating Where user_name = ?";
		Object[] params = new Object[] { userName };
		try {
			List<Rating> rating = this.getJdbcTemplate().query(sql, params, new RatingMapper());
			return rating;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean check(String userName, String recieverName) {
		String sql = "SELECT * FROM Rating Where user_name = ? and reciever_name = ?";
		Object[] params = new Object[] { userName, recieverName };
		try {
			List<Rating> rating = this.getJdbcTemplate().query(sql, params, new RatingMapper());
			System.out.println(userName + " " + recieverName + " " + rating);
			if (userName != null && !rating.isEmpty()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public List<Rating> getAllRating() {
		String sql = "SELECT * FROM Rating";
		try {
			List<Rating> rating = this.getJdbcTemplate().query(sql, new RatingMapper());
			return rating;
		} catch (Exception e) {
			return null;
		}
	}

	public CountByDate countRatingByDate() {
		CountByDate count = new CountByDate();
		try {
			String sqlDate = "select count(*) from Rating where date(create_date)=date(date_sub(now(),interval 1 day));";
			int countDate = this.getJdbcTemplate().queryForObject(sqlDate, Integer.class);

			String sqlMonth = "select count(*) from Rating where month(create_date)=month(date_sub(now(),interval 1 day));";
			int countMonth = this.getJdbcTemplate().queryForObject(sqlMonth, Integer.class);

			String sqlYear = "select count(*) from Rating where year(create_date)=year(now());";
			int countYear = this.getJdbcTemplate().queryForObject(sqlYear, Integer.class);

			count.setByDate(countDate);
			count.setByMonth(countMonth);
			count.setByYear(countYear);

			return count;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean addRating(Rating rating) {
		try {
			String sql = "INSERT INTO Rating (rate_id, user_name, reciever_name, create_date, update_date, rate, comment, active)\r\n"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			this.getJdbcTemplate().update(sql, rating.getRateId(), rating.getUserName(), rating.getRecieverName(),
					rating.getCreateDate(), rating.getUpdateDate(), rating.getRate(), rating.getComment(),
					rating.isActive());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean removeRating(String rateId, boolean active) {
		try {
			String sql = "UPDATE Rating SET active = ? WHERE rate_id = ?;";
			this.getJdbcTemplate().update(sql, active, rateId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateRating(Rating rating) {
		try {
			String sql = "UPDATE Rating SET update_date = ?, rate = ?, comment = ? WHERE rate_id = ?;";
			this.getJdbcTemplate().update(sql, rating.getUpdateDate(), rating.getRate(), rating.getComment(),
					rating.getRateId());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
