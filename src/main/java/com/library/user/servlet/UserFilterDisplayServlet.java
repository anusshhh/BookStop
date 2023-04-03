package com.library.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.library.dao.CategoryDao;
import com.library.dto.Book;
import com.library.dto.Category;
import com.library.dto.User;
import com.library.service.BookServiceImpl;
import com.library.service.CategoryServiceImpl;
import com.library.service.IBookService;
import com.library.service.ICategoryService;
import com.library.util.Utilities;

@WebServlet("/filterLibrary")
public class UserFilterDisplayServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int filterPageNo, maxBooks = 18;
		IBookService bookService = new BookServiceImpl();
		filterPageNo = (request.getParameter("filterPageNo") == "" || (request.getParameter("filterPageNo") == null))
				? 0
				: Integer.parseInt(request.getParameter("filterPageNo"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		try (PrintWriter pw = response.getWriter()) {
			CategoryDao catDao = new CategoryDao(Utilities.getConnection());
			ICategoryService categoryService = new CategoryServiceImpl();
			String op = request.getParameter("operation");

			if (op.equals("category")) {
				List<Category> clist = catDao.selectCategory(user.getUserAdminId());
				Gson json = new Gson();
				String categoryList = json.toJson(clist);
				response.setContentType("text/html");
				response.getWriter().write(categoryList);
			}
			if (op.equals("books")) {
				String cat = request.getParameter("id");
				Map<String, Object> bookMap = bookService.getBookByCategory(cat, user.getUserAdminId(), maxBooks,
						filterPageNo);
				bookMap.put("filterPageNo", filterPageNo);
				Gson json = new Gson();
				String bookList = json.toJson(bookMap);
				response.setContentType("text/html");
				response.getWriter().write(bookList);

			}
		}
	}
}
