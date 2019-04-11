package com.acatim.acatimver1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.entity.Course;
import com.acatim.acatimver1.entity.SearchValue;
import com.acatim.acatimver1.service.CategoriesService;
import com.acatim.acatimver1.service.CourseService;
import com.acatim.acatimver1.service.DiscountCodeService;
import com.acatim.acatimver1.service.PageableService;
import com.acatim.acatimver1.service.PageableServiceImpl;
import com.acatim.acatimver1.service.SubjectService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = {""})
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private DiscountCodeService discountCodeServiceImpl;
	
	private PageableService pageableService;
	
	@RequestMapping(value = "/course" , method = RequestMethod.GET)
	public String showAllCourseFull(@RequestParam(required = false, name = "page") String page, Model model,
			@RequestParam(required = false, name = "subjectId") String subjectId,
			@RequestParam(required = false, name = "categoryId}") String categoryId) {
		
		
		if (page == null) {
			page = 1 + "";
		}
		
		if(subjectId == null) {
			subjectId = "0";
		}
		
		if(categoryId == null) {
			categoryId = "0";
		}
		
		try {
			int currentPage = Integer.parseInt(page);

			if (currentPage < 1) {
				currentPage = 1;
			}
			
			int total = subjectService.getAllSubject().size();
			
			pageableService = new PageableServiceImpl(8, currentPage - 1, total, currentPage, null);
			
			model.addAttribute("allCategories", categoriesService.getAllCategories());
			if(!categoryId.equals("0") && subjectId.equals("0")) {
				model.addAttribute("allCourses", courseService.getAllCourseByCateIdPaging(pageableService, categoryId));
				model.addAttribute("allSubjects", subjectService.getSubjectByCategoryId(categoryId));
			}else if(!subjectId.equals("0")) {
				model.addAttribute("allCourses", courseService.getAllCourseBySujectIdPaging(pageableService, subjectId));
				model.addAttribute("allSubjects", subjectService.getListSubject());
			}else{
				model.addAttribute("allSubjects", subjectService.getListSubject());
				model.addAttribute("allCourses", courseService.getAllCoursePaging(pageableService));
			}
			
			
			
			model.addAttribute("totalPages", pageableService.listPage());
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("hasPrevious", pageableService.hasPrevious());
			model.addAttribute("hasNext", pageableService.hasNext());
			model.addAttribute("previous", pageableService.previous());
			model.addAttribute("next", pageableService.next());
			model.addAttribute("last", pageableService.last());
			model.addAttribute("first", pageableService.first());
			
			SearchValue searchValue = new SearchValue();
			searchValue.setCategoryId(categoryId);
			searchValue.setSubjectId(subjectId);
			model.addAttribute("searchValue", searchValue);
			System.out.println(searchValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		
//		model.addAttribute("courses", courseService.getAllCourse());
//		Course search = new Course();
//		model.addAttribute("search",search);
		
		model.addAttribute("discountCode", discountCodeServiceImpl.getAllDiscountCode());
		return "course";
	}
	

	@RequestMapping(value = "/course", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid @ModelAttribute("search") Course khanh, BindingResult bindingResult)
			throws NotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		
		courseService.searchCourseByCourseName(khanh.getCourseName());
		modelAndView.setViewName("course");
		return modelAndView;
	}
}
