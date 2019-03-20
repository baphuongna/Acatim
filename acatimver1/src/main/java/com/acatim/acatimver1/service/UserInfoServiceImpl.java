package com.acatim.acatimver1.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.RoleDAO;
import com.acatim.acatimver1.dao.StudentDAO;
import com.acatim.acatimver1.dao.StudyCenterDAO;
import com.acatim.acatimver1.dao.TeacherDAO;
import com.acatim.acatimver1.dao.UserDAO;
import com.acatim.acatimver1.form.StudentForm;
import com.acatim.acatimver1.form.StudyCenterForm;
import com.acatim.acatimver1.form.TeacherForm;
import com.acatim.acatimver1.model.Student;
import com.acatim.acatimver1.model.StudyCenter;
import com.acatim.acatimver1.model.Teacher;
import com.acatim.acatimver1.model.UserModel;

import javassist.NotFoundException;

@Service
public class UserInfoServiceImpl implements UserInfoService {
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
	public UserModel loadUserByUsername(String username) throws NotFoundException {
		UserModel user = this.UserDAO.findUserAccount(username);
		return user;
	}
	
	@Override
	public UserModel loadUserbyEmail(String email) throws NotFoundException {
		UserModel user = this.UserDAO.findUserAccountByEmail(email);
		return user;
	}

	@Override
	public List<Teacher> loadAllTeacher() throws NotFoundException {
		return this.TeacherDAO.getAllTeacher();
	}

	@Override
	public List<StudyCenter> loadAllStudyCenter() throws NotFoundException {
		return this.StudyCenterDAO.getAllStudyCenter();
	}

	@Override
	public List<Student> loadAllStudent() throws NotFoundException {
		return this.StudentDAO.getAllStudent();
	}
	
	@Override
	public List<UserModel> loadAllUserTeacher() throws NotFoundException {
		return this.TeacherDAO.getAllUserTeacher();
	}

	@Override
	public List<UserModel> loadAllUserStudyCenter() throws NotFoundException {
		return this.StudyCenterDAO.getAllUserStudyCenter();
	}

	@Override
	public List<UserModel> loadAllUserStudent() throws NotFoundException {
		return this.StudentDAO.getAllUserStudent();
	}

	@Override
	public Teacher loadTeacherByUsername(String username) throws NotFoundException {
		return this.TeacherDAO.getTeacherByAccount(username);
	}

	@Override
	public StudyCenter loadStudyCenterByUsername(String username) throws NotFoundException {
		return this.StudyCenterDAO.findInfoUserAccount(username);
	}

	@Override
	public Student loadStudentByUsername(String username) throws NotFoundException {
		return this.StudentDAO.findInfoUserAccount(username);
	}

	@Override
	public void addUserInfo(UserModel user) throws NotFoundException {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		this.UserDAO.addUser(user);

	}

	@Override
	public void addTeacherInfo(Teacher teacher) throws NotFoundException {
		this.TeacherDAO.addTeacherInfo(teacher);

	}

	@Override
	public void addStudyCenterInfo(StudyCenter studyCenter) throws NotFoundException {
		this.StudyCenterDAO.addStudyCenterInfo(studyCenter);
	}

	@Override
	public void addStudentInfo(Student student) throws NotFoundException {
		this.StudentDAO.addStudentInfo(student);

	}

	@Override
	public void updateUserInfo(UserModel user) throws NotFoundException {
		this.UserDAO.updateUser(user);

	}

	@Override
	public void removeUser(String userName) throws NotFoundException {
		boolean active = false;
		this.UserDAO.removeUser(userName, active);
	}
	
	@Override
	public void unlockUser(String userName) throws NotFoundException {
		boolean active = true;
		this.UserDAO.removeUser(userName, active);
		
	}

	@Override
	public void changePassword(String userName, String password) throws NotFoundException {
		this.UserDAO.changePassword(userName, password);

	}

	@Override
	public List<UserModel> searchUserByName(String fullName) throws NotFoundException {
		return this.UserDAO.searchUserByName(fullName);
	}

	@Override
	public List<UserModel> searchUserByEmail(String email) throws NotFoundException {
		return this.UserDAO.searchUserByEmail(email);
	}

	@Override
	public boolean checkUserExist(String userName) throws NotFoundException {
		return this.UserDAO.checkUserExist(userName);
	}

	@Override
	public void updateTeacherInfo(Teacher teacher) throws NotFoundException {
		this.TeacherDAO.updateTeacherInfo(teacher);

	}

	@Override
	public void updateStudyCenterInfo(StudyCenter studyCenter) throws NotFoundException {
		this.StudyCenterDAO.updateStudyCenterInfo(studyCenter);

	}

	@Override
	public void updateStudentInfo(Student student) throws NotFoundException {
		this.StudentDAO.updateStudentInfo(student);

	}

	@Override
	public String getRoleName(String userName) throws NotFoundException {
		return this.RoleDAO.getRoleNameByUserName(userName);
	}

	@Override
	public StudentForm getUserStudentByUserName(String userName) throws NotFoundException {
		return this.StudentDAO.getUserStudentByUserName(userName);
	}

	@Override
	public List<UserModel> getAllUsers(String roleId) throws NotFoundException {
		return this.UserDAO.getAllUsers(roleId);
	}

	@Override
	public List<UserModel> getAllUsersPageable(Pageable pageable, String roleId) throws NotFoundException {
		return this.UserDAO.getAllUsersPageable(pageable, roleId);
	}

	@Override
	public List<UserModel> searchAllUsersByUserName(Pageable pageable, String userName, String roleId) throws NotFoundException {
		return this.UserDAO.searchAllUsersByUserName(pageable, userName, roleId);
	}

	@Override
	public List<UserModel> searchAllUsersByEmail(Pageable pageable, String email, String roleId) throws NotFoundException {
		return this.UserDAO.searchAllUsersByEmail(pageable, email, roleId);
	}

	@Override
	public List<UserModel> searchAllUsersByFullName(Pageable pageable, String fullName, String roleId) throws NotFoundException {
		return this.UserDAO.searchAllUsersByFullName(pageable, fullName, roleId);
	}

	@Override
	public TeacherForm getUserTeacherByUserName(String userName) throws NotFoundException {
		return this.TeacherDAO.getUserTeacherByUserName(userName);
	}

	@Override
	public StudyCenterForm getUserStudyCenterByUserName(String userName) throws NotFoundException {
		return this.StudyCenterDAO.getUserStudyCenterByUserName(userName);
	}
	
}
