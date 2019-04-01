package com.acatim.acatimver1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.service.UserInfoServiceImpl;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = {"/admin"})
public class EmployeeController {
	
	@Autowired
	private UserInfoServiceImpl userInfoService;
	
	@RequestMapping(value = {"all-managers"}, method = RequestMethod.GET)
	public ModelAndView allmanagers() {
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("allManager", userInfoService.getAllManager());
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelAndView.setViewName("admin/all-managers");
		return modelAndView;
	}
	
	@RequestMapping(value = {"add-manager"}, method = RequestMethod.GET)
	public ModelAndView addmanager() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/add-manager");
		return modelAndView;
	}
	
	@RequestMapping(value = {"edit-manager"}, method = RequestMethod.GET)
	public ModelAndView editmanager() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/edit-manager");
		return modelAndView;
	}
	
	@RequestMapping(value = {"/manager-profile"}, method = RequestMethod.GET)
	public ModelAndView managerProfile() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin//manager-profile");
		return modelAndView;
	}
}
