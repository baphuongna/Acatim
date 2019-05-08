package com.acatim.acatimver1.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acatim.acatimver1.entity.History;
import com.acatim.acatimver1.entity.Student;
import com.acatim.acatimver1.entity.UserModel;
import com.acatim.acatimver1.form.StudentForm;
import com.acatim.acatimver1.format.DateFormat;
import com.acatim.acatimver1.service.HistoryService;
import com.acatim.acatimver1.service.UserInfoService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = {"/admin"})
public class ManagerEmployeeController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private HistoryService historyService;
	
	private DateFormat dateformat = new DateFormat();
	
	@RequestMapping(value = {"all-managers"}, method = RequestMethod.GET)
	public ModelAndView allmanagers() {
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("allManager", userInfoService.getAllManager());
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("admin/all-managers");
		return modelAndView;
	}
	
	@RequestMapping(value = { "blockManager" }, method = RequestMethod.GET)
	public ModelAndView blockManager(@RequestParam("userName") String userName, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		try {

			userInfoService.removeUser(userName);
			
			User loginedUser = null;
			if (principal != null) {
				loginedUser = (User) ((Authentication) principal).getPrincipal();
			}
			
			History history = new History();
			history.setIdChange(userName);
			history.setValueChanged("Blocked");
			history.setDateChange(dateformat.currentDate());
			history.setBy(loginedUser.getUsername());
			historyService.addHistory(history);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/all-managers");
		return modelAndView;
	}

	@RequestMapping(value = { "unlockManager" }, method = RequestMethod.GET)
	public ModelAndView unlockManager(@RequestParam("userName") String userName, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			
			userInfoService.unlockUser(userName);
			
			User loginedUser = null;
			if (principal != null) {
				loginedUser = (User) ((Authentication) principal).getPrincipal();
			}

			History history = new History();
			history.setIdChange(userName);
			history.setValueChanged("Active");
			history.setDateChange(dateformat.currentDate());
			history.setBy(loginedUser.getUsername());
			historyService.addHistory(history);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/all-managers");
		return modelAndView;
	}
	
	
	@RequestMapping(value = {"add-manager"}, method = RequestMethod.GET)
	public ModelAndView addmanager() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("studentForm", new StudentForm());
		modelAndView.setViewName("admin/add-manager");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/add-manager" }, method = RequestMethod.POST)
	public ModelAndView addNewManager(@Valid @ModelAttribute("studentForm") StudentForm studentForm,
			BindingResult result, final RedirectAttributes redirectAttributes) throws NotFoundException {

		ModelAndView modelAndView = new ModelAndView();

		boolean userExists = userInfoService.checkUserExist(studentForm.getUserName());
		if (userExists == true) {
			result.rejectValue("userName", "error.user",
					"Tên Tài khoản này đã tồn tại, vui lòng nhập một Tên Tài khoản khác !");
		}

		UserModel checkEmail = userInfoService.loadUserbyEmail(studentForm.getEmail());

		if (checkEmail != null) {
			result.rejectValue("email", "error.email", "Eamil này đã tồn tại, vui lòng nhập một địa chỉ Eamil khác !");
		}

		if (result.hasErrors()) {
			modelAndView.setViewName("admin/add-manager");
			return modelAndView;
		}

		UserModel user = new UserModel(studentForm.getUserName(), 4, studentForm.getFullName(),
				studentForm.getEmail(), studentForm.getPassword(), dateformat.currentDate() , studentForm.getPhoneNo(),
				studentForm.getAddress(), true);

		Student studentInfo = new Student(studentForm.getUserName(), studentForm.getDob(), studentForm.isGender());
//			System.out.println(user);
//			System.out.println(studentInfo);
		try {
			userInfoService.addUserInfo(user);
			userInfoService.addStudentInfo(studentInfo);
			System.out.println("Success");
		} catch (Exception e) {
			modelAndView.addObject("studentForm", new StudentForm());
			modelAndView.addObject("errorMessage", "Error: " + e.getMessage());
			modelAndView.setViewName("admin/add-manager");
			return modelAndView;
		}

//		redirectAttributes.addFlashAttribute("flashUser", user);

		modelAndView.setViewName("redirect:/admin/all-managers");
		return modelAndView;
	}
	
	@RequestMapping(value = {"edit-manager"}, method = RequestMethod.GET)
	public ModelAndView editManager(@RequestParam("userName") String userName) {
		ModelAndView modelAndView = new ModelAndView();
		
		try {

			StudentForm student = userInfoService.getUserStudentByUserName(userName);
			System.out.println(userName + student);
			modelAndView.addObject("studentForm", student);

		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		modelAndView.setViewName("admin/edit-manager");
		return modelAndView;
	}
	
	
	@RequestMapping(value = { "edit-manager" }, method = RequestMethod.POST)
	public ModelAndView updateManager(@ModelAttribute("studentForm") @Validated StudentForm studentForm, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(studentForm);

		try {

			StudentForm currentStudent = userInfoService.getUserStudentByUserName(studentForm.getUserName());

			UserModel user = new UserModel(studentForm.getUserName(), studentForm.getRole_id(),
					studentForm.getFullName(), studentForm.getEmail(), studentForm.getPassword(),
					currentStudent.getCreateDate(), studentForm.getPhoneNo(), studentForm.getAddress(), true);

			Student studentInfo = new Student(studentForm.getUserName(), studentForm.getDob(), studentForm.isGender());

			userInfoService.updateUserInfo(user);
			userInfoService.updateStudentInfo(studentInfo);


			User loginedUser = null;
			if (principal != null) {
				loginedUser = (User) ((Authentication) principal).getPrincipal();
			}
			
			History history = new History();
			history.setIdChange(studentForm.getUserName());
			history.setValueChanged("Infomation");
			history.setDateChange(dateformat.currentDate());
			history.setBy(loginedUser.getUsername());
			historyService.addHistory(history);

		} catch (NotFoundException e) {
			e.printStackTrace();
			modelAndView.addObject("studentForm", studentForm);
			modelAndView.setViewName("admin/edit-manager");
			return modelAndView;
		}

		modelAndView.setViewName("redirect:/admin/all-managers");
		return modelAndView;
	}
	
	@RequestMapping(value = {"/manager-profile"}, method = RequestMethod.GET)
	public ModelAndView managerProfile() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin//manager-profile");
		return modelAndView;
	}
}
