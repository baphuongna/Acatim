package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.data.domain.Sort;

public interface PageableService {
	
	int getNumberPager();
	
	List<Integer> listPage();
	
	int getPageSize();

	int getOffset();
	
	int next();

	int previous();
	
	int first();
	
	int last();

	boolean hasPrevious();
	
	boolean hasNext();
	
	Sort sort();
}
