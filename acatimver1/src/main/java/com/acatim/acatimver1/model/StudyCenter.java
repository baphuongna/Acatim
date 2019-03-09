package com.acatim.acatimver1.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudyCenter extends UserModel {
	
	private String description;
	
	private float rate;

	public StudyCenter(String userName, int role_id, String fullName, String email, String password, String createDate,
			String phone, String address, boolean active, String description, float rate) {
		super(userName, role_id, fullName, email, password, createDate, phone, address, active);
		this.description = description;
		this.rate = rate;
	}
	
}
