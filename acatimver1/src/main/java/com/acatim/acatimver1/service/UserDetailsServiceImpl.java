package com.acatim.acatimver1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.RoleDAO;
import com.acatim.acatimver1.dao.UserDAO;
import com.acatim.acatimver1.entity.UserModel;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
    private UserDAO UserDAO;
 
    @Autowired
    private RoleDAO RoleDAO;
 
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	UserModel appUser = this.UserDAO.findUserAccount(userName);
    	if(appUser.isActive() == false) {
    		appUser = null;
    	}
        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
 
        System.out.println("Found User: " + appUser);
 
        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.RoleDAO.getRoleNames(appUser.getUserName());
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
                appUser.getPassword(), grantList);
 
        return userDetails;
    }
}
