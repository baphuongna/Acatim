package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.acatim.acatimver1.entity.History;

public interface HistoryService {
	
	List<History> getAllHistory();
	
	List<History> getAllHistoryPageble(Pageable pageable);
	
	void addHistory(History status);
	
	void updateHistory(History status);
}
