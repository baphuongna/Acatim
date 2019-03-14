package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.StatusDAO;
import com.acatim.acatimver1.model.Status;

@Service
public class StatusServiceImpl {
	
	@Autowired
	private StatusDAO statusDAO;
	
	public List<Status> getAllStatus(){
		return this.statusDAO.getAllStatus();
	}
	
	public void addStatus(Status status) {
		this.statusDAO.addStatus(status);
	}
	
	public void updateStatus(Status status) {
		this.statusDAO.updateStatus(status);
	}
}
