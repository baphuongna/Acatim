package com.acatim.acatimver1.form;

import lombok.Data;

@Data
public class StudentForm {
	
	private String userName;
    
    private int role_id = 1;
    
    private String fullName;
     
    private String email;
   
    private String password;
    
    private String createDate;
    
    private String phone;
    
    private String address;
    
    private boolean active;
    
    private String dob;
	
	private boolean gender;
}
