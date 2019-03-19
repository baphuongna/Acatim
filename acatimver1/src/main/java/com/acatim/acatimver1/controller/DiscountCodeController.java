package com.acatim.acatimver1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.acatim.acatimver1.model.Contact;
import com.acatim.acatimver1.model.Course;
import com.acatim.acatimver1.model.DiscountCode;
import com.acatim.acatimver1.model.ObjectUser;
import com.acatim.acatimver1.service.DiscountCodeServiceImpl;;

@Controller
@RequestMapping(value = {""})
public class DiscountCodeController {
	
	@Autowired
	private DiscountCodeServiceImpl discountCodeService;
	
	@RequestMapping(value = "/courseDiscountCode", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid @ModelAttribute("codeInfo") DiscountCode code, BindingResult bindingResult)
			throws NotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateCurent = (String) dateFormat.format(date);
		code.setCreateDate(dateCurent);
		code.setExpireDate(dateCurent);
		code.setActive(true); 
		code.setStatus("actived"); 
		Course search = new Course();
		modelAndView.addObject("search",search);
		DiscountCode discountCode = new DiscountCode(code.getCodeId(), code.getUserName(), code.getCourseId(), code.getCreateDate(), code.getExpireDate(), code.getStatus(), code.isActive());
		discountCodeService.addDiscountCode(discountCode);
		System.out.println(code);
		
		modelAndView.setViewName("redirect:/course");
		return modelAndView;
	}
	
}