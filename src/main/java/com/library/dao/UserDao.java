package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.library.dto.User;

public class UserDao {
	private Connection con;

	public UserDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean userRegister(User user) {
		boolean f = false;
		try {
			String sql = "insert into user (first_name,last_name,email,password,phone,userAdminId) values (?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setLong(5, user.getPhone());
			ps.setLong(6, user.getUserAdminId());
			if (ps.executeUpdate() == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public User userLogin(String email, String password) {
		User user = null;
		try {
			String sql = "select * from user where email=? and password=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setPhone(rs.getLong(6));
				user.setUserAdminId(rs.getInt(7));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;

	}

	public List<User> selectUser(int userAdminId) {
		List<User> userList = new ArrayList<User>();
		try {
			String sql = "select * from user where userAdminId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userAdminId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPhone(rs.getLong(6));
				user.setUserAdminId(rs.getInt(7));
				userList.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;

	}

	public int countUser(int adminId) {
		int count = 0;
		String sql;
		try {
			sql = "select count(user_id) from user where userAdminId=?";

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

	public boolean updateProfile(User user) {
		boolean f = false;
		try {

			String sql = "Update user set first_name=?,last_name=?,email=?,password=?,phone=?,userAdminId=? where user_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setLong(5, user.getPhone());
			ps.setInt(6, user.getUserAdminId());
			ps.setInt(7, user.getUserId());
			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
