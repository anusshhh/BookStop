package com.library.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.library.dto.Book;
import com.library.service.BookServiceImpl;
import com.library.service.IBookService;

/**
 * Servlet implementation class AdminAddBookServlet
 */
@WebServlet("/addBook")
@MultipartConfig
public class AdminAddBookServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookName = request.getParameter("bookname");
		String author = request.getParameter("author");
		String category = request.getParameter("category");
		int price = Integer.parseInt(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String summary = request.getParameter("summary");
		Part part = request.getPart("bookpic");
		String fileName = part.getSubmittedFileName();
		int adminId = 1;
		Book book = new Book(bookName, author, category, price, quantity, summary, fileName, adminId);
		IBookService bookService = new BookServiceImpl();
		boolean f = bookService.addBook(book);
		HttpSession session = request.getSession();

		if (f)
			session.setAttribute("bookAdded", bookName + " added successfully.");

		else
			session.setAttribute("bookNotAdded", bookName + " couldn't be added.");

		request.getRequestDispatcher("adminLibrary").forward(request, response);
	}
}
