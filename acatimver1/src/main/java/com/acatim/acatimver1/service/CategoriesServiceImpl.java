package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.CategoriesDAO;
import com.acatim.acatimver1.entity.Categories;

@Service
public class CategoriesServiceImpl implements CategoriesService{
	
	@Autowired
	private CategoriesDAO categoriesDAO;
	
	public List<Categories> getAllCategories(){
		return this.categoriesDAO.getAllCategories();
	}
	
	public Categories getCategoriesByCategoryId(String categoryId) {
		return this.categoriesDAO.getCategoriesByCategoryId(categoryId);
	}
	
	public boolean addCategories(Categories categories) {
		return this.categoriesDAO.addCategories(categories);
	}
	
	public boolean updateCategories(Categories categories) {
		return this.categoriesDAO.updateCategories(categories);
	}
	
	public boolean removeCategories(String categoryId) {
		boolean active = false;
		return this.categoriesDAO.removeCategories(categoryId, active);
	}
	
	public boolean unlockCategories(String categoryId) {
		boolean active = true;
		return this.categoriesDAO.removeCategories(categoryId, active);
	}
	
}
