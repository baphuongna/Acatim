package com.acatim.acatimver1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.form.StudentForm;
import com.acatim.acatimver1.service.UserInfoServiceImpl;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = {"/admin"})
public class ManagerStudentController {

	@Autowired
	private UserInfoServiceImpl userInfoService;
	
	@RequestMapping(value = {"/all-students"}, method = RequestMethod.GET)
	public ModelAndView allStudents() {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			StudentForm studentForm = new StudentForm();
			
			modelAndView.addObject("studentForm", studentForm);
			modelAndView.addObject("allStudent", userInfoService.loadAllUserStudent());
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("admin/all-students");
		return modelAndView;
	}
	
	@RequestMapping(value = {"studentUpdate"}, method = RequestMethod.POST)
	public ModelAndView updateStudent(@ModelAttribute("appUserForm") @Validated StudentForm studentForm) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(studentForm);
		modelAndView.setViewName("redirect:/admin/all-students");
		return modelAndView;
	}
	
	@RequestMapping(value = {"blockStudent"}, method = RequestMethod.GET)
	public ModelAndView blockStudent(@RequestParam("userName") String userName) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			userInfoService.removeUser(userName);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/all-students");
		return modelAndView;
	}
	
	@RequestMapping(value = {"unlockStudent"}, method = RequestMethod.GET)
	public ModelAndView unlockStudent(@RequestParam("userName") String userName) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			userInfoService.unlockUser(userName);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/all-students");
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
