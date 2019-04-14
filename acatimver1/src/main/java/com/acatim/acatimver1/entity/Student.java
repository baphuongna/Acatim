package com.acatim.acatimver1.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student{

	@Id
	private String userName;
	
	private String dob;
	
	private boolean gender;
	
}
