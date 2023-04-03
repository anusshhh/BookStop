package com.library.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library.dto.Book;
import com.library.dto.History;
import com.library.dto.Record;
import com.library.dto.User;

public class HistoryDao {
	private Record history;
	private User user;
	private Book book;
	private String sql;
	private Connection con;

	public HistoryDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean addToHistory(int recordId, Date returnDate) {
		boolean f = false;
		try {
			String sql = "insert into bookstop.history (history_id,issue_date,due_date,historyUserId,fine,historyAdminId,historyBookId) select * from record where record_id="
					+ recordId;
			String sql2 = "update bookstop.history set return_date='" + returnDate + "' where history_id=" + recordId
					+ ";";
			Statement ps = con.createStatement();

			ps.addBatch(sql);
			ps.addBatch(sql2);
			int result[] = ps.executeBatch();
			if (result.length > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public List<Record> displayHistory(int adminId, int start, int end, String query) {
		List<Record> historyList = new ArrayList<>();
		try {

			if (query == null)
				sql = "SELECT h.history_id, u.first_name, u.last_name, b.book_name,b.author,b.category, h.issue_date,h.due_date,h.return_date,h.fine,u.phone FROM bookstop.history h inner join bookstop.user u on u.user_id=h.historyUserId  inner join bookstop.book b on h.historyBookId=b.book_id  where h.historyAdminId=? order by return_date limit ?,?;";
			else
				sql = "SELECT h.history_id, u.first_name, u.last_name, b.book_name,b.author,b.category, h.issue_date,h.due_date,h.return_date,h.fine,u.phone FROM bookstop.history h inner join bookstop.user u on u.user_id=h.historyUserId  inner join bookstop.book b on h.historyBookId=b.book_id  where h.historyAdminId=? and u.first_name like '%"
						+ query + "%' or u.last_name like '%" + query + "%' or b.book_name like '%" + query + "%' or b.author like '%" + query + "%' order by h.return_date limit ?,?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, adminId);
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				history = new History();
				book = new Book();
				user = new User();
				history.setRecordId(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				book.setBookName(rs.getString(4));
				book.setAuthor(rs.getString(5));
				book.setCategory(rs.getString(6));
				history.setIssueDate(rs.getDate(7));
				history.setDueDate(rs.getDate(8));
				history.setReturnDate(rs.getDate(9));
				history.setFine(rs.getInt(10));
				user.setPhone(rs.getLong(11));

				history.setRecordBook(book);
				history.setRecordUser(user);
				historyList.add(history);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return historyList;
	}

	public int historyCount(int adminId, String query) {
		int count = 0;
		String sql;
		try {
			if (query != null)
				sql = "Select count(history_id) from (SELECT h.history_id, u.first_name, u.last_name, b.book_name,b.category, h.issue_date,h.due_date,h.return_date,h.fine,u.phone FROM bookstop.history h inner join bookstop.user u on u.user_id=h.historyUserId  inner join bookstop.book b on h.historyBookId=b.book_id  where h.historyAdminId=? and b.book_name like '%"
						+ query + "%' or b.author like '%" + query + "%') as c; ";
			else
				sql = "select count(history_id) from history where historyAdminId=?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, adminId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;

	}

	public List<Record> userHistory(int userId, int start, int end, String query) {
		List<Record> recordHistory = new ArrayList<>();
		try {
			Record history;
			String sql;
			if (query == null)
				sql = "SELECT b.picture, b.book_name,b.author,b.category, h.issue_date,h.due_date,h.return_date,h.fine FROM bookstop.history h inner join bookstop.user u on u.user_id=h.historyUserId  inner join bookstop.book b on h.historyBookId=b.book_id  where h.historyUserId=? order by return_date limit ?,?;";
			else
				sql = "SELECT b.picture, b.book_name,b.author,b.category, h.issue_date,h.due_date,h.return_date,h.fine FROM bookstop.history h inner join bookstop.user u on u.user_id=h.historyUserId  inner join bookstop.book b on h.historyBookId=b.book_id  where h.historyUserId=? and b.book_name like '%"
						+ query + "%' or b.author like '%" + query + "%' order by h.return_date limit ?,?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				history = new Record();
				book = new Book();
				user = new User();
				book.setPicture(rs.getString(1));
				book.setBookName(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setCategory(rs.getString(4));
				history.setIssueDate(rs.getDate(5));
				history.setDueDate(rs.getDate(6));
				history.setReturnDate(rs.getDate(7));
				history.setFine(rs.getInt(8));

				history.setRecordBook(book);
				history.setRecordUser(user);
				recordHistory.add(history);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return recordHistory;

	}

	public int userHistoryCount(int adminId, String query) {
		int count = 0;
		String sql;
		try {
			if (query != null)
				sql = "Select count(history_id) from (SELECT h.history_id, u.first_name, u.last_name, b.book_name,b.category, h.issue_date,h.due_date,h.return_date,h.fine,u.phone FROM bookstop.history h inner join bookstop.user u on u.user_id=h.historyUserId  inner join bookstop.book b on h.historyBookId=b.book_id  where h.historyUserId=? and b.book_name like '%"
						+ query + "%' or b.author like '%" + query + "%') as c; ";
			else
				sql = "select count(history_id) from history where historyUserId=?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, adminId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;

	}

	public String favouriteCategory(int userId) {
		String favouriteCategory=null;
	    try {
	    	String sql = "SELECT max(b.category) FROM bookstop.history h inner join book b on h.historyBookId=b.book_id where h.historyUserId=?";
	    	PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				favouriteCategory = rs.getString(1);
			}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    return favouriteCategory; 
	}
	
}
