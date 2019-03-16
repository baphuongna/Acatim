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
public class StudyCenter{
	
	@Id
	private String userName;
	
	private String description;
	
	private float rate;
	
	@ManyToOne
	@JoinColumn
	private UserModel user;

	public StudyCenter(String userName, String description, float rate) {
		super();
		this.userName = userName;
		this.description = description;
		this.rate = rate;
	}
	
	

}
