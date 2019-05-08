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
import com.acatim.acatimver1.entity.ConfirmEmail;
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
	public boolean addUserInfo(UserModel user) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return this.UserDAO.addUser(user);

	}

	@Override
	public boolean addTeacherInfo(Teacher teacher) {
		return this.TeacherDAO.addTeacherInfo(teacher);

	}

	@Override
	public boolean addStudyCenterInfo(StudyCenter studyCenter) {
		return this.StudyCenterDAO.addStudyCenterInfo(studyCenter);
	}

	@Override
	public boolean addStudentInfo(Student student) {
		return this.StudentDAO.addStudentInfo(student);

	}

	@Override
	public boolean updateUserInfo(UserModel user) {
		return this.UserDAO.updateUser(user);

	}

	@Override
	public boolean removeUser(String userName) {
		boolean active = false;
		return this.UserDAO.removeUser(userName, active);
	}

	@Override
	public boolean unlockUser(String userName) {
		boolean active = true;
		return this.UserDAO.removeUser(userName, active);

	}

	@Override
	public boolean changePassword(String userName, String password) throws NotFoundException {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		password = bCryptPasswordEncoder.encode(password);
		return this.UserDAO.changePassword(userName, password);

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
	public boolean updateTeacherInfo(Teacher teacher) {
		return this.TeacherDAO.updateTeacherInfo(teacher);

	}

	@Override
	public boolean updateStudyCenterInfo(StudyCenter studyCenter) {
		return this.StudyCenterDAO.updateStudyCenterInfo(studyCenter);

	}

	@Override
	public boolean updateStudentInfo(Student student) {
		return this.StudentDAO.updateStudentInfo(student);

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
	public boolean removeContact(String userName) throws NotFoundException {
		boolean active = false;
		return this.UserDAO.removeContact(userName, active);
	}

	@Override
	public boolean unlockContact(String userName) throws NotFoundException {
		boolean active = true;
		return this.UserDAO.removeContact(userName, active);

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

	@Override
	public boolean updateAvatar(String userName, String avatar) {
		return this.UserDAO.updateAvatar(userName, avatar);
	}

	@Override
	public UserModel findAccConfirm(String userName, String email) {
		return this.UserDAO.findAccConfirm(userName, email);
	}

	@Override
	public boolean addConfirm(ConfirmEmail confirmEmail) {
		return this.UserDAO.addConfirm(confirmEmail);
	}

	@Override
	public boolean updateConfirm(String userName, boolean status) {
		return this.UserDAO.updateConfirm(userName, status);
	}

}
