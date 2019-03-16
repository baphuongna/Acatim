package com.acatim.acatimver1.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.model.Rating;
import com.acatim.acatimver1.model.Student;
import com.acatim.acatimver1.model.Teacher;
import com.acatim.acatimver1.model.UserModel;
import com.acatim.acatimver1.service.CourseServiceImpl;
import com.acatim.acatimver1.service.RatingServiceImpl;
import com.acatim.acatimver1.service.SubjectServiceImpl;
import com.acatim.acatimver1.service.UserInfoServiceImpl;
import com.acatim.acatimver1.utils.WebUtils;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = {""})
public class ProfileController {

	@Autowired
	private UserInfoServiceImpl userInfoService;

	@Autowired
	private CourseServiceImpl courseService;

	@Autowired
	private SubjectServiceImpl subjectService;
	
	@Autowired
	private RatingServiceImpl ratingService;
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView userInfo(Model model, Principal principal) throws NotFoundException {

		// Sau khi user login thanh cong se co principal
		String userName = principal.getName();
		System.out.println("User Name: " + userName);

		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String roleName = WebUtils.toString(loginedUser);
		UserModel useInfo = userInfoService.loadUserByUsername(userName);
		
		ModelAndView modelAndView = new ModelAndView();
		String gender = null;
		 if (useInfo == null) {
	            System.out.println("User not found! " + userName);
	            modelAndView.setViewName("index");
	            throw new NotFoundException("User " + userName + " was not found in the database");
	           
	    }else {
	    	model.addAttribute("useInfo", useInfo);
			model.addAttribute("roleName", roleName);
			if(roleName.equals("Student")) {
				Student student = userInfoService.loadStudentByUsername(userName);
				model.addAttribute("studentInfo", userInfoService.loadStudentByUsername(userName));
				if(student.isGender() == true) {
					gender = "Nam";
				}else {
					gender = "Nữ";
				}
				model.addAttribute("gender", gender);
			}else if(roleName.equals("Teacher")) {
				Teacher teacher = userInfoService.loadTeacherByUsername(userName);
				model.addAttribute("teacherInfo", teacher);
				if(teacher.isGender() == true) {
					gender = "Nam";
				}else {
					gender = "Nữ";
				}
				model.addAttribute("gender", gender);
				List<Rating> ratings = ratingService.getAllRatingTeacherByRecieverName(userName);
				System.out.println(ratings);
				model.addAttribute("ratings", ratings);
				
			}else if(roleName.equals("Study Center")) {
				
				model.addAttribute("studyCenterInfo", userInfoService.loadStudyCenterByUsername(userName));
				
				List<Rating> ratings = ratingService.getAllRatingStudyCenterByRecieverName(userName);
				System.out.println(ratings);
				model.addAttribute("ratings", ratings);
			}
			modelAndView.setViewName("profile");
	    }
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/profileDetail", method = RequestMethod.GET)
	public ModelAndView profile(@RequestParam("userName") String name, Model model) throws NotFoundException {

		// Sau khi user login thanh cong se co principal
		//String userName = principal.getName();
		System.out.println("User Name: " + name);

		//User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String roleName = userInfoService.getRoleName(name);
		UserModel useInfo = userInfoService.loadUserByUsername(name);
		
		ModelAndView modelAndView = new ModelAndView();
		String gender = null;
		 if (useInfo == null) {
	            System.out.println("User not found! " + name);
	            modelAndView.setViewName("index");
	            throw new NotFoundException("User " + name + " was not found in the database");
	           
	    }else {
	    	model.addAttribute("useInfo", useInfo);
			model.addAttribute("roleName", roleName);
			if(roleName.equals("Student")) {
				Student student = userInfoService.loadStudentByUsername(name);
				model.addAttribute("studentInfo", userInfoService.loadStudentByUsername(name));
				if(student.isGender() == true) {
					gender = "Nam";
				}else {
					gender = "Nữ";
				}
				model.addAttribute("gender", gender);
			}else if(roleName.equals("Teacher")) {
				Teacher teacher = userInfoService.loadTeacherByUsername(name);
				model.addAttribute("teacherInfo", teacher);
				if(teacher.isGender() == true) {
					gender = "Nam";
				}else {
					gender = "Nữ";
				}
				model.addAttribute("gender", gender);
				List<Rating> ratings = ratingService.getAllRatingTeacherByRecieverName(name);
				System.out.println(ratings);
				model.addAttribute("ratings", ratings);
				
			}else if(roleName.equals("Study Center")) {
				
				model.addAttribute("studyCenterInfo", userInfoService.loadStudyCenterByUsername(name));
				
				List<Rating> ratings = ratingService.getAllRatingStudyCenterByRecieverName(name);
				System.out.println(ratings);
				model.addAttribute("ratings", ratings);
			}
			modelAndView.setViewName("profile");
	    }
		
		return modelAndView;
	}

}
