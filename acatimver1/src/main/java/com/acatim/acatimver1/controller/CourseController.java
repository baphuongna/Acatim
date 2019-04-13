package com.acatim.acatimver1.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.entity.Course;
import com.acatim.acatimver1.entity.DiscountCode;
import com.acatim.acatimver1.entity.SearchValue;
import com.acatim.acatimver1.entity.UserModel;
import com.acatim.acatimver1.service.CategoriesService;
import com.acatim.acatimver1.service.CourseService;
import com.acatim.acatimver1.service.DiscountCodeService;
import com.acatim.acatimver1.service.PageableService;
import com.acatim.acatimver1.service.PageableServiceImpl;
import com.acatim.acatimver1.service.SubjectService;
import com.acatim.acatimver1.service.UserInfoService;

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
	private UserInfoService userInfoService;
	
	@Autowired
	private DiscountCodeService discountCodeService;
	
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
			
			pageableService = new PageableServiceImpl(8, total, currentPage, null);
			
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

//		model.addAttribute("discountCode", discountCodeServiceImpl.getAllDiscountCode());
		return "course";
	}
	

	@RequestMapping(value = "/course", method = RequestMethod.POST)
	public ModelAndView searchCourse(@RequestParam(required = false, name = "page") String page, Model model,
			@ModelAttribute("searchValue") SearchValue search)
			throws NotFoundException {
		
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
			
			int total = subjectService.getAllSubject().size();
			
			modelAndView.addObject("allCategories", categoriesService.getAllCategories());
			Sort sort = null;
			if(search.getSortValue() != null) {
				if(!search.getSortValue().equals("0")) {
					if(search.getSortValue().equals("1")) {
						sort = Sort.by("courseName").ascending();
					}else if(search.getSortValue().equals("2")) {
						sort = Sort.by("create_date").ascending();
					}else if(search.getSortValue().equals("3")) {
						sort = Sort.by("price").ascending();
					}else if(search.getSortValue().equals("4")) {
						sort = Sort.by("price").descending();
					}
				}
			}
			
			
			
			if (search.getSearch() == null) {
				total = subjectService.getAllSubject().size();
				pageableService = new PageableServiceImpl(8, total, currentPage, sort);
				if(!search.getCategoryId().equals("0")) {
					modelAndView.addObject("allCourses", courseService.getAllCourseByCateIdPaging(pageableService, search.getCategoryId()));
				}else{
					modelAndView.addObject("allCourses", courseService.getAllCoursePaging(pageableService));
				}
				
			}else {
				total = courseService.searchCourseByCourseName(search.getSearch()).size();
				pageableService = new PageableServiceImpl(8, total, currentPage, sort);
				if(!search.getCategoryId().equals("0")) {
					modelAndView.addObject("allCourses", courseService.searchAllCourseByCateIdPaging(pageableService, search.getCategoryId(), search.getSearch()));
				}else{
					modelAndView.addObject("allCourses", courseService.searchAllCoursePaging(pageableService, search.getSearch()));
				}
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
			searchValue.setCategoryId(search.getCategoryId());
			searchValue.setSubjectId(search.getSubjectId());
			model.addAttribute("searchValue", searchValue);
			System.out.println(searchValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		modelAndView.setViewName("course");
		return modelAndView;
	}
	
	@RequestMapping(value = "/detail-course", method = RequestMethod.GET)
	public ModelAndView detailCourse(@RequestParam(required = false, name = "courseId") String courseId, Principal principal)
			throws NotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		String userName = null;
		if (principal != null) {
			userName = principal.getName();
		}
		
		try {
			Course course = courseService.getCourseById(courseId);
			
			UserModel user = userInfoService.loadUserByUsername(course.getUserName());
			
			modelAndView.addObject("course", course);
			modelAndView.addObject("creater", user);

			if(userName != null) {
				DiscountCode discountCode = discountCodeService.getDiscountCodeByUserName(userName, courseId);
				modelAndView.addObject("discountCode", discountCode);
			}else {
				modelAndView.addObject("discountCode", new DiscountCode());
			}
			modelAndView.addObject("loging", userName);

			modelAndView.setViewName("detail-course");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return modelAndView;
	}
}
