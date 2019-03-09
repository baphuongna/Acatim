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
public class Course {
	
	@Id
	private String courseId;
	
	private String subjectId;
	
	private String userName;
	
	private String courseName;
	
	private String courseDescription;
	
	private String startTime;
	
	private String endTime;
	
	private String startDate;
	
	private String endDate;
	
	private float price;
	
	private String createDate;
	
	private String updateDate;
}
