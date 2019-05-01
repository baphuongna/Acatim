package com.acatim.acatimver1.service;

import java.util.List;
import com.acatim.acatimver1.entity.History;
import com.acatim.acatimver1.entity.SearchValue;

public interface HistoryService {
	
	List<History> getAllHistory();
	
	int countAllHistory(SearchValue search);
	
	List<History> getAllHistoryPageble(PageableService pageable, SearchValue search);
	
	void addHistory(History status);
	
	void updateHistory(History status);
}
