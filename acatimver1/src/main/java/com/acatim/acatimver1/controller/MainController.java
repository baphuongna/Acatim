package com.acatim.acatimver1.controller;

import java.security.Principal;
import java.util.List;

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

import com.acatim.acatimver1.model.ObjectUser;
import com.acatim.acatimver1.model.Student;
import com.acatim.acatimver1.model.StudyCenter;
import com.acatim.acatimver1.model.Teacher;
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
public class MainController {

	@Autowired
	private UserInfoServiceImpl userInfoService;

	@Autowired
	private CourseServiceImpl courseService;

	@Autowired
	private SubjectServiceImpl subjectService;
	
	@Autowired
	private RatingServiceImpl ratingService;

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
	
	@RequestMapping("/course")
	public String showAllCourseFull(Model model) {
		model.addAttribute("courses", courseService.getAllCourse());
		return "course";
	}

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
				model.addAttribute("courses", courseService.getCourseByUserName(userName));
			}
			modelAndView.setViewName("profile");
	    }
		
		return modelAndView;
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
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		UserModel user = new UserModel();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid @ModelAttribute("user") ObjectUser data, BindingResult bindingResult) {
		System.out.println("11111112");
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("bbbb" + data);
		// Object userExists =
		// userInfoService.loadUserByUsername(user.getUserName(),"Student");
		/*
		 * if (userExists != null) { bindingResult .rejectValue("email", "error.user",
		 * "There is already a user registered with the email provided"); }
		 */

		if (bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage","Bạn đã nhập sai một số thông tin,vui long kiểm tra lại");
			modelAndView.setViewName("registration");
		} else {
			modelAndView.addObject("successMessage", "Chúc mừng bạn đã đăng kí thành công");
			System.out.println("thanh công");
			try {
				UserModel user = new UserModel(data.getUserName(), data.getRole_id(), data.getFullName(),
						data.getEmail(), data.getPassword(), data.getCreateDate(), data.getPhone(), data.getAddress(),
						data.isActive());
				if (data.getRole_id() == 1) {
					Student newStudent = new Student(data.getUserName(), data.getCreateDate(), data.isGender());
					userInfoService.addUserInfo(user);
					userInfoService.addStudentInfo(newStudent);
				}
				if (data.getRole_id() == 2) {
					Teacher newTeacher = new Teacher(data.getUserName(), data.getDob(), data.isGender(),
							data.getDescription(), 1);
					userInfoService.addUserInfo(user);
					userInfoService.addTeacherInfo(newTeacher);
				}
				if (data.getRole_id() == 3) {
					StudyCenter newStudyCenter = new StudyCenter(data.getUserName(), data.getDescription(),1);
					userInfoService.addUserInfo(user);
					userInfoService.addStudyCenterInfo(newStudyCenter);
				}
			} catch (NotFoundException e) {
				System.out.println("error regitration");
				e.printStackTrace();
			}
			modelAndView.addObject("successMessage", "Chúc mừng bạn đã đăng kí thành công");
			// modelAndView.addObject("user", new User());
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
