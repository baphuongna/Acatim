package com.acatim.acatimver1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;

public class PageableServiceImpl implements PageableService{

	private int limit;
	private int total;
	private int currentPage;
	private Sort sort;
	
	public PageableServiceImpl(int limit, int total, int currentPage, Sort sort) {
		this.limit = limit;
		this.total = total;
		this.currentPage = currentPage;
		this.sort = sort;
	}


	public int getNumberPager() {
		int totalPage;
		if(total%getPageSize() !=0) {
			totalPage = total/getPageSize()+1;
		}else {
			totalPage = total/getPageSize();
		}
		
		return totalPage;
	}
	
	public List<Integer> listPage(){
		List<Integer> totalPages = new ArrayList<>();
		for(int i = 1; i<= getNumberPager(); i++) {
			totalPages.add(i);
		}
		return totalPages;
	}
	
	public int getPageSize() {
		return limit;
	}

	
	public int getOffset() {
		return (currentPage*limit)-limit;
	}
	
	public int next() {
		return currentPage + 1;
	}

	
	public int previous() {
		return  currentPage - 1;
	}

	
	public int first() {
		return 1;
	}
	
	public int last() {
		return getNumberPager();
	}

	
	public boolean hasPrevious() {
		if(currentPage > 1) {
			return true;
		}
		return false;
	}
	
	public boolean hasNext() {
		if(currentPage < getNumberPager()) {
			return true;
		}
		return false;
	}


	@Override
	public Sort sort() {
		return this.sort;
	}
	
}
