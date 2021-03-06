package com.acatim.acatimver1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.service.CategoriesService;
import com.acatim.acatimver1.service.ContactService;
import com.acatim.acatimver1.service.CourseService;
import com.acatim.acatimver1.service.RatingService;
import com.acatim.acatimver1.service.SubjectService;
import com.acatim.acatimver1.service.UserInfoService;

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
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private RatingService ratingService;
	
	@RequestMapping(value = { "/", "/index"}, method = RequestMethod.GET)
	public ModelAndView indexAdmin() {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			
			modelAndView.addObject("allStudent", userInfoService.countStudent());
			modelAndView.addObject("allStudyCenter", userInfoService.countStudyCenter());
			modelAndView.addObject("allTeacher", userInfoService.countTeacher());
			modelAndView.addObject("allCourse", courseService.getAllCourse().size());
			modelAndView.addObject("allSubject", subjectService.getListSubject().size());
			modelAndView.addObject("allCategories", categoriesService.getAllCategories().size());
			
			modelAndView.addObject("countStudent", userInfoService.countStudentByDate());
			modelAndView.addObject("countTeacher", userInfoService.countTeacherByDate());
			modelAndView.addObject("countStudyCenter", userInfoService.countStudyCentertByDate());
			
			modelAndView.addObject("countCourse", courseService.countCourseByDate());
			modelAndView.addObject("countContact", contactService.countContactUsByDate());
			modelAndView.addObject("countRating", ratingService.countRatingByDate());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		modelAndView.setViewName("admin/index");
		return modelAndView;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "not found")
	public ModelAndView notFoundException(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("404");
		return modelAndView;
	}
	
	@RequestMapping(value = {"error"}, method = RequestMethod.GET)
	public ModelAndView error() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		return modelAndView;
	}
}
