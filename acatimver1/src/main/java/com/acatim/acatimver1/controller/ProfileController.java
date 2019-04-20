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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acatim.acatimver1.entity.CountRate;
import com.acatim.acatimver1.entity.Course;
import com.acatim.acatimver1.entity.RateStudyCenter;
import com.acatim.acatimver1.entity.RateTeacher;
import com.acatim.acatimver1.entity.Rating;
import com.acatim.acatimver1.entity.Student;
import com.acatim.acatimver1.entity.StudyCenter;
import com.acatim.acatimver1.entity.Teacher;
import com.acatim.acatimver1.entity.UserModel;
import com.acatim.acatimver1.form.PasswordForm;
import com.acatim.acatimver1.format.DateFormat;
import com.acatim.acatimver1.service.CourseService;
import com.acatim.acatimver1.service.RatingService;
import com.acatim.acatimver1.service.UserInfoService;
import com.acatim.acatimver1.utils.WebUtils;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = { "" })
public class ProfileController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private RatingService ratingService;
	
	private DateFormat dateformat = new DateFormat();

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView userInfo(Model model, Principal principal) throws NotFoundException {
		// Sau khi user login thanh cong se co principal
		String userName = principal.getName();

		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String roleName = WebUtils.toString(loginedUser);
		UserModel useInfo = userInfoService.loadUserByUsername(userName);

		ModelAndView modelAndView = new ModelAndView();
		if (useInfo == null) {
			System.out.println("User not found! " + userName);
			modelAndView.setViewName("index");
			throw new NotFoundException("User " + userName + " was not found in the database");
		} else {
			if (roleName.equals("Student")) {
				modelAndView.setViewName("redirect:/profile-student");
			} else if (roleName.equals("Teacher")) {
				modelAndView.setViewName("redirect:/profile-teacher");
			} else if (roleName.equals("Study Center")) {
				modelAndView.setViewName("redirect:/profile-study-center");
			}
		}
		return modelAndView;
	}

	@RequestMapping(value = "/profileDetail", method = RequestMethod.GET)
	public ModelAndView profile(@RequestParam("userName") String name, Model model, Principal principal, RedirectAttributes redirectAttributes)
			throws NotFoundException {

		String roleName = userInfoService.getRoleName(name);
		UserModel useInfo = userInfoService.loadUserByUsername(name);
		ModelAndView modelAndView = new ModelAndView();

		if (useInfo == null) {
			System.out.println("User not found! " + name);
			modelAndView.setViewName("index");
			throw new NotFoundException("User " + name + " was not found in the database");
		} else {
			redirectAttributes.addAttribute("userName", name);
			if (roleName.equals("Student")) {
				modelAndView.setViewName("redirect:/DetailStudent");
			} else if (roleName.equals("Teacher")) {
				modelAndView.setViewName("redirect:/DetailTeacher");
			} else if (roleName.equals("Study Center")) {
				modelAndView.setViewName("redirect:/DetailStudyCenter");
			}
		}
		return modelAndView;
	}

	@RequestMapping(value = "/profile-student", method = RequestMethod.GET)
	public ModelAndView profileStudent(Model model, Principal principal) throws NotFoundException {
		// Sau khi user login thanh cong se co principal
		boolean checkDetail = false;
		String userName = principal.getName();

		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String roleName = WebUtils.toString(loginedUser);
		UserModel useInfo = userInfoService.loadUserByUsername(userName);

		ModelAndView modelAndView = new ModelAndView();
		String gender = null;
		if (useInfo == null) {
			System.out.println("User not found! " + userName);
			modelAndView.setViewName("index");
			throw new NotFoundException("User " + userName + " was not found in the database");

		} else {
			model.addAttribute("useInfo", useInfo);
			model.addAttribute("roleName", roleName);
			model.addAttribute("checkDetail", checkDetail);

			Student student = userInfoService.loadStudentByUsername(userName);
			model.addAttribute("studentInfo", student);
			if (student.isGender() == true) {
				gender = "Nam";
			} else {
				gender = "Nữ";
			}
			model.addAttribute("gender", gender);

			List<Rating> ratings = ratingService.getAllRatingByUserName(userName);
			model.addAttribute("ratings", ratings);

			modelAndView.setViewName("profile-student");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/profile-teacher", method = RequestMethod.GET)
	public ModelAndView profileTeacher(Model model, Principal principal) throws NotFoundException {
		// Sau khi user login thanh cong se co principal
		boolean checkDetail = false;
		String userName = principal.getName();

		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String roleName = WebUtils.toString(loginedUser);
		UserModel useInfo = userInfoService.loadUserByUsername(userName);

		ModelAndView modelAndView = new ModelAndView();

		String gender = null;
		float rateAverage = 0;

		if (useInfo == null) {
			System.out.println("User not found! " + userName);
			modelAndView.setViewName("index");
			throw new NotFoundException("User " + userName + " was not found in the database");

		} else {
			model.addAttribute("useInfo", useInfo);
			model.addAttribute("roleName", roleName);
			model.addAttribute("checkDetail", checkDetail);
			List<Course> courses = courseService.getCourseByUserNameWithFullInfo(userName);

			Teacher teacher = userInfoService.loadTeacherByUsername(userName);
			model.addAttribute("teacherInfo", teacher);

			rateAverage = teacher.getRate();

			if (teacher.isGender() == true) {
				gender = "Nam";
			} else {
				gender = "Nữ";
			}
			model.addAttribute("gender", gender);

			List<Rating> ratings = ratingService.getAllRatingTeacherByRecieverName(userName);

			CountRate count = ratingService.countRatingTeacher(userName);
			RateTeacher caculater = ratingService.caculaterRateTeacher(userName);
			
			model.addAttribute("rateAverage", rateAverage);
			model.addAttribute("courses", courses);
			model.addAttribute("ratings", ratings);
			model.addAttribute("count", count);
			model.addAttribute("caculater", caculater);

			modelAndView.setViewName("profile-teacher");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/profile-study-center", method = RequestMethod.GET)
	public ModelAndView profileStudyCenter(Model model, Principal principal) throws NotFoundException {
		// Sau khi user login thanh cong se co principal
		boolean checkDetail = false;
		String userName = principal.getName();

		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String roleName = WebUtils.toString(loginedUser);
		UserModel useInfo = userInfoService.loadUserByUsername(userName);

		ModelAndView modelAndView = new ModelAndView();

		float rateAverage = 0;

		if (useInfo == null) {
			System.out.println("User not found! " + userName);
			modelAndView.setViewName("index");
			throw new NotFoundException("User " + userName + " was not found in the database");

		} else {
			model.addAttribute("useInfo", useInfo);
			model.addAttribute("roleName", roleName);
			model.addAttribute("checkDetail", checkDetail);
			List<Course> courses = courseService.getCourseByUserNameWithFullInfo(userName);

			StudyCenter studyCenter = userInfoService.loadStudyCenterByUsername(userName);
			model.addAttribute("studyCenterInfo", studyCenter);

			List<Rating> ratings = ratingService.getAllRatingStudyCenterByRecieverName(userName);

			rateAverage = studyCenter.getRate();

			CountRate count = ratingService.countRatingStudyCenter(userName);
			RateStudyCenter caculater = ratingService.caculaterRateStudyCenter(userName);
			
			model.addAttribute("rateAverage", rateAverage);
			model.addAttribute("courses", courses);
			model.addAttribute("ratings", ratings);
			model.addAttribute("count", count);
			model.addAttribute("caculater", caculater);
			
			modelAndView.setViewName("profile-study-center");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/DetailStudent", method = RequestMethod.GET)
	public ModelAndView detailStudent(@RequestParam("userName") String name, Model model, Principal principal)
			throws NotFoundException {

		// Sau khi user login thanh cong se co principal
		// String userName = principal.getName();
		String curentUserName = null;
		if (principal != null) {
			curentUserName = principal.getName();
		}

		boolean checkDetail = true;
		boolean showDetailMySelf = false;
		String curentRoleName;
		String roleName = userInfoService.getRoleName(name);
		if (curentUserName != null) {
			curentRoleName = userInfoService.getRoleName(curentUserName);
		} else {
			curentRoleName = "";
		}
		UserModel useInfo = userInfoService.loadUserByUsername(name);
		ModelAndView modelAndView = new ModelAndView();

		String gender = null;
		if (useInfo == null) {
			System.out.println("User not found! " + name);
			modelAndView.setViewName("index");
			throw new NotFoundException("User " + name + " was not found in the database");

		} else {
			model.addAttribute("useInfo", useInfo);
			model.addAttribute("roleName", roleName);
			model.addAttribute("checkDetail", checkDetail);
			model.addAttribute("curentRoleName", curentRoleName);

			if (curentUserName != null && name.equals(curentUserName)) {
				showDetailMySelf = true;
			} else {
				showDetailMySelf = false;
			}

			model.addAttribute("showDetailMySelf", showDetailMySelf);

			Student student = userInfoService.loadStudentByUsername(name);
			model.addAttribute("studentInfo", userInfoService.loadStudentByUsername(name));
			if (student.isGender() == true) {
				gender = "Nam";
			} else {
				gender = "Nữ";
			}
			model.addAttribute("gender", gender);
			List<Rating> ratings = ratingService.getAllRatingByUserName(name);
			model.addAttribute("ratings", ratings);

			modelAndView.setViewName("profile-student");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/DetailTeacher", method = RequestMethod.GET)
	public ModelAndView detailTeacher(@RequestParam("userName") String name, Model model, Principal principal)
			throws NotFoundException {

		// Sau khi user login thanh cong se co principal
		// String userName = principal.getName();
		String curentUserName = null;
		if (principal != null) {
			curentUserName = principal.getName();
		}

		boolean checkDetail = true;
		boolean showDetailMySelf = false;
		String curentRoleName;
		String roleName = userInfoService.getRoleName(name);
		if (curentUserName != null) {
			curentRoleName = userInfoService.getRoleName(curentUserName);
		} else {
			curentRoleName = "";
		}
		UserModel useInfo = userInfoService.loadUserByUsername(name);
		List<Course> courses = courseService.getCourseByUserNameWithFullInfo(name);
		ModelAndView modelAndView = new ModelAndView();

		float rateAverage = 0;

		String gender = null;
		if (useInfo == null) {
			System.out.println("User not found! " + name);
			modelAndView.setViewName("index");
			throw new NotFoundException("User " + name + " was not found in the database");

		} else {
			model.addAttribute("useInfo", useInfo);
			model.addAttribute("roleName", roleName);
			model.addAttribute("checkDetail", checkDetail);
			model.addAttribute("curentRoleName", curentRoleName);

			if (curentUserName != null && name.equals(curentUserName)) {
				showDetailMySelf = true;
			} else {
				showDetailMySelf = false;
			}

			model.addAttribute("showDetailMySelf", showDetailMySelf);

			Teacher teacher = userInfoService.loadTeacherByUsername(name);
			model.addAttribute("teacherInfo", teacher);

			rateAverage = teacher.getRate();

			if (teacher.isGender() == true) {
				gender = "Nam";
			} else {
				gender = "Nữ";
			}
			model.addAttribute("gender", gender);
			List<Rating> ratings = ratingService.getAllRatingTeacherByRecieverName(name);

			CountRate count = ratingService.countRatingTeacher(name);
			RateTeacher caculater = ratingService.caculaterRateTeacher(name);

			model.addAttribute("rateAverage", rateAverage);
			model.addAttribute("courses", courses);
			model.addAttribute("ratings", ratings);
			model.addAttribute("count", count);
			model.addAttribute("caculater", caculater);
			
			Rating rating = new Rating();
			rating.setRateTeacher(new RateTeacher());
			rating.setRecieverName(name);
			model.addAttribute("rateTeacher", rating);
			
			model.addAttribute("checkRateExist", ratingService.check(curentUserName, name));

			modelAndView.setViewName("profile-teacher");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/DetailStudyCenter", method = RequestMethod.GET)
	public ModelAndView dettailStudyCenter(@RequestParam("userName") String name, Model model, Principal principal)
			throws NotFoundException {

		// Sau khi user login thanh cong se co principal
		// String userName = principal.getName();
		String curentUserName = null;
		if (principal != null) {
			curentUserName = principal.getName();
		}
		System.out.println("User Name: " + name);
		boolean checkDetail = true;
		boolean showDetailMySelf = false;
		String curentRoleName;
		String roleName = userInfoService.getRoleName(name);
		if (curentUserName != null) {
			curentRoleName = userInfoService.getRoleName(curentUserName);
		} else {
			curentRoleName = "";
		}
		UserModel useInfo = userInfoService.loadUserByUsername(name);
		List<Course> courses = courseService.getCourseByUserNameWithFullInfo(name);
		ModelAndView modelAndView = new ModelAndView();

		float rateAverage = 0;

		if (useInfo == null) {
			System.out.println("User not found! " + name);
			modelAndView.setViewName("index");
			throw new NotFoundException("User " + name + " was not found in the database");

		} else {
			model.addAttribute("useInfo", useInfo);
			model.addAttribute("roleName", roleName);
			model.addAttribute("checkDetail", checkDetail);
			model.addAttribute("curentRoleName", curentRoleName);

			if (curentUserName != null && name.equals(curentUserName)) {
				showDetailMySelf = true;
			} else {
				showDetailMySelf = false;
			}

			model.addAttribute("showDetailMySelf", showDetailMySelf);

			StudyCenter studyCenter = userInfoService.loadStudyCenterByUsername(name);
			model.addAttribute("studyCenterInfo", studyCenter);

			List<Rating> ratings = ratingService.getAllRatingStudyCenterByRecieverName(name);

			rateAverage = studyCenter.getRate();
			
			CountRate count = ratingService.countRatingStudyCenter(name);
			
			RateStudyCenter caculater = ratingService.caculaterRateStudyCenter(name);
			
			model.addAttribute("rateAverage", rateAverage);
			model.addAttribute("courses", courses);
			model.addAttribute("ratings", ratings);
			model.addAttribute("count", count);
			model.addAttribute("caculater", caculater);
			
			Rating rating = new Rating();
			rating.setRecieverName(name);
			rating.setRateStudyCenter(new RateStudyCenter());
			model.addAttribute("rateStudyCenter", rating);
			model.addAttribute("checkRateExist", ratingService.check(curentUserName, name));
			
			
			modelAndView.setViewName("profile-study-center");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/rateTeacher", method = RequestMethod.POST)
	public ModelAndView rateTeacher(@ModelAttribute("rateTeacher") Rating rating, Model model, Principal principal, RedirectAttributes redirectAttributes, BindingResult bindingResult)
			throws NotFoundException {

		// Sau khi user login thanh cong se co principal
		// String userName = principal.getName();
		String curentUserName = null;
		if (principal != null) {
			curentUserName = principal.getName();
		}

		ModelAndView modelAndView = new ModelAndView();
		
		
		try {
			rating.setUserName(curentUserName);
			rating.setCreateDate(dateformat.currentDate());
			rating.setRateId(ratingService.genRatingId());
			
			ratingService.addRating(rating);
			
			rating.getRateTeacher().setCheckTeaNull("not null");
			rating.getRateTeacher().setRateId(rating.getRateId());
			
			ratingService.addRateTeacher(rating.getRateTeacher());

			float rateAvg = ratingService.updateTotalRateStudyCenter(ratingService.getAllRatingTeacherByRecieverName(rating.getRecieverName()));

			ratingService.updateRateTeacher(rateAvg, rating.getRecieverName());
		}catch (Exception e) {
			e.fillInStackTrace();
		}
		
		redirectAttributes.addAttribute("userName", rating.getRecieverName());
		modelAndView.setViewName("redirect:/DetailTeacher");
		return modelAndView;
	}
	
	@RequestMapping(value = "/rateStudyCenter", method = RequestMethod.POST)
	public ModelAndView rateStudyCenter(@Valid @ModelAttribute("rateStudyCenter") Rating rating, Model model, Principal principal, RedirectAttributes redirectAttributes, BindingResult bindingResult)
			throws NotFoundException {

		String curentUserName = null;
		if (principal != null) {
			curentUserName = principal.getName();
		}
		
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			rating.setUserName(curentUserName);
			rating.setCreateDate(dateformat.currentDate());
			rating.setRateId(ratingService.genRatingId());
			
			ratingService.addRating(rating);

			rating.getRateStudyCenter().setCheckSCNull("not null");
			rating.getRateStudyCenter().setRateId(rating.getRateId());
			
			ratingService.addRateStudyCenter(rating.getRateStudyCenter());
			
			float rateAvg = ratingService.updateTotalRateStudyCenter(ratingService.getAllRatingStudyCenterByRecieverName(rating.getRecieverName()));
			ratingService.updateRateStudyCenter(rateAvg, rating.getRecieverName());
		}catch (Exception e) {
			e.fillInStackTrace();
		}
		
		redirectAttributes.addAttribute("userName", rating.getRecieverName());
		modelAndView.setViewName("redirect:/DetailStudyCenter");
		return modelAndView;
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.GET)
	public ModelAndView changePassword() {
		ModelAndView modelAndView = new ModelAndView();
		PasswordForm passwordForm = new PasswordForm();
		modelAndView.addObject("passwordForm", passwordForm);
		modelAndView.setViewName("change-password");
		return modelAndView;
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	public ModelAndView updatePassword(@Valid @ModelAttribute("passwordForm") PasswordForm passwordForm,
			BindingResult bindingResult, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();

		String curentUserName = null;
		if (principal != null) {
			curentUserName = principal.getName();
		}

		if (!userInfoService.checkPassword(curentUserName, passwordForm.getOldPassword())) {
			bindingResult.rejectValue("oldPassword", "error.passwordForm", "Nhập Sai Mật Khẩu Hiện tại !");
		}

		if (!passwordForm.getNewPassword().equals(passwordForm.getReNewPassword())) {
			bindingResult.rejectValue("reNewPassword", "error.passwordForm", "Nhập Không giống với mật khẩu mới !");
		}

		modelAndView.addObject("passwordForm", passwordForm);

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("change-password");
			return modelAndView;
		}

		try {

			userInfoService.changePassword(curentUserName, passwordForm.getNewPassword());

		} catch (Exception e) {
			e.fillInStackTrace();
			modelAndView.setViewName("change-password");
			return modelAndView;
		}

		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}

	@RequestMapping(value = "/update-info", method = RequestMethod.GET)
	public ModelAndView changeInfo(Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		
		String curentUserName = null;
		if (principal != null) {
			curentUserName = principal.getName();
		}
		
		UserModel user;
		try {
			user = userInfoService.loadUserByUsername(curentUserName);
			
			if(user.getRole_id() == 1) {
				user.setStudent(userInfoService.loadStudentByUsername(curentUserName));
			}else if(user.getRole_id() == 2) {
				user.setTeacher(userInfoService.loadTeacherByUsername(curentUserName));
			}else if(user.getRole_id() == 3) {
				user.setStudyCenter(userInfoService.loadStudyCenterByUsername(curentUserName));
			}
			
			modelAndView.addObject("user", user);
			modelAndView.setViewName("update-info");
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/update-info", method = RequestMethod.POST)
	public ModelAndView updateInfo(@Valid @ModelAttribute("user") UserModel user,
			BindingResult bindingResult, Principal principal)  throws NotFoundException {
		ModelAndView modelAndView = new ModelAndView();

		String curentUserName = null;
		if (principal != null) {
			curentUserName = principal.getName();
		}
		
		UserModel checkEmail = userInfoService.loadUserbyEmail(user.getEmail());
		UserModel currentUser = userInfoService.loadUserByUsername(curentUserName);
		
		if (checkEmail != null) {
			if(!user.getEmail().equals(currentUser.getEmail())) {
				bindingResult.rejectValue("email", "error.email", "Eamil này đã tồn tại, vui lòng nhập một địa chỉ Eamil khác !");
			}
		}
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("update-info");
			return modelAndView;
		}
		

		try {
			userInfoService.updateUserInfo(user);

			if(currentUser.getRole_id() == 1) {
				user.getStudent().setUserName(user.getUserName());
				userInfoService.updateStudentInfo(user.getStudent());
			}else if(currentUser.getRole_id() == 2) {
				user.getTeacher().setUserName(user.getUserName());
				userInfoService.updateTeacherInfo(user.getTeacher());
			}else if(currentUser.getRole_id() == 3) {
				user.getStudyCenter().setUserName(user.getUserName());
				userInfoService.updateStudyCenterInfo(user.getStudyCenter());
			}

		} catch (Exception e) {
			e.fillInStackTrace();
			modelAndView.setViewName("update-info");
			return modelAndView;
		}

		modelAndView.setViewName("redirect:/update-info");
		return modelAndView;
	}
}
