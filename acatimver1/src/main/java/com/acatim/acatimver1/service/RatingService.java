package com.acatim.acatimver1.service;

import java.util.List;

import com.acatim.acatimver1.entity.CountByDate;
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

	boolean addRating(Rating rating);

	boolean removeRating(String rateId);

	boolean updateRating(Rating rating);

	RateTeacher getRateTeacherByUserName(String rateId);

	boolean addRateTeacher(RateTeacher rateTeacher);

	boolean updateRateTeacher(RateTeacher rateTeacher);

	RateStudyCenter getRateStudyCenterByUserName(String rateId);
	
	boolean addRateStudyCenter(RateStudyCenter rateStudyCenter);

	boolean updateRateStudyCenter(RateStudyCenter rateStudyCenter);
	
	CountRate countRatingTeacher(String userName);
	
	CountRate countRatingStudyCenter(String userName);
	
	RateTeacher caculaterRateTeacher(String userName);
	
	RateStudyCenter caculaterRateStudyCenter(String userName);
	
	float updateTotalRateTeacher(List<Rating> ratings);
	
	float updateTotalRateStudyCenter(List<Rating> ratings);
	
	boolean updateRateTeacher(float rate, String userName);
	
	boolean updateRateStudyCenter(float rate, String userName);
	
	String genRatingId();
	
	boolean check(String userName, String recieverName);
	
	CountByDate countRatingByDate();
	
	Rating setRateNull(Rating rating);
}
