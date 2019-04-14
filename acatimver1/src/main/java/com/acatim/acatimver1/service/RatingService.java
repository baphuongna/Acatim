package com.acatim.acatimver1.service;

import java.util.List;

import com.acatim.acatimver1.entity.CountRate;
import com.acatim.acatimver1.entity.RateStudyCenter;
import com.acatim.acatimver1.entity.RateTeacher;
import com.acatim.acatimver1.entity.Rating;

public interface RatingService {
	
	List<Rating> getAllRatingTeacher();

	List<Rating> getAllRatingStudyCenter();

	List<Rating> getAllRatingTeacherByRecieverName(String recieverName);

	List<Rating> getAllRatingStudyCenterByRecieverName(String recieverName);

	List<Rating> getAllRatingByUserName(String userName);

	List<Rating> getAllRating();

	void addRating(Rating rating);

	void removeRating(String rateId);

	void updateRating(Rating rating);

	RateTeacher getRateTeacherByUserName(String rateId);

	void addRateTeacher(RateTeacher rateTeacher);

	void updateRateTeacher(RateTeacher rateTeacher);

	RateStudyCenter getRateStudyCenterByUserName(String rateId);
	
	void addRateStudyCenter(RateStudyCenter rateStudyCenter);

	void updateRateStudyCenter(RateStudyCenter rateStudyCenter);
	
	CountRate countRatingTeacher(String userName);
	
	CountRate countRatingStudyCenter(String userName);
	
	RateTeacher caculaterRateTeacher(String userName);
	
	RateStudyCenter caculaterRateStudyCenter(String userName);
	
	void updateTotalRateTeacher(String userName);
	
	void updateTotalRateStudyCenter(String userName);
	
	String genRatingId();
	
	boolean check(String userName, String recieverName);
}
