package com.acatim.acatimver1.service;

import java.util.List;

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

public interface UserInfoService {
	UserModel loadUserByUsername(String username) throws NotFoundException;
	UserModel loadUserbyEmail(String email) throws NotFoundException;
	Teacher loadTeacherByUsername(String username) throws NotFoundException;
	StudyCenter loadStudyCenterByUsername(String username) throws NotFoundException;
	Student loadStudentByUsername(String username) throws NotFoundException;
	boolean addUserInfo(UserModel user);
	boolean addTeacherInfo(Teacher teacher);
	boolean addStudyCenterInfo(StudyCenter studyCenter);
	boolean addStudentInfo(Student student);
	boolean updateUserInfo(UserModel user);
	boolean updateTeacherInfo(Teacher teacher);
	boolean updateStudyCenterInfo(StudyCenter studyCenter);
	boolean updateStudentInfo(Student student);
	boolean removeUser(String userName);
	boolean unlockUser(String userName);
	
	boolean changePassword(String userName, String password) throws NotFoundException;
	boolean checkPassword(String userName, String password);

	boolean checkUserExist(String userName) throws NotFoundException;
	String getRoleName(String userName);
	
	StudentForm getUserStudentByUserName(String userName) throws NotFoundException;
	TeacherForm getUserTeacherByUserName(String userName) throws NotFoundException;
	StudyCenterForm getUserStudyCenterByUserName(String userName) throws NotFoundException;

	List<UserModel> getAllUsersPageable(PageableService pageable, SearchValue Search) throws NotFoundException;
	List<UserModel> getAllUsers(SearchValue search);
	
	boolean removeContact(String userName) throws NotFoundException;
	boolean unlockContact(String userName) throws NotFoundException;
	List<UserModel> getAllTeacherST() throws NotFoundException;
	
	List<UserModel> getAllManager() throws NotFoundException;
	
	CountByDate countStudentByDate();
	CountByDate countTeacherByDate();
	CountByDate countStudyCentertByDate();

	int countTeacher();
	int countStudent();
	int countStudyCenter();
	
	boolean updateAvatar(String userName, String avatar);
	
	UserModel findAccConfirm(String userName, String email);
	boolean addConfirm(ConfirmEmail confirmEmail);
	boolean updateConfirm(String userName,  boolean status);
	
}
