package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.RateStudyCenterDAO;
import com.acatim.acatimver1.dao.RateTeacherDAO;
import com.acatim.acatimver1.dao.RatingDAO;
import com.acatim.acatimver1.dao.StudyCenterDAO;
import com.acatim.acatimver1.dao.TeacherDAO;
import com.acatim.acatimver1.entity.CountByDate;
import com.acatim.acatimver1.entity.CountRate;
import com.acatim.acatimver1.entity.RateStudyCenter;
import com.acatim.acatimver1.entity.RateTeacher;
import com.acatim.acatimver1.entity.Rating;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingDAO ratingDAO;

	@Autowired
	private RateStudyCenterDAO rateStudyCenterDAO;

	@Autowired
	private RateTeacherDAO rateTeacherDAO;
	
	@Autowired
	private StudyCenterDAO StudyCenterDAO;

	@Autowired
	private TeacherDAO TeacherDAO;

	public List<Rating> getAllRatingTeacher() {
		return this.ratingDAO.getAllRatingTeacher();
	}

	public List<Rating> getAllRatingStudyCenter() {
		return this.ratingDAO.getAllRatingStudyCenter();
	}

	@Override
	public List<Rating> getAllRatingTeacherByRecieverName(String recieverName) {
		return this.ratingDAO.getAllRatingTeacherByRecieverName(recieverName);
	}

	@Override
	public List<Rating> getAllRatingStudyCenterByRecieverName(String recieverName) {
		return this.ratingDAO.getAllRatingStudyCenterByRecieverName(recieverName);
	}

	@Override
	public List<Rating> getAllRatingByUserName(String userName) {
		return this.ratingDAO.getAllRatingByUserName(userName);
	}

	@Override
	public List<Rating> getAllRating() {
		return this.ratingDAO.getAllRating();
	}

	@Override
	public void addRating(Rating rating) {
		this.ratingDAO.addRating(rating);
	}

	@Override
	public void removeRating(String rateId) {
		boolean active = false;
		this.ratingDAO.removeRating(rateId, active);
	}

	@Override
	public void updateRating(Rating rating) {
		this.ratingDAO.updateRating(rating);
	}

	@Override
	public RateTeacher getRateTeacherByUserName(String rateId) {
		return this.rateTeacherDAO.getRateTeacherByUserName(rateId);
	}

	@Override
	public void addRateTeacher(RateTeacher rateTeacher) {
		this.rateTeacherDAO.addRateTeacher(rateTeacher);
	}

	@Override
	public void updateRateTeacher(RateTeacher rateTeacher) {
		this.rateTeacherDAO.updateRateTeacher(rateTeacher);
	}

	@Override
	public RateStudyCenter getRateStudyCenterByUserName(String rateId) {
		return this.rateStudyCenterDAO.getRateStudyCenterByUserName(rateId);
	}

	@Override
	public void addRateStudyCenter(RateStudyCenter rateStudyCenter) {
		this.rateStudyCenterDAO.addRateStudyCenter(rateStudyCenter);
	}

	@Override
	public void updateRateStudyCenter(RateStudyCenter rateStudyCenter) {
		this.rateStudyCenterDAO.updateRateStudyCenter(rateStudyCenter);
	}

	public CountRate countRatingTeacher(String userName) {

		float progress5 = 0;
		float progress4 = 0;
		float progress3 = 0;
		float progress2 = 0;
		float progress1 = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;

		List<Rating> ratings = this.ratingDAO.getAllRatingTeacherByRecieverName(userName);
		float numberPeopleRate = 0;

		for (int i = 0; i < ratings.size(); i++) {
			numberPeopleRate++;
			if (ratings.get(i).getRate() <= 1) {
				count1++;
			} else if (ratings.get(i).getRate() > 1 && ratings.get(i).getRate() <= 2) {
				count2++;
			} else if (ratings.get(i).getRate() > 2 && ratings.get(i).getRate() <= 3) {
				count3++;
			} else if (ratings.get(i).getRate() > 3 && ratings.get(i).getRate() <= 4) {
				count4++;
			} else if (ratings.get(i).getRate() > 4 && ratings.get(i).getRate() <= 5) {
				count5++;
			}
		}
		if (numberPeopleRate > 0) {
			progress5 = (count5 / numberPeopleRate) * 100;
			progress4 = (count4 / numberPeopleRate) * 100;
			progress3 = (count3 / numberPeopleRate) * 100;
			progress2 = (count2 / numberPeopleRate) * 100;
			progress1 = (count1 / numberPeopleRate) * 100;
		}
		CountRate count = new CountRate();
		count.setOneStar(progress1);
		count.setTwoStar(progress2);
		count.setThreeStar(progress3);
		count.setFourStar(progress4);
		count.setFiveStar(progress5);
		count.setNumberPeopleRate(numberPeopleRate);
		return count;
	}

	public CountRate countRatingStudyCenter(String userName) {

		float progress5 = 0;
		float progress4 = 0;
		float progress3 = 0;
		float progress2 = 0;
		float progress1 = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;

		List<Rating> ratings = this.ratingDAO.getAllRatingStudyCenterByRecieverName(userName);

		float numberPeopleRate = 0;

		for (int i = 0; i < ratings.size(); i++) {
			numberPeopleRate++;
			if (ratings.get(i).getRate() <= 1) {
				count1++;
			} else if (ratings.get(i).getRate() > 1 && ratings.get(i).getRate() <= 2) {
				count2++;
			} else if (ratings.get(i).getRate() > 2 && ratings.get(i).getRate() <= 3) {
				count3++;
			} else if (ratings.get(i).getRate() > 3 && ratings.get(i).getRate() <= 4) {
				count4++;
			} else if (ratings.get(i).getRate() > 4 && ratings.get(i).getRate() <= 5) {
				count5++;
			}
		}
		if (numberPeopleRate > 0) {
			progress5 = (count5 / numberPeopleRate) * 100;
			progress4 = (count4 / numberPeopleRate) * 100;
			progress3 = (count3 / numberPeopleRate) * 100;
			progress2 = (count2 / numberPeopleRate) * 100;
			progress1 = (count1 / numberPeopleRate) * 100;
		}

		CountRate count = new CountRate();
		count.setOneStar(progress1);
		count.setTwoStar(progress2);
		count.setThreeStar(progress3);
		count.setFourStar(progress4);
		count.setFiveStar(progress5);
		count.setNumberPeopleRate(numberPeopleRate);
		return count;
	}

	public RateTeacher caculaterRateTeacher(String userName) {
		float easyLevel = 0;
		float examDifficulty = 0;
		float textbookUse = 0;
		float helpfulLevel = 0;
		float clarityLevel = 0;
		float knowledgeable = 0;

		List<Rating> ratings = this.ratingDAO.getAllRatingTeacherByRecieverName(userName);
		int count = 0;
		for (Rating rate : ratings) {
			count++;
			easyLevel += rate.getRateTeacher().getEasyLevel();
			examDifficulty += rate.getRateTeacher().getExamDifficulty();
			textbookUse += rate.getRateTeacher().getTextbookUse();
			helpfulLevel += rate.getRateTeacher().getHelpfulLevel();
			clarityLevel += rate.getRateTeacher().getClarityLevel();
			knowledgeable += rate.getRateTeacher().getKnowledgeable();
		}
		if (count > 0) {
			easyLevel = easyLevel / count;
			examDifficulty = examDifficulty / count;
			textbookUse = textbookUse / count;
			helpfulLevel = helpfulLevel / count;
			clarityLevel = clarityLevel / count;
			knowledgeable = knowledgeable / count;
		}

		return new RateTeacher(null, easyLevel, examDifficulty, textbookUse, helpfulLevel, clarityLevel, knowledgeable,
				null);
	}

	public RateStudyCenter caculaterRateStudyCenter(String userName) {

		float equipmentQuality = 0;
		float staffAttitude = 0;
		float reputation = 0;
		float happiness = 0;
		float safety = 0;
		float internet = 0;
		float location = 0;
		float teachingQuality = 0;

		List<Rating> ratings = this.ratingDAO.getAllRatingStudyCenterByRecieverName(userName);

		int count = 0;
		for (Rating rate : ratings) {
			count++;
			equipmentQuality += rate.getRateStudyCenter().getEquipmentQuality();
			staffAttitude += rate.getRateStudyCenter().getStaffAttitude();
			reputation += rate.getRateStudyCenter().getReputation();
			happiness += rate.getRateStudyCenter().getHappiness();
			safety += rate.getRateStudyCenter().getSafety();
			internet += rate.getRateStudyCenter().getInternet();
			location += rate.getRateStudyCenter().getLocation();
			teachingQuality += rate.getRateStudyCenter().getTeachingQuality();
		}
		if (count > 0) {
			equipmentQuality = equipmentQuality / count;
			staffAttitude = staffAttitude / count;
			reputation = reputation / count;
			happiness = happiness / count;
			safety = safety / count;
			internet = internet / count;
			location = location / count;
			teachingQuality = teachingQuality / count;
		}
		return new RateStudyCenter(null, equipmentQuality, staffAttitude, reputation, happiness, safety, internet,
				location, teachingQuality, null);
	}

	@Override
	public float updateTotalRateTeacher(List<Rating> ratings) {
		
		float totalRate = 0;
		int count = 0;
		float rateAvg = 0;
		
		for (Rating rate : ratings) {
			count++;
			totalRate += rate.getRate();
		}
		if (count > 0) {
			rateAvg = totalRate/count;
		}

		return rateAvg;
	}

	@Override
	public float updateTotalRateStudyCenter(List<Rating> ratings) {
		
		float totalRate = 0;
		int count = 0;
		float rateAvg = 0;
		
		for (Rating rate : ratings) {
			count++;
			totalRate += rate.getRate();
		}
		if (count > 0) {
			rateAvg = totalRate/count;
		}
		return rateAvg;
	}

	@Override
	public String genRatingId() {
		List<Rating> ratings = this.ratingDAO.getAllRating();
		int incNumber = ratings.size() + 1;
		String newrateId = "rate" + incNumber;
		return newrateId;
	}

	@Override
	public boolean check(String userName, String recieverName) {
		return this.ratingDAO.check(userName, recieverName);
	}

	@Override
	public void updateRateTeacher(float rate, String userName) {
		this.TeacherDAO.updateRateTeacher(rate, userName);
	}

	@Override
	public void updateRateStudyCenter(float rate, String userName) {
		this.StudyCenterDAO.updateRateStudyCenter(rate, userName);
	}

	@Override
	public CountByDate countRatingByDate() {
		return this.ratingDAO.countRatingByDate();
	}

	@Override
	public Rating setRateNull(Rating rating) {
		
		if(rating.getRate() == 0) {
			rating.setRate(1);
		}
		
		if(rating.getRateTeacher() != null) {
			if(rating.getRateTeacher().getClarityLevel() == 0) {
				rating.getRateTeacher().setClarityLevel(1);
			}
			
			if(rating.getRateTeacher().getEasyLevel() == 0) {
				rating.getRateTeacher().setEasyLevel(1);
			}
			
			if(rating.getRateTeacher().getExamDifficulty() == 0) {
				rating.getRateTeacher().setExamDifficulty(1);
			}
			
			if(rating.getRateTeacher().getHelpfulLevel() == 0) {
				rating.getRateTeacher().setHelpfulLevel(1);
			}
			
			if(rating.getRateTeacher().getKnowledgeable() == 0) {
				rating.getRateTeacher().setKnowledgeable(1);
			}
			
			if(rating.getRateTeacher().getTextbookUse() == 0) {
				rating.getRateTeacher().setTextbookUse(1);
			}
			
			if(rating.getRateTeacher().getClarityLevel() == 0) {
				rating.getRateTeacher().setClarityLevel(1);
			}
		}
		
		if(rating.getRateStudyCenter() != null) {
			if(rating.getRateStudyCenter().getEquipmentQuality() == 0) {
				rating.getRateStudyCenter().setEquipmentQuality(1);
			}
			
			if(rating.getRateStudyCenter().getHappiness() == 0) {
				rating.getRateStudyCenter().setHappiness(1);
			}
			
			if(rating.getRateStudyCenter().getInternet() == 0) {
				rating.getRateStudyCenter().setInternet(1);
			}
			
			if(rating.getRateStudyCenter().getLocation() == 0) {
				rating.getRateStudyCenter().setLocation(1);
			}
			
			if(rating.getRateStudyCenter().getReputation() == 0) {
				rating.getRateStudyCenter().setReputation(1);
			}
			
			if(rating.getRateStudyCenter().getSafety() == 0) {
				rating.getRateStudyCenter().setSafety(1);
			}
			
			if(rating.getRateStudyCenter().getStaffAttitude() == 0) {
				rating.getRateStudyCenter().setStaffAttitude(1);
			}
			
			if(rating.getRateStudyCenter().getTeachingQuality() == 0) {
				rating.getRateStudyCenter().setTeachingQuality(1);
			}
		}
		
		return rating;
	}

}
