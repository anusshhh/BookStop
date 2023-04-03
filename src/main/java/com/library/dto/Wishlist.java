package com.library.dto;

public class Wishlist {
	private int id;
	private Book book;
	private User user;
	
	
	public Wishlist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Wishlist(int id, Book book, User user) {
		super();
		this.id = id;
		this.book = book;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
