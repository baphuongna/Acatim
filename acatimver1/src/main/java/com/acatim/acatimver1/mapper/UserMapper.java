package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.acatim.acatimver1.entity.Role;
import com.acatim.acatimver1.entity.Student;
import com.acatim.acatimver1.entity.StudyCenter;
import com.acatim.acatimver1.entity.Teacher;
import com.acatim.acatimver1.entity.UserModel;

public class UserMapper implements RowMapper<UserModel> {
	public static final String BASE_SQL //
			= "Select * From User u ";

	@Override
	public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {

		UserModel user = null;

		String userName = rs.getString("user_name");
		int roleId = rs.getInt("role_id");
		String fullName = rs.getString("full_name");
		String email = rs.getString("email");
		String password = rs.getString("password");
		String createDate = rs.getString("create_date");
		String phone = rs.getString("phone");
		String address = rs.getString("address");
		String avatar = rs.getString("avatar");
		boolean active = rs.getBoolean("active");
		
		user = new UserModel(userName, roleId, fullName, email, password, createDate, phone, address, avatar, active);
		
		try {
			Role role = new Role();
			String roleName = rs.getString("Role.role_name");
			role = new Role(roleId, roleName);
			user.setRole(role);
		}catch (Exception e) {
		}
		
		try {
			Student student = new Student();
			String dob = rs.getString("Student.dob");
			boolean gender = rs.getBoolean("Student.gender");
			student = new Student(userName, dob, gender);
			user.setStudent(student);
		}catch (Exception e) {
		}
		
		try {
			Teacher teacher = new Teacher();
			String dob = rs.getString("Teacher.dob");
			boolean gender = rs.getBoolean("Teacher.gender");
			String description = rs.getString("Teacher.description");
			float rate = rs.getFloat("Teacher.rate");
			teacher = new Teacher(userName, dob, gender, description, rate);
			user.setTeacher(teacher);
		}catch (Exception e) {
		}
		
		try {
			StudyCenter studyCenter = new StudyCenter();
			String description = rs.getString("StudyCenter.description");
			float rate = rs.getFloat("StudyCenter.rate");
			studyCenter = new StudyCenter(userName, description, rate);
			user.setStudyCenter(studyCenter);
		}catch (Exception e) {
		}
		
		return user;
	}
}
