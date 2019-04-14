package com.acatim.acatimver1.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class PasswordForm {
	
    @NotEmpty(message = "*Mật khẩu không được để trống")
    private String oldPassword;
	
	@Length(min = 5, message = "*Mật khẩu có độ dài tối thiểu là 5")
    @NotEmpty(message = "*Mật khẩu không được để trống")
    private String NewPassword;
	
	@Length(min = 5, message = "*Mật khẩu có độ dài tối thiểu là 5")
    @NotEmpty(message = "*Mật khẩu không được để trống")
    private String reNewPassword;
}
