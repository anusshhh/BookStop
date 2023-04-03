package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.dto.Book;

public class BookDao {
	private Connection con;

	public BookDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean addBook(Book book) {
		boolean f = false;
		try {
			String sql = "insert into book (book_name,author,category,price,quantity,summary,picture,adminId) values (?,?,?,?,?,?,?,?) ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, book.getBookName());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getCategory());
			ps.setInt(4, book.getPrice());
			ps.setInt(5, book.getQuantity());
			ps.setString(6, book.getSummary());
			ps.setString(7, book.getPicture());
			ps.setInt(8, book.getAdminId());
			if (ps.executeUpdate() == 1) {
				f = true;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

//-----------------------------------------------------------------------------------------------------------------------
	public Book editDisplay(int bid) {
		Book b = new Book();
		try {
			String sql = "Select * from book where book_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setCategory(rs.getString(4));
				b.setPrice(rs.getInt(5));
				b.setQuantity(rs.getInt(6));
				b.setSummary(rs.getString(7));
				b.setPicture(rs.getString(8));
				b.setAdminId(rs.getInt(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;

	}

	// --------------------------------------------------------------------------------------------------------------------------------

	public boolean editBook(Book b) {
		boolean f = false;
		try {

			String sql = "Update book set book_name=?,author=?,category=?,price=?,quantity=?,summary=?,adminId=? where book_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, b.getBookName());
			ps.setString(2, b.getAuthor());
			ps.setString(3, b.getCategory());
			ps.setInt(4, b.getPrice());
			ps.setInt(5, b.getQuantity());
			ps.setString(6, b.getSummary());
			ps.setInt(7, b.getAdminId());
			ps.setInt(8, b.getBookId());
			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

//-------------------------------------------------------------------------------------------------------------------------------------
	public List<Book> selectBooks(int adminId) {
		List<Book> bookList = new ArrayList<Book>();

		try {

			String sql = "select book_id,book_name from book where adminId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, adminId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book b = new Book();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));

				bookList.add(b);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookList;

	}

//-----------------------------------------------------------------------------------------------------------------------------------
	public List<Book> getBookByCategory(String category,int adminId) {
		List<Book> cbookList = new ArrayList<Book>();

		try {

			String sql = "select book_id,book_name,picture from book where category=? and adminId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, category);
			ps.setInt(2, adminId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book b = new Book();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setPicture(rs.getString(3));
				cbookList.add(b);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cbookList;

	}



//------------------------------------------------------------------------------------------------------------------------------------
	public List<Book> displayBook(int adminId, String type) {
		List<Book> books = new ArrayList<Book>();
		try {
			Book b;
			String sql = null;
			if (type == "name")
				sql = "SELECT * FROM bookstop.book where adminid=? order by book_name";
			else if (type == "id")
				sql = "SELECT * FROM bookstop.book where adminid=? order by book_id desc";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, adminId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				b = new Book();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setCategory(rs.getString(4));
				b.setPrice(rs.getInt(5));
				b.setQuantity(rs.getInt(6));
				b.setSummary(rs.getString(7));
				b.setPicture(rs.getString(8));
				b.setAdminId(rs.getInt(9));
				books.add(b);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

//------------------------------------------------------------------------------------------------------------------------------------

	public List<Book> searchBooks(int adminId,String query) {
		List<Book> books = new ArrayList<Book>();
		try {
			Book b;
			String sql = "SELECT * FROM book where adminid=? and book_name like '%" + query + "%' OR author like '%"
					+ query + "%' order by book_name";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, adminId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				b = new Book();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setCategory(rs.getString(4));
				b.setPrice(rs.getInt(5));
				b.setQuantity(rs.getInt(6));
				b.setSummary(rs.getString(7));
				b.setPicture(rs.getString(8));
				b.setAdminId(rs.getInt(9));
				books.add(b);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

	public boolean updateQuantity(int bookId, int update) {
		String sql = "Update book set quantity=quantity+" + update + " where book_id=?";
		boolean f = false;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bookId);
			f = (ps.executeUpdate() == 1) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}

	public boolean calculateFine() {
		String sql = "update bookstop.record set fine=calculateFine(DATEDIFF(now(),due_date)) where record_id IN (select*from(SELECT record_id FROM bookstop.record  where DATEDIFF(now(),due_date)>0 ) as f) ;";
		boolean f = false;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			f = (ps.executeUpdate() == 1) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------
	public String getSummary(int bookId) {
	String sql="SELECT summary FROM bookstop.book where book_id=?";
	String summary = null;
	try {
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, bookId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			summary = rs.getString(1);
		}
	} catch(Exception e) {
			e.printStackTrace();
		}
	return summary;
	}
}
