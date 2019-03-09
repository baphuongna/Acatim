package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.model.Role;

public class RoleMapper implements RowMapper<Role>{

	
	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		int roleId = rs.getInt("role_id");
		String roleName = rs.getString("role_name");
		
		return new Role(roleId, roleName);
	}
	
}
