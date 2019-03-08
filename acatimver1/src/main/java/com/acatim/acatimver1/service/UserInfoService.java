package com.acatim.acatimver1.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.acatim.acatimver1.model.UserModel;

public interface UserInfoService {
	UserModel loadUserByUsername(String username) throws UsernameNotFoundException;
}
