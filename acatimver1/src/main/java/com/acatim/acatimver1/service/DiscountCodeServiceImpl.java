package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.DiscountCodeDAO;
import com.acatim.acatimver1.model.DiscountCode;

@Service
public class DiscountCodeServiceImpl {
	
	@Autowired
	private DiscountCodeDAO discountCodeDAO;
	
	public List<DiscountCode> getAllDiscountCode(){
		return this.discountCodeDAO.getAllDiscountCode();
	}
	
	public void addDiscountCode(DiscountCode discountCode) {
		this.discountCodeDAO.addDiscountCode(discountCode);
	}
	
	public void removeDiscountCode(String codeId) {
		boolean active = false;
		this.discountCodeDAO.removeDiscountCode(codeId, active);
	}
	
	public void updateDiscountCode(DiscountCode discountCode) {
		this.discountCodeDAO.updateDiscountCode(discountCode);
	}
}
