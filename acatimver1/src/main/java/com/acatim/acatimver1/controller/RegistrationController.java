package com.acatim.acatimver1.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.model.ObjectUser;
import com.acatim.acatimver1.model.Student;
import com.acatim.acatimver1.model.StudyCenter;
import com.acatim.acatimver1.model.Teacher;
import com.acatim.acatimver1.model.UserModel;
import com.acatim.acatimver1.service.UserInfoServiceImpl;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = {""})
public class RegistrationController {
	
	@Autowired
	private UserInfoServiceImpl userInfoService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		UserModel user = new UserModel();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid @ModelAttribute("user") ObjectUser data, BindingResult bindingResult) throws NotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("error   "+ data);
		boolean userExists = userInfoService.checkUserExist(data.getUserName());
		  if (userExists == true) { 
			  bindingResult .rejectValue("userName", "error.user","Tài khoản email này đã tồn tại, vui lòng nhập một địa chỉ email khác"); 
		  }
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage","Bạn đã nhập sai một số thông tin, vui lòng kiểm tra lại");
			modelAndView.setViewName("registration");
		} else {
			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String dateCurent=(String)dateFormat.format(date);
				System.out.println("hhhhhieu  "+dateCurent);
				data.setCreateDate(dateCurent);
				UserModel user = new UserModel(data.getUserName(), data.getRole_id(), data.getFullName(),
						data.getEmail(), data.getPassword(), data.getCreateDate(), data.getPhone(), data.getAddress(),
						data.isActive());
				System.out.println("hhhhhieu");
				System.out.println("hhhhhieu  "+user);
				if (data.getRole_id() == 1) {
					Student newStudent = new Student(data.getUserName(), data.getCreateDate(), data.isGender());
					System.out.println("student"+newStudent);
					userInfoService.addUserInfo(user);
					userInfoService.addStudentInfo(newStudent);
					System.out.println("học sinh thanh cong");
				}
				if (data.getRole_id() == 2) {
					Teacher newTeacher = new Teacher(data.getUserName(), data.getDob(), data.isGender(),
							data.getDescription(), 1);
					userInfoService.addUserInfo(user);
					userInfoService.addTeacherInfo(newTeacher);
					System.out.println("giáo vien thanh cong");
				}
				if (data.getRole_id() == 3) {
					StudyCenter newStudyCenter = new StudyCenter(data.getUserName(), data.getDescription(),1);
					userInfoService.addUserInfo(user);
					userInfoService.addStudyCenterInfo(newStudyCenter);
					System.out.println("trung tam thanh cong");
				}
			} catch (NotFoundException e) {
				System.out.println("error regitration");
				e.printStackTrace();
			}
			modelAndView.addObject("successMessage", "Chúc mừng bạn đã đăng kí thành công tài khoản");
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

}
