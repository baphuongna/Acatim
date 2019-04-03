package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.acatim.acatimver1.entity.Status;

public interface StatusService {
	
	List<Status> getAllStatus();
	
	List<Status> getAllStatusPageble(Pageable pageable);
	
	void addStatus(Status status);
	
	void updateStatus(Status status);
}
