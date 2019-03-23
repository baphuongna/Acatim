package com.acatim.acatimver1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.model.SearchValue;
import com.acatim.acatimver1.service.CategoriesServiceImpl;
import com.acatim.acatimver1.service.CourseServiceImpl;
import com.acatim.acatimver1.service.PageableService;
import com.acatim.acatimver1.service.SubjectServiceImpl;

@Controller
@RequestMapping(value = {"/admin"})
public class ManagerCourseController {

	@Autowired
	private CourseServiceImpl courseService;
	
	@Autowired
	private SubjectServiceImpl subjectService;
	
	@Autowired
	private CategoriesServiceImpl categoriesService;
	
	private PageableService pageableService;

	@RequestMapping(value = {"all-courses"}, method = RequestMethod.GET)
	public ModelAndView allCourses(@RequestParam(required = false, name = "page") String page,
			@RequestParam(required = false, name = "subjectId") String subjectId,
			@RequestParam(required = false, name = "categoryId}") String categoryId) {
		
		ModelAndView modelAndView = new ModelAndView();
		
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
			
			@SuppressWarnings("deprecation")
			Pageable pageable = new PageRequest(currentPage - 1, 8);

			int total = subjectService.getAllSubject().size();
			
			pageableService = new PageableService(8, currentPage - 1, total, currentPage);
			
			
			
			
			modelAndView.addObject("allCategories", categoriesService.getAllCategories());
			if(!categoryId.equals("0") && subjectId.equals("0")) {
				modelAndView.addObject("allCourses", courseService.getAllCourseByCateIdPaging(pageable, categoryId));
				modelAndView.addObject("allSubjects", subjectService.getSubjectByCategoryId(categoryId));
			}else if(!subjectId.equals("0")) {
				modelAndView.addObject("allCourses", courseService.getAllCourseBySujectIdPaging(pageable, subjectId));
				modelAndView.addObject("allSubjects", subjectService.getListSubject());
			}else{
				modelAndView.addObject("allSubjects", subjectService.getListSubject());
				modelAndView.addObject("allCourses", courseService.getAllCoursePaging(pageable));
			}
			
			
			
			modelAndView.addObject("totalPages", pageableService.listPage());
			modelAndView.addObject("currentPage", currentPage);
			modelAndView.addObject("hasPrevious", pageableService.hasPrevious());
			modelAndView.addObject("hasNext", pageableService.hasNext());
			modelAndView.addObject("previous", pageableService.previous());
			modelAndView.addObject("next", pageableService.next());
			modelAndView.addObject("last", pageableService.last());
			modelAndView.addObject("first", pageableService.first());
			
			SearchValue searchValue = new SearchValue();
			searchValue.setCategoryId(categoryId);
			searchValue.setSubjectId(subjectId);
			modelAndView.addObject("searchValue", searchValue);
			System.out.println(searchValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		modelAndView.setViewName("admin/all-courses");
		return modelAndView;
	}
	
	@RequestMapping(value = {"all-courses"}, method = RequestMethod.POST)
	public ModelAndView searchCourses(@RequestParam(required = false, name = "page") String page,
			@ModelAttribute("searchValue") SearchValue search) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if (page == null) {
			page = 1 + "";
		}
		
		if(search.getSubjectId() == null) {
			search.setSubjectId("0");
		}
		
		if(search.getCategoryId() == null) {
			search.setCategoryId("0");
		}
		
		
		
		try {
			int currentPage = Integer.parseInt(page);

			if (currentPage < 1) {
				currentPage = 1;
			}
			
			@SuppressWarnings("deprecation")
			Pageable pageable = new PageRequest(currentPage - 1, 8);

			
			
			int total = 0;
			
			modelAndView.addObject("allCategories", categoriesService.getAllCategories());
			
			if (search.getSearch().trim().length() == 0) {
				total = subjectService.getAllSubject().size();
								
				if(!search.getCategoryId().equals("0") && search.getSubjectId().equals("0")) {
					modelAndView.addObject("allCourses", courseService.getAllCourseByCateIdPaging(pageable, search.getCategoryId()));
					modelAndView.addObject("allSubjects", subjectService.getSubjectByCategoryId(search.getCategoryId()));
				}else if(!search.getSubjectId().equals("0")) {
					modelAndView.addObject("allCourses", courseService.getAllCourseBySujectIdPaging(pageable, search.getSubjectId()));
					modelAndView.addObject("allSubjects", subjectService.getListSubject());
				}else{
					modelAndView.addObject("allSubjects", subjectService.getListSubject());
					modelAndView.addObject("allCourses", courseService.getAllCoursePaging(pageable));
				}
				
			}else {
				if(!search.getCategoryId().equals("0") && search.getSubjectId().equals("0")) {
					modelAndView.addObject("allCourses", courseService.searchAllCourseByCateIdPaging(pageable, search.getCategoryId(), search.getSearch()));
					modelAndView.addObject("allSubjects", subjectService.getSubjectByCategoryId(search.getCategoryId()));
				}else if(!search.getSubjectId().equals("0")) {
					modelAndView.addObject("allCourses", courseService.searchAllCourseBySujectIdPaging(pageable, search.getSubjectId(), search.getSearch()));
					modelAndView.addObject("allSubjects", subjectService.getListSubject());
				}else{
					modelAndView.addObject("allSubjects", subjectService.getListSubject());
					modelAndView.addObject("allCourses", courseService.searchAllCoursePaging(pageable, search.getSearch()));
				}
			}
			
			pageableService = new PageableService(8, currentPage - 1, total, currentPage);
			
			
			
			
			
			modelAndView.addObject("totalPages", pageableService.listPage());
			modelAndView.addObject("currentPage", currentPage);
			modelAndView.addObject("hasPrevious", pageableService.hasPrevious());
			modelAndView.addObject("hasNext", pageableService.hasNext());
			modelAndView.addObject("previous", pageableService.previous());
			modelAndView.addObject("next", pageableService.next());
			modelAndView.addObject("last", pageableService.last());
			modelAndView.addObject("first", pageableService.first());

			modelAndView.addObject("searchValue", search);
			System.out.println(search);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		modelAndView.setViewName("admin/all-courses");
		return modelAndView;
	}

	@RequestMapping(value = {"add-course"}, method = RequestMethod.GET)
	public ModelAndView addCourse() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/add-course");
		return modelAndView;
	}

	@RequestMapping(value = {"edit-course"}, method = RequestMethod.GET)
	public ModelAndView editCourse() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/edit-course");
		return modelAndView;
	}

	@RequestMapping(value = {"course-info"}, method = RequestMethod.GET)
	public ModelAndView courseInfo() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/course-info");
		return modelAndView;
	}

}
