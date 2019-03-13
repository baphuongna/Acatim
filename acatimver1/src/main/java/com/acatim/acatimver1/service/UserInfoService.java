package com.acatim.acatimver1.service;

import java.util.List;

import com.acatim.acatimver1.model.Student;
import com.acatim.acatimver1.model.StudyCenter;
import com.acatim.acatimver1.model.Teacher;
import com.acatim.acatimver1.model.UserModel;

import javassist.NotFoundException;

public interface UserInfoService {
	UserModel loadUserByUsername(String username) throws NotFoundException;
	Teacher loadTeacherByUsername(String username) throws NotFoundException;
	StudyCenter loadStudyCenterByUsername(String username) throws NotFoundException;
	Student loadStudentByUsername(String username) throws NotFoundException;
	List<Teacher> loadAllTeacher() throws NotFoundException;
	List<StudyCenter> loadAllStudyCenter() throws NotFoundException;
	List<Student> loadAllStudent() throws NotFoundException;
}
