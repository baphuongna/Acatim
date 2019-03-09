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
public class DiscountCode {
	
	@Id
	private String codeId;
	
	private String userName;
	
	private String courseId;
	
	private String createDate;
	
	private String expireDate;
	
	private String status;
}
