package com.acatim.acatimver1.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acatim.acatimver1.model.Categories;
import com.acatim.acatimver1.model.SearchValue;
import com.acatim.acatimver1.model.Subject;
import com.acatim.acatimver1.service.CategoriesServiceImpl;
import com.acatim.acatimver1.service.PageableService;
import com.acatim.acatimver1.service.SubjectServiceImpl;

import javassist.NotFoundException;


@Controller
@RequestMapping(value = {"/admin"})
public class ManagerCategoryController {

	@Autowired
	private SubjectServiceImpl subjectService;
	
	@Autowired
	private CategoriesServiceImpl categoriesService;
	
	private PageableService pageableService;
	
	@RequestMapping(value = {"all-subjects"}, method = RequestMethod.GET)
	public ModelAndView allSubjects(@RequestParam(required = false, name = "categoryId") String categoryId,
			@RequestParam(required = false, name = "page") String page) {
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

			int total = subjectService.getListSubject().size();

			pageableService = new PageableService(8, currentPage - 1, total, currentPage);

			modelAndView.addObject("totalPages", pageableService.listPage());
			modelAndView.addObject("currentPage", currentPage);
			modelAndView.addObject("hasPrevious", pageableService.hasPrevious());
			modelAndView.addObject("hasNext", pageableService.hasNext());
			modelAndView.addObject("previous", pageableService.previous());
			modelAndView.addObject("next", pageableService.next());
			modelAndView.addObject("last", pageableService.last());
			modelAndView.addObject("first", pageableService.first());
			List<Subject> allSubjects = null;
			
			if(categoryId == null) {
				allSubjects = subjectService.getListSubjectPageable(pageable);
				categoryId = "0";
			}else {
				allSubjects = subjectService.getSubjectByCategoryIdPageable(pageable, categoryId);
			}
			
			modelAndView.addObject("allSubjects", allSubjects);
			
			modelAndView.addObject("allCategories", categoriesService.getAllCategories());
			
			SearchValue searchValue = new SearchValue();
			searchValue.setCategoryId(categoryId);
			modelAndView.addObject("searchValue", searchValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		modelAndView.setViewName("admin/all-subjects");
		return modelAndView;
	}

	@RequestMapping(value = {"add-subject"}, method = RequestMethod.GET)
	public ModelAndView addSubject() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allCategories", categoriesService.getAllCategories());
		modelAndView.addObject("subject", new Subject());
		modelAndView.setViewName("admin/add-subject");
		return modelAndView;
	}
	
	@RequestMapping(value = {"add-subject"}, method = RequestMethod.POST)
	public ModelAndView addNewSubject(@Valid @ModelAttribute("subject") Subject subject, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("allCategories", categoriesService.getAllCategories());
		modelAndView.addObject("subject", subject);
		
		if (subject.getCategoryId() == 0) {
			modelAndView.addObject("errorMessage", "Chọn Môn học Thuộc Thể Loại Nào trước khi hoàn thành đăng ký thông tin khóa học!");
			modelAndView.setViewName("admin/add-subject");
			return modelAndView;
		}

		if (result.hasErrors()) {
			modelAndView.setViewName("admin/add-subject");
			return modelAndView;
		}
		
		String subjectId = subjectService.genSubjectId();
		
		Date date = new Date();
		long time = date.getTime();
		Timestamp currentDate = new Timestamp(time);
		
		subject.setSubjectId(subjectId);
		subject.setCreateDate(currentDate+"");
		subject.setActive(true);
		
		try {
			subjectService.addSubject(subject);
			System.out.println("Success");
		} catch (Exception e) {
			modelAndView.setViewName("admin/add-subject");
			return modelAndView;
		}
		
		modelAndView.setViewName("redirect:/admin/all-subjects");
		return modelAndView;
	}

	@RequestMapping(value = { "blockSubject" }, method = RequestMethod.GET)
	public ModelAndView blockSubject(@RequestParam("subjectId") String subjectId) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			subjectService.removeSubject(subjectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/all-subjects");
		return modelAndView;
	}

	@RequestMapping(value = { "unlockSubject" }, method = RequestMethod.GET)
	public ModelAndView unlockSubject(@RequestParam("subjectId") String subjectId) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			subjectService.unlockSubject(subjectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/all-subjects");
		return modelAndView;
	}
	
	@RequestMapping(value = {"edit-subject"}, method = RequestMethod.GET)
	public ModelAndView editSubject(@RequestParam("subjectId") String subjectId) {
		ModelAndView modelAndView = new ModelAndView();
		
		Subject subject = subjectService.getSubjectBySubjectId(subjectId);
		
		modelAndView.addObject("subject", subject);
		List<Categories> allCategories = categoriesService.getAllCategories();
		modelAndView.addObject("allCategories", allCategories);
		modelAndView.setViewName("admin/edit-subject");
		return modelAndView;
	}
	
	
	@RequestMapping(value = {"edit-subject"}, method = RequestMethod.POST)
	public ModelAndView updateSubjecy(@Valid @ModelAttribute("subject") Subject subject) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(subject);
		
		Date date = new Date();
		long time = date.getTime();
		Timestamp currentDate = new Timestamp(time);
		
		subject.setUpdateDate(currentDate+"");
		
		subjectService.updateSubject(subject);
		
		System.out.println(subject);
		modelAndView.setViewName("redirect:/admin/all-subjects");
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
		List<Categories> allCategories = categoriesService.getAllCategories();
		modelAndView.addObject("allCategories", allCategories);
		modelAndView.setViewName("admin/all-categories");
		return modelAndView;
	}

	@RequestMapping(value = {"add-category"}, method = RequestMethod.GET)
	public ModelAndView addCategory() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("category", new Categories());
		modelAndView.setViewName("admin/add-category");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/add-category" }, method = RequestMethod.POST)
	public ModelAndView addNewCategory(@Valid @ModelAttribute("category") Categories category,
			BindingResult result, final RedirectAttributes redirectAttributes) throws NotFoundException {

		ModelAndView modelAndView = new ModelAndView();
		System.out.println(category);

		Date date = new Date();
		long time = date.getTime();
		Timestamp currentDate = new Timestamp(time);
		
		category.setCreateDate(currentDate+"");
		category.setActive(true);
		try {
			categoriesService.addCategories(category);
			System.out.println("Success");
		} catch (Exception e) {
			modelAndView.addObject("category", new Categories());
			modelAndView.setViewName("admin/add-category");
			return modelAndView;
		}

		modelAndView.setViewName("redirect:/admin/all-categories");
		return modelAndView;
	}
	
	@RequestMapping(value = { "blockCategory" }, method = RequestMethod.GET)
	public ModelAndView blockCategory(@RequestParam("categoryId") String categoryId) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			categoriesService.removeCategories(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/all-categories");
		return modelAndView;
	}

	@RequestMapping(value = { "unlockCategory" }, method = RequestMethod.GET)
	public ModelAndView unlockCategory(@RequestParam("categoryId") String categoryId) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			categoriesService.unlockCategories(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/all-categories");
		return modelAndView;
	}

	@RequestMapping(value = {"edit-category"}, method = RequestMethod.GET)
	public ModelAndView editCategory(@RequestParam("categoryId") String categoryId) {
		ModelAndView modelAndView = new ModelAndView();
		
		Categories category = categoriesService.getCategoriesByCategoryId(categoryId);
		
		modelAndView.addObject("category", category);
		
		modelAndView.setViewName("admin/edit-category");
		return modelAndView;
	}
	
	@RequestMapping(value = {"edit-category"}, method = RequestMethod.POST)
	public ModelAndView updateCategory(@Valid @ModelAttribute("category") Categories category) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(category);
		
		Date date = new Date();
		long time = date.getTime();
		Timestamp currentDate = new Timestamp(time);
		
		category.setUpdateDate(currentDate+"");
		
		categoriesService.updateCategories(category);
		
		modelAndView.setViewName("redirect:/admin/all-categories");
		return modelAndView;
	}

	@RequestMapping(value = {"category-info"}, method = RequestMethod.GET)
	public ModelAndView categoryInfo() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/category-info");
		return modelAndView;
	}
}
