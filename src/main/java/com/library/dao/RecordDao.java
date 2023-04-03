package com.library.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.dto.Admin;
import com.library.dto.Book;
import com.library.dto.Record;
import com.library.dto.User;

public class RecordDao {
	Record r;
	Book b;
	User u;
	Admin a;
	private Connection con;

	public RecordDao(Connection con) {
		super();
		this.con = con;
	}

	public List<Record> searchRecord(int adminId, int start, int end, String query) {
		List<Record> records = new ArrayList<Record>();
		try {

			String sql = "SELECTr.record_id, u.first_name, u.last_name, b.book_name,b.author,b.category, r.issue_date,r.due_date,r.fine,u.phone, r.admin_id FROM bookstop.record r inner join bookstop.user u on u.user_id=r.user_id  inner join bookstop.book b on r.book_id=b.book_id where r.admin_id=? and u.first_name like '%"
					+ query + "%' or last_name like '%" + query + "%' order by r.record_id limit ?,?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, adminId);
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r = new Record();
				b = new Book();
				u = new User();
				a = new Admin();
				r.setRecordId(rs.getInt(1));
				u.setFirstName(rs.getString(2));
				u.setLastName(rs.getString(3));
				b.setBookName(rs.getString(4));
				b.setAuthor(rs.getString(5));
				b.setCategory(rs.getString(6));
				r.setIssueDate(rs.getDate(7));
				r.setDueDate(rs.getDate(8));
				r.setFine(rs.getInt(9));
				u.setPhone(rs.getLong(10));
				a.setAdminId(rs.getInt(11));

				r.setRecordBook(b);
				r.setRecordUser(u);
				records.add(r);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return records;
	}

//-------------------------------------------------------------------------------------------------------------------------------------
	public List<Record> displayRecord(int adminId, int start, int end) {
		List<Record> records = new ArrayList<Record>();
		try {
			Record r;
			String sql = "SELECT * FROM bookstop.record r inner join bookstop.user u using(user_id)  inner join bookstop.book b using(book_id) where r.admin_id=? order by r.record_id desc limit ?,?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, adminId);
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r = new Record();
				b = new Book();
				u = new User();
				a = new Admin();
				b.setBookId(rs.getInt(1));
				u.setUserId(rs.getInt(2));
				r.setRecordId(rs.getInt(3));
				r.setIssueDate(rs.getDate(4));
				r.setDueDate(rs.getDate(5));
				r.setFine(rs.getInt(6));
				a.setAdminId(rs.getInt(7));
				u.setFirstName(rs.getString(8));
				u.setLastName(rs.getString(9));
				u.setEmail(rs.getString(10));
				u.setPassword(rs.getString(11));
				u.setPhone(rs.getLong(12));
				u.setUserAdminId(rs.getInt(13));
				b.setBookName(rs.getString(14));
				b.setAuthor(rs.getString(15));
				b.setCategory(rs.getString(16));
				b.setPrice(rs.getInt(17));
				b.setQuantity(rs.getInt(18));
				b.setSummary(rs.getString(19));
				b.setPicture(rs.getString(20));
				b.setAdminId(rs.getInt(21));

				r.setRecordBook(b);
				r.setRecordUser(u);
				records.add(r);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return records;
	}

//--------------------------------------------------------------------------------------------------------------------------------------
	public boolean addRecord(Record record) {
		boolean f = false;
		try {
			String sql = "insert into record (issue_date,due_date,user_id,fine,admin_id,book_id) values (?,?,?,?,?,?) ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(record.getIssueDate().getTime()));
			ps.setDate(2, new java.sql.Date(record.getDueDate().getTime()));
			ps.setInt(3, record.getRecordUser().getUserId());
			ps.setInt(4, record.getFine());
			ps.setInt(5, record.getRecordAdmin().getAdminId());
			ps.setInt(6, record.getRecordBook().getBookId());

			if (ps.executeUpdate() == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

//-----------------------------------------------------------------------------------------------------------------------------
	public int recordCount(int adminId, String query) {
		int count = 0;
		String sql;
		try {
			if (query != null)
				sql = "Select count(record_id) from (SELECT r.record_id, u.first_name, u.last_name, b.book_name,b.category, r.issue_date,r.due_date,r.fine,u.phone, r.admin_id FROM bookstop.record r inner join bookstop.user u on u.user_id=r.user_id  inner join bookstop.book b on r.book_id=b.book_id where r.admin_id=? and u.first_name like '%"
						+ query + "%' or last_name like '%" + query + "%') as c; ";
			else
				sql = "select count(record_id) from record where admin_id=?";

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

//--------------------------------------------------------------------------------------------------------------------
	public boolean renewBook(int recordId, Date dueDate) {
		boolean f = false;
		try {
			String sql = "Update bookstop.record set due_date=? where record_id=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, dueDate);
			ps.setInt(2, recordId);
			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

//------------------------------------------------------------------------------------------------------------------------
	public int getBookId(int recordId) {
		String sql = "Select r.book_id from record r where record_id=?;";
		int bookId = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, recordId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bookId = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bookId;
	}

//------------------------------------------------------------------------------------------------------------------
	public boolean deleteRecord(int recordId) {
		boolean f = false;
		try {
			String sql = "delete from bookstop.record where record_id=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, recordId);
			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

//-----------------------------------------------------------------------------------------------------------------------
	public List<Record> getRecordByUser(int userId) {
		List<Record> records = new ArrayList<Record>();
		try {
			Record r;
			String sql = "SELECT r.record_id, u.first_name, u.last_name, b.book_name,b.author,b.category,b.picture,r.issue_date,r.due_date,r.fine,u.phone, r.admin_id FROM bookstop.record r inner join bookstop.user u on u.user_id=r.user_id  inner join bookstop.book b on r.book_id=b.book_id where r.user_id=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r = new Record();
				b = new Book();
				u = new User();
				a = new Admin();

				r.setRecordId(rs.getInt(1));
				u.setFirstName(rs.getString(2));
				u.setLastName(rs.getString(3));
				b.setBookName(rs.getString(4));
				b.setAuthor(rs.getString(5));
				b.setCategory(rs.getString(6));
				b.setPicture(rs.getString(7));			
				r.setIssueDate(rs.getDate(8));
				r.setDueDate(rs.getDate(9));
				r.setFine(rs.getInt(10));
				u.setPhone(rs.getLong(11));
				a.setAdminId(rs.getInt(12));
				r.setRecordBook(b);
				r.setRecordUser(u);
				records.add(r);

				r.setRecordBook(b);
				r.setRecordUser(u);
				r.setRecordAdmin(a);
				records.add(r);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return records;
	}

//-------------------------------------------------------------------------------------------------------------------
	public int pendingFines(int adminId) {
		int totalFine=0;
		String sql = "SELECT sum(fine) FROM bookstop.record where fine>0 and admin_id=?;";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, adminId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				totalFine = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalFine;

	}
//-----------------------------------------------------------------------------------------------------------------=
	public int pendingFineUsers(int adminId) {
		int users=0;
		String sql = "SELECT count(user_id) FROM bookstop.record where fine>0 and admin_id=?;";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, adminId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				users = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
