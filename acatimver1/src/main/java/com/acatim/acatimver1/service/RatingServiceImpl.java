package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.RateStudyCenterDAO;
import com.acatim.acatimver1.dao.RateTeacherDAO;
import com.acatim.acatimver1.dao.RatingDAO;
import com.acatim.acatimver1.model.RateStudyCenter;
import com.acatim.acatimver1.model.RateTeacher;
import com.acatim.acatimver1.model.Rating;

@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	private RatingDAO ratingDAO;

	@Autowired
	private RateStudyCenterDAO rateStudyCenterDAO;

	@Autowired
	private RateTeacherDAO rateTeacherDAO;

	public List<Rating> getAllRatingTeacher() {
		return this.ratingDAO.getAllRatingTeacher();
	}

	public List<Rating> getAllRatingStudyCenter() {
		return this.ratingDAO.getAllRatingStudyCenter();
	}

	public List<Rating> getAllRatingTeacherByRecieverName(String recieverName) {
		return this.ratingDAO.getAllRatingTeacherByRecieverName(recieverName);
	}

	public List<Rating> getAllRatingStudyCenterByRecieverName(String recieverName) {
		return this.ratingDAO.getAllRatingStudyCenterByRecieverName(recieverName);
	}

	public List<Rating> getAllRatingByUserName(String userName) {
		return this.ratingDAO.getAllRatingByUserName(userName);
	}

	public List<Rating> getAllRating() {
		return this.ratingDAO.getAllRating();
	}

	public void addRating(Rating rating) {
		this.ratingDAO.addRating(rating);
	}

	public void removeRating(String rateId) {
		boolean active = false;
		this.ratingDAO.removeRating(rateId, active);
	}

	public void updateRating(Rating rating) {
		this.ratingDAO.updateRating(rating);
	}

	public RateTeacher getRateTeacherByUserName(String rateId) {
		return this.rateTeacherDAO.getRateTeacherByUserName(rateId);
	}

	public void addRateTeacher(RateTeacher rateTeacher) {
		this.rateTeacherDAO.addRateTeacher(rateTeacher);
	}

	public void updateRateTeacher(RateTeacher rateTeacher) {
		this.rateTeacherDAO.updateRateTeacher(rateTeacher);
	}

	public RateStudyCenter getRateStudyCenterByUserName(String rateId) {
		return this.rateStudyCenterDAO.getRateStudyCenterByUserName(rateId);
	}

	public void addRateStudyCenter(RateStudyCenter rateStudyCenter) {
		this.rateStudyCenterDAO.addRateStudyCenter(rateStudyCenter);
	}

	public void updateRateStudyCenter(RateStudyCenter rateStudyCenter) {
		this.rateStudyCenterDAO.updateRateStudyCenter(rateStudyCenter);
	}
}
