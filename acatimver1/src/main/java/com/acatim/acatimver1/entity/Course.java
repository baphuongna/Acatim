package com.acatim.acatimver1.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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
	
	@NotEmpty(message = "*Tên khóa học không được để trống")
	private String courseName;
	
	@NotEmpty(message = "*Thông Tin khóa học không được để trống")
	private String courseDescription;
	
	@NotEmpty(message = "*Thời Gian bắt đầu khóa học không được để trống")
	private String startTime;
	
	@NotEmpty(message = "*Thời Gian kết thúc khóa học không được để trống")
	private String endTime;
	
	@NotEmpty(message = "*Ngày bắt đầu khóa học không được để trống")
	private String startDate;
	
	@NotEmpty(message = "*ngày kết thúc khóa học không được để trống")
	private String endDate;
	
	private float price;
	
	private String createDate;
	
	private String updateDate;
	
	private boolean active;
	
	@ManyToOne
	@JoinColumn
	private Subject subject;
	
	@ManyToOne
	@JoinColumn
	private UserModel userModel;

	public Course() {
		super();
	}

	public Course(String courseId, String subjectId, String userName, String courseName, String courseDescription,
			String startTime, String endTime, String startDate, String endDate, float price, String createDate,
			String updateDate, boolean active) {
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
		this.active = active;
	}
}
