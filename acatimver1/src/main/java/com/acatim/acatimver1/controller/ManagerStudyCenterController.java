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
public class ManagerStudyCenterController {

	
	@Autowired
	private UserInfoServiceImpl userInfoService;
	

	@RequestMapping(value = {"all-study-centers"}, method = RequestMethod.GET)
	public ModelAndView allStudyCenters() {
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("allStudyCenter", userInfoService.loadAllUserStudyCenter());
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("admin/all-study-centers");
		return modelAndView;
	}

	@RequestMapping(value = {"add-study-centers"}, method = RequestMethod.GET)
	public ModelAndView addStudyCenters() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/add-study-centers");
		return modelAndView;
	}

	@RequestMapping(value = {"edit-study-centers"}, method = RequestMethod.GET)
	public ModelAndView editStudyCenters() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/edit-study-centers");
		return modelAndView;
	}

	@RequestMapping(value = {"study-centers-profile"}, method = RequestMethod.GET)
	public ModelAndView studyCentersProfile() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/study-centers-profile");
		return modelAndView;
	}

}
