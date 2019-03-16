package com.acatim.acatimver1.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.acatim.acatimver1.model.Student;
import com.acatim.acatimver1.model.UserModel;

public class StudentExtractor implements ResultSetExtractor<List<UserModel>>{

	@Override
	public List<UserModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<String, UserModel> map = new HashMap<String, UserModel>();
		UserModel user = null;

		while (rs.next()) {

			String userName = rs.getString("user_name");
			user = map.get(userName);

			if (user == null) {
				int roleId = rs.getInt("role_id");
				String fullName = rs.getString("full_name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				boolean active = rs.getBoolean("active");

				user = new UserModel(userName, roleId, fullName, email, password, createDate, phone, address, active);
				map.put(userName, user);
			}

			Student student = new Student();
			String dob = rs.getString("dob");
			boolean gender = rs.getBoolean("gender");
			student = new Student(userName, dob, gender);
			user.setStudent(student);
		}
		return new ArrayList<UserModel>(map.values());
	}

}
