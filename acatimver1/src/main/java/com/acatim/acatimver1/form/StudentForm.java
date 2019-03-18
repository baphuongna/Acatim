package com.acatim.acatimver1.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class StudentForm {
	
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
    private String phone;
    
    @NotEmpty(message = "*Địa chỉ không được bỏ trống")
    private String address;
    
    private boolean active;
    
    @NotEmpty(message = "*Ngày Sinh không được bỏ trống")
    private String dob;
	
	private boolean gender;
}
