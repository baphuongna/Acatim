package com.acatim.acatimver1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.RoleDAO;
import com.acatim.acatimver1.dao.UserDAO;
import com.acatim.acatimver1.model.UserModel;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Autowired
	private UserDAO UserDAO;

	@Autowired
	private RoleDAO RoleDAO;

	@Override
	public UserModel loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel appUser = this.UserDAO.findUserAccount(username);
		
		List<String> roleNames = this.RoleDAO.getRoleNames(appUser.getUserName());
		 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
		
		return appUser;
	}
	
	
}
