package com.acatim.acatimver1.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class StudyCenterForm {
	@NotEmpty(message = "*Tên của bạn không được để trống")
	private String userName;
    
    private int role_id = 1;
    
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
    private String phoneNo;
    
    @NotEmpty(message = "*Địa chỉ không được bỏ trống")
    private String address;
    
    private boolean active;
    
    private String description;
	
	private float rate;

	public StudyCenterForm() {
		super();
	}

	public StudyCenterForm(@NotEmpty(message = "*Tên của bạn không được để trống") String userName, int role_id,
			@NotEmpty(message = "*Họ và Tên không được để trống") String fullName,
			@Email(message = "*Địa chỉ email chưa đúng") @NotEmpty(message = "*Email không được để trống") String email,
			@Length(min = 5, message = "*Mật khẩu có độ dài tối thiểu là 5") @NotEmpty(message = "*Mật khẩu không được để trống") String password,
			String createDate,
			@Digits(fraction = 0, integer = 11, message = "Số điện thoại có độ dài tối đa là 11") @NotEmpty(message = "*Số điện thoại không được bỏ trống") String phoneNo,
			@NotEmpty(message = "*Địa chỉ không được bỏ trống") String address, boolean active, String description,
			float rate) {
		super();
		this.userName = userName;
		this.role_id = role_id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.createDate = createDate;
		this.phoneNo = phoneNo;
		this.address = address;
		this.active = active;
		this.description = description;
		this.rate = rate;
	}
	
	
}