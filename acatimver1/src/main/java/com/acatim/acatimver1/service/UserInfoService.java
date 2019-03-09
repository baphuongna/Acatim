package com.acatim.acatimver1.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserInfoService {
	Object loadUserByUsername(String username, String roleName) throws UsernameNotFoundException;
}
