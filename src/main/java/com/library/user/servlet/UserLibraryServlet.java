package com.library.user.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.library.dto.Book;
import com.library.dto.User;
import com.library.service.BookServiceImpl;
import com.library.service.IBookService;

/**
 * Servlet implementation class UserDisplayServlet
 */
@WebServlet("/userLibrary")
public class UserLibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int adminId, pageNo, totalBooks, maxBooks = 18;
		HttpSession session = request.getSession();
		String query;
		Map<String, Object> bookMap;
		User user = (User) (session.getAttribute("user"));
		pageNo = (request.getParameter("pageNo") == "" || (request.getParameter("pageNo") == null)) ? 0
				: Integer.parseInt(request.getParameter("pageNo"));
		query = (request.getParameter("query") == "") ? null : request.getParameter("query");
		IBookService bookService = new BookServiceImpl();
		adminId = user.getUserAdminId();
		bookMap = bookService.displayBooks(adminId, pageNo,maxBooks,query);
		totalBooks = (int)bookMap.get("total");

		if (!bookMap.isEmpty()) {
			List<Book> bookList = (List<Book>) bookMap.get("books");
			session.setAttribute("books", bookList);
			session.setAttribute("noOfPage", (int) bookMap.get("noOfPage"));
			session.setAttribute("pageNo", pageNo);
			session.setAttribute("totalBooks", totalBooks);
			session.setAttribute("query", query);
			response.sendRedirect("user/userLibrary.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
