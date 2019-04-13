package com.acatim.acatimver1.service;

import java.util.List;

import com.acatim.acatimver1.entity.DiscountCode;

public interface DiscountCodeService {
	
	List<DiscountCode> getAllDiscountCode();
	
	void addDiscountCode(DiscountCode discountCode);
	
	void removeDiscountCode(String codeId);
	
	void updateDiscountCode(DiscountCode discountCode);
	
	DiscountCode getDiscountCodeByUserName(String userName, String courseId);
}
