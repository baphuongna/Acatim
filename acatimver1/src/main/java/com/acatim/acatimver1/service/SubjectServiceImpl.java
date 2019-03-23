package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.SubjectDAO;
import com.acatim.acatimver1.model.Subject;

@Service
public class SubjectServiceImpl {
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
	
	public void addCourse(Subject subject) {
		this.subjectDAO.addCourse(subject);
	}
	
	public void updateCourse(Subject subject) {
		this.subjectDAO.updateCourse(subject);
	}
	
	public void removeCourse(String subjectId) {
		boolean active = false;
		this.subjectDAO.removeCourse(subjectId, active);
	}
	
	public List<Subject> getSubjectByCategoryId(String categoryId) {
		return this.subjectDAO.getSubjectByCategoryId(categoryId);
	}
	
	public String genSubjectId() {
		List<Subject> listSubject = this.subjectDAO.getListSubject();
		int incNumber = listSubject.size() + 1;
		String SubjectId = "sj" + incNumber;
		return SubjectId;
	}
}
