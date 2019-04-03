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
public class RateStudyCenter {
	
	@Id
	private String rateId;
	
	private float equipmentQuality;
	private float staffAttitude;
	private float reputation;
	private float happiness;
	private float safety;
	private float internet;
	private float location;
	private float teachingQuality;
	private String checkSCNull;
}
