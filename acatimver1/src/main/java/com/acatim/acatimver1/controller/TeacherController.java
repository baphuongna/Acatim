package com.acatim.acatimver1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.entity.UserModel;
import com.acatim.acatimver1.service.UserInfoService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = {""})
public class TeacherController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value = { "/teacher" }, method = RequestMethod.GET)
	public ModelAndView teacher() {
		ModelAndView modelAndView = new ModelAndView();
		List<UserModel> listTeacher = null;
		List<UserModel> listStudyCenter = null;
		
		try {
			//get teacher
			listTeacher = this.userInfoService.loadAllUserTeacher();
			if(listTeacher != null && listTeacher.size() > 0) {
				for (UserModel teacher : listTeacher) {
					
					//split description
					if(teacher.getTeacher().getDescription().length() > 40) {
						teacher.getTeacher().setDescription(teacher.getTeacher().getDescription().substring(0, 40) + " ...");
					}
					if(teacher.getTeacher().getDescription().contains("</br>")) {
						String[] splitDes = teacher.getTeacher().getDescription().split("</br>");
						teacher.getTeacher().setDescription(splitDes[0]+ " ...");
					}
				}
			}
			//get study center
			listStudyCenter = this.userInfoService.loadAllUserStudyCenter();
			if(listStudyCenter != null && listStudyCenter.size() > 0) {
				for (UserModel studyCenter : listStudyCenter) {
					
					//split description
					if(studyCenter.getStudyCenter().getDescription().length() > 40) {
						studyCenter.getStudyCenter().setDescription(studyCenter.getStudyCenter().getDescription().substring(0, 40)+ " ...");
					}
					if(studyCenter.getStudyCenter().getDescription().contains("</br>")) {
						String[] splitDes = studyCenter.getStudyCenter().getDescription().split("</br>");
						studyCenter.getStudyCenter().setDescription(splitDes[0]+ " ...");
					}
				}
			}
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.addObject("listTeacher", listTeacher);
		modelAndView.addObject("listStudyCenter", listStudyCenter);
		modelAndView.setViewName("teacher");
		return modelAndView;
	}
}
