package com.acatim.acatimver1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acatim.acatimver1.entity.SearchValue;
import com.acatim.acatimver1.format.DateFormat;
import com.acatim.acatimver1.service.PageableService;
import com.acatim.acatimver1.service.PageableServiceImpl;

import javassist.NotFoundException;

import com.acatim.acatimver1.service.HistoryService;

@Controller
@RequestMapping(value = { "/admin" })
public class ManagerStatusController {

	@Autowired
	private HistoryService statusService;

	private PageableService pageableService;

	private DateFormat dateformat = new DateFormat();

	@RequestMapping(value = { "allStatus" }, method = RequestMethod.GET)
	public ModelAndView allHistory(@RequestParam(required = false, name = "page") String page,
			@ModelAttribute("searchValue") SearchValue search,
			@RequestParam(required = false, name = "search") String searchold) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(search);
		if (page == null || dateformat.isNumeric(page) == false) {
			page = 1 + "";
		}
		
		if(searchold != null) {
			search.setSearch(searchold);
		}
		
		if (search.getSearch() != null && search.getSearch().trim().equals("")) {
			search.setSearch(null);
		}

		try {
			int currentPage = Integer.parseInt(page);

			if (currentPage < 1) {
				currentPage = 1;
			}

			int total = statusService.countAllHistory(search);

			pageableService = new PageableServiceImpl(8, total, currentPage, null);

			modelAndView.addObject("totalPages", pageableService.listPage());
			modelAndView.addObject("currentPage", currentPage);
			modelAndView.addObject("hasPrevious", pageableService.hasPrevious());
			modelAndView.addObject("hasNext", pageableService.hasNext());
			modelAndView.addObject("previous", pageableService.previous());
			modelAndView.addObject("next", pageableService.next());
			modelAndView.addObject("last", pageableService.last());
			modelAndView.addObject("first", pageableService.first());

			modelAndView.addObject("allStatus", statusService.getAllHistoryPageble(pageableService, search));
			System.out.println();

			modelAndView.addObject("checklist", total == 0);

			modelAndView.addObject("searchValue", search);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("admin/all-status");
		return modelAndView;
	}

	@RequestMapping(value = "/allStatus", method = RequestMethod.POST)
	public ModelAndView searchHistory(@ModelAttribute("searchValue") SearchValue search,
			RedirectAttributes redirectAttributes) throws NotFoundException {
		System.out.println(search);
		ModelAndView modelAndView = new ModelAndView();
		redirectAttributes.addFlashAttribute("searchValue", search);
		modelAndView.setViewName("redirect:/admin/allStatus");
		return modelAndView;
	}
}
