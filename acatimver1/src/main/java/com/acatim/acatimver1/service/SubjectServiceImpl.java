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
	
	public boolean addSubject(Subject subject) {
		return this.subjectDAO.addSubject(subject);
	}
	
	public boolean updateSubject(Subject subject) {
		return this.subjectDAO.updateSubject(subject);
	}
	
	public boolean removeSubject(String subjectId) {
		boolean active = false;
		return this.subjectDAO.removeSubject(subjectId, active);
	}
	
	public boolean unlockSubject(String subjectId) {
		boolean active = true;
		return this.subjectDAO.removeSubject(subjectId, active);
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
}
