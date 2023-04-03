package com.library.admin.servlet;

import java.io.IOException;
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
import com.library.dto.User;
import com.library.service.BookServiceImpl;
import com.library.service.IBookService;
import com.library.service.IRecordService;
import com.library.service.IUserService;
import com.library.service.RecordServiceImpl;
import com.library.service.UserServiceImpl;

@WebServlet("/dashboard")
public class AdminDashboard extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IBookService bookService = new BookServiceImpl();
		IRecordService recordService = new RecordServiceImpl();
		IUserService userService = new UserServiceImpl();
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		int adminId=admin.getAdminId();
		Map<String, Object> bookMap=bookService.displayBooks(adminId,0, 5,null);
		int bookCount = (int)bookMap.get("total");
		int recordCount = recordService.recordCount(adminId, null);
		int userCount = userService.countUser(adminId);
		int pendingFineUsers = recordService.pendingFineUsers(adminId);
		int pendingFine = recordService.pendingFines(adminId);
		List<User> userList = userService.selectUser(adminId);
		if (userList.size() > 5)
			userList = userList.subList(0, 5);
	
		List<Book> bookList=(List<Book>) bookMap.get("books");
		session.setAttribute("bookCount", bookCount);
		session.setAttribute("recordCount", recordCount);
		session.setAttribute("userCount", userCount);
		session.setAttribute("pendingFineUsers", pendingFineUsers);
		session.setAttribute("pendingFine", pendingFine);
		session.setAttribute("books", bookList);
		session.setAttribute("userList", userList);
		response.sendRedirect("admin/dashboard.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
