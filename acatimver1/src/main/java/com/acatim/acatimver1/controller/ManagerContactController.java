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
import com.acatim.acatimver1.service.ContactService;
import com.acatim.acatimver1.service.PageableService;
import com.acatim.acatimver1.service.PageableServiceImpl;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = { "/admin" })
public class ManagerContactController {

	@Autowired
	private ContactService contactService;

	private PageableService pageableService;

	@RequestMapping(value = { "/allContact" }, method = RequestMethod.GET)
	public ModelAndView allContact(@RequestParam(required = false, name = "page") String page) {
		ModelAndView modelAndView = new ModelAndView();
		if (page == null) {
			page = 1 + "";
		}
		try {
			int currentPage = 1;
			try {
				currentPage = Integer.parseInt(page);
			}catch (Exception e) {
				currentPage = 1;
			}

			if (currentPage < 1) {
				currentPage = 1;
			}

			@SuppressWarnings("deprecation")
			Pageable pageable = new PageRequest(currentPage - 1, 8);

			int total = contactService.getAllContact().size();
			List<Contact> listUser = null;
			pageableService = new PageableServiceImpl(8, total, currentPage, null);
			listUser=contactService.getAllContactPageable(pageable);
			System.out.println("trung   "+listUser.get(0).isActive());	

			modelAndView.addObject("totalPages", pageableService.listPage());
			modelAndView.addObject("currentPage", currentPage);
			modelAndView.addObject("hasPrevious", pageableService.hasPrevious());
			modelAndView.addObject("hasNext", pageableService.hasNext());
			modelAndView.addObject("previous", pageableService.previous());
			modelAndView.addObject("next", pageableService.next());
			modelAndView.addObject("last", pageableService.last());
			modelAndView.addObject("first", pageableService.first());

			modelAndView.addObject("allContact", contactService.getAllContactPageable(pageable));
			SearchValue searchValue = new SearchValue();
			modelAndView.addObject("searchValue", searchValue);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("admin/allContact");
		return modelAndView;
	}

	@RequestMapping(value = { "/allContact" }, method = RequestMethod.POST)
	public ModelAndView searchContacts(@RequestParam(required = false, name = "page") String page,
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
				total = contactService.getAllContact().size();
//				System.out.println(
//						total + " " + userInfoService.getAllUsersPageable(pageableService, search.getRoleId()).size());
				modelAndView.addObject("allContact", contactService.getAllContactPageable(pageable));
			} else {
				List<Contact> listUser = null;
				if (search.getValue().equals("userName")) {
					listUser = contactService.searchAllContactByUserName(pageable, search.getSearch());
					total = listUser.size();
					modelAndView.addObject("allContact", listUser);
				} else if (search.getValue().equals("email")) {
					listUser = contactService.searchAllContactByEmail(pageable, search.getSearch());
					total = listUser.size();
					modelAndView.addObject("allContact", listUser);
				}
			}

			pageableService = new PageableServiceImpl(8, total, currentPage, null);

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


}
