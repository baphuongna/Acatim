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
import com.acatim.acatimver1.service.CategoriesService;
import com.acatim.acatimver1.service.PageableService;
import com.acatim.acatimver1.service.PageableServiceImpl;
import com.acatim.acatimver1.service.SubjectService;
import com.acatim.acatimver1.service.UserInfoService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = { "" })
public class StudyCenterController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private UserInfoService userInfoService;

	private PageableService pageableService;

	@RequestMapping(value = { "/study-center" }, method = RequestMethod.GET)
	public ModelAndView studyCenter(@RequestParam(required = false, name = "page") String page,
			@RequestParam(required = false, name = "sortValue") String sortValue,
			@ModelAttribute("searchValue") SearchValue search) {
		search.setAdmin(false);
		if (page == null) {
			page = 1 + "";
		}

		ModelAndView modelAndView = new ModelAndView();

		List<UserModel> listStudyCenter = null;

		try {
			int currentPage = Integer.parseInt(page);

			if (currentPage < 1) {
				currentPage = 1;
			}
			
			if (search.getSubjectId() != null && search.getSubjectId().equals("0")) {
				search.setSubjectId(null);
			}

			if (search.getCategoryId() != null && search.getCategoryId().equals("0") || search.getCategoryId() != null && search.getCategoryId().trim().equals("")) {
				search.setCategoryId(null);
			}
			
			if (search.getRateFilter() != null && search.getRateFilter().equals("0") || search.getRateFilter() != null && search.getRateFilter().trim().equals("")) {
				search.setRateFilter(null);
			}

			Sort sort = null;

			if (sortValue != null) {
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

			modelAndView.addObject("allCategories", categoriesService.getAllCategories());
			
			if (search.getCategoryId() != null) {
				modelAndView.addObject("allSubjects", subjectService.getSubjectByCategoryId(search.getCategoryId()));
			}else {
				modelAndView.addObject("allSubjects", subjectService.getAllSubject());
			}
			
			search.setRoleId("3");
			
			int total = userInfoService.getAllUsers(search).size();

			pageableService = new PageableServiceImpl(9, total, currentPage, sort);
			
			
			
			listStudyCenter = userInfoService.getAllUsersPageable(pageableService, search);

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
		
		modelAndView.addObject("searchValue", search);
		System.out.println(search);
		modelAndView.addObject("listStudyCenter", listStudyCenter);
		modelAndView.setViewName("study-center");
		return modelAndView;
	}

	@RequestMapping(value = { "/study-center" }, method = RequestMethod.POST)
	public ModelAndView searchTeacher(@RequestParam(required = false, name = "page") String page,
			@ModelAttribute("searchValue") SearchValue search, RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView();
		redirectAttributes.addFlashAttribute("searchValue", search);
		System.out.println(search);
		modelAndView.setViewName("redirect:/study-center");
		return modelAndView;
	}
}
