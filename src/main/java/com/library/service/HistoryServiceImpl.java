package com.library.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.dao.HistoryDao;
import com.library.dto.Record;
import com.library.util.Utilities;

public class HistoryServiceImpl implements IHistoryService {

	private HistoryDao hDao = new HistoryDao(Utilities.getConnection());

	@Override
	public Map<String, Object> getHistoryByAdmin(int adminId, int pageNo, int maxRecords, String query) {
		int start = Utilities.findStart(maxRecords, pageNo);
		List<Record> recordHistory = Collections.emptyList();
		Map<String, Object> historyObj = new HashMap<>();

		recordHistory = hDao.displayHistory(adminId, start, maxRecords, query);
		int total = hDao.historyCount(adminId, query);
		int noOfPage = Utilities.findTotalPages(maxRecords, total);

		historyObj.put("recordHistory", recordHistory);
		historyObj.put("noOfPage", noOfPage);
		return historyObj;
	}

	@Override
	public Map<String, Object> getHistoryByUser(int userId, int pageNo, String query) {
		int maxRecords = 5;
		int start = Utilities.findStart(maxRecords, pageNo);
		List<Record> userHistory = Collections.emptyList();
		Map<String, Object> historyObj = new HashMap<>();

		userHistory = hDao.userHistory(userId, start, maxRecords, query);
		int total = hDao.historyCount(userId, query);
		int noOfPage = Utilities.findTotalPages(maxRecords, total);

		historyObj.put("userHistory", userHistory);
		historyObj.put("noOfPage", noOfPage);
		return historyObj;
	}
	
	public String favouriteCategory(int userId) {
		return hDao.favouriteCategory(userId);
	}

}
