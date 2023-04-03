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
 * Servlet implementation class AdminEditBookServlet
 */
@WebServlet("/editBook")
@MultipartConfig
public class AdminEditBookServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int bookid = Integer.parseInt(request.getParameter("bookid"));
		String bookName = request.getParameter("bookname");
		String author = request.getParameter("author");
		String category = request.getParameter("category");
		int price = Integer.parseInt(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String summary = request.getParameter("summary");
		Part part = request.getPart("bookpic");
		String fileName = part.getSubmittedFileName();
		int adminId = 1;
		Book book = new Book(bookid, bookName, author, category, price, quantity, summary, fileName, adminId);
		IBookService service = new BookServiceImpl();
		boolean f = service.editBook(book);
		HttpSession session = request.getSession();
		if (f)
			session.setAttribute("bookUpdated", bookName + " updated successfully.");
		else
			session.setAttribute("updateFailed", bookName + " could'nt be updated.Please try again.");

		request.getRequestDispatcher("adminLibrary").forward(request, response);

	}

}
