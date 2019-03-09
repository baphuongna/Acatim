package com.acatim.acatimver1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.RoleDAO;
import com.acatim.acatimver1.dao.StudentDAO;
import com.acatim.acatimver1.dao.StudyCenterDAO;
import com.acatim.acatimver1.dao.TeacherDAO;
import com.acatim.acatimver1.dao.UserDAO;
import com.acatim.acatimver1.model.Role;
import com.acatim.acatimver1.model.UserModel;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Autowired
	private UserDAO UserDAO;

	@Autowired
	private RoleDAO RoleDAO;

	@Autowired
	private StudentDAO StudentDAO;
	
	@Autowired
	private StudyCenterDAO StudyCenterDAO;
	
	@Autowired
	private TeacherDAO TeacherDAO;
	
	@Override
	public Object loadUserByUsername(String username, String roleName) throws UsernameNotFoundException {
		UserModel user = this.UserDAO.findUserAccount(username);

//		Role role = this.RoleDAO.findRoleAccount(user.getUserName());
//
//		
//		// [ROLE_USER, ROLE_ADMIN,..]
//        List<String> roleNames = this.RoleDAO.getRoleNames(user.getUserName());
// 
//        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
//        if (roleNames != null) {
//            for (String roles : roleNames) {
//                // ROLE_USER, ROLE_ADMIN,..
//                GrantedAuthority authority = new SimpleGrantedAuthority(roles);
//                grantList.add(authority);
//            }
//        }
// 
//        UserDetails userDetails = (UserDetails) new User(user.getUserName(), //
//        		user.getPassword(), grantList);
		
		
		if(roleName.equals("Student")) {
			return this.StudentDAO.findInfoUserAccount(user.getUserName());
		}else if(roleName.equals("Teacher")) {
			return this.TeacherDAO.findInfoUserAccount(user.getUserName());
		}else if(roleName.equals("Study Center")) {
			return this.StudyCenterDAO.findInfoUserAccount(user.getUserName());
		}
		
		return user;
	}
	
	
}
