package com.acatim.acatimver1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.service.CategoriesService;
import com.acatim.acatimver1.service.CourseService;
import com.acatim.acatimver1.service.SubjectService;
import com.acatim.acatimver1.service.UserInfoService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = {"/admin"})
public class AdminController {
	
	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private CategoriesService categoriesService;
	
	@RequestMapping(value = { "/", "/index"}, method = RequestMethod.GET)
	public ModelAndView indexAdmin() {
		ModelAndView modelAndView = new ModelAndView();
		
		
		
		try {
			modelAndView.addObject("allStudent", userInfoService.loadAllUserStudent().size());
			modelAndView.addObject("allStudyCenter", userInfoService.loadAllUserStudyCenter().size());
			modelAndView.addObject("allTeacher", userInfoService.loadAllUserTeacher().size());
			modelAndView.addObject("allCourse", courseService.getAllCourse().size());
			modelAndView.addObject("allSubject", subjectService.getListSubject().size());
			modelAndView.addObject("allCategories", categoriesService.getAllCategories().size());

			
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		modelAndView.setViewName("admin/index");
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
