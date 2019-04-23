package com.acatim.acatimver1.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.RoleDAO;
import com.acatim.acatimver1.dao.StudentDAO;
import com.acatim.acatimver1.dao.StudyCenterDAO;
import com.acatim.acatimver1.dao.TeacherDAO;
import com.acatim.acatimver1.dao.UserDAO;
import com.acatim.acatimver1.entity.CountByDate;
import com.acatim.acatimver1.entity.SearchValue;
import com.acatim.acatimver1.entity.Student;
import com.acatim.acatimver1.entity.StudyCenter;
import com.acatim.acatimver1.entity.Teacher;
import com.acatim.acatimver1.entity.UserModel;
import com.acatim.acatimver1.form.StudentForm;
import com.acatim.acatimver1.form.StudyCenterForm;
import com.acatim.acatimver1.form.TeacherForm;

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
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		password = bCryptPasswordEncoder.encode(password);
		this.UserDAO.changePassword(userName, password);

	}
	
	@Override
	public boolean checkPassword(String userName, String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		UserModel user = this.UserDAO.findUserAccount(userName);
		
		if(bCryptPasswordEncoder.matches(password, user.getPassword())) {
			return true;
		}else {
			return false;
		}
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
	public String getRoleName(String userName) {
		return this.RoleDAO.getRoleNameByUserName(userName);
	}

	@Override
	public StudentForm getUserStudentByUserName(String userName) throws NotFoundException {
		return this.StudentDAO.getUserStudentByUserName(userName);
	}

	@Override
	public List<UserModel> getAllUsersPageable(PageableService pageable, SearchValue search) throws NotFoundException {
		return this.UserDAO.getAllUsersPageable(pageable, search);
	}

	@Override
	public TeacherForm getUserTeacherByUserName(String userName) throws NotFoundException {
		return this.TeacherDAO.getUserTeacherByUserName(userName);
	}

	@Override
	public StudyCenterForm getUserStudyCenterByUserName(String userName) throws NotFoundException {
		return this.StudyCenterDAO.getUserStudyCenterByUserName(userName);
	}

	
	@Override
	public void removeContact(String userName) throws NotFoundException {
		boolean active = false;
		this.UserDAO.removeContact(userName, active);
	}

	@Override
	public void unlockContact(String userName) throws NotFoundException {
		boolean active = true;
		this.UserDAO.removeContact(userName, active);

	}

	@Override
	public List<UserModel> getAllTeacherST() throws NotFoundException {
		return this.UserDAO.getAllTeacherST();
	}

	@Override
	public List<UserModel> getAllManager() throws NotFoundException {
		return this.UserDAO.getAllManager();
	}

	@Override
	public List<UserModel> getAllUsers(SearchValue search) {
		return this.UserDAO.getAllUsers(search);
	}

	@Override
	public CountByDate countStudentByDate() {
		return this.UserDAO.countStudentByDate();
	}

	@Override
	public CountByDate countTeacherByDate() {
		return this.UserDAO.countTeacherByDate();
	}

	@Override
	public CountByDate countStudyCentertByDate() {
		return this.UserDAO.countStudyCentertByDate();
	}

	@Override
	public int countTeacher() {
		return this.TeacherDAO.countTeacher();
	}

	@Override
	public int countStudent() {
		return this.StudentDAO.countStudent();
	}

	@Override
	public int countStudyCenter() {
		return this.StudyCenterDAO.countStudyCenter();
	}

}
