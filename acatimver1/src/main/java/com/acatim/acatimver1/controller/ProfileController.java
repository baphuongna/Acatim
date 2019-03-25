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

import com.acatim.acatimver1.model.Course;
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
		boolean checkDetail = false;
		String userName = principal.getName();
		System.out.println("User Name: " + userName);

		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String roleName = WebUtils.toString(loginedUser);
		UserModel useInfo = userInfoService.loadUserByUsername(userName);
		
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(roleName);
		String gender = null;
		int progress5 = 80;
		int progress4 = 60;
		int progress3 = 40;
		int progress2 = 20;
		int progress1 = 15;
		 if (useInfo == null) {
	            System.out.println("User not found! " + userName);
	            modelAndView.setViewName("index");
	            throw new NotFoundException("User " + userName + " was not found in the database");
	           
	    }else {
	    	model.addAttribute("useInfo", useInfo);
			model.addAttribute("roleName", roleName);
			model.addAttribute("checkDetail", checkDetail);
			List<Course> courses = courseService.getCourseByUserNameWithFullInfo(userName);
			if(roleName.equals("Student")) {
				Student student = userInfoService.loadStudentByUsername(userName);
				model.addAttribute("studentInfo", student);
				if(student.isGender() == true) {
					gender = "Nam";
				}else {
					gender = "Nữ";
				}
				model.addAttribute("gender", gender);
				
				List<Rating> ratings = ratingService.getAllRatingByUserName(userName);
				model.addAttribute("ratings", ratings);
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
				int numberPeopleRate = 0;
				
				for(int i=0; i< ratings.size(); i++) {
					numberPeopleRate++;
				}
				model.addAttribute("courses", courses);
				model.addAttribute("ratings", ratings);
				model.addAttribute("progress5", progress5);
				model.addAttribute("progress4", progress4);
				model.addAttribute("progress3", progress3);
				model.addAttribute("progress2", progress2);
				model.addAttribute("progress1", progress1);
				model.addAttribute("numberPeopleRate", numberPeopleRate);
				
			}else if(roleName.equals("Study Center")) {
				
				model.addAttribute("studyCenterInfo", userInfoService.loadStudyCenterByUsername(userName));
				List<Rating> ratings = ratingService.getAllRatingStudyCenterByRecieverName(userName);
				
				System.out.println(courses);
				System.out.println(ratings);
				int numberPeopleRate = 0;
				
				for(int i=0; i< ratings.size(); i++) {
					numberPeopleRate++;
				}
				model.addAttribute("courses", courses);
				model.addAttribute("ratings", ratings);
				model.addAttribute("progress5", progress5);
				model.addAttribute("progress4", progress4);
				model.addAttribute("progress3", progress3);
				model.addAttribute("progress2", progress2);
				model.addAttribute("progress1", progress1);
				model.addAttribute("numberPeopleRate", numberPeopleRate);
				
			}
			modelAndView.setViewName("profile");
	    }
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/profileDetail", method = RequestMethod.GET)
	public ModelAndView profile(@RequestParam("userName") String name, Model model, Principal principal) throws NotFoundException {

		// Sau khi user login thanh cong se co principal
		//String userName = principal.getName();
		String curentUserName = principal.getName();
		System.out.println("User Name: " + name);
		boolean checkDetail = true;
		boolean showDetailMySelf = false;
		
		//User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String roleName = userInfoService.getRoleName(name);
		UserModel useInfo = userInfoService.loadUserByUsername(name);
		List<Course> courses = courseService.getCourseByUserNameWithFullInfo(name);
		ModelAndView modelAndView = new ModelAndView();
		
		
		int progress5 = 80;
		int progress4 = 60;
		int progress3 = 40;
		int progress2 = 20;
		int progress1 = 15;
		String gender = null;
		 if (useInfo == null) {
	            System.out.println("User not found! " + name);
	            modelAndView.setViewName("index");
	            throw new NotFoundException("User " + name + " was not found in the database");
	           
	    }else {
	    	model.addAttribute("useInfo", useInfo);
			model.addAttribute("roleName", roleName);
			model.addAttribute("checkDetail", checkDetail);
			
			
			if (name.equals(curentUserName)) {
				showDetailMySelf = true;
			}
			model.addAttribute("showDetailMySelf", showDetailMySelf);
			
			if(roleName.equals("Student")) {
				Student student = userInfoService.loadStudentByUsername(name);
				model.addAttribute("studentInfo", userInfoService.loadStudentByUsername(name));
				if(student.isGender() == true) {
					gender = "Nam";
				} else {
					gender = "Nữ";
				}
				model.addAttribute("gender", gender);
				List<Rating> ratings = ratingService.getAllRatingByUserName(name);
				model.addAttribute("ratings", ratings);
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
				int numberPeopleRate = 0;
				
				for(int i=0; i< ratings.size(); i++) {
					numberPeopleRate++;
				}
				model.addAttribute("courses", courses);
				model.addAttribute("ratings", ratings);
				model.addAttribute("progress5", progress5);
				model.addAttribute("progress4", progress4);
				model.addAttribute("progress3", progress3);
				model.addAttribute("progress2", progress2);
				model.addAttribute("progress1", progress1);
				model.addAttribute("numberPeopleRate", numberPeopleRate);
				
			}else if(roleName.equals("Study Center")) {
				
				model.addAttribute("studyCenterInfo", userInfoService.loadStudyCenterByUsername(name));
				
				List<Rating> ratings = ratingService.getAllRatingStudyCenterByRecieverName(name);
				System.out.println(ratings);
				int numberPeopleRate = 0;
				
				for(int i=0; i< ratings.size(); i++) {
					numberPeopleRate++;
				}
				
				model.addAttribute("courses", courses);
				model.addAttribute("ratings", ratings);
				model.addAttribute("progress5", progress5);
				model.addAttribute("progress4", progress4);
				model.addAttribute("progress3", progress3);
				model.addAttribute("progress2", progress2);
				model.addAttribute("progress1", progress1);
				model.addAttribute("numberPeopleRate", numberPeopleRate);
			}
			modelAndView.setViewName("profile");
	    }
		
		return modelAndView;
	}

}
