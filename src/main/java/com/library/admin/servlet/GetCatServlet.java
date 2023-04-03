package com.library.admin.servlet;

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
import com.library.dto.Admin;
import com.library.dto.Book;
import com.library.dto.Category;
import com.library.service.BookServiceImpl;
import com.library.service.IBookService;
import com.library.util.Utilities;

@WebServlet("/GetCatServlet")
public class GetCatServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesssion=request.getSession();
		Admin admin=(Admin)request.getAttribute("admin");
		try (PrintWriter pw=response.getWriter()){
			CategoryDao catDao=new CategoryDao(Utilities.getConnection());
			IBookService bookService=new BookServiceImpl();
			String op=request.getParameter("operation");
			
			if(op.equals("category")) {
				List<Category> clist=catDao.selectCategory(admin.getAdminId());
				Gson json=new Gson();
				String categoryList=json.toJson(clist);
				response.setContentType("text/html");
				response.getWriter().write(categoryList);
			}
			if(op.equals("books")) {
				String cat=request.getParameter("id");
				Map<String,Object> bookMap=bookService.getBookByCategory(cat,admin.getAdminId(), -1, 0);
			    List<Book> blist=(List<Book>)bookMap.get("books");
			    Gson json=new Gson();
				String bookList=json.toJson(blist);
				response.setContentType("text/html");
				response.getWriter().write(bookList);
			}	
		}
	}
}
