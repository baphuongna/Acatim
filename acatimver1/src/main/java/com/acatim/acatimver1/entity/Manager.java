package com.acatim.acatimver1.entity;

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
public class Manager {
	
	@Id
	private String userName;
	
	private String dob;
	
	private boolean gender;
	
	private String description;
}
