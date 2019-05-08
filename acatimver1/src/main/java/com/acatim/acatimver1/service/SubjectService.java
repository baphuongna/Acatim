package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.acatim.acatimver1.entity.Subject;

public interface SubjectService {
	
	List<Subject> getAllSubject();
	
	List<Subject> getListSubject();
	
	Subject getSubjectBySubjectId(String subjectId);
	
	boolean addSubject(Subject subject);
	
	boolean updateSubject(Subject subject);
	
	boolean removeSubject(String subjectId);
	
	boolean unlockSubject(String subjectId);
	
	List<Subject> getSubjectByCategoryId(String categoryId);
	
	List<Subject> getListSubjectPageable(Pageable pageable);
	
	List<Subject> getSubjectByCategoryIdPageable(Pageable pageable, String categoryId);
	
	String genSubjectId();
}
