package com.library.service;

import java.util.Map;

public interface IHistoryService {
	public Map<String, Object> getHistoryByAdmin(int adminId, int pageNo, int maxRecords, String query);

	public Map<String, Object> getHistoryByUser(int userId, int pageNo, String query);
	
	public String favouriteCategory(int userId);

}
