package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.HistoryDAO;
import com.acatim.acatimver1.entity.History;

@Service
public class HistoryServiceImpl implements HistoryService{
	
	@Autowired
	private HistoryDAO statusDAO;
	
	public List<History> getAllHistory(){
		return this.statusDAO.getAllHistory();
	}
	
	public void addHistory(History history) {
		this.statusDAO.addHistory(history);
	}
	
	public void updateHistory(History history) {
		this.statusDAO.updateHistory(history);
	}

	@Override
	public List<History> getAllHistoryPageble(Pageable pageable) {
		return this.statusDAO.getAllHistoryPageble(pageable);
	}
}
