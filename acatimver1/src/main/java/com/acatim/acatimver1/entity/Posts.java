package com.acatim.acatimver1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Posts {
	@Id
	private int id;
	
	private String userName;
	
	private String title;
	
	private String linkVideo;
	
	private String description;
	
	private String teacher;
	
	private String subject;
	
	private String createDate;
	
	private String updateDate;
	
	private boolean active;
}
