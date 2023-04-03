package com.library.dto;

import java.util.Date;

public class Record {
	private int recordId;
	private java.sql.Date issueDate;
	private java.sql.Date dueDate;
	private User recordUser;
	private Admin recordAdmin;
	private Book recordBook;
	private int fine;
	private java.sql.Date returnDate;

	public Record() {
		super();
	}

    
	public Record(Date issueDate,Date dueDate, User recordUser, Admin recordAdmin,
			 Book recordBook,Date returnDate) {
		super();
		this.issueDate = new java.sql.Date(issueDate.getTime());
		this.dueDate = new java.sql.Date(dueDate.getTime());
		this.recordUser = recordUser;
		this.recordAdmin = recordAdmin;
		this.recordBook = recordBook;
		this.returnDate = new java.sql.Date(returnDate.getTime());
	}

	public Record(Date issueDate, Date dueDate, User recordUser, Admin recordAdmin, Book recordBook) {
		super();
		this.issueDate = new java.sql.Date(issueDate.getTime());
		this.dueDate = new java.sql.Date(dueDate.getTime());
		this.recordUser = recordUser;
		this.recordAdmin = recordAdmin;
		this.recordBook = recordBook;
	}


	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	public java.sql.Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(java.sql.Date issueDate) {
		this.issueDate = issueDate;
	}

	public java.sql.Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(java.sql.Date dueDate) {
		this.dueDate = dueDate;
	}

	public java.sql.Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(java.sql.Date returnDate) {
		this.returnDate = returnDate;
	}

	public User getRecordUser() {
		return recordUser;
	}

	public void setRecordUser(User recordUser) {
		this.recordUser = recordUser;
	}

	public Admin getRecordAdmin() {
		return recordAdmin;
	}

	public void setRecordAdmin(Admin recordAdmin) {
		this.recordAdmin = recordAdmin;
	}

	public Book getRecordBook() {
		return recordBook;
	}

	public void setRecordBook(Book recordBook) {
		this.recordBook = recordBook;
	}
	
	

}
