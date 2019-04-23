package com.acatim.acatimver1.service;

import java.util.List;

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
	boolean checkPassword(String userName, String password);

	boolean checkUserExist(String userName) throws NotFoundException;
	String getRoleName(String userName);
	
	StudentForm getUserStudentByUserName(String userName) throws NotFoundException;
	TeacherForm getUserTeacherByUserName(String userName) throws NotFoundException;
	StudyCenterForm getUserStudyCenterByUserName(String userName) throws NotFoundException;

	List<UserModel> getAllUsersPageable(PageableService pageable, SearchValue Search) throws NotFoundException;
	List<UserModel> getAllUsers(SearchValue search);
	
	void removeContact(String userName) throws NotFoundException;
	void unlockContact(String userName) throws NotFoundException;
	List<UserModel> getAllTeacherST() throws NotFoundException;
	
	List<UserModel> getAllManager() throws NotFoundException;
	
	CountByDate countStudentByDate();
	CountByDate countTeacherByDate();
	CountByDate countStudyCentertByDate();

	int countTeacher();
	int countStudent();
	int countStudyCenter();
}
