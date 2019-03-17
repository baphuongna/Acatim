package com.acatim.acatimver1.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contact {
	@Id
	private String userName;
	
	private String email;
	
	private String title;
	
	private String message;
	
	private String createDate;
	
	boolean isActive;
	
	 
}
