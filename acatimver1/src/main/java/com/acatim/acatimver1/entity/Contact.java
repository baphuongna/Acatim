package com.acatim.acatimver1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contact {
	@Id
	private int id;

	private String name;

	private String email;

	private String title;

	private String message;

	private String createDate;

	private boolean isActive;

	public Contact(String name, String email, String title, String message, String createDate, boolean isActive) {
		super();
		this.name = name;
		this.email = email;
		this.createDate = createDate;
		this.isActive = isActive;
	}
}
