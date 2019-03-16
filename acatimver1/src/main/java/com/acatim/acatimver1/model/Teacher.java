package com.acatim.acatimver1.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher{

	@Id
	private String userName;
	
	private String dob;
	
	private boolean gender;
	
	private String description;
	
	private float rate;
	
	@ManyToOne
	@JoinColumn
	private UserModel user;

	public Teacher(String userName, String dob, boolean gender, String description, float rate) {
		super();
		this.userName = userName;
		this.dob = dob;
		this.gender = gender;
		this.description = description;
		this.rate = rate;
	}
	
	

}
