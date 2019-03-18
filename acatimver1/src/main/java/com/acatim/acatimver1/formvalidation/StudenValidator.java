package com.acatim.acatimver1.formvalidation;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.acatim.acatimver1.dao.UserDAO;
import com.acatim.acatimver1.form.StudentForm;
import com.acatim.acatimver1.model.UserModel;

@Component
public class StudenValidator implements Validator {

	private EmailValidator emailValidator = new EmailValidator();
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == StudentForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		StudentForm studentForm = (StudentForm) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.studentForm.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "NotEmpty.studentForm.fullName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.studentForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.studentForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createDate", "NotEmpty.studentForm.createDate");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.studentForm.phone");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.studentForm.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryCode", "NotEmpty.studentForm.countryCode");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "NotEmpty.studentForm.dob");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.studentForm.gender");
        
        if (!this.emailValidator.isValid(studentForm.getEmail(), null)) {
            // Email không hợp lệ.
            errors.rejectValue("email", "Pattern.appUserForm.email");
        } else if (studentForm.getUserName() == null) {
            UserModel dbUser = userDAO.findUserAccountByEmail(studentForm.getEmail());
            if (dbUser != null) {
                // Email đã được sử dụng bởi tài khoản khác.
                errors.rejectValue("email", "Duplicate.appUserForm.email");
            }
        }
// 
//        if (!errors.hasFieldErrors("userName")) {
//        	UserModel dbUser = userDAO.findUserAccount(studentForm.getUserName());
//            if (dbUser != null) {
//                // Tên tài khoản đã bị sử dụng bởi người khác.
//                errors.rejectValue("userName", "Duplicate.appUserForm.userName");
//            }
//        }


	}

}
