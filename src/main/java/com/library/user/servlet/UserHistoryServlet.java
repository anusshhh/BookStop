package com.library.user.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dto.User;
import com.library.service.HistoryServiceImpl;
import com.library.service.IHistoryService;

/**
 * Servlet implementation class UserHistoryServlet
 */
@WebServlet("/userHistory")
public class UserHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserHistoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int pageNo = (request.getParameter("pageNo") == "" || (request.getParameter("pageNo") == null)) ? 0
				: Integer.parseInt(request.getParameter("pageNo"));
		String query = (request.getParameter("query") == "") ? null : request.getParameter("query");
		IHistoryService historyService = new HistoryServiceImpl();
		Map<String, Object> userHistory = historyService.getHistoryByUser(user.getUserId(), pageNo, query);

		if (!userHistory.isEmpty()) {
			session.setAttribute("userHistory", (ArrayList<Record>) userHistory.get("userHistory"));
			session.setAttribute("noOfPage", (int) userHistory.get("noOfPage"));
			session.setAttribute("pageNo", pageNo);
			session.setAttribute("query", query);
			response.sendRedirect("user/userHistory.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
