package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.SubjectDAO;
import com.acatim.acatimver1.entity.Subject;

@Service
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	private SubjectDAO subjectDAO;
	
	public List<Subject> getAllSubject(){
		return this.subjectDAO.getAllSubject();
	}
	
	public List<Subject> getListSubject(){
		return this.subjectDAO.getListSubject();
	}
	
	public Subject getSubjectBySubjectId(String subjectId) {
		return this.subjectDAO.getSubjectBySubjectId(subjectId);
	}
	
	public void addSubject(Subject subject) {
		this.subjectDAO.addSubject(subject);
	}
	
	public void updateSubject(Subject subject) {
		this.subjectDAO.updateSubject(subject);
	}
	
	public void removeSubject(String subjectId) {
		boolean active = false;
		this.subjectDAO.removeSubject(subjectId, active);
	}
	
	public void unlockSubject(String subjectId) {
		boolean active = true;
		this.subjectDAO.removeSubject(subjectId, active);
	}
	
	public List<Subject> getSubjectByCategoryId(String categoryId) {
		return this.subjectDAO.getSubjectByCategoryId(categoryId);
	}
	
	public List<Subject> getListSubjectPageable(Pageable pageable){
		return this.subjectDAO.getListSubjectPageable(pageable);
	}
	
	public List<Subject> getSubjectByCategoryIdPageable(Pageable pageable, String categoryId){
		return this.subjectDAO.getSubjectByCategoryIdPageable(pageable, categoryId);
	}
	
	public String genSubjectId() {
		List<Subject> listSubject = this.subjectDAO.getListSubject();
		int incNumber = listSubject.size() + 1;
		String SubjectId = "sj" + incNumber;
		return SubjectId;
	}

	@Override
	public List<Subject> getSubjectByUserName(String userName) {
		return this.subjectDAO.getSubjectByUserName(userName);
	}
}
