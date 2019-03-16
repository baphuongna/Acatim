package com.acatim.acatimver1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/admin"})
public class EmployeeController {
	
	@RequestMapping(value = {"all-professors"}, method = RequestMethod.GET)
	public ModelAndView blog() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/all-professors");
		return modelAndView;
	}
}
