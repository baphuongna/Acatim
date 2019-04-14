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
public class RateTeacher {
	
	@Id
	private String rateId;
	
	
	private float easyLevel;
	
	
	private float examDifficulty;
	
	
	private float textbookUse;
	
	
	private float helpfulLevel;
	
	
	private float clarityLevel;
	
	
	private float knowledgeable;

	private String checkTeaNull;
}
