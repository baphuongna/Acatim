package com.acatim.acatimver1.service;

import java.util.List;

import com.acatim.acatimver1.entity.Categories;

public interface CategoriesService {
	
	List<Categories> getAllCategories();
	
	Categories getCategoriesByCategoryId(String categoryId);
	
	boolean addCategories(Categories categories);
	
	boolean updateCategories(Categories categories);
	
	boolean removeCategories(String categoryId);
	
	boolean unlockCategories(String categoryId);
}
