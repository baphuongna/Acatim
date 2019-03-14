package com.acatim.acatimver1.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;

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

	private String subjectName;

	private String createDate;

	private String updateDate;

	private boolean active;

	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
	private List<Course> courses;

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

}
