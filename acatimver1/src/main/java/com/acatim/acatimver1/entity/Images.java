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
public class Images {
	@Id
	private int id;
	
	private String userName;
	
	private String linkimage;
	
	private String description;
	
	private String createDate;
	
	private String updateDate;
	
	private boolean active;
}
