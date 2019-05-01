package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.HistoryDAO;
import com.acatim.acatimver1.entity.History;
import com.acatim.acatimver1.entity.SearchValue;

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
	public int countAllHistory(SearchValue search) {
		return this.statusDAO.countAllHistory(search);
	}

	@Override
	public List<History> getAllHistoryPageble(PageableService pageable, SearchValue search) {
		return this.statusDAO.getAllHistoryPageble(pageable, search);
	}

	
}
