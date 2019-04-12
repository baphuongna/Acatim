package com.acatim.acatimver1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.entity.SearchValue;
import com.acatim.acatimver1.service.PageableService;
import com.acatim.acatimver1.service.PageableServiceImpl;
import com.acatim.acatimver1.service.HistoryService;

@Controller
@RequestMapping(value = { "/admin" })
public class ManagerStatusController {

	@Autowired
	private HistoryService statusService;
	
	private PageableService pageableService;
	
	@RequestMapping(value = { "/allStatus" }, method = RequestMethod.GET)
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

			int total = statusService.getAllHistory().size();

			pageableService = new PageableServiceImpl(8, total, currentPage, null);

			modelAndView.addObject("totalPages", pageableService.listPage());
			modelAndView.addObject("currentPage", currentPage);
			modelAndView.addObject("hasPrevious", pageableService.hasPrevious());
			modelAndView.addObject("hasNext", pageableService.hasNext());
			modelAndView.addObject("previous", pageableService.previous());
			modelAndView.addObject("next", pageableService.next());
			modelAndView.addObject("last", pageableService.last());
			modelAndView.addObject("first", pageableService.first());

			modelAndView.addObject("allStatus", statusService.getAllHistoryPageble(pageable));
			System.out.println();
			
			modelAndView.addObject("checklist", statusService.getAllHistoryPageble(pageable).size() == 0);
			
			SearchValue searchValue = new SearchValue();
			modelAndView.addObject("searchValue", searchValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("admin/all-status");
		return modelAndView;
	}
}
