package com.acatim.acatimver1.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categories {
	
	@Id
	private int categoryId;
	
	@Length(min = 5, message = "*Tên Thể Loại có độ dài tối thiểu là 5 ký tự")
	@NotEmpty(message = "*Tên Thể Loại không được để trống")
	private String categoryName;
	
	private String createDate;
	
	private String updateDate;
	
	private boolean active;
	
}
