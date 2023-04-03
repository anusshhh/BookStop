package com.library.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dto.Admin;
import com.library.service.AccessServiceImpl;
import com.library.service.IAccessService;

/**
 * Servlet implementation class AdminRegisterServlet
 */
@WebServlet("/adminRegister")
public class AdminRegisterServlet extends HttpServlet {

	  public AdminRegisterServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request,response);
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		long phone = Long.parseLong(request.getParameter("phone"));
		Admin admin = new Admin(firstName, lastName, email, password, phone);
		IAccessService service=new AccessServiceImpl();
		boolean f=service.adminRegister(admin);
		HttpSession session = request.getSession();
		if(f) {	
				session.setAttribute("adminRegSuccess","Registeration Successful.");
				response.sendRedirect("admin/admin.jsp");
			}
			else {
				session.setAttribute("adminRegFail", "Invalid Credentials.");
				response.sendRedirect("admin/adminReg.jsp");
			}

}
}