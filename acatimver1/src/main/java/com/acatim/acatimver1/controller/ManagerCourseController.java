package com.acatim.acatimver1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.service.CourseServiceImpl;

@Controller
@RequestMapping(value = {"/admin"})
public class ManagerCourseController {

	@Autowired
	private CourseServiceImpl courseService;
	

	@RequestMapping(value = {"all-courses"}, method = RequestMethod.GET)
	public ModelAndView allCourses() {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			modelAndView.addObject("allCourses", courseService.getAllCourse());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		modelAndView.setViewName("admin/all-courses");
		return modelAndView;
	}

	@RequestMapping(value = {"add-course"}, method = RequestMethod.GET)
	public ModelAndView addCourse() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/add-course");
		return modelAndView;
	}

	@RequestMapping(value = {"edit-course"}, method = RequestMethod.GET)
	public ModelAndView editCourse() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/edit-course");
		return modelAndView;
	}

	@RequestMapping(value = {"course-info"}, method = RequestMethod.GET)
	public ModelAndView courseInfo() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/course-info");
		return modelAndView;
	}

}
