package com.library.dto;

public class Book {
	private int bookId;
	private String bookName;
	private String author;
	private String category;
	private int price;
	private int quantity;
	private String summary;
	private String picture;
	private int adminId;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String bookName, String author, String category, int price, int quantity, String summary,
			String picture, int adminId) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.summary = summary;
		this.picture = picture;
		this.adminId = adminId;
	}

	public Book(int bookid, String bookName, String author, String category, int price, int quantity, String summary,
			String fileName, int adminId) {
		super();
		this.bookId = bookid;
		this.bookName = bookName;
		this.author = author;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.summary = summary;
		this.adminId = adminId;
		this.picture = fileName;

	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", author=" + author + ", category=" + category
				+ ", price=" + price + ", quantity=" + quantity + ", picture=" + picture + ", adminId=" + adminId + "]";
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
