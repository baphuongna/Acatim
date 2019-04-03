package com.acatim.acatimver1.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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
	
	@NotEmpty(message = "*Tên Thể Loại không được để trống")
	private String categoryName;
	
	private String createDate;
	
	private String updateDate;
	
	private boolean active;
	
}
