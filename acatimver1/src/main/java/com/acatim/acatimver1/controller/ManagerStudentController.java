package com.acatim.acatimver1.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acatim.acatimver1.form.StudentForm;
import com.acatim.acatimver1.model.Student;
import com.acatim.acatimver1.model.UserModel;
import com.acatim.acatimver1.service.UserInfoServiceImpl;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = { "/admin" })
public class ManagerStudentController {

	@Autowired
	private UserInfoServiceImpl userInfoService;

	@RequestMapping(value = { "/all-students" }, method = RequestMethod.GET)
	public ModelAndView allStudents() {
		ModelAndView modelAndView = new ModelAndView();

		try {
			StudentForm studentForm = new StudentForm();

			modelAndView.addObject("studentForm", studentForm);
			modelAndView.addObject("allStudent", userInfoService.loadAllUserStudent());
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("admin/all-students");
		return modelAndView;
	}

	@RequestMapping(value = { "studentUpdate" }, method = RequestMethod.POST)
	public ModelAndView updateStudent(@ModelAttribute("studentForm") @Validated StudentForm studentForm) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(studentForm);
		
		
		
		modelAndView.setViewName("redirect:/admin/all-students");
		return modelAndView;
	}

	@RequestMapping(value = { "blockStudent" }, method = RequestMethod.GET)
	public ModelAndView blockStudent(@RequestParam("userName") String userName) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			userInfoService.removeUser(userName);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/all-students");
		return modelAndView;
	}

	@RequestMapping(value = { "unlockStudent" }, method = RequestMethod.GET)
	public ModelAndView unlockStudent(@RequestParam("userName") String userName) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			userInfoService.unlockUser(userName);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/all-students");
		return modelAndView;
	}

	@RequestMapping(value = { "add-student" }, method = RequestMethod.GET)
	public ModelAndView addStudent() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("studentForm", new StudentForm());
		modelAndView.setViewName("admin/add-student");
		return modelAndView;
	}

	@RequestMapping(value = { "/addNewStudent" }, method = RequestMethod.POST)
	public ModelAndView addNewStudent(@Valid @ModelAttribute("studentForm") StudentForm studentForm,
			BindingResult result, final RedirectAttributes redirectAttributes) throws NotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(studentForm);
		boolean userExists = userInfoService.checkUserExist(studentForm.getUserName());
		if (userExists == true) {
			result.rejectValue("userName", "error.user",
					"Tên Tài khoản này đã tồn tại, vui lòng nhập một Tên Tài khoản khác !");
		}
		
		UserModel checkEmail = userInfoService.loadUserbyEmail(studentForm.getEmail());
		
		if (checkEmail != null) {
			result.rejectValue("email", "error.email",
					"Eamil này đã tồn tại, vui lòng nhập một địa chỉ Eamil khác !");
		}
		
		
		if (result.hasErrors()) {
			modelAndView.setViewName("admin/add-student");
			return modelAndView;
		}

		Date date = new Date();
		long time = date.getTime();
		Timestamp currentDate = new Timestamp(time);

		UserModel user = new UserModel(studentForm.getUserName(), studentForm.getRole_id(), studentForm.getFullName(),
				studentForm.getEmail(), studentForm.getPassword(), currentDate + "", studentForm.getPhoneNo(),
				studentForm.getAddress(), true);
		
		Student studentInfo = new Student(studentForm.getUserName(), studentForm.getDob(), studentForm.isGender());
		System.out.println(user);
		System.out.println(studentInfo);
		try {
			userInfoService.addUserInfo(user);
			userInfoService.addStudentInfo(studentInfo);
			System.out.println("Success");
		} catch (Exception e) {
			modelAndView.addObject("studentForm", new StudentForm());
			modelAndView.addObject("errorMessage", "Error: " + e.getMessage());
			modelAndView.setViewName("admin/add-student");
			return modelAndView;
		}
		redirectAttributes.addFlashAttribute("flashUser", user);

		modelAndView.setViewName("redirect:/admin/all-students");
		return modelAndView;
	}

	@RequestMapping(value = { "edit-student" }, method = RequestMethod.GET)
	public ModelAndView editStudent(@RequestParam("userName") String userName) {
		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("studentForm", new StudentForm());
		try {
			StudentForm student = userInfoService.getUserStudentByUserName(userName);
			
			modelAndView.addObject("studentForm", student);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelAndView.setViewName("admin/edit-student");
		return modelAndView;
	}

	@RequestMapping(value = { "student-profile" }, method = RequestMethod.GET)
	public ModelAndView studentProfile() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/student-profile");
		return modelAndView;
	}
}
