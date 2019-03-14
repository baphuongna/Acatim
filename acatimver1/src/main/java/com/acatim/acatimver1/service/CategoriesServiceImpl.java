package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.CategoriesDAO;
import com.acatim.acatimver1.model.Categories;

@Service
public class CategoriesServiceImpl {
	
	@Autowired
	private CategoriesDAO categoriesDAO;
	
	public List<Categories> getAllCategories(){
		return this.categoriesDAO.getAllCategories();
	}
	
	public Categories getCategoriesBySubjectId(String categoryId) {
		return this.categoriesDAO.getCategoriesBySubjectId(categoryId);
	}
	
	public void addCategories(Categories categories) {
		this.categoriesDAO.addCategories(categories);
	}
	
	public void updateCategories(Categories categories) {
		this.categoriesDAO.updateCategories(categories);
	}
	
	public void removeCategories(Categories categoryId) {
		boolean active = false;
		this.categoriesDAO.removeCategories(categoryId, active);
	}
}
