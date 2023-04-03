package com.library.admin.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.service.HistoryServiceImpl;
import com.library.service.IHistoryService;

/**
 * Servlet implementation class AdminHistory
 */
@WebServlet("/history")
public class AdminHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminHistoryServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// int adminId=Integer.parseInt(request.getParameter("adminId"));
		int maxRecords = 10;
		int pageNo = (request.getParameter("pageNo") == "" || (request.getParameter("pageNo") == null)) ? 0
				: Integer.parseInt(request.getParameter("pageNo"));
		String query = (request.getParameter("query") == "") ? null : request.getParameter("query");
		IHistoryService historyService = new HistoryServiceImpl();
		Map<String, Object> recordHistory = historyService.getHistoryByAdmin(1, pageNo, maxRecords, query);
		HttpSession session = request.getSession();
		if (!recordHistory.isEmpty()) {
			session.setAttribute("recordHistory", (ArrayList<Record>) recordHistory.get("recordHistory"));
			session.setAttribute("noOfPage", (int) recordHistory.get("noOfPage"));
			session.setAttribute("pageNo", pageNo);
			session.setAttribute("query", query);
			response.sendRedirect("admin/adminHistory.jsp");
		}

	}

}
