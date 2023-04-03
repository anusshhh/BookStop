package com.library.dto;

public class Category {
	String category;
	int categoryAdminId;
	public Category(String category, int categoryAdminId) {
		super();
		this.category = category;
		this.categoryAdminId = categoryAdminId;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getCategoryAdminId() {
		return categoryAdminId;
	}
	public void setCategoryAdminId(int categoryAdminId) {
		this.categoryAdminId = categoryAdminId;
	}
	@Override
	public String toString() {
		return "Category [category=" + category + ", categoryAdminId=" + categoryAdminId + "]";
	}
	

}
