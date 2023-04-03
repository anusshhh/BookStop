package com.library.admin.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.service.IRecordService;
import com.library.service.RecordServiceImpl;
import com.library.util.DateUtil;

/**
 * Servlet implementation class RenewServlet
 */
@WebServlet("/Renew")
public class RenewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RenewServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IRecordService recordService = new RecordServiceImpl();
		int recordId = Integer.parseInt(request.getParameter("id"));
		String dueDateStr = request.getParameter("dueDate");
		Date dueDate = DateUtil.toDate(dueDateStr);
		boolean f = recordService.renewBook(recordId, dueDate);
		HttpSession session = request.getSession();
		if (f)
			session.setAttribute("renewed", "Record ID = " + recordId + " renewed successfully.");
		else
			session.setAttribute("renewFailed", "Record ID = " + recordId + " couldn't be renewed.");
		request.getRequestDispatcher("records").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
