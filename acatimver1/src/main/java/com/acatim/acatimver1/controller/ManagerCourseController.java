package com.acatim.acatimver1.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.entity.Course;
import com.acatim.acatimver1.entity.History;
import com.acatim.acatimver1.entity.SearchValue;
import com.acatim.acatimver1.format.DateFormat;
import com.acatim.acatimver1.service.CategoriesService;
import com.acatim.acatimver1.service.CourseService;
import com.acatim.acatimver1.service.HistoryService;
import com.acatim.acatimver1.service.PageableService;
import com.acatim.acatimver1.service.PageableServiceImpl;
import com.acatim.acatimver1.service.SubjectService;
import com.acatim.acatimver1.service.UserInfoService;


@Controller
@RequestMapping(value = {"/admin"})
public class ManagerCourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private HistoryService historyService;
	
	private PageableService pageableService;
	
	private DateFormat dateformat = new DateFormat();

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
			
			int total = subjectService.getAllSubject().size();
			
			pageableService = new PageableServiceImpl(8, total, currentPage, null);
			
			
			
			
			modelAndView.addObject("allCategories", categoriesService.getAllCategories());
			if(!categoryId.equals("0") && subjectId.equals("0")) {
				modelAndView.addObject("allCourses", courseService.getAllCourseByCateIdPaging(pageableService, categoryId));
				modelAndView.addObject("allSubjects", subjectService.getSubjectByCategoryId(categoryId));
			}else if(!subjectId.equals("0")) {
				modelAndView.addObject("allCourses", courseService.getAllCourseBySujectIdPaging(pageableService, subjectId));
				modelAndView.addObject("allSubjects", subjectService.getListSubject());
			}else{
				modelAndView.addObject("allSubjects", subjectService.getListSubject());
				modelAndView.addObject("allCourses", courseService.getAllCoursePaging(pageableService));
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
			
			int total = 0;
			
			modelAndView.addObject("allCategories", categoriesService.getAllCategories());
			
			if (search.getSearch().trim().length() == 0) {
				total = subjectService.getAllSubject().size();
								
				if(!search.getCategoryId().equals("0") && search.getSubjectId().equals("0")) {
					modelAndView.addObject("allCourses", courseService.getAllCourseByCateIdPaging(pageableService, search.getCategoryId()));
					modelAndView.addObject("allSubjects", subjectService.getSubjectByCategoryId(search.getCategoryId()));
				}else if(!search.getSubjectId().equals("0")) {
					modelAndView.addObject("allCourses", courseService.getAllCourseBySujectIdPaging(pageableService, search.getSubjectId()));
					modelAndView.addObject("allSubjects", subjectService.getListSubject());
				}else{
					modelAndView.addObject("allSubjects", subjectService.getListSubject());
					modelAndView.addObject("allCourses", courseService.getAllCoursePaging(pageableService));
				}
				
			}else {
				if(!search.getCategoryId().equals("0") && search.getSubjectId().equals("0")) {
					modelAndView.addObject("allCourses", courseService.searchAllCourseByCateIdPaging(pageableService, search.getCategoryId(), search.getSearch()));
					modelAndView.addObject("allSubjects", subjectService.getSubjectByCategoryId(search.getCategoryId()));
				}else if(!search.getSubjectId().equals("0")) {
					modelAndView.addObject("allCourses", courseService.searchAllCourseBySujectIdPaging(pageableService, search.getSubjectId(), search.getSearch()));
					modelAndView.addObject("allSubjects", subjectService.getListSubject());
				}else{
					modelAndView.addObject("allSubjects", subjectService.getListSubject());
					modelAndView.addObject("allCourses", courseService.searchAllCoursePaging(pageableService, search.getSearch()));
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
			System.out.println(search);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		modelAndView.setViewName("admin/all-courses");
		return modelAndView;
	}
	
	@RequestMapping(value = { "blockStudent" }, method = RequestMethod.GET)
	public ModelAndView blockStudent(@RequestParam("courseId") String courseId,Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			courseService.removeCourse(courseId);
			
			User loginedUser = null;
			if (principal != null) {
				loginedUser = (User) ((Authentication) principal).getPrincipal();
			}
			
			History history = new History();
			history.setIdChange(courseId);
			history.setValueChanged("Blocked");
			history.setDateChange(dateformat.currentDate());
			history.setBy(loginedUser.getUsername());
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
			
			User loginedUser = null;
			if (principal != null) {
				loginedUser = (User) ((Authentication) principal).getPrincipal();
			}

			History history = new History();
			history.setIdChange(courseId);
			history.setValueChanged("Active");
			history.setDateChange(dateformat.currentDate());
			history.setBy(loginedUser.getUsername());
			historyService.addHistory(history);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/all-courses");
		return modelAndView;
	}

	@RequestMapping(value = {"add-course"}, method = RequestMethod.GET)
	public ModelAndView addCourse() {
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("allTeacherSC", userInfoService.getAllTeacherST());
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.addObject("allSubjects", subjectService.getListSubject());
		modelAndView.addObject("course", new Course());
		modelAndView.setViewName("admin/add-course");
		return modelAndView;
	}
	
	@RequestMapping(value = {"add-course"}, method = RequestMethod.POST)
	public ModelAndView addNewCourse(@Valid @ModelAttribute("course") Course course,
			BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		
		String courseId = courseService.genCourseId();
		course.setCourseId(courseId);
		course.setCreateDate(dateformat.currentDate());
		
		modelAndView.addObject("allSubjects", subjectService.getListSubject());
		try {
			modelAndView.addObject("allTeacherSC", userInfoService.getAllTeacherST());
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.addObject("course", course);
		
		if (result.hasErrors()) {
			modelAndView.setViewName("admin/add-course");
			return modelAndView;
		}
		
		if(course.getSubjectId().equals("0")) {
			modelAndView.addObject("errorMessage", "Chọn Môn học trước khi hoàn thành đăng ký thông tin khóa học!");
			modelAndView.setViewName("admin/add-course");
			return modelAndView;
		}
		
		if(course.getUserName().equals("0")) {
			modelAndView.addObject("errorUserName", "Chọn Người dùng có khóa học này trước khi hoàn thành đăng ký thông tin khóa học!");
			modelAndView.setViewName("admin/add-course");
			return modelAndView;
		}
		
		
		try {
			
			course.setStartDate(dateformat.StringToDateSQL(course.getStartDate()));
			course.setEndDate(dateformat.StringToDateSQL(course.getEndDate()));
			
			
			System.out.println(course);
			courseService.addCourse(course);

		}catch (Exception e) {
			modelAndView.addObject("allSubjects", subjectService.getListSubject());
			modelAndView.addObject("course", course);
			modelAndView.setViewName("admin/add-course");
			return modelAndView;
		}
		
		modelAndView.setViewName("redirect:/admin/all-courses");
		return modelAndView;
	}

	@RequestMapping(value = {"edit-course"}, method = RequestMethod.GET)
	public ModelAndView editCourse(@RequestParam("courseId") String courseId) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("allTeacherSC", userInfoService.getAllTeacherST());
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.addObject("course", courseService.getCourseById(courseId));
		modelAndView.addObject("allSubjects", subjectService.getListSubject());
		
		modelAndView.setViewName("admin/edit-course");
		return modelAndView;
	}
	
	@RequestMapping(value = {"edit-course"}, method = RequestMethod.POST)
	public ModelAndView updateCourse(@Valid @ModelAttribute("course") Course course,
			BindingResult result, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		
		System.out.println(course);
		
		try {
			modelAndView.addObject("allTeacherSC", userInfoService.getAllTeacherST());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (result.hasErrors()) {
			modelAndView.setViewName("admin/edit-course");
			modelAndView.addObject("course", course);
			return modelAndView;
		}
		
		if(course.getSubjectId().equals("0")) {
			modelAndView.addObject("errorMessage", "Chọn Môn học trước khi hoàn thành đăng ký thông tin khóa học!");
			modelAndView.addObject("course", course);
			modelAndView.setViewName("admin/edit-course");
			return modelAndView;
		}
		
		if(course.getUserName().equals("0")) {
			modelAndView.addObject("errorUserName", "Chọn Người dùng có khóa học này trước khi hoàn thành đăng ký thông tin khóa học!");
			modelAndView.addObject("course", course);
			modelAndView.setViewName("admin/edit-course");
			return modelAndView;
		}
		
		try {
			course.setUpdateDate(dateformat.currentDate());
			System.out.println(course);
			courseService.updateCourse(course);
			
			User loginedUser = null;
			if (principal != null) {
				loginedUser = (User) ((Authentication) principal).getPrincipal();
			}
			
			History history = new History();
			history.setIdChange(course.getCourseId());
			history.setValueChanged("Infomation");
			history.setDateChange(dateformat.currentDate());
			history.setBy(loginedUser.getUsername());
			historyService.addHistory(history);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		modelAndView.setViewName("redirect:/admin/all-courses");
		return modelAndView;
	}

	@RequestMapping(value = {"course-info"}, method = RequestMethod.GET)
	public ModelAndView courseInfo() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/course-info");
		return modelAndView;
	}

}
