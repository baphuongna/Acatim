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
public class ManagerStudentController {

	@Autowired
	private UserInfoServiceImpl userInfoService;
	
	@RequestMapping(value = {"all-students"}, method = RequestMethod.GET)
	public ModelAndView allStudents() {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			modelAndView.addObject("allStudent", userInfoService.loadAllUserStudent());
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("admin/all-students");
		return modelAndView;
	}
	
	@RequestMapping(value = {"add-student"}, method = RequestMethod.GET)
	public ModelAndView addStudent() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/add-student");
		return modelAndView;
	}

	@RequestMapping(value = {"edit-student"}, method = RequestMethod.GET)
	public ModelAndView editStudent() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/edit-student");
		return modelAndView;
	}

	@RequestMapping(value = {"student-profile"}, method = RequestMethod.GET)
	public ModelAndView studentProfile() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/student-profile");
		return modelAndView;
	}
}
