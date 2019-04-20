package com.acatim.acatimver1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.service.CategoriesService;
import com.acatim.acatimver1.service.CourseService;
import com.acatim.acatimver1.service.SubjectService;
import com.acatim.acatimver1.service.UserInfoService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = {"/admin"})
public class AdminController {
	
	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private CategoriesService categoriesService;
	
	@RequestMapping(value = { "/", "/index"}, method = RequestMethod.GET)
	public ModelAndView indexAdmin() {
		ModelAndView modelAndView = new ModelAndView();
		
		
		
		try {
			modelAndView.addObject("allStudent", userInfoService.loadAllUserStudent().size());
			modelAndView.addObject("allStudyCenter", userInfoService.loadAllUserStudyCenter().size());
			modelAndView.addObject("allTeacher", userInfoService.loadAllUserTeacher().size());
			modelAndView.addObject("allCourse", courseService.getAllCourse().size());
			modelAndView.addObject("allSubject", subjectService.getListSubject().size());
			modelAndView.addObject("allCategories", categoriesService.getAllCategories().size());

			
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		modelAndView.setViewName("admin/index");
		return modelAndView;
	}

	@RequestMapping(value = {"tinymc"}, method = RequestMethod.GET)
	public ModelAndView textEditer() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/tinymc");
		return modelAndView;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "not found")
	public ModelAndView notFoundException(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("404");
		return modelAndView;
	}
}
