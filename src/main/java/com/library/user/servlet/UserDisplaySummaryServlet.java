package com.library.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.library.dto.User;
import com.library.service.BookServiceImpl;
import com.library.service.IBookService;

/**
 * Servlet implementation class UserDisplaySummary
 */
@WebServlet("/loadSummary")
public class UserDisplaySummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try(PrintWriter pw=response.getWriter()){
		IBookService bookService=new BookServiceImpl();
	
		int bid=Integer.parseInt(request.getParameter("id"));
		String summary=bookService.getBookSummary(bid);
		Gson json=new Gson();
		String summaryJson=json.toJson(summary);
		response.setContentType("text/html");
		response.getWriter().write(summaryJson);
	}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
