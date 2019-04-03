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
public class Status {
	@Id
	private int id;
	
	private String idChange;
	
	private String valueChanged;
	
	private String by;
	
	private String dateChange;
}
