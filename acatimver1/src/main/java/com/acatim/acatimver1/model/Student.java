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
public class Student {
	
	@Id
	private String userName;
	
	private String dob;
	
	private boolean gender;
}
