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
public class Student extends UserModel{

	private String dob;
	
	private boolean gender;

	public Student(String userName, int role_id, String fullName, String email, String password, String createDate,
			String phone, String address, boolean active, String dob, boolean gender) {
		super(userName, role_id, fullName, email, password, createDate, phone, address, active);
		this.dob = dob;
		this.gender = gender;
	}
	
	
}
