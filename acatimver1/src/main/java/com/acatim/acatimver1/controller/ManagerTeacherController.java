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
public class ManagerTeacherController {

	@Autowired
	private UserInfoServiceImpl userInfoService;
	
	@RequestMapping(value = {"all-teachers"}, method = RequestMethod.GET)
	public ModelAndView allTeachers() {
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("allTeacher", userInfoService.loadAllUserTeacher());
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("admin/all-teachers");
		return modelAndView;
	}

	@RequestMapping(value = {"add-teacher"}, method = RequestMethod.GET)
	public ModelAndView addTeacher() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/add-teacher");
		return modelAndView;
	}

	@RequestMapping(value = {"edit-teacher"}, method = RequestMethod.GET)
	public ModelAndView editTeacher() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/edit-teacher");
		return modelAndView;
	}

	@RequestMapping(value = {"teacher-profile"}, method = RequestMethod.GET)
	public ModelAndView teacherProfile() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/teacher-profile");
		return modelAndView;
	}
}
