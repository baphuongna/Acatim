package com.acatim.acatimver1.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.dao.UserDAO;
import com.acatim.acatimver1.model.UserModel;
import com.acatim.acatimver1.service.CourseServiceImpl;
import com.acatim.acatimver1.service.SubjectServiceImpl;
import com.acatim.acatimver1.service.UserDetailsServiceImpl;
import com.acatim.acatimver1.service.UserInfoServiceImpl;
import com.acatim.acatimver1.utils.WebUtils;

import javassist.NotFoundException;

@Controller
public class MainController {

	@Autowired
	private UserInfoServiceImpl userInfoService;

	@Autowired
	private CourseServiceImpl courseService;

	@Autowired
	private SubjectServiceImpl subjectService;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listCourse", courseService.getAllCourse());
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

	@RequestMapping(value = "/profileS", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {

		// Sau khi user login thanh cong se co principal
		String userName = principal.getName();
		System.out.println("User Name: " + userName);

		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String roleName = WebUtils.toString(loginedUser);
		Object useInfo = userInfoService.loadUserByUsername(userName, roleName);

		model.addAttribute("useInfo", useInfo);

		model.addAttribute("roleName", roleName);

		return "profile";
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

	@RequestMapping(value = { "/course" }, method = RequestMethod.GET)
	public ModelAndView course() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("course");
		return modelAndView;
	}

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

	 @RequestMapping(value="/registration", method = RequestMethod.GET)
	    public ModelAndView registration(){
	        ModelAndView modelAndView = new ModelAndView();
	        UserModel user = new UserModel();
	        modelAndView.addObject("user", user);
	        modelAndView.setViewName("registration");
	        return modelAndView;
	    }

	    @RequestMapping(value = "/registration", method = RequestMethod.POST)
	    public ModelAndView createNewUser(@Valid @ModelAttribute("user") UserModel data, BindingResult bindingResult) {
	    	 System.out.println("11111112");
	        ModelAndView modelAndView = new ModelAndView();
	      //  Object userExists =  userInfoService.loadUserByUsername(user.getUserName(),"Student");
		/*
		 * if (userExists != null) { bindingResult .rejectValue("email", "error.user",
		 * "There is already a user registered with the email provided"); }
		 */
	        
	        if (bindingResult.hasErrors()) {
	            modelAndView.setViewName("registration");
	            System.out.println("1111111111");
	        } else {
	        	 System.out.println("55555555555");
	           // userService.saveUser(user);
	            modelAndView.addObject("successMessage", "User has been registered successfully");
	          //  modelAndView.addObject("user", new User());
	            modelAndView.setViewName("registration");

	        }
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
