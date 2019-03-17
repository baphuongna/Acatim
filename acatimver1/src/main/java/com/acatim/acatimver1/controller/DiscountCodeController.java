package com.acatim.acatimver1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.service.UserInfoServiceImpl;

import javassist.NotFoundException;

import com.acatim.acatimver1.model.DiscountCode;
import com.acatim.acatimver1.model.ObjectUser;
import com.acatim.acatimver1.service.DiscountCodeServiceImpl;;

@Controller
@RequestMapping(value = {""})
public class DiscountCodeController {
	
	@Autowired
	private DiscountCodeServiceImpl discountCodeService;
	
	@RequestMapping(value = "/courseDiscountCode", method = RequestMethod.POST)
	public ModelAndView addNewDiscountCode(@Valid @ModelAttribute("codeInfo") DiscountCode code) throws NotFoundException {
		System.out.println("Phong");
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("phonggggg       " + code);
		DiscountCode discountCode = new DiscountCode(code.getCodeId(), code.getUserName(), code.getCourseId(), code.getCreateDate(), code.getExpireDate(), code.getStatus(), code.isActive());
		//discountCodeService.addDiscountCode(discountCode);
		modelAndView.setViewName("course");
		return modelAndView;
	}
	
}