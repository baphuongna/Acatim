package com.acatim.acatimver1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/admin"})
public class AdminController {
	
	@RequestMapping(value = { "/", "/index"}, method = RequestMethod.GET)
	public ModelAndView indexAdmin() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/index");
		return modelAndView;
	}
	
	@RequestMapping(value = {"all-students"}, method = RequestMethod.GET)
	public ModelAndView allStudents() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/all-students");
		return modelAndView;
	}
	
	@RequestMapping(value = {"add-student"}, method = RequestMethod.GET)
	public ModelAndView addStudent() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/add-student");
		return modelAndView;
	}

	@RequestMapping(value = {"edit-student"}, method = RequestMethod.GET)
	public ModelAndView editStudent() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/edit-student");
		return modelAndView;
	}

	@RequestMapping(value = {"student-profile"}, method = RequestMethod.GET)
	public ModelAndView studentProfile() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/student-profile");
		return modelAndView;
	}

	@RequestMapping(value = {"all-teachers"}, method = RequestMethod.GET)
	public ModelAndView allTeachers() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/all-teachers");
		return modelAndView;
	}

	@RequestMapping(value = {"add-teacher"}, method = RequestMethod.GET)
	public ModelAndView addTeacher() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/add-teacher");
		return modelAndView;
	}

	@RequestMapping(value = {"edit-teacher"}, method = RequestMethod.GET)
	public ModelAndView editTeacher() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/edit-teacher");
		return modelAndView;
	}

	@RequestMapping(value = {"teacher-profile"}, method = RequestMethod.GET)
	public ModelAndView teacherProfile() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/teacher-profile");
		return modelAndView;
	}

	@RequestMapping(value = {"all-study-centers"}, method = RequestMethod.GET)
	public ModelAndView allStudyCenters() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/all-study-centers");
		return modelAndView;
	}

	@RequestMapping(value = {"add-study-centers"}, method = RequestMethod.GET)
	public ModelAndView addStudyCenters() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/add-study-centers");
		return modelAndView;
	}

	@RequestMapping(value = {"edit-study-centers"}, method = RequestMethod.GET)
	public ModelAndView editStudyCenters() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/edit-study-centers");
		return modelAndView;
	}

	@RequestMapping(value = {"study-centers-profile"}, method = RequestMethod.GET)
	public ModelAndView studyCentersProfile() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/study-centers-profile");
		return modelAndView;
	}

	@RequestMapping(value = {"all-courses"}, method = RequestMethod.GET)
	public ModelAndView allCourses() {
		ModelAndView modelAndView = new ModelAndView();
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

	@RequestMapping(value = {"all-subjects"}, method = RequestMethod.GET)
	public ModelAndView allSubjects() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/all-subjects");
		return modelAndView;
	}

	@RequestMapping(value = {"add-subject"}, method = RequestMethod.GET)
	public ModelAndView addSubject() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/add-subject");
		return modelAndView;
	}

	@RequestMapping(value = {"edit-subject"}, method = RequestMethod.GET)
	public ModelAndView editSubject() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/edit-subject");
		return modelAndView;
	}

	@RequestMapping(value = {"subject-info"}, method = RequestMethod.GET)
	public ModelAndView subjectInfo() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/subject-info");
		return modelAndView;
	}

	@RequestMapping(value = {"all-categories"}, method = RequestMethod.GET)
	public ModelAndView allCategories() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/all-categories");
		return modelAndView;
	}

	@RequestMapping(value = {"add-category"}, method = RequestMethod.GET)
	public ModelAndView addCategory() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/add-category");
		return modelAndView;
	}

	@RequestMapping(value = {"edit-category"}, method = RequestMethod.GET)
	public ModelAndView editCategory() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/edit-category");
		return modelAndView;
	}

	@RequestMapping(value = {"category-info"}, method = RequestMethod.GET)
	public ModelAndView categoryInfo() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/category-info");
		return modelAndView;
	}

	@RequestMapping(value = {"library-assets"}, method = RequestMethod.GET)
	public ModelAndView libraryAssets() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/library-assets");
		return modelAndView;
	}

	@RequestMapping(value = {"add-library-assets"}, method = RequestMethod.GET)
	public ModelAndView addLibraryAssets() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/add-library-assets");
		return modelAndView;
	}

	@RequestMapping(value = {"edit-library-assets"}, method = RequestMethod.GET)
	public ModelAndView editLibraryAssets() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/edit-library-assets");
		return modelAndView;
	}

	@RequestMapping(value = {"departments"}, method = RequestMethod.GET)
	public ModelAndView departments() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/departments");
		return modelAndView;
	}

	@RequestMapping(value = {"add-department"}, method = RequestMethod.GET)
	public ModelAndView addDepartment() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/add-department");
		return modelAndView;
	}

	@RequestMapping(value = {"edit-department"}, method = RequestMethod.GET)
	public ModelAndView editDepartment() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/edit-department");
		return modelAndView;
	}

	@RequestMapping(value = {"data-table"}, method = RequestMethod.GET)
	public ModelAndView dataTable() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/data-table");
		return modelAndView;
	}

	@RequestMapping(value = {"basic-form-element"}, method = RequestMethod.GET)
	public ModelAndView basicFormElement() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/basic-form-element");
		return modelAndView;
	}

	@RequestMapping(value = {"advance-form-element"}, method = RequestMethod.GET)
	public ModelAndView advanceFormElement() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/advance-form-element");
		return modelAndView;
	}

	@RequestMapping(value = {"password-meter"}, method = RequestMethod.GET)
	public ModelAndView passwordMeter() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/password-meter");
		return modelAndView;
	}

	@RequestMapping(value = {"multi-upload"}, method = RequestMethod.GET)
	public ModelAndView multiUpload() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/multi-upload");
		return modelAndView;
	}

	@RequestMapping(value = {"tinymc"}, method = RequestMethod.GET)
	public ModelAndView textEditer() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/tinymc");
		return modelAndView;
	}

	@RequestMapping(value = {"dual-list-box"}, method = RequestMethod.GET)
	public ModelAndView duaListBox() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/dual-list-box");
		return modelAndView;
	}

	@RequestMapping(value = {"notifications"}, method = RequestMethod.GET)
	public ModelAndView notifications() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/notifications");
		return modelAndView;
	}

	@RequestMapping(value = {"alerts"}, method = RequestMethod.GET)
	public ModelAndView alerts() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/alerts");
		return modelAndView;
	}

	@RequestMapping(value = {"modals"}, method = RequestMethod.GET)
	public ModelAndView modals() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/modals");
		return modelAndView;
	}

	@RequestMapping(value = {"buttons"}, method = RequestMethod.GET)
	public ModelAndView buttons() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/buttons");
		return modelAndView;
	}

	@RequestMapping(value = {"tabs"}, method = RequestMethod.GET)
	public ModelAndView tabs() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/tabs");
		return modelAndView;
	}

	@RequestMapping(value = {"accordion"}, method = RequestMethod.GET)
	public ModelAndView accordion() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/accordion");
		return modelAndView;
	}

	@RequestMapping(value = {"login"}, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/login");
		return modelAndView;
	}

	@RequestMapping(value = {"register"}, method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/register");
		return modelAndView;
	}

	@RequestMapping(value = {"password-recovery"}, method = RequestMethod.GET)
	public ModelAndView passwordRecovery() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/password-recovery");
		return modelAndView;
	}

	@RequestMapping(value = {"404"}, method = RequestMethod.GET)
	public ModelAndView error404() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/404");
		return modelAndView;
	}

	@RequestMapping(value = {"500"}, method = RequestMethod.GET)
	public ModelAndView error500() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/500");
		return modelAndView;
	}
}
