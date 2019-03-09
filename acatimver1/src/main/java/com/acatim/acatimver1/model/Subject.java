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
public class Subject {
	
	@Id
	private String subjectId;
	
	private int categoryId;
	
	private String subjectName;
	
	private String createDate;
	
	private String updateDate;
}
