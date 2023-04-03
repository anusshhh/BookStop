/**
 * 
 */
package com.library.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.library.dto.Record;
import com.library.dto.User;

/**
 * @author Lenovo
 *
 */
public interface IRecordService {

	public boolean addRecord(Record record);

	public Map<String, Object> displayRecord(int adminId, int pageNo, int maxRecords, String query);

	public boolean renewBook(int recordId, Date returnDate);

	public boolean returnBook(int recordId);

	public boolean editRecord(Record record);

	public boolean deleteRecord(int recordId);

	public List<Record> getRecordByUser(int userId);

	public int recordCount(int adminId, String query);

	public int pendingFines(int adminId);

	public int pendingFineUsers(int adminId);

}
