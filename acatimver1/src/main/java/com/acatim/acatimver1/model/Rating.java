package com.acatim.acatimver1.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
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
	
	@OneToOne
	@JoinColumn
	private RateTeacher rateTeacher;
	
	@OneToOne
	@JoinColumn
	private RateStudyCenter rateStudyCenter;

	public Rating(String rateId, String userName, String recieverName, String createDate, String updateDate, float rate,
			String comment, boolean active) {
		super();
		this.rateId = rateId;
		this.userName = userName;
		this.recieverName = recieverName;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.rate = rate;
		this.comment = comment;
		this.active = active;
	}

	public Rating() {
		super();
	}
	
	
}
