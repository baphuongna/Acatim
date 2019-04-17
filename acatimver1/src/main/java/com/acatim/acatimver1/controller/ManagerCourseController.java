package com.acatim.acatimver1.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
@RequestMapping(value = { "/admin" })
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

	@RequestMapping(value = { "all-courses" }, method = RequestMethod.GET)
	public ModelAndView allCourses(@RequestParam(required = false, name = "page") String page,
			@RequestParam(required = false, name = "sortValue") String sortValue,
			@ModelAttribute("searchValue") SearchValue search) {

		ModelAndView modelAndView = new ModelAndView();

		if (page == null) {
			page = 1 + "";
		}

		if (search.getSubjectId() != null && search.getSubjectId().equals("0")) {
			search.setSubjectId(null);
		}

		if (search.getCategoryId() != null && search.getCategoryId().equals("0")) {
			search.setCategoryId(null);
		}

		try {
			int currentPage = Integer.parseInt(page);

			if (currentPage < 1) {
				currentPage = 1;
			}

			int total = courseService.getAllCourse().size();

			modelAndView.addObject("allCategories", categoriesService.getAllCategories());
			modelAndView.addObject("allSubjects", subjectService.getListSubject());
			
			Sort sort = null;

			if (sortValue != null) {
				search.setSortValue(sortValue);
			}

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

			total = courseService.getAllCourse(search).size();

			pageableService = new PageableServiceImpl(8, total, currentPage, sort);

			List<Course> Courses = courseService.getAllCoursePaging(pageableService, search);
			
			modelAndView.addObject("allCourses", Courses);

			modelAndView.addObject("totalPages", pageableService.listPage());
			modelAndView.addObject("currentPage", currentPage);
			modelAndView.addObject("hasPrevious", pageableService.hasPrevious());
			modelAndView.addObject("hasNext", pageableService.hasNext());
			modelAndView.addObject("previous", pageableService.previous());
			modelAndView.addObject("next", pageableService.next());
			modelAndView.addObject("last", pageableService.last());
			modelAndView.addObject("first", pageableService.first());
			modelAndView.addObject("searchValue", search);
		} catch (Exception e) {
			e.printStackTrace();
		}

		modelAndView.setViewName("admin/all-courses");
		return modelAndView;
	}

	@RequestMapping(value = { "all-courses" }, method = RequestMethod.POST)
	public ModelAndView searchCourses(@ModelAttribute("searchValue") SearchValue search,
			RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView();

		redirectAttributes.addFlashAttribute("searchValue", search);
		modelAndView.setViewName("redirect:/admin/all-courses");
		return modelAndView;
	}

	@RequestMapping(value = { "blockStudent" }, method = RequestMethod.GET)
	public ModelAndView blockStudent(@RequestParam("courseId") String courseId, Principal principal) {
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

	@RequestMapping(value = { "add-course" }, method = RequestMethod.GET)
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

	@RequestMapping(value = { "add-course" }, method = RequestMethod.POST)
	public ModelAndView addNewCourse(@Valid @ModelAttribute("course") Course course, BindingResult result) {
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

		if (course.getSubjectId().equals("0")) {
			modelAndView.addObject("errorMessage", "Chọn Môn học trước khi hoàn thành đăng ký thông tin khóa học!");
			modelAndView.setViewName("admin/add-course");
			return modelAndView;
		}

		if (course.getUserName().equals("0")) {
			modelAndView.addObject("errorUserName",
					"Chọn Người dùng có khóa học này trước khi hoàn thành đăng ký thông tin khóa học!");
			modelAndView.setViewName("admin/add-course");
			return modelAndView;
		}

		try {

			course.setStartDate(dateformat.StringToDateSQL(course.getStartDate()));
			course.setEndDate(dateformat.StringToDateSQL(course.getEndDate()));

			System.out.println(course);
			courseService.addCourse(course);

		} catch (Exception e) {
			modelAndView.setViewName("admin/add-course");
			return modelAndView;
		}

		modelAndView.setViewName("redirect:/admin/all-courses");
		return modelAndView;
	}

	@RequestMapping(value = { "edit-course" }, method = RequestMethod.GET)
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

	@RequestMapping(value = { "edit-course" }, method = RequestMethod.POST)
	public ModelAndView updateCourse(@Valid @ModelAttribute("course") Course course, BindingResult result,
			Principal principal) {
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

		if (course.getSubjectId().equals("0")) {
			modelAndView.addObject("errorMessage", "Chọn Môn học trước khi hoàn thành đăng ký thông tin khóa học!");
			modelAndView.addObject("course", course);
			modelAndView.setViewName("admin/edit-course");
			return modelAndView;
		}

		if (course.getUserName().equals("0")) {
			modelAndView.addObject("errorUserName",
					"Chọn Người dùng có khóa học này trước khi hoàn thành đăng ký thông tin khóa học!");
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

		} catch (Exception e) {
			e.printStackTrace();
		}

		modelAndView.setViewName("redirect:/admin/all-courses");
		return modelAndView;
	}

	@RequestMapping(value = { "course-info" }, method = RequestMethod.GET)
	public ModelAndView courseInfo() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/course-info");
		return modelAndView;
	}

}
