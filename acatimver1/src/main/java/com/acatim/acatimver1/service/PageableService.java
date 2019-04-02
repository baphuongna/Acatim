package com.acatim.acatimver1.service;

import java.util.List;

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
}
