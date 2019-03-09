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
public class Teacher extends UserModel{

	private String dob;
	
	private boolean gender;
	
	private String description;
	
	private float rate;

	public Teacher(String userName, int role_id, String fullName, String email, String password, String createDate,
			String phone, String address, boolean active, String dob, boolean gender, String description, float rate) {
		super(userName, role_id, fullName, email, password, createDate, phone, address, active);
		this.dob = dob;
		this.gender = gender;
		this.description = description;
		this.rate = rate;
	}


	@Override
	public String toString() {
		return "Teacher [date=" + dob + ", gender=" + gender + ", description=" + description + ", rate=" + rate + "]";
	}
	
	
}
