package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.acatim.acatimver1.model.Student;
import com.acatim.acatimver1.model.StudyCenter;
import com.acatim.acatimver1.model.Teacher;

import javassist.NotFoundException;

public interface UserInfoService {
	Object loadUserByUsername(String username, String roleName) throws UsernameNotFoundException;
	List<Teacher> loadAllTeacher() throws NotFoundException;
	List<StudyCenter> loadAllStudyCenter() throws NotFoundException;
	List<Student> loadAllStudent() throws NotFoundException;
}
