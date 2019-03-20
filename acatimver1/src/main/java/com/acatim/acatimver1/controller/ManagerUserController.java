package com.acatim.acatimver1.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.acatim.acatimver1.form.StudyCenterForm;
import com.acatim.acatimver1.form.TeacherForm;
import com.acatim.acatimver1.model.SearchValue;
import com.acatim.acatimver1.model.Student;
import com.acatim.acatimver1.model.StudyCenter;
import com.acatim.acatimver1.model.Teacher;
import com.acatim.acatimver1.model.UserModel;
import com.acatim.acatimver1.service.PageableService;
import com.acatim.acatimver1.service.UserInfoServiceImpl;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = { "/admin" })
public class ManagerUserController {
	@Autowired
	private UserInfoServiceImpl userInfoService;

	private PageableService pageableService;

	@RequestMapping(value = { "/allUser" }, method = RequestMethod.GET)
	public ModelAndView allUsers(@RequestParam(required = false, name = "page") String page) {
		ModelAndView modelAndView = new ModelAndView();
		if (page == null) {
			page = 1 + "";
		}

		try {
			int currentPage = Integer.parseInt(page);

			if (currentPage < 1) {
				currentPage = 1;
			}

			@SuppressWarnings("deprecation")
			Pageable pageable = new PageRequest(currentPage - 1, 8);

			int total = userInfoService.getAllUsers("").size();
			
			pageableService = new PageableService(8, currentPage - 1, total, currentPage);

			modelAndView.addObject("totalPages", pageableService.listPage());
			modelAndView.addObject("currentPage", currentPage);
			modelAndView.addObject("hasPrevious", pageableService.hasPrevious());
			modelAndView.addObject("hasNext", pageableService.hasNext());
			modelAndView.addObject("previous", pageableService.previous());
			modelAndView.addObject("next", pageableService.next());
			modelAndView.addObject("last", pageableService.last());
			modelAndView.addObject("first", pageableService.first());

			modelAndView.addObject("allUser", userInfoService.getAllUsersPageable(pageable, ""));
			SearchValue searchValue = new SearchValue();
			modelAndView.addObject("searchValue", searchValue);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("admin/allUser");
		return modelAndView;
	}

	@RequestMapping(value = { "/allUser" }, method = RequestMethod.POST)
	public ModelAndView searchUsers(@RequestParam(required = false, name = "page") String page,
			@ModelAttribute("searchValue") SearchValue search) {
		ModelAndView modelAndView = new ModelAndView();
		if (page == null) {
			page = 1 + "";
		}
		System.out.println(search);
		try {

			int currentPage = Integer.parseInt(page);

			if (currentPage < 1) {
				currentPage = 1;
			}

			@SuppressWarnings("deprecation")
			Pageable pageable = new PageRequest(currentPage - 1, 8);

			int total = 0;

			if (search.getSearch().trim().length() == 0) {
				total = userInfoService.getAllUsers(search.getRoleId()).size();
				System.out.println(total + " " + userInfoService.getAllUsersPageable(pageable, search.getRoleId()).size());
				modelAndView.addObject("allUser", userInfoService.getAllUsersPageable(pageable, search.getRoleId()));
			} else {
				List<UserModel> listUser = null;
				if (search.getValue().equals("userName")) {
					listUser = userInfoService.searchAllUsersByUserName(pageable, search.getSearch(), search.getRoleId());
					total = listUser.size();
					modelAndView.addObject("allUser",listUser);
				} else if (search.getValue().equals("fullName")) {
					listUser = userInfoService.searchAllUsersByFullName(pageable, search.getSearch(), search.getRoleId());
					total = listUser.size();
					modelAndView.addObject("allUser",listUser);
				} else if (search.getValue().equals("email")) {
					listUser = userInfoService.searchAllUsersByEmail(pageable, search.getSearch(), search.getRoleId());
					total = listUser.size();
					modelAndView.addObject("allUser",listUser);
				}
			}

			pageableService = new PageableService(8, currentPage - 1, total, currentPage);

			modelAndView.addObject("totalPages", pageableService.listPage());
			modelAndView.addObject("currentPage", currentPage);
			modelAndView.addObject("hasPrevious", pageableService.hasPrevious());
			modelAndView.addObject("hasNext", pageableService.hasNext());
			modelAndView.addObject("previous", pageableService.previous());
			modelAndView.addObject("next", pageableService.next());
			modelAndView.addObject("last", pageableService.last());
			modelAndView.addObject("first", pageableService.first());

			modelAndView.addObject("searchValue", search);

		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("admin/allUser");
		return modelAndView;
	}
	
	@RequestMapping(value = { "blockUser" }, method = RequestMethod.GET)
	public ModelAndView blockStudent(@RequestParam("userName") String userName) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			userInfoService.removeUser(userName);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/allUser");
		return modelAndView;
	}

	@RequestMapping(value = { "unlockUser" }, method = RequestMethod.GET)
	public ModelAndView unlockStudent(@RequestParam("userName") String userName) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			userInfoService.unlockUser(userName);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/allUser");
		return modelAndView;
	}

	@RequestMapping(value = { "addStudent" }, method = RequestMethod.GET)
	public ModelAndView addStudent() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("studentForm", new StudentForm());
		modelAndView.setViewName("admin/addStudent");
		return modelAndView;
	}

	@RequestMapping(value = { "addTeacher" }, method = RequestMethod.GET)
	public ModelAndView addTeacher() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("teacherForm", new TeacherForm());
		modelAndView.setViewName("admin/addTeacher");
		return modelAndView;
	}

	@RequestMapping(value = { "addStudyCenter" }, method = RequestMethod.GET)
	public ModelAndView addStudyCenter() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("studyCenterForm", new StudyCenterForm());
		modelAndView.setViewName("admin/addStudyCenter");
		return modelAndView;
	}

	@RequestMapping(value = { "/addStudent" }, method = RequestMethod.POST)
	public ModelAndView addNewStudent(@Valid @ModelAttribute("studentForm") StudentForm studentForm,
			BindingResult result,
			final RedirectAttributes redirectAttributes) throws NotFoundException {

		ModelAndView modelAndView = new ModelAndView();
		System.out.println(studentForm);

		Date date = new Date();
		long time = date.getTime();
		Timestamp currentDate = new Timestamp(time);

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
			modelAndView.setViewName("admin/addStudent");
			return modelAndView;
		}

		UserModel user = new UserModel(studentForm.getUserName(), studentForm.getRole_id(), studentForm.getFullName(),
				studentForm.getEmail(), studentForm.getPassword(), currentDate + "", studentForm.getPhoneNo(),
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
			modelAndView.setViewName("admin/addStudent");
			return modelAndView;
		}

//		redirectAttributes.addFlashAttribute("flashUser", user);

		modelAndView.setViewName("redirect:/admin/allUser");
		return modelAndView;
	}

	@RequestMapping(value = { "/addTeacher" }, method = RequestMethod.POST)
	public ModelAndView addNewTeacher(@Valid @ModelAttribute("teacherForm") TeacherForm teacherForm,
			BindingResult result, final RedirectAttributes redirectAttributes) throws NotFoundException {

		ModelAndView modelAndView = new ModelAndView();
		System.out.println(teacherForm);

		Date date = new Date();
		long time = date.getTime();
		Timestamp currentDate = new Timestamp(time);

		boolean userExists = userInfoService.checkUserExist(teacherForm.getUserName());
		if (userExists == true) {
			result.rejectValue("userName", "error.user",
					"Tên Tài khoản này đã tồn tại, vui lòng nhập một Tên Tài khoản khác !");
		}

		UserModel checkEmail = userInfoService.loadUserbyEmail(teacherForm.getEmail());

		if (checkEmail != null) {
			result.rejectValue("email", "error.email", "Eamil này đã tồn tại, vui lòng nhập một địa chỉ Eamil khác !");
		}

		if (result.hasErrors()) {
			modelAndView.setViewName("admin/addTeacher");
			return modelAndView;
		}

		UserModel user = new UserModel(teacherForm.getUserName(), teacherForm.getRole_id(), teacherForm.getFullName(),
				teacherForm.getEmail(), teacherForm.getPassword(), currentDate + "", teacherForm.getPhoneNo(),
				teacherForm.getAddress(), true);

		Teacher teacherInfo = new Teacher(teacherForm.getUserName(), teacherForm.getDob(), teacherForm.isGender(),
				teacherForm.getDescription(), 0);
//			System.out.println(user);
//			System.out.println(studentInfo);
		try {
			userInfoService.addUserInfo(user);
			userInfoService.addTeacherInfo(teacherInfo);
			System.out.println("Success");
		} catch (Exception e) {
			modelAndView.addObject("teacherForm", new TeacherForm());
			modelAndView.addObject("errorMessage", "Error: " + e.getMessage());
			modelAndView.setViewName("admin/addTeacher");
			return modelAndView;
		}

//		redirectAttributes.addFlashAttribute("flashUser", user);

		modelAndView.setViewName("redirect:/admin/allUser");
		return modelAndView;
	}

	@RequestMapping(value = { "/addStudyCenter" }, method = RequestMethod.POST)
	public ModelAndView addNewStudyCenter(@Valid @ModelAttribute("studyCenterForm") StudyCenterForm studyCenterForm,
			BindingResult result, final RedirectAttributes redirectAttributes) throws NotFoundException {

		ModelAndView modelAndView = new ModelAndView();
		System.out.println(studyCenterForm);

		Date date = new Date();
		long time = date.getTime();
		Timestamp currentDate = new Timestamp(time);

		boolean userExists = userInfoService.checkUserExist(studyCenterForm.getUserName());
		if (userExists == true) {
			result.rejectValue("userName", "error.user",
					"Tên Tài khoản này đã tồn tại, vui lòng nhập một Tên Tài khoản khác !");
		}

		UserModel checkEmail = userInfoService.loadUserbyEmail(studyCenterForm.getEmail());

		if (checkEmail != null) {
			result.rejectValue("email", "error.email", "Eamil này đã tồn tại, vui lòng nhập một địa chỉ Eamil khác !");
		}

		if (result.hasErrors()) {
			modelAndView.setViewName("admin/addStudyCenter");
			return modelAndView;
		}

		UserModel user = new UserModel(studyCenterForm.getUserName(), studyCenterForm.getRole_id(),
				studyCenterForm.getFullName(), studyCenterForm.getEmail(), studyCenterForm.getPassword(),
				currentDate + "", studyCenterForm.getPhoneNo(), studyCenterForm.getAddress(), true);

		StudyCenter studyCenterInfo = new StudyCenter(studyCenterForm.getUserName(), studyCenterForm.getDescription(),
				0);
		try {
			userInfoService.addUserInfo(user);
			userInfoService.addStudyCenterInfo(studyCenterInfo);
			System.out.println("Success");
		} catch (Exception e) {
			modelAndView.addObject("studyCenterForm", new StudyCenterForm());
			modelAndView.addObject("errorMessage", "Error: " + e.getMessage());
			modelAndView.setViewName("admin/addStudent");
			return modelAndView;
		}

//		redirectAttributes.addFlashAttribute("flashUser", user);

		modelAndView.setViewName("redirect:/admin/allUser");
		return modelAndView;
	}

	@RequestMapping(value = { "editUser" }, method = RequestMethod.GET)
	public ModelAndView editStudent(@RequestParam("userName") String userName) {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			UserModel user = userInfoService.loadUserByUsername(userName);
			
			if(user.getRole_id() == 1) {
				StudentForm student = userInfoService.getUserStudentByUserName(userName);
				modelAndView.addObject("studentForm", student);
				modelAndView.setViewName("admin/editStudent");
			}else if(user.getRole_id() == 2) {
				TeacherForm teacher = userInfoService.getUserTeacherByUserName(userName);
				modelAndView.addObject("teacherForm", teacher);
				modelAndView.setViewName("admin/editTeacher");
			}else if(user.getRole_id() == 3) {
				StudyCenterForm studyCenter = userInfoService.getUserStudyCenterByUserName(userName);
				modelAndView.addObject("studyCenterForm", studyCenter);
				modelAndView.setViewName("admin/editStudyCenter");
			}
			
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = { "userStudent" }, method = RequestMethod.POST)
	public ModelAndView updateStudent(@ModelAttribute("studentForm") @Validated StudentForm studentForm) {
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

		} catch (NotFoundException e) {
			e.printStackTrace();
			modelAndView.addObject("studentForm", studentForm);
			modelAndView.setViewName("admin/edit-student");
			return modelAndView;
		}

		modelAndView.setViewName("redirect:/admin/allUser");
		return modelAndView;
	}
	
	@RequestMapping(value = { "userTeacher" }, method = RequestMethod.POST)
	public ModelAndView updateTeacher(@ModelAttribute("teacherForm") @Validated TeacherForm teacherForm) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(teacherForm);

		try {

			TeacherForm currentTeacher = userInfoService.getUserTeacherByUserName(teacherForm.getUserName());

			UserModel user = new UserModel(teacherForm.getUserName(), teacherForm.getRole_id(),
					teacherForm.getFullName(), teacherForm.getEmail(), teacherForm.getPassword(),
					currentTeacher.getCreateDate(), teacherForm.getPhoneNo(), teacherForm.getAddress(), true);

			Teacher teacher = new Teacher(teacherForm.getUserName(), teacherForm.getDob(), teacherForm.isGender(), teacherForm.getDescription(), currentTeacher.getRate());

			userInfoService.updateUserInfo(user);
			userInfoService.updateTeacherInfo(teacher);

		} catch (NotFoundException e) {
			e.printStackTrace();
			modelAndView.addObject("studentForm", teacherForm);
			modelAndView.setViewName("admin/editTeacher");
			return modelAndView;
		}

		modelAndView.setViewName("redirect:/admin/allUser");
		return modelAndView;
	}
	
	@RequestMapping(value = { "userStudyCenter" }, method = RequestMethod.POST)
	public ModelAndView updateStudyCenter(@ModelAttribute("studyCenterForm") @Validated StudyCenterForm studyCenterForm) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(studyCenterForm);

		try {

			StudyCenterForm currentSt = userInfoService.getUserStudyCenterByUserName(studyCenterForm.getUserName());

			UserModel user = new UserModel(studyCenterForm.getUserName(), studyCenterForm.getRole_id(),
					studyCenterForm.getFullName(), studyCenterForm.getEmail(), studyCenterForm.getPassword(),
					currentSt.getCreateDate(), studyCenterForm.getPhoneNo(), studyCenterForm.getAddress(), true);

			StudyCenter studyCenter = new StudyCenter(studyCenterForm.getUserName(), studyCenterForm.getDescription(), currentSt.getRate());
			userInfoService.updateUserInfo(user);
			userInfoService.updateStudyCenterInfo(studyCenter);

		} catch (NotFoundException e) {
			e.printStackTrace();
			modelAndView.addObject("studyCenterForm", studyCenterForm);
			modelAndView.setViewName("admin/editStudyCenter");
			return modelAndView;
		}

		modelAndView.setViewName("redirect:/admin/allUser");
		return modelAndView;
	}
	
	@RequestMapping(value = { "student-profile" }, method = RequestMethod.GET)
	public ModelAndView studentProfile() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/student-profile");
		return modelAndView;
	}

}
