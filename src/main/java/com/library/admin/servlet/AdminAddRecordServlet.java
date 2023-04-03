package com.library.admin.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dto.Admin;
import com.library.dto.Book;
import com.library.dto.Record;
import com.library.dto.User;
import com.library.service.IRecordService;
import com.library.service.RecordServiceImpl;
import com.library.util.DateUtil;

/**
 * Servlet implementation class AdminAddRecord
 */
@WebServlet("/addRecord")
public class AdminAddRecordServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IRecordService recordService = new RecordServiceImpl();
		Record record;
		Book book=new Book();
		User user=new User();
		Admin admin=new Admin();
		admin.setAdminId(1);
		//int recordAdminId = Integer.parseInt(request.getParameter("adminId"));
		user.setUserId(Integer.parseInt(request.getParameter("user")));
		book.setCategory(request.getParameter("category"));
		book.setBookId(Integer.parseInt(request.getParameter("books")));
		String issueDateStr = request.getParameter("issueDate");
		Date issueDate = DateUtil.toDate(issueDateStr);
		Date dueDate = DateUtil.addDays(issueDate, 7);
		record = new Record(issueDate, dueDate, user,admin,book);
		boolean f = recordService.addRecord(record);
		HttpSession session = request.getSession();
		if (f) 
			session.setAttribute("recordAdded", "Record added successfully.");
		 
		else 
			session.setAttribute("recordFailed", "Record could'nt be added.Please try again.");
			request.getRequestDispatcher("records").forward(request, response);}

}
