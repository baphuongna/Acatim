package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.acatim.acatimver1.form.StudentForm;
import com.acatim.acatimver1.form.StudyCenterForm;
import com.acatim.acatimver1.form.TeacherForm;
import com.acatim.acatimver1.model.Contact;
import com.acatim.acatimver1.model.Student;
import com.acatim.acatimver1.model.StudyCenter;
import com.acatim.acatimver1.model.Teacher;
import com.acatim.acatimver1.model.UserModel;

import javassist.NotFoundException;

public interface UserInfoService {
	UserModel loadUserByUsername(String username) throws NotFoundException;
	UserModel loadUserbyEmail(String email) throws NotFoundException;
	Teacher loadTeacherByUsername(String username) throws NotFoundException;
	StudyCenter loadStudyCenterByUsername(String username) throws NotFoundException;
	Student loadStudentByUsername(String username) throws NotFoundException;
	void addUserInfo(UserModel user) throws NotFoundException;
	void addTeacherInfo(Teacher teacher) throws NotFoundException;
	void addStudyCenterInfo(StudyCenter studyCenter) throws NotFoundException;
	void addStudentInfo(Student student) throws NotFoundException;
	void updateUserInfo(UserModel user) throws NotFoundException;
	void updateTeacherInfo(Teacher teacher) throws NotFoundException;
	void updateStudyCenterInfo(StudyCenter studyCenter) throws NotFoundException;
	void updateStudentInfo(Student student) throws NotFoundException;
	void removeUser(String userName) throws NotFoundException;
	void unlockUser(String userName) throws NotFoundException;
	void changePassword(String userName, String password) throws NotFoundException;
	List<UserModel> searchUserByName(String fullName)  throws NotFoundException;
	List<UserModel> searchUserByEmail(String email)  throws NotFoundException;
	List<Teacher> loadAllTeacher() throws NotFoundException;
	List<StudyCenter> loadAllStudyCenter() throws NotFoundException;
	List<Student> loadAllStudent() throws NotFoundException;
	List<UserModel> loadAllUserTeacher() throws NotFoundException;
	List<UserModel> loadAllUserStudyCenter() throws NotFoundException;
	List<UserModel> loadAllUserStudent() throws NotFoundException;
	boolean checkUserExist(String userName) throws NotFoundException;
	String getRoleName(String userName) throws NotFoundException;
	
	StudentForm getUserStudentByUserName(String userName) throws NotFoundException;
	TeacherForm getUserTeacherByUserName(String userName) throws NotFoundException;
	StudyCenterForm getUserStudyCenterByUserName(String userName) throws NotFoundException;
	
	List<UserModel> getAllUsers(String roleId) throws NotFoundException;
	List<UserModel> getAllUsersPageable(Pageable pageable, String roleId) throws NotFoundException;
	List<UserModel> searchAllUsersByUserName(Pageable pageable, String userName, String roleId) throws NotFoundException;
	List<UserModel> searchAllUsersByEmail(Pageable pageable, String email, String roleId) throws NotFoundException;
	List<UserModel> searchAllUsersByFullName(Pageable pageable, String fullName, String roleId) throws NotFoundException;
	
	List<Contact> getAllContact() throws NotFoundException;
	List<Contact> getAllContactPageable(Pageable pageable) throws NotFoundException;
	List<Contact> searchAllContactByUserName(Pageable pageable, String userName) throws NotFoundException;
	List<Contact> searchAllContactByEmail(Pageable pageable, String email) throws NotFoundException;
	
	void removeContact(String userName) throws NotFoundException;
	void unlockContact(String userName) throws NotFoundException;
	List<UserModel> getAllTeacherST() throws NotFoundException;
	
	List<UserModel> getAllManager() throws NotFoundException;
}
