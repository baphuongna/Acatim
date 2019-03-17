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
	
	

}
