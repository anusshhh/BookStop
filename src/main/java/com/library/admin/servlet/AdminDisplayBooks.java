package com.library.admin.servlet;

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

import com.library.dto.Admin;
import com.library.dto.Book;
import com.library.dto.Category;
import com.library.service.BookServiceImpl;
import com.library.service.CategoryServiceImpl;
import com.library.service.IBookService;
import com.library.service.ICategoryService;

/**
 * Servlet implementation class AdminDisplayBooks
 */
@WebServlet("/adminLibrary")
public class AdminDisplayBooks extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int maxBooks=5;
		HttpSession session = request.getSession();
		Admin admin=(Admin)session.getAttribute("admin");
		int pageNo = (request.getParameter("pageNo") == ""||(request.getParameter("pageNo") ==null)) ? 0 : Integer.parseInt(request.getParameter("pageNo"));
		String query = (request.getParameter("query")=="")?null:request.getParameter("query");
		IBookService bookService = new BookServiceImpl();
		ICategoryService categoryService=new CategoryServiceImpl();
		Map<String, Object> books = bookService.displayBooks(admin.getAdminId(), pageNo,maxBooks,query); //<---- Replace adminId for 1
		List<Category> category=categoryService.showCategory(admin.getAdminId());
		
		if (!books.isEmpty()) {
			session.setAttribute("query", query);
			session.setAttribute("noOfPage", (int) books.get("noOfPage"));
			session.setAttribute("books", (ArrayList<Book>) books.get("books"));
			session.setAttribute("pageNo", pageNo);
			session.setAttribute("category", category);
			response.sendRedirect("admin/adminLibrary.jsp");
	}

}}