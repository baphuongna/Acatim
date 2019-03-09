package com.acatim.acatimver1.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {
	
	@Id
	private String userName;
	
	private String date;
	
	private boolean gender;
	
	private String description;
	
	private float rate;
}
