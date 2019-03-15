package com.acatim.acatimver1.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class ObjectUser {
	
	@Id
    @NotEmpty(message = "*Please provide your user name")
    private String userName;
    
    private int role_id;
    
    @NotEmpty(message = "*Please provide your full name")
    private String fullName;
     
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;
   
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;
    
    private String createDate;
    
    @Digits(fraction = 0, integer = 10, message ="Please provide number")
    @NotEmpty(message = "*Please provide your phone")
    private String phone;
    
    @NotEmpty(message = "*Please provide your address")
    private String address;
    
    @NotEmpty(message = "*Hãy cho chúng tôi biết ngày sinh của bạn")
    private String dob;
    
    private boolean gender;
    
	private String description;
    
    private boolean active;
}
