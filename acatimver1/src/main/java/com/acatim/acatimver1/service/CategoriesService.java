package com.acatim.acatimver1.service;

import java.util.List;

import com.acatim.acatimver1.model.Categories;

public interface CategoriesService {
	
	List<Categories> getAllCategories();
	
	Categories getCategoriesByCategoryId(String categoryId);
	
	void addCategories(Categories categories);
	
	void updateCategories(Categories categories);
	
	void removeCategories(String categoryId);
	
	void unlockCategories(String categoryId);
}
