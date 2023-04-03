package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.library.dto.Category;

public class CategoryDao {
	private Connection con;

	public CategoryDao(Connection con) {
		super();
		this.con = con;
	}
	public List<Category> selectCategory(int adminId){
		List<Category> cbookList=new ArrayList<Category>();

		try {
			
			String sql = "select category from category where categoryAdminId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, adminId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category c=new Category();
				c.setCategory(rs.getString(1));
				cbookList.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cbookList;

	}

}
