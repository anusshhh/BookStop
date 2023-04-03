package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.dto.Admin;

public class AdminDao {
	private Connection con;

	public AdminDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean adminRegister(Admin admin) {
		boolean f = false;
		try {
			String sql = "insert into admin (first_name,last_name,email,password,phone) values (?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, admin.getFirstName());
			ps.setString(2, admin.getLastName());
			ps.setString(3, admin.getEmail());
			ps.setString(4, admin.getPassword());
			ps.setLong(5, admin.getPhone());
			if(ps.executeUpdate()==1) {
				f=true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public Admin adminLogin(String email,String password) {
		Admin admin=null;
		try {
			String sql="select * from admin where email=? and password=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				admin=new Admin();
				admin.setAdminId(rs.getInt(1));
				admin.setFirstName(rs.getString(2));
				admin.setLastName(rs.getString(3));
				admin.setEmail(rs.getString(4));
				admin.setPassword(rs.getString(5));
				admin.setPhone(rs.getLong(6));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();}
		return admin;
		
	}
	
	public boolean updateProfile(Admin admin) {
		boolean f = false;
		try {

			String sql = "Update admin set first_name=?,last_name=?,email=?,password=?,phone=? where admin_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, admin.getFirstName());
			ps.setString(2, admin.getLastName());
			ps.setString(3, admin.getEmail());
			ps.setString(4, admin.getPassword());
			ps.setLong(5, admin.getPhone());
			ps.setInt(6, admin.getAdminId());
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
