package com.acatim.acatimver1.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
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
	
	@ManyToOne
	@JoinColumn
	private Subject subject;

	public Course(String courseId, String subjectId, String userName, String courseName, String courseDescription,
			String startTime, String endTime, String startDate, String endDate, float price, String createDate,
			String updateDate) {
		super();
		this.courseId = courseId;
		this.subjectId = subjectId;
		this.userName = userName;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public Course() {
		super();
	}
	
	
	
}
