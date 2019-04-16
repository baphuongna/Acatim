package com.acatim.acatimver1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acatim.acatimver1.entity.SearchValue;
import com.acatim.acatimver1.entity.UserModel;
import com.acatim.acatimver1.service.PageableService;
import com.acatim.acatimver1.service.PageableServiceImpl;
import com.acatim.acatimver1.service.UserInfoService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = { "" })
public class TeacherController {

	@Autowired
	private UserInfoService userInfoService;

	private PageableService pageableService;

	@RequestMapping(value = { "/teacher" }, method = RequestMethod.GET)
	public ModelAndView teacher(@RequestParam(required = false, name = "page") String page,
			@RequestParam(required = false, name = "sortValue") String sortValue,
			@ModelAttribute("searchValue") SearchValue search) {

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

			Sort sort = null;
			
			if(sortValue != null) {
				search.setSortValue(sortValue);
			}
			
			if (search.getSortValue() != null) {
				if (!search.getSortValue().equals("0")) {
					if (search.getSortValue().equals("1")) {
						sort = Sort.by("full_name").ascending();
					} else if (search.getSortValue().equals("2")) {
						sort = Sort.by("create_date").ascending();
					} else if (search.getSortValue().equals("3")) {
						sort = Sort.by("rate").ascending();
					} else if (search.getSortValue().equals("4")) {
						sort = Sort.by("rate").descending();
					}
				}
			}

			int total = userInfoService.loadAllUserTeacher().size();

			pageableService = new PageableServiceImpl(9, total, currentPage, sort);

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

		//SearchValue searchValue = new SearchValue();
		modelAndView.addObject("searchValue", search);
		System.out.println(search);

		modelAndView.addObject("listTeacher", listTeacher);
		modelAndView.setViewName("teacher");
		return modelAndView;
	}

	@RequestMapping(value = { "/teacher" }, method = RequestMethod.POST)
	public ModelAndView searchTeacher(@ModelAttribute("searchValue") SearchValue search, RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView();
		redirectAttributes.addFlashAttribute("searchValue", search);
		System.out.println(search);
		modelAndView.setViewName("redirect:/teacher");
		return modelAndView;
	}

}
