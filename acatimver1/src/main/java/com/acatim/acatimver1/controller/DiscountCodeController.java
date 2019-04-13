package com.acatim.acatimver1.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acatim.acatimver1.entity.DiscountCode;
import com.acatim.acatimver1.format.DateFormat;
import com.acatim.acatimver1.service.DiscountCodeService;

import javassist.NotFoundException;;

@Controller
@RequestMapping(value = {""})
public class DiscountCodeController {
	
	@Autowired
	private DiscountCodeService discountCodeService;
	
	private DateFormat dateformat = new DateFormat();
	
	@RequestMapping(value = "/courseDiscountCode", method = RequestMethod.GET)
	public ModelAndView getDiscountCode(@RequestParam("courseId") String courseId, RedirectAttributes redirectAttributes, Principal principal)
			throws NotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		DiscountCode code = new DiscountCode();
		String userName = principal.getName();
		code.setUserName(userName);
		code.setCourseId(courseId);
		code.setCodeId(dateformat.RandomCode());
		code.setCreateDate(dateformat.currentDate());
		code.setExpireDate(dateformat.currentDate());
		code.setActive(true); 
		code.setStatus("actived"); 

		DiscountCode discountCode = new DiscountCode(code.getCodeId(), code.getUserName(), code.getCourseId(), code.getCreateDate(), code.getExpireDate(), code.getStatus(), code.isActive());
		
		System.out.println(discountCode + " " + courseId);
		
		discountCodeService.addDiscountCode(discountCode);
		
		redirectAttributes.addAttribute("courseId", courseId);
		
		modelAndView.setViewName("redirect:/detail-course");
		return modelAndView;
	}
	
}