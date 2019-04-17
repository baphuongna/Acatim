package com.acatim.acatimver1.entity;

import lombok.Data;

@Data
public class SearchValue {
	private String value;
	
	private String roleId;
	
	private String categoryId;
	
	private String subjectId;
	
	private String search;
	
	private String userName;
	
	private String sortValue;

	public SearchValue() {
		super();
	}

	public SearchValue(String value, String roleId, String search) {
		super();
		this.value = value;
		this.roleId = roleId;
		this.search = search;
	}

	public SearchValue(String value, String roleId, String categoryId, String subjectId, String search) {
		super();
		this.value = value;
		this.roleId = roleId;
		this.categoryId = categoryId;
		this.subjectId = subjectId;
		this.search = search;
	}
	
	public SearchValue(String value, String search) {
		super();
		this.value = value;
		this.search = search;
	}
	
	
	
}
