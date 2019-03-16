package com.acatim.acatimver1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/admin"})
public class EmployeeController {
	
	@RequestMapping(value = {"all-professors"}, method = RequestMethod.GET)
	public ModelAndView allProfessors() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/all-professors");
		return modelAndView;
	}
	
	@RequestMapping(value = {"add-professor"}, method = RequestMethod.GET)
	public ModelAndView addProfessor() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/add-professor");
		return modelAndView;
	}
	
	@RequestMapping(value = {"edit-professor"}, method = RequestMethod.GET)
	public ModelAndView editProfessor() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/edit-professor");
		return modelAndView;
	}
	
	@RequestMapping(value = {"/professor-profile"}, method = RequestMethod.GET)
	public ModelAndView professorProfile() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin//professor-profile");
		return modelAndView;
	}
}
