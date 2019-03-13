package com.acatim.acatimver1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserModel {

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
    
    @NotEmpty(message = "*Please provide your create date")
    private String createDate;
    
    @NotEmpty(message = "*Please provide your phone")
    private String phone;
    
    @NotEmpty(message = "*Please provide your address")
    private String address;
    
    private boolean active;
    
}
