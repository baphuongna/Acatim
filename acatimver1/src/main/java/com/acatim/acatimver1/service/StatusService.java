package com.acatim.acatimver1.service;

import java.util.List;

import com.acatim.acatimver1.model.Status;

public interface StatusService {
	
	List<Status> getAllStatus();
	
	void addStatus(Status status);
	
	void updateStatus(Status status);
}
