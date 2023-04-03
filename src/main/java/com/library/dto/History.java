package com.library.dto;

import java.util.Date;

public class History extends Record {

	public History() {
		super();
		// TODO Auto-generated constructor stub
	}

	public History(Date issueDate, Date dueDate, User recordUser, Admin recordAdmin, Book recordBook, Date returnDate) {
		super(issueDate,dueDate,recordUser,recordAdmin,recordBook,returnDate);
		// TODO Auto-generated constructor stub
	}

	public History(int recordId, java.sql.Date issueDate, java.sql.Date dueDate, User recordUser, Admin recordAdmin,
			Book recordBook) {
		super(issueDate, dueDate, recordUser, recordAdmin, recordBook);
		// TODO Auto-generated constructor stub
	}

}
