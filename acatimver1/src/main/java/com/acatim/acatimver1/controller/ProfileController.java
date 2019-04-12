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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acatim.acatimver1.entity.Course;
import com.acatim.acatimver1.entity.Rating;
import com.acatim.acatimver1.entity.Student;
import com.acatim.acatimver1.entity.StudyCenter;
import com.acatim.acatimver1.entity.Teacher;
import com.acatim.acatimver1.entity.UserModel;
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

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView userInfo(Model model, Principal principal) throws NotFoundException {
		// Sau khi user login thanh cong se co principal
		String userName = principal.getName();
		System.out.println("User Name: " + userName);

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
		System.out.println("User Name: " + userName);

		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String roleName = WebUtils.toString(loginedUser);
		UserModel useInfo = userInfoService.loadUserByUsername(userName);

		ModelAndView modelAndView = new ModelAndView();
		System.out.println(roleName);
		String gender = null;
		float rateAverage = 0;
		float progress5 = 0;
		float progress4 = 0;
		float progress3 = 0;
		float progress2 = 0;
		float progress1 = 0;

		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;
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
			float numberPeopleRate = 0;

			for (int i = 0; i < ratings.size(); i++) {
				if (ratings.get(i).getRate() <= 1) {
					count1++;
				} else if (ratings.get(i).getRate() > 1 && ratings.get(i).getRate() <= 2) {
					count2++;
				} else if (ratings.get(i).getRate() > 2 && ratings.get(i).getRate() <= 3) {
					count3++;
				} else if (ratings.get(i).getRate() > 3 && ratings.get(i).getRate() <= 4) {
					count4++;
				} else if (ratings.get(i).getRate() > 4 && ratings.get(i).getRate() <= 5) {
					count5++;
				}
			}

			for (int i = 0; i < ratings.size(); i++) {
				numberPeopleRate++;
			}

			progress5 = (count5 / numberPeopleRate) * 100;
			progress4 = (count4 / numberPeopleRate) * 100;
			progress3 = (count3 / numberPeopleRate) * 100;
			progress2 = (count2 / numberPeopleRate) * 100;
			progress1 = (count1 / numberPeopleRate) * 100;

			model.addAttribute("rateAverage", rateAverage);
			model.addAttribute("courses", courses);
			model.addAttribute("ratings", ratings);
			model.addAttribute("progress5", progress5);
			model.addAttribute("progress4", progress4);
			model.addAttribute("progress3", progress3);
			model.addAttribute("progress2", progress2);
			model.addAttribute("progress1", progress1);
			model.addAttribute("numberPeopleRate", numberPeopleRate);

			modelAndView.setViewName("profile-teacher");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/profile-study-center", method = RequestMethod.GET)
	public ModelAndView profileStudyCenter(Model model, Principal principal) throws NotFoundException {
		// Sau khi user login thanh cong se co principal
		boolean checkDetail = false;
		String userName = principal.getName();
		System.out.println("User Name: " + userName);

		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String roleName = WebUtils.toString(loginedUser);
		UserModel useInfo = userInfoService.loadUserByUsername(userName);

		ModelAndView modelAndView = new ModelAndView();
		System.out.println(roleName);
		float rateAverage = 0;
		float progress5 = 0;
		float progress4 = 0;
		float progress3 = 0;
		float progress2 = 0;
		float progress1 = 0;

		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;
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

			for (int i = 0; i < ratings.size(); i++) {
				if (ratings.get(i).getRate() <= 1) {
					count1++;
				} else if (ratings.get(i).getRate() > 1 && ratings.get(i).getRate() <= 2) {
					count2++;
				} else if (ratings.get(i).getRate() > 2 && ratings.get(i).getRate() <= 3) {
					count3++;
				} else if (ratings.get(i).getRate() > 3 && ratings.get(i).getRate() <= 4) {
					count4++;
				} else if (ratings.get(i).getRate() > 4 && ratings.get(i).getRate() <= 5) {
					count5++;
				}
			}

			System.out.println(courses);
			System.out.println(ratings);
			float numberPeopleRate = 0;

			for (int i = 0; i < ratings.size(); i++) {
				numberPeopleRate++;
			}

			progress5 = (count5 / numberPeopleRate) * 100;
			progress4 = (count4 / numberPeopleRate) * 100;
			progress3 = (count3 / numberPeopleRate) * 100;
			progress2 = (count2 / numberPeopleRate) * 100;
			progress1 = (count1 / numberPeopleRate) * 100;

			System.out.println(
					" PROGRESS: " + progress5 + " " + progress4 + " " + progress3 + " " + progress2 + " " + progress1);

			model.addAttribute("rateAverage", rateAverage);
			model.addAttribute("courses", courses);
			model.addAttribute("ratings", ratings);
			model.addAttribute("progress5", progress5);
			model.addAttribute("progress4", progress4);
			model.addAttribute("progress3", progress3);
			model.addAttribute("progress2", progress2);
			model.addAttribute("progress1", progress1);
			model.addAttribute("numberPeopleRate", numberPeopleRate);

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

			System.out.println("checkDetail " + checkDetail);
			System.out.println("roleName " + roleName);

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
		float progress5 = 80;
		float progress4 = 60;
		float progress3 = 40;
		float progress2 = 20;
		float progress1 = 15;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;
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

			System.out.println("checkDetail " + checkDetail);
			System.out.println("roleName " + roleName);

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
			float numberPeopleRate = 0;

			for (int i = 0; i < ratings.size(); i++) {
				if (ratings.get(i).getRate() <= 1) {
					count1++;
				} else if (ratings.get(i).getRate() > 1 && ratings.get(i).getRate() <= 2) {
					count2++;
				} else if (ratings.get(i).getRate() > 2 && ratings.get(i).getRate() <= 3) {
					count3++;
				} else if (ratings.get(i).getRate() > 3 && ratings.get(i).getRate() <= 4) {
					count4++;
				} else if (ratings.get(i).getRate() > 4 && ratings.get(i).getRate() <= 5) {
					count5++;
				}
			}

			for (int i = 0; i < ratings.size(); i++) {
				numberPeopleRate++;
			}

			progress5 = (count5 / numberPeopleRate) * 100;
			progress4 = (count4 / numberPeopleRate) * 100;
			progress3 = (count3 / numberPeopleRate) * 100;
			progress2 = (count2 / numberPeopleRate) * 100;
			progress1 = (count1 / numberPeopleRate) * 100;

			model.addAttribute("rateAverage", rateAverage);
			model.addAttribute("courses", courses);
			model.addAttribute("ratings", ratings);
			model.addAttribute("progress5", progress5);
			model.addAttribute("progress4", progress4);
			model.addAttribute("progress3", progress3);
			model.addAttribute("progress2", progress2);
			model.addAttribute("progress1", progress1);
			model.addAttribute("numberPeopleRate", numberPeopleRate);

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
		float progress5 = 80;
		float progress4 = 60;
		float progress3 = 40;
		float progress2 = 20;
		float progress1 = 15;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;
		if (useInfo == null) {
			System.out.println("User not found! " + name);
			modelAndView.setViewName("index");
			throw new NotFoundException("User " + name + " was not found in the database");

		} else {
			model.addAttribute("useInfo", useInfo);
			model.addAttribute("roleName", roleName);
			model.addAttribute("checkDetail", checkDetail);
			model.addAttribute("curentRoleName", curentRoleName);

			System.out.println("checkDetail " + checkDetail);
			System.out.println("roleName " + roleName);

			if (curentUserName != null && name.equals(curentUserName)) {
				showDetailMySelf = true;
			} else {
				showDetailMySelf = false;
			}

			model.addAttribute("showDetailMySelf", showDetailMySelf);

			StudyCenter studyCenter = userInfoService.loadStudyCenterByUsername(name);
			model.addAttribute("studyCenterInfo", studyCenter);

			List<Rating> ratings = ratingService.getAllRatingStudyCenterByRecieverName(name);
			System.out.println(ratings);
			float numberPeopleRate = 0;

			for (int i = 0; i < ratings.size(); i++) {
				if (ratings.get(i).getRate() <= 1) {
					count1++;
				} else if (ratings.get(i).getRate() > 1 && ratings.get(i).getRate() <= 2) {
					count2++;
				} else if (ratings.get(i).getRate() > 2 && ratings.get(i).getRate() <= 3) {
					count3++;
				} else if (ratings.get(i).getRate() > 3 && ratings.get(i).getRate() <= 4) {
					count4++;
				} else if (ratings.get(i).getRate() > 4 && ratings.get(i).getRate() <= 5) {
					count5++;
				}
			}

			rateAverage = studyCenter.getRate();

			for (int i = 0; i < ratings.size(); i++) {
				numberPeopleRate++;
			}

			progress5 = (count5 / numberPeopleRate) * 100;
			progress4 = (count4 / numberPeopleRate) * 100;
			progress3 = (count3 / numberPeopleRate) * 100;
			progress2 = (count2 / numberPeopleRate) * 100;
			progress1 = (count1 / numberPeopleRate) * 100;

			model.addAttribute("rateAverage", rateAverage);
			model.addAttribute("courses", courses);
			model.addAttribute("ratings", ratings);
			model.addAttribute("progress5", progress5);
			model.addAttribute("progress4", progress4);
			model.addAttribute("progress3", progress3);
			model.addAttribute("progress2", progress2);
			model.addAttribute("progress1", progress1);
			model.addAttribute("numberPeopleRate", numberPeopleRate);

			modelAndView.setViewName("profile-study-center");
		}
		return modelAndView;
	}

}
