package com.acatim.acatimver1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.entity.Course;
import com.acatim.acatimver1.service.CourseService;
import com.acatim.acatimver1.service.DiscountCodeService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = {""})
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private DiscountCodeService discountCodeServiceImpl;
	
	@RequestMapping(value = "/course" , method = RequestMethod.GET)
	public String showAllCourseFull(Model model) {
		model.addAttribute("courses", courseService.getAllCourse());
		Course search = new Course();
		model.addAttribute("search",search);
		
		model.addAttribute("discountCode", discountCodeServiceImpl.getAllDiscountCode());
		return "course";
	}
	

	@RequestMapping(value = "/course", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid @ModelAttribute("search") Course khanh, BindingResult bindingResult)
			throws NotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		
		courseService.searchCourseByCourseName(khanh.getCourseName());
		modelAndView.setViewName("course");
		return modelAndView;
	}
}
