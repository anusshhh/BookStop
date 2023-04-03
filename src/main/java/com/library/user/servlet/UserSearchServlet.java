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
 * Servlet implementation class UserSearchServlet
 */
@WebServlet("/userSearch")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSearchServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		int maxBooks=5;
		User user = (User) session.getAttribute("user");
		String query = request.getParameter("newQuery");
		IBookService bookService = new BookServiceImpl();
		Map<String, Object> bookMap = bookService.displayBooks(user.getUserAdminId(),0,maxBooks,query);
		List<Book> bookList = (ArrayList<Book>) bookMap.get("books");
		session.setAttribute("noOfPage", (int) bookMap.get("noOfPage"));
		request.setAttribute("newQuery", query);
		Gson json = new Gson();
		String bookJson = json.toJson(bookList);
		response.setContentType("text/html");
		response.getWriter().write(bookJson);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
