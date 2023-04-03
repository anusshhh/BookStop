package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.library.dto.Book;
import com.library.dto.User;
import com.library.dto.Wishlist;

public class WishlistDao {
	private Connection con;

	public WishlistDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean addToWishList(int userId, int bookId) {
		boolean f = false;

		try {
			String sql = "insert into bookstop.wishlist (book_id,user_id) values (?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.setInt(2, userId);
			if (ps.executeUpdate() == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

	public List<Wishlist> getAll(int userId) {
		List<Wishlist> wishlist = new ArrayList<>();
		try {
			Wishlist w;
			Book b;
			User u;

			String sql = "SELECT * fROM bookstop.wishlist w inner join bookstop.book b using(book_id) inner join bookstop.user u using (user_id) where  w.user_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
		;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				w = new Wishlist();
				b = new Book();
				u = new User();
				u.setUserId(rs.getInt(1));
				b.setBookId(rs.getInt(2));
				w.setId(rs.getInt(3));
				b.setBookName(rs.getString(4));
				b.setAuthor(rs.getString(5));
				b.setCategory(rs.getString(6));
				b.setPrice(rs.getInt(7));
				b.setQuantity(rs.getInt(8));
				b.setSummary(rs.getString(9));
				b.setPicture(rs.getString(10));
				b.setAdminId(rs.getInt(11));
				u.setFirstName(rs.getString(12));
				u.setLastName(rs.getString(13));
				u.setEmail(rs.getString(14));
				u.setPassword(rs.getString(15));
				u.setPhone(rs.getLong(16));
				u.setUserAdminId(rs.getInt(17));

				w.setBook(b);
				w.setUser(u);
				wishlist.add(w);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return wishlist;
	}

	public boolean removeWishlist(int wishlistId) {
		boolean f = false;
		try {
			String sql = "delete from bookstop.wishlist where id=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, wishlistId);
			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public int countWishlistItems(int userId) {
		int count = 0;
		try {
			String sql = "SELECT count(id) fROM bookstop.wishlist w  where w.user_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;

	}

}
