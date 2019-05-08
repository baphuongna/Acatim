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
			@RequestParam(required = false, name = "rateFilter") String rateFilter,
			@RequestParam(required = false, name = "search") String find,
			@ModelAttribute("searchValue") SearchValue search) {
		search.setAdmin(false);
		if (page == null) {
			page = 1 + "";
		}

		ModelAndView modelAndView = new ModelAndView();
		List<UserModel> listTeacher = null;

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

			
			if (search.getSearch() != null && search.getSearch().trim().length() == 0) {
				search.setSearch(null);
			}else if (search.getSearch() == null){
				if (find != null && find.trim().length() == 0 ) {
					search.setSearch(null);
				}else if (find != null) {
					search.setSearch(find);
				}
			}
			
			if (search.getRateFilter() != null && search.getRateFilter().equals("0") || search.getRateFilter() != null && search.getRateFilter().trim().equals("")) {
				search.setRateFilter(null);
			}
			
			Sort sort = null;
			
			if(sortValue != null) {
				search.setSortValue(sortValue);
			}
			
			String sortName = null;
			
			if (search.getSortValue() != null) {
				if (!search.getSortValue().equals("0")) {
					if (search.getSortValue().equals("1")) {
						sort = Sort.by("full_name").ascending();
						sortName = "Tên Giáo Viên";
					} else if (search.getSortValue().equals("2")) {
						sort = Sort.by("create_date").ascending();
						sortName = "Ngày cập nhật";
					} else if (search.getSortValue().equals("3")) {
						sort = Sort.by("rate").ascending();
						sortName = "Đánh Giá Tăng Dần";
					} else if (search.getSortValue().equals("4")) {
						sort = Sort.by("rate").descending();
						sortName = "Đánh Giá Giảm Dần";
					}
				}
			}
			
			modelAndView.addObject("sortName", sortName);
			
			search.setRoleId("2");
			
			int total = userInfoService.getAllUsers(search).size();

			pageableService = new PageableServiceImpl(9, total, currentPage, sort);
			
			
			
			listTeacher = userInfoService.getAllUsersPageable(pageableService, search);
			
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
		modelAndView.setViewName("redirect:/teacher");
		return modelAndView;
	}

}
