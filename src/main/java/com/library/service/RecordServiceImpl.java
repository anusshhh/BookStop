package com.library.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.dao.BookDao;
import com.library.dao.HistoryDao;
import com.library.dao.RecordDao;
import com.library.dao.UserDao;
import com.library.dto.Record;
import com.library.dto.User;
import com.library.util.DateUtil;
import com.library.util.Utilities;

public class RecordServiceImpl implements IRecordService {
	private RecordDao recordDao = new RecordDao(Utilities.getConnection());
	private HistoryDao hDao = new HistoryDao(Utilities.getConnection());
	private BookDao bDao = new BookDao(Utilities.getConnection());

	@Override
	public boolean addRecord(Record record) {

		return (recordDao.addRecord(record) && (bDao.updateQuantity(record.getRecordBook().getBookId(), -1)) ? true
				: false);
	}

	@Override
	public Map<String, Object> displayRecord(int adminId, int pageNo, int maxRecords, String query) {
		List<Record> record = Collections.emptyList();
		int start = Utilities.findStart(maxRecords, pageNo);

		Map<String, Object> recordObj = new HashMap<>();
		if (query != null)
			record = recordDao.searchRecord(adminId, start, maxRecords, query);

		else
			record = recordDao.displayRecord(adminId, start, maxRecords);

		int total = recordDao.recordCount(adminId, query);
		int noOfPage = Utilities.findTotalPages(maxRecords, total);

		recordObj.put("record", record);
		recordObj.put("noOfPage", noOfPage);
		return recordObj;
	}

	@Override
	public boolean editRecord(Record record) {
		return false;
	}

	
	@Override
	public boolean renewBook(int recordId, Date dueDate) {
		java.sql.Date dueDateSQL = new java.sql.Date(DateUtil.addDays(dueDate, 7).getTime());
		return recordDao.renewBook(recordId, dueDateSQL);
	}

	@Override
	public boolean returnBook(int recordId) {
		java.sql.Date returnDate = new java.sql.Date(System.currentTimeMillis());
		if (hDao.addToHistory(recordId, returnDate) && bDao.updateQuantity(recordDao.getBookId(recordId), +1)
				&& recordDao.deleteRecord(recordId))
			return true;
		return false;
	}

	@Override
	public boolean deleteRecord(int recordId) {
		return recordDao.deleteRecord(recordId);
	}

	@Override
	public List<Record> getRecordByUser(int userId) {
		return recordDao.getRecordByUser(userId);
	}

	@Override
	public int recordCount(int adminId, String query) {
		return recordDao.recordCount(adminId, query);
	}

	@Override
	public int pendingFines(int adminId) {
		return recordDao.pendingFines(adminId);
	}

	@Override
	public int pendingFineUsers(int adminId) {
		return recordDao.pendingFineUsers(adminId);
	}
}
