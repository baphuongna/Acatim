package com.acatim.acatimver1.model;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(exclude = "courses")
@Entity
public class Subject {

	@Id
	private String subjectId;

	private int categoryId;
	
	@NotEmpty(message = "*Tên Môn Học không được để trống")
	private String subjectName;

	private String createDate;

	private String updateDate;

	private boolean active;

	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
	private List<Course> courses;
	
	@ManyToOne
    @JoinColumn
    private Categories category;

//	public Subject(String subjectId, int categoryId, String subjectName, String createDate, String updateDate,
//			Course courses) {
//		super();
//		this.subjectId = subjectId;
//		this.categoryId = categoryId;
//		this.subjectName = subjectName;
//		this.createDate = createDate;
//		this.updateDate = updateDate;
//		this.courses = Stream.of(courses).collect(Collectors.toSet());
//		this.courses.forEach(x -> x.setSubject(this));
//	}

	public Subject() {
		super();
	}

	public Subject(String subjectId, int categoryId, String subjectName, String createDate, String updateDate,
			boolean active) {
		super();
		this.subjectId = subjectId;
		this.categoryId = categoryId;
		this.subjectName = subjectName;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.active = active;
	}

	public Subject(String subjectId, int categoryId, String subjectName, String createDate, String updateDate,
			boolean active, List<Course> courses) {
		super();
		this.subjectId = subjectId;
		this.categoryId = categoryId;
		this.subjectName = subjectName;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.active = active;
		this.courses = courses;
	}
	
	

}
