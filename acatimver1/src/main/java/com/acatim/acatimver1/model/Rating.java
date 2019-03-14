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
public class Rating {
	
	@Id
	private String rateId;
	
	private String userName;
	
	private String recieverName;
	
	private String createDate;
	
	private String updateDate;
	
	private float rate;
	
	private String comment;
	
	private boolean active;
}
