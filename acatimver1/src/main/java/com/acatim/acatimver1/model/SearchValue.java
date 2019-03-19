package com.acatim.acatimver1.model;

import lombok.Data;

@Data
public class SearchValue {
	private String value;
	
	private String search;

	public SearchValue() {
		super();
	}

	public SearchValue(String value, String search) {
		super();
		this.value = value;
		this.search = search;
	}
	
	
}
