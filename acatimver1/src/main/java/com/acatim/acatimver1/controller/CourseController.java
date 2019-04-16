package com.acatim.acatimver1.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acatim.acatimver1.entity.Course;
import com.acatim.acatimver1.entity.DiscountCode;
import com.acatim.acatimver1.entity.History;
import com.acatim.acatimver1.entity.SearchValue;
import com.acatim.acatimver1.entity.UserModel;
import com.acatim.acatimver1.format.DateFormat;
import com.acatim.acatimver1.service.CategoriesService;
import com.acatim.acatimver1.service.CourseService;
import com.acatim.acatimver1.service.DiscountCodeService;
import com.acatim.acatimver1.service.HistoryService;
import com.acatim.acatimver1.service.PageableService;
import com.acatim.acatimver1.service.PageableServiceImpl;
import com.acatim.acatimver1.service.SubjectService;
import com.acatim.acatimver1.service.UserInfoService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = { "" })
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
	
	@Autowired
	private HistoryService historyService;

	private PageableService pageableService;

	private DateFormat dateformat = new DateFormat();

	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public String showAllCourseFull(@RequestParam(required = false, name = "page") String page, Model model,
			@RequestParam(required = false, name = "subjectId") String subjectId,
			@RequestParam(required = false, name = "categoryId}") String categoryId) {

		if (page == null) {
			page = 1 + "";
		}

		if (subjectId == null) {
			subjectId = "0";
		}

		if (categoryId == null) {
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
			if (!categoryId.equals("0") && subjectId.equals("0")) {
				model.addAttribute("allCourses", courseService.getAllCourseByCateIdPaging(pageableService, categoryId));
				model.addAttribute("allSubjects", subjectService.getSubjectByCategoryId(categoryId));
			} else if (!subjectId.equals("0")) {
				model.addAttribute("allCourses",
						courseService.getAllCourseBySujectIdPaging(pageableService, subjectId));
				model.addAttribute("allSubjects", subjectService.getListSubject());
			} else {
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
			@ModelAttribute("searchValue") SearchValue search) throws NotFoundException {

		ModelAndView modelAndView = new ModelAndView();

		if (page == null) {
			page = 1 + "";
		}

		if (search.getSubjectId() == null) {
			search.setSubjectId("0");
		}

		if (search.getCategoryId() == null) {
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
			if (search.getSortValue() != null) {
				if (!search.getSortValue().equals("0")) {
					if (search.getSortValue().equals("1")) {
						sort = Sort.by("courseName").ascending();
					} else if (search.getSortValue().equals("2")) {
						sort = Sort.by("create_date").ascending();
					} else if (search.getSortValue().equals("3")) {
						sort = Sort.by("price").ascending();
					} else if (search.getSortValue().equals("4")) {
						sort = Sort.by("price").descending();
					}
				}
			}

			if (search.getSearch() == null) {
				total = subjectService.getAllSubject().size();
				pageableService = new PageableServiceImpl(8, total, currentPage, sort);
				if (!search.getCategoryId().equals("0")) {
					modelAndView.addObject("allCourses",
							courseService.getAllCourseByCateIdPaging(pageableService, search.getCategoryId()));
				} else {
					modelAndView.addObject("allCourses", courseService.getAllCoursePaging(pageableService));
				}

			} else {
				total = courseService.searchCourseByCourseName(search.getSearch()).size();
				pageableService = new PageableServiceImpl(8, total, currentPage, sort);
				if (!search.getCategoryId().equals("0")) {
					modelAndView.addObject("allCourses", courseService.searchAllCourseByCateIdPaging(pageableService,
							search.getCategoryId(), search.getSearch()));
				} else {
					modelAndView.addObject("allCourses",
							courseService.searchAllCoursePaging(pageableService, search.getSearch()));
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
	public ModelAndView detailCourse(@RequestParam(required = false, name = "courseId") String courseId,
			Principal principal) throws NotFoundException {
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

			if (userName != null) {
				DiscountCode discountCode = discountCodeService.getDiscountCodeByUserName(userName, courseId);
				modelAndView.addObject("discountCode", discountCode);
			} else {
				modelAndView.addObject("discountCode", new DiscountCode());
			}
			modelAndView.addObject("loging", userName);

			modelAndView.setViewName("detail-course");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/manager-course", method = RequestMethod.GET)
	public ModelAndView managerCourse(@RequestParam(required = false, name = "page") String page, Principal principal,
			@RequestParam(required = false, name = "subjectId") String subjectId, @ModelAttribute("searchValue") SearchValue search) throws NotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		String userName = null;
		if (principal != null) {
			userName = principal.getName();
		}
		
		if (page == null) {
			page = 1 + "";
		}
		
		if(subjectId == null) {
			subjectId = "0";
		}else {
			search.setSubjectId(subjectId);
		}
		
		if(search.getSubjectId() == null) {
			search.setSubjectId("0");
		}
		
		
		
		try {
			int currentPage = Integer.parseInt(page);

			if (currentPage < 1) {
				currentPage = 1;
			}
			
			int total = courseService.getCourseByUserName(userName).size();
			
			pageableService = new PageableServiceImpl(8, total, currentPage, null);

			modelAndView.addObject("allCategories", categoriesService.getAllCategories());
			
			if(search.getSubjectId().equals("0")) {
				modelAndView.addObject("allCourses", courseService.getAllCourseByUserName(pageableService, userName, null));
			}else {
				modelAndView.addObject("allCourses", courseService.getAllCourseByUserName(pageableService, userName, search.getSubjectId()));
			}
			modelAndView.addObject("allSubjects", subjectService.getListSubject());
			
			
			
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
	
		modelAndView.setViewName("manager-course");

		return modelAndView;
	}
	
	@RequestMapping(value = {"manager-course"}, method = RequestMethod.POST)
	public ModelAndView searchCourses(@ModelAttribute("searchValue") SearchValue search, RedirectAttributes redirectAttributes) {
		
		ModelAndView modelAndView = new ModelAndView();

		redirectAttributes.addFlashAttribute("searchValue", search);
		System.out.println(search);
		
		modelAndView.setViewName("redirect:/manager-course");
		return modelAndView;
	}

	@RequestMapping(value = "/add-course", method = RequestMethod.GET)
	public ModelAndView addCourse() throws NotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		try {

			modelAndView.addObject("allSubjects", subjectService.getListSubject());
			modelAndView.addObject("course", new Course());
			modelAndView.setViewName("add-course");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}

	@RequestMapping(value = "/add-course", method = RequestMethod.POST)
	public ModelAndView addNewCourse(@Valid @ModelAttribute("course") Course course, BindingResult result,
			Principal principal, RedirectAttributes redirectAttributes) throws NotFoundException {
		ModelAndView modelAndView = new ModelAndView();

		String userName = null;
		if (principal != null) {
			userName = principal.getName();
		}

		String courseId = courseService.genCourseId();
		course.setCourseId(courseId);
		course.setCreateDate(dateformat.currentDate());

		if (course.getSubjectId().equals("0")) {
			modelAndView.addObject("allSubjects", subjectService.getListSubject());
			modelAndView.addObject("course", course);
			modelAndView.addObject("errorMessage", "Chọn Môn học trước khi hoàn thành đăng ký thông tin khóa học!");
			modelAndView.setViewName("add-course");
			return modelAndView;
		}

		if (result.hasErrors()) {
			modelAndView.addObject("allSubjects", subjectService.getListSubject());
			modelAndView.addObject("course", course);
			modelAndView.setViewName("add-course");
			return modelAndView;
		}

		try {
			course.setUserName(userName);
			course.setStartDate(dateformat.StringToDateSQL(course.getStartDate()));
			course.setEndDate(dateformat.StringToDateSQL(course.getEndDate()));
			course.setActive(true);
			System.out.println(course);
			courseService.addCourse(course);
			modelAndView.setViewName("redirect:/manager-course");
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.setViewName("add-course");
			return modelAndView;
		}

		return modelAndView;
	}
	
	@RequestMapping(value = { "blockStudent" }, method = RequestMethod.GET)
	public ModelAndView blockStudent(@RequestParam("courseId") String courseId,Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			courseService.removeCourse(courseId);
			
			String userName = null;
			if (principal != null) {
				userName = principal.getName();
			}
			
			History history = new History();
			history.setIdChange(courseId);
			history.setValueChanged("Blocked");
			history.setDateChange(dateformat.currentDate());
			history.setBy(userName);
			historyService.addHistory(history);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/all-courses");
		return modelAndView;
	}

	@RequestMapping(value = { "unlockStudent" }, method = RequestMethod.GET)
	public ModelAndView unlockStudent(@RequestParam("courseId") String courseId, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			courseService.unlockCourse(courseId);
			
			String userName = null;
			if (principal != null) {
				userName = principal.getName();
			}

			History history = new History();
			history.setIdChange(courseId);
			history.setValueChanged("Active");
			history.setDateChange(dateformat.currentDate());
			history.setBy(userName);
			historyService.addHistory(history);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/all-courses");
		return modelAndView;
	}

}
