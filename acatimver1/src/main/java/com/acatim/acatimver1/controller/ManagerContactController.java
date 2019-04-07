package com.acatim.acatimver1.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.entity.Contact;
import com.acatim.acatimver1.entity.SearchValue;
import com.acatim.acatimver1.service.PageableService;
import com.acatim.acatimver1.service.PageableServiceImpl;
import com.acatim.acatimver1.service.UserInfoService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = { "/admin" })
public class ManagerContactController {
	@Autowired
	private UserInfoService userInfoService;

	private PageableService pageableService;

	@RequestMapping(value = { "/allContact" }, method = RequestMethod.GET)
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
			Pageable pageable = new PageRequest(currentPage - 1, 2);

			int total = userInfoService.getAllContact().size();
			List<Contact> listUser = null;
			pageableService = new PageableServiceImpl(2, currentPage - 1, total, currentPage);
			listUser=userInfoService.getAllContactPageable(pageable);
			System.out.println("trung   "+listUser.get(0).isActive());	

			modelAndView.addObject("totalPages", pageableService.listPage());
			modelAndView.addObject("currentPage", currentPage);
			modelAndView.addObject("hasPrevious", pageableService.hasPrevious());
			modelAndView.addObject("hasNext", pageableService.hasNext());
			modelAndView.addObject("previous", pageableService.previous());
			modelAndView.addObject("next", pageableService.next());
			modelAndView.addObject("last", pageableService.last());
			modelAndView.addObject("first", pageableService.first());

			modelAndView.addObject("allContact", userInfoService.getAllContactPageable(pageable));
			SearchValue searchValue = new SearchValue();
			modelAndView.addObject("searchValue", searchValue);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("admin/allContact");
		return modelAndView;
	}

	@RequestMapping(value = { "/allContact" }, method = RequestMethod.POST)
	public ModelAndView searchUsers(@RequestParam(required = false, name = "page") String page,
			@ModelAttribute("searchValue") SearchValue search) {
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

			int total = 0;

			if (search.getSearch().trim().length() == 0) {
				total = userInfoService.getAllContact().size();
				System.out.println(
						total + " " + userInfoService.getAllUsersPageable(pageable, search.getRoleId()).size());
				modelAndView.addObject("allContact", userInfoService.getAllContactPageable(pageable));
			} else {
				List<Contact> listUser = null;
				if (search.getValue().equals("userName")) {
					listUser = userInfoService.searchAllContactByUserName(pageable, search.getSearch());
					total = listUser.size();
					modelAndView.addObject("allContact", listUser);
				} else if (search.getValue().equals("email")) {
					listUser = userInfoService.searchAllContactByEmail(pageable, search.getSearch());
					total = listUser.size();
					modelAndView.addObject("allContact", listUser);
				}
			}

			pageableService = new PageableServiceImpl(8, currentPage - 1, total, currentPage);

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
		modelAndView.setViewName("admin/allContact");
		return modelAndView;
	}

	@RequestMapping(value = { "blockContact" }, method = RequestMethod.GET)
	public ModelAndView blockStudent(@RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			userInfoService.removeContact(id);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/allContact");
		return modelAndView;
	}

	@RequestMapping(value = { "unlockContact" }, method = RequestMethod.GET)
	public ModelAndView unlockStudent(@RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			userInfoService.unlockUser(id);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/allContact");
		return modelAndView;
	}



}
