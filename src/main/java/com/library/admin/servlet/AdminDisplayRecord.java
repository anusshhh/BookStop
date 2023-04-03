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

import com.library.dto.User;
import com.library.service.IRecordService;
import com.library.service.IUserService;
import com.library.service.RecordServiceImpl;
import com.library.service.UserServiceImpl;

/**
 * Servlet implementation class AdminDisplayRecord
 */
@WebServlet("/records")
public class AdminDisplayRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int maxRecords = 5;
		// int adminId=Integer.parseInt(request.getParameter("adminId"));
		int pageNo = (request.getParameter("pageNo") == "" || (request.getParameter("pageNo") == null)) ? 0
				: Integer.parseInt(request.getParameter("pageNo"));
		String query = (request.getParameter("query") == "") ? null : request.getParameter("query");
		IRecordService recordService = new RecordServiceImpl();
		IUserService userService = new UserServiceImpl();
		Map<String, Object> records = recordService.displayRecord(1, pageNo, maxRecords, query);// --------> replace
																								// adminId
		List<User> userList = userService.selectUser(1); // ----------> replace adminId
		HttpSession session = request.getSession();
		if (!records.isEmpty()) {
			session.setAttribute("record", (ArrayList<Record>) records.get("record"));
			session.setAttribute("noOfPage", (int) records.get("noOfPage"));
			session.setAttribute("userList", userList);
			session.setAttribute("pageNo", pageNo);
			session.setAttribute("query", query);
			response.sendRedirect("admin/adminIssueBook.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
