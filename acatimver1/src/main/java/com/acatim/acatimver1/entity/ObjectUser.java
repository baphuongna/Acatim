package com.acatim.acatimver1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ObjectUser {
	
	@Id
	@Length(min = 5, message = "*Mật khẩu có độ dài tối thiểu là 5 ký tự")
    @NotEmpty(message = "*Tên của bạn không được để trống")
    private String userName;
    
    private int role_id;
    
    @NotEmpty(message = "*Họ và Tên không được để trống")
    private String fullName;
     
    @Email(message = "*Địa chỉ email chưa đúng")
    @NotEmpty(message = "*Email không được để trống")
    private String email;
   
    @Length(min = 5, message = "*Mật khẩu có độ dài tối thiểu là 5 ký tự")
    @NotEmpty(message = "*Mật khẩu không được để trống")
    private String password;
    
    private String createDate;
    
//    @Digits(fraction = 0, integer = 11, message ="Số điện thoại có độ dài tối đa là 11")
    @Pattern(regexp = "[0-9]{10,11}", message="*Số điện thoại có độ dài tối đa là 10 đến 11 chữ số.")
    @NotEmpty(message = "*Số điện thoại không được bỏ trống")
    private String phone;
    
    @NotEmpty(message = "*Địa chỉ không được bỏ trống")
    private String address;
    
    private String dob;
    
    private boolean gender;
    
	private String description;
    
    private boolean active;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getFullName() {
		return fullName.trim();
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email.trim();
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address.trim();
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDob() {
		return dob.trim();
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getDescription() {
		return description.trim();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
    
    
}
