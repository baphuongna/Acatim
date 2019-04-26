package com.acatim.acatimver1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserModel {

    @Id
    @NotEmpty(message = "*Tên của bạn không được để trống")
    private String userName;
    
    private int role_id;
    
    @NotEmpty(message = "*Họ và Tên không được để trống")
    private String fullName;
     
    @Email(message = "*Địa chỉ email chưa đúng")
    @NotEmpty(message = "*Email không được để trống")
    private String email;
   
    @Length(min = 5, message = "*Mật khẩu có độ dài tối thiểu là 5")
    @NotEmpty(message = "*Mật khẩu không được để trống")
    private String password;
    
    private String createDate;
    
    @Digits(fraction = 0, integer = 11, message ="Số điện thoại có độ dài tối đa là 11")
    @NotEmpty(message = "*Số điện thoại không được bỏ trống")
    private String phone;
    
    @NotEmpty(message = "*Địa chỉ không được bỏ trống")
    private String address;
    
    private String avatar;
    
    private boolean active;
    
    @OneToOne
    @JoinColumn
    private Student student;
    
    @OneToOne
    @JoinColumn
    private Teacher teacher;
    
    @OneToOne
    @JoinColumn
    private StudyCenter studyCenter;
    
    @OneToOne
    @JoinColumn
    private Role role;

	public UserModel(@NotEmpty(message = "*Please provide your user name") String userName, int role_id,
			@NotEmpty(message = "*Please provide your full name") String fullName,
			@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email,
			@Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String password,
			@NotEmpty(message = "*Please provide your create date") String createDate,
			@NotEmpty(message = "*Please provide your phone") String phone,
			@NotEmpty(message = "*Please provide your address") String address, boolean active) {
		super();
		this.userName = userName;
		this.role_id = role_id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.createDate = createDate;
		this.phone = phone;
		this.address = address;
		this.active = active;
	}
	
	

	public UserModel() {
		super();
	}



	public UserModel(@NotEmpty(message = "*Tên của bạn không được để trống") String userName, int role_id,
			@NotEmpty(message = "*Họ và Tên không được để trống") String fullName,
			@Email(message = "*Địa chỉ email chưa đúng") @NotEmpty(message = "*Email không được để trống") String email,
			@Length(min = 5, message = "*Mật khẩu có độ dài tối thiểu là 5") @NotEmpty(message = "*Mật khẩu không được để trống") String password,
			String createDate,
			@Digits(fraction = 0, integer = 11, message = "Số điện thoại có độ dài tối đa là 11") @NotEmpty(message = "*Số điện thoại không được bỏ trống") String phone,
			@NotEmpty(message = "*Địa chỉ không được bỏ trống") String address, String avatar, boolean active) {
		super();
		this.userName = userName;
		this.role_id = role_id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.createDate = createDate;
		this.phone = phone;
		this.address = address;
		this.avatar = avatar;
		this.active = active;
	}
}
