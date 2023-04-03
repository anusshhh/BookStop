package com.library.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.service.BookServiceImpl;
import com.library.service.IBookService;
import com.library.service.IRecordService;
import com.library.service.RecordServiceImpl;

/**
 * Servlet implementation class ReturnBookServlet
 */
@WebServlet("/Return")
public class ReturnBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReturnBookServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IRecordService recordService=new RecordServiceImpl();
		IBookService bookService=new BookServiceImpl();
		int recordId=Integer.parseInt(request.getParameter("id"));
		boolean f=recordService.returnBook(recordId);
		HttpSession session=request.getSession();
		if(f) {
			session.setAttribute("returned","Record Id : "+recordId+" has been returned");
		}
			else
				session.setAttribute("returnFailed", "Book return failed. Try again.");
		
		request.getRequestDispatcher("records").forward(request, response);	
	}

}
