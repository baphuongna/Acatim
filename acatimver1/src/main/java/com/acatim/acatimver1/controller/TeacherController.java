package com.acatim.acatimver1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.entity.UserModel;
import com.acatim.acatimver1.service.PageableService;
import com.acatim.acatimver1.service.PageableServiceImpl;
import com.acatim.acatimver1.service.UserInfoService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = {""})
public class TeacherController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	private PageableService pageableService;
	
	@RequestMapping(value = { "/teacher" }, method = RequestMethod.GET)
	public ModelAndView teacher(@RequestParam(required = false, name = "page") String page) {
		
		if (page == null) {
			page = 1 + "";
		}
		
		ModelAndView modelAndView = new ModelAndView();
		List<UserModel> listTeacher = null;

		try {
			int currentPage = Integer.parseInt(page);

			if (currentPage < 1) {
				currentPage = 1;
			}
			int total = userInfoService.loadAllUserTeacher().size();
	
			pageableService = new PageableServiceImpl(9, total, currentPage, null);
			
			listTeacher = userInfoService.getAllUsersPageable(pageableService, "2");
			
			modelAndView.addObject("totalPages", pageableService.listPage());
			modelAndView.addObject("currentPage", currentPage);
			modelAndView.addObject("hasPrevious", pageableService.hasPrevious());
			modelAndView.addObject("hasNext", pageableService.hasNext());
			modelAndView.addObject("previous", pageableService.previous());
			modelAndView.addObject("next", pageableService.next());
			modelAndView.addObject("last", pageableService.last());
			modelAndView.addObject("first", pageableService.first());
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.addObject("listTeacher", listTeacher);
		modelAndView.setViewName("teacher");
		return modelAndView;
	}
}
