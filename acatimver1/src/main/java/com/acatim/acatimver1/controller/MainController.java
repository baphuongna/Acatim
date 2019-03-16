package com.acatim.acatimver1.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.service.CourseServiceImpl;
import com.acatim.acatimver1.service.SubjectServiceImpl;
import com.acatim.acatimver1.service.UserInfoServiceImpl;
import com.acatim.acatimver1.utils.WebUtils;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = {""})
public class MainController {

	@Autowired
	private UserInfoServiceImpl userInfoService;

	@Autowired
	private CourseServiceImpl courseService;

	@Autowired
	private SubjectServiceImpl subjectService;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public ModelAndView index(Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listCourse", courseService.getAllCourse());
		
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userRole = WebUtils.toString(loginedUser);

			modelAndView.addObject("userRole", userRole);
		}
		
		try {
			modelAndView.addObject("numberOfTeacher", userInfoService.loadAllTeacher().size());
			modelAndView.addObject("numberOfStudyCenter", userInfoService.loadAllStudyCenter().size());
			modelAndView.addObject("numberOfStudent", userInfoService.loadAllStudent().size());
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelAndView.setViewName("index");
		return modelAndView;
	}

//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public String adminPage(Model model, Principal principal) {
//         
//        User loginedUser = (User) ((Authentication) principal).getPrincipal();
// 
//        String userInfo = WebUtils.toString(loginedUser);
//        model.addAttribute("userInfo", userInfo);
//         
//        return "adminPage";
//    }
// 
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String loginPage(Model model) {
// 
//        return "loginPage";
//    }

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String logoutSuccessfulPage(Model model) {
//        //model.addAttribute("title", "Logout");
//        return "index";
//    }

	@RequestMapping("/test")
	public String showAllCourse(Model model) {
		model.addAttribute("subjects", subjectService.getAllSubject());
		model.addAttribute("courses", courseService.getAllCourse());
		return "TestShowCourse";
	}
	
	@RequestMapping("/course")
	public String showAllCourseFull(Model model) {
		model.addAttribute("courses", courseService.getAllCourse());
		return "course";
	}
	
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginForm() {
		return "loginPage";
	}


	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userInfo = WebUtils.toString(loginedUser);

			model.addAttribute("userInfo", userInfo);

			String message = "Hi " + principal.getName() //
					+ "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);

		}

		return "403Page";
	}

	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "index";
	}

//    @RequestMapping(value={"/profileS"}, method = RequestMethod.GET)
//    public ModelAndView profileS(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("profileS");
//        return modelAndView;
//    }

	@RequestMapping(value = { "/about" }, method = RequestMethod.GET)
	public ModelAndView about() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("about");
		return modelAndView;
	}

//	@RequestMapping(value = { "/course" }, method = RequestMethod.GET)
//	public ModelAndView course() {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("course");
//		return modelAndView;
//	}

	@RequestMapping(value = { "/teacher" }, method = RequestMethod.GET)
	public ModelAndView teacher() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("teacher");
		return modelAndView;
	}

	@RequestMapping(value = { "/rating" }, method = RequestMethod.GET)
	public ModelAndView rating() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("rating");
		return modelAndView;
	}
	
	
	@RequestMapping(value = { "/blog" }, method = RequestMethod.GET)
	public ModelAndView blog() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("blog");
		return modelAndView;
	}

	@RequestMapping(value = { "/event" }, method = RequestMethod.GET)
	public ModelAndView event() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("event");
		return modelAndView;
	}

	@RequestMapping(value = { "/contact" }, method = RequestMethod.GET)
	public ModelAndView contact() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contact");
		return modelAndView;
	}

	@RequestMapping(value = { "/ratingTeacher" }, method = RequestMethod.GET)
	public ModelAndView ratingTeacher() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ratingTeacher");
		return modelAndView;
	}

}
